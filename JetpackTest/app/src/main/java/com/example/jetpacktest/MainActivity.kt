package com.example.jetpacktest

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.edit
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.Observer
import androidx.lifecycle.OnLifecycleEvent
import androidx.lifecycle.ViewModelProvider
import androidx.work.OneTimeWorkRequest
import androidx.work.PeriodicWorkRequest
import androidx.work.WorkInfo
import androidx.work.WorkManager
import com.example.jetpacktest.databinding.ActivityMainBinding
import java.util.concurrent.TimeUnit
import kotlin.concurrent.thread

// jetpack的生命周期组件，生命周期组件是livedata和vm沟通的桥梁确保不会内存泄漏
class MyObserver(val lifecycle: Lifecycle) : LifecycleObserver {
    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun activityStart() {
        Log.d("MyObserver", "activityStart")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun activityStop() {
        Log.d("MyObserver", "activityStop")
    }
}

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var viewModel: MainViewModel
    lateinit var sp: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sp = getPreferences(Context.MODE_PRIVATE)
        val countReserved = sp.getInt("count_reserved", 0)
        // viewModel = ViewModelProvider(this, MainViewModelFactory(countReserved)).get(MainViewModel::class.java)
        viewModel = ViewModelProvider(this, MainViewModelFactory(countReserved))
            .get(MainViewModel::class.java)

        binding.plusOneBtn.setOnClickListener {
            viewModel.plusOne()
        }
        binding.clearBtn.setOnClickListener {
            viewModel.clear()
        }
        viewModel.counter.observe(this, Observer { count ->
            binding.infoText.text = count.toString()
        })

        // livedata绑定
        binding.getUserBtn.setOnClickListener {
            val userId = (0..10000).random().toString()
            viewModel.getUser(userId)
        }
        viewModel.user.observe(this, Observer { user ->
            binding.infoText.text = user.firstName
        })

        lifecycle.addObserver(MyObserver(lifecycle))

        // Room默认不允许在主线程有耗时操作
        val userDao = AppDatabase.getDatabase(this).userDao()
        val user1 = User("Tom", "Brady", 40)
        val user2 = User("Tom", "Hanks", 63)
        binding.addDataBtn.setOnClickListener {
            thread {
                user1.id = userDao.insertUser(user1)
                user2.id = userDao.insertUser(user2)
            }
        }
        binding.updateDataBtn.setOnClickListener {
            thread {
                user1.age = 42
                userDao.updateUser(user1)
            }
        }
        binding.deleteDataBtn.setOnClickListener {
            thread {
                userDao.deleteUserByLastName("Hanks")
            }
        }
        binding.queryDataBtn.setOnClickListener {
            thread {
                for (user in userDao.loadAllUsers()) {
                    Log.d("MainActivity", user.toString())
                }
            }
        }

        // WorkManager: OneTimeWorkRequest.Builder是WorkRequest.Builder的子类，用于构建单次运行的后台任务请求
        val request = OneTimeWorkRequest.Builder(SimpleWorker::class.java)
            .setInitialDelay(5, TimeUnit.MINUTES)
            .addTag("simple")
            .build()
        // PeriodicWorkRequest.Builder，可用于构建周期性运行的后台任务请求，
        // 但是为了降低设备性能消耗，PeriodicWorkRequest.Builder构造函数中传入的运行周期间隔不能短于15分钟
        // val request = PeriodicWorkRequest.Builder(SimpleWorker::class.java, 15, TimeUnit.MINUTES).build()
        binding.doWorkBtn.setOnClickListener {
            WorkManager.getInstance(this).enqueue(request)
        }
        // getWorkInfoByIdLiveData返回可以被监听的livedata
        WorkManager.getInstance(this)
            .getWorkInfoByIdLiveData(request.id)
            .observe(this) { workInfo ->
                if (workInfo.state == WorkInfo.State.SUCCEEDED) {
                    Log.d("MainActivity", "do work succeeded")
                } else if (workInfo.state == WorkInfo.State.FAILED) {
                    Log.d("MainActivity", "do work failed")
                }
            }
    }

    override fun onPause() {
        super.onPause()
        sp.edit {
            viewModel.counter.value?.let { putInt("count_reserved", it) }
        }
    }
}
