package com.zhp.contacttests

import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.os.Bundle
import android.provider.ContactsContract
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.zhp.contacttests.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val contactsList = ArrayList<String>()
    private lateinit var adapter: ArrayAdapter<String>

    lateinit var viewBinding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, contactsList)
        viewBinding.contactsView.adapter = adapter

        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.READ_CONTACTS)
            != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.READ_CONTACTS), 1)
        } else {
            readContacts()
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>,
                                            grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            1 -> {
                if (grantResults.isNotEmpty()
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    readContacts()
                } else {
                    Toast.makeText(this, "You denied the permission",
                        Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun readContacts() {
        // 查询联系人数据
        contentResolver.query(
            ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
            null, null, null, null)?.apply {
            while (moveToNext()) {
                try {
                    // 获取联系人姓名
                    val displayName = getString(getColumnIndexOrThrow(
                        ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME))
                    // 获取联系人手机号
                    val number = getString(getColumnIndexOrThrow(
                        ContactsContract.CommonDataKinds.Phone.NUMBER))
                    contactsList.add("$displayName\n$number")
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
            adapter.notifyDataSetChanged()
            close()
        }
    }
}