package com.example.jetpacktest

import androidx.room.Dao
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.PrimaryKey
import androidx.room.Query

@Entity
data class Book(var name: String, var pages: Int, var author: String) {
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0
}

@Dao
interface BookDao {
    @Insert
    fun insertBook(book: Book): Long

    @Query("select * from Book")
    fun loadAllBooks(): List<Book>
}