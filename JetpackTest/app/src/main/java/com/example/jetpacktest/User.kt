package com.example.jetpacktest

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.PrimaryKey
import androidx.room.Query
import androidx.room.Update

/**
 * data修饰提供一些自动生成的功能。使用data修饰符的类会自动生成以下功能：
 * equals() 和 hashCode() 方法：数据类会基于其属性自动生成这两个方法，使得对象比较和哈希操作更加方便。
 * toString() 方法：自动生成的toString()方法会返回一个包含类名和属性值的字符串，方便调试和输出。
 * copy() 方法：数据类会自动提供一个copy()方法，可以方便地创建对象的副本，并且可以选择性地修改某些属性。
 * componentN() 方法：自动生成的componentN()方法允许你使用解构声明（destructuring declaration），方便地提取属性值。
 * */
@Entity
data class User(var firstName: String, var lastName: String, var age: Int) {

    @PrimaryKey(autoGenerate = true)
    var id: Long = 0

}


// 直接使用注解标识即可，不用编写SQL语句。
// 如果是特殊查询需要在注解里面写sql
@Dao
interface UserDao {

    @Insert
    fun insertUser(user: User): Long

    @Update
    fun updateUser(newUser: User)

    @Query("select * from User")
    fun loadAllUsers(): List<User>

    @Query("select * from User where age > :age")
    fun loadUsersOlderThan(age: Int): List<User>

    @Delete
    fun deleteUser(user: User)

    @Query("delete from User where lastName = :lastName")
    fun deleteUserByLastName(lastName: String): Int
}

