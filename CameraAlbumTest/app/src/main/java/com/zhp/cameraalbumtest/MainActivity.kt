package com.zhp.cameraalbumtest

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Matrix
import android.media.ExifInterface
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.FileProvider
import com.zhp.cameraalbumtest.databinding.ActivityMainBinding
import java.io.File

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    val takePhoto = 1
    val fromAlbum = 2
    lateinit var imageUri: Uri
    lateinit var outputImage: File

    val takePhotoPicker = registerForActivityResult(ActivityResultContracts.TakePicture()) { success ->
        if (success) {
            // 将拍摄的照片显示出来
            // photoUri 格式类似：
            // content://com.example.app.fileprovider/external_files/Pictures/photo123.jpg
            // 拍照：使用应用自己的 FileProvider，uri 指向应用私有存储空间
            val bitmap = BitmapFactory.decodeStream(contentResolver.openInputStream(imageUri))
            binding.imageView.setImageBitmap(rotateIfRequired(bitmap))
        }
    }

    val takeAlbumPhotoPicker = registerForActivityResult(ActivityResultContracts.GetContent()) { uri ->
        uri?.let {
            // 处理选中的图片
            // 返回的 uri 格式类似：
            // content://com.android.providers.media.documents/document/image:1234
            // 相册选择：使用系统的 MediaProvider，uri 指向系统媒体数据库
            contentResolver.openFileDescriptor(uri, "r")?.use {
                val bitmap = BitmapFactory.decodeFileDescriptor(it.fileDescriptor)
                binding.imageView.setImageBitmap(bitmap)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.takePhotoBtn.setOnClickListener {
            // 创建file对象，用于存储拍照后的图片
            outputImage = File(externalCacheDir, "output_image.jpg")
            if (outputImage.exists()) {
                outputImage.delete()
            }
            outputImage.createNewFile()
            imageUri =
                FileProvider.getUriForFile(this, "${applicationContext.packageName}.fileprovider",
                    outputImage)
            // 启动相机
            /*
            val intent = Intent("android.media.action,IMAGE_CAPTURE")
            intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri)
            startActivityForResult(intent, takePhoto)
            */
            // 在 Activity/Fragment 中注册
            takePhotoPicker.launch(imageUri)
        }

        binding.fromAlbumBtn.setOnClickListener {
            // 打开文件选择器
            // val intent = Intent(Intent.ACTION_OPEN_DOCUMENT)
            // intent.addCategory(Intent.CATEGORY_OPENABLE)
            // 指定只显示图片
            // intent.type = "image/*"
            // tartActivityForResult(intent, fromAlbum)
            takeAlbumPhotoPicker.launch("image/*")
          }
    }

    private fun rotateIfRequired(bitmap: Bitmap): Bitmap {
        val exif = ExifInterface(outputImage.path)
        val orientation = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION,
            ExifInterface.ORIENTATION_NORMAL)
        return when (orientation) {
            ExifInterface.ORIENTATION_ROTATE_90 -> rotateBitmap(bitmap, 90)
            ExifInterface.ORIENTATION_ROTATE_180 -> rotateBitmap(bitmap, 180)
            ExifInterface.ORIENTATION_ROTATE_270 -> rotateBitmap(bitmap, 270)
            else -> bitmap
        }
    }

    private fun rotateBitmap(bitmap: Bitmap, degree: Int): Bitmap {
        val matrix = Matrix()
        matrix.postRotate(degree.toFloat())
        val rotatedBitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.width, bitmap.height,
            matrix, true)
        bitmap.recycle() // 将不再需要的Bitmap对象回收
        return rotatedBitmap
    }
}