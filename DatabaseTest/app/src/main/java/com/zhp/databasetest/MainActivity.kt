package com.zhp.databasetest

import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.zhp.databasetest.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // val dbHelper = MyDatabaseHelper(this, "BookStore.db", 1)
        // 升级，重新初始化表走upgrade方法
        val dbHelper = MyDatabaseHelper(this, "BookStore.db", 1)
        binding.createDatabase.setOnClickListener {
            // getter方法实现数据库创建
            dbHelper.writableDatabase
        }

        binding.addData.setOnClickListener {
            // getter方法实现数据库创建
            val db = dbHelper.writableDatabase
            val values1 = ContentValues().apply {
                // 开始组装第一条数据
                put("name", "The Da Vinci Code")
                put("author", "Dan Brown")
                put("pages", 454)
                put("price", 16.96)
            }
            db.insert("Book", null, values1) // 插入第一条数据
            val values2 = ContentValues().apply {
                // 开始组装第二条数据
                put("name", "The Lost Symbol")
                put("author", "Dan Brown")
                put("pages", 510)
                put("price", 19.95)
            }
            db.insert("Book", null, values2) // 插入第二条数据

            // sql方式
            /*
            db.execSQL("insert into Book (name, author, pages, price) values(?, ?, ?, ?)",
                arrayOf("The Da Vinci Code", "Dan Brown", "454", "16.96")
            )
            db.execSQL("insert into Book (name, author, pages, price) values(?, ?, ?, ?)",
                arrayOf("The Lost Symbol", "Dan Brown", "510", "19.95")
            )
            */
        }

        binding.updateData.setOnClickListener {
            // getter方法实现数据库创建
            val db = dbHelper.writableDatabase
            db.delete("Book", "pages > ?", arrayOf("500"))

            // sql方式
            /**
             * db.execSQL("update Book set price = ? where name = ?", arrayOf("10.99", "The Da Vinci Code"))
             * */
        }

        binding.queryData.setOnClickListener {
            val db = dbHelper.writableDatabase
            // 查询Book表中所有的数据
            val cursor = db.query("Book", null, null, null, null, null, null)
            if (cursor.moveToFirst()) {
                do {
                    try {
                        // 遍历Cursor对象，取出数据并打印
                        val name = cursor.getString(cursor.getColumnIndexOrThrow("name"))
                        val author = cursor.getString(cursor.getColumnIndexOrThrow("author"))
                        val pages = cursor.getInt(cursor.getColumnIndexOrThrow("pages"))
                        val price = cursor.getDouble(cursor.getColumnIndexOrThrow("price"))
                        Log.d("MainActivity", "book name is $name")
                        Log.d("MainActivity", "book author is $author")
                        Log.d("MainActivity", "book pages is $pages")
                        Log.d("MainActivity", "book price is $price")
                    } catch (e: IllegalArgumentException) {
                        // 列名不存在的处理
                    }
                } while (cursor.moveToNext())
            }
            cursor.close()

            // sql方式
            /*
            * db.execSQL("delete from Book where pages > ?", arrayOf("500"))
            * */
        }

        binding.replaceData.setOnClickListener {
            val db = dbHelper.writableDatabase
            db.beginTransaction() // 开启事务
            try {
                db.delete("Book", null, null)
                if (true) {
                    // 手动抛出一个异常，让事务失败
                    throw NullPointerException()
                }
                val values = ContentValues().apply {
                    put("name", "Game of Thrones")
                    put("author", "George Martin")
                    put("pages", 720)
                    put("price", 20.85)
                }
                db.insert("Book", null, values)
                db.setTransactionSuccessful() // 事务已经执行成功
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                db.endTransaction() // 结束事务
            }
        }
    }

}
