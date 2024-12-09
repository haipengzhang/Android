package com.example.jetpacktest

/**
 * data修饰提供一些自动生成的功能。使用data修饰符的类会自动生成以下功能：
 * equals() 和 hashCode() 方法：数据类会基于其属性自动生成这两个方法，使得对象比较和哈希操作更加方便。
 * toString() 方法：自动生成的toString()方法会返回一个包含类名和属性值的字符串，方便调试和输出。
 * copy() 方法：数据类会自动提供一个copy()方法，可以方便地创建对象的副本，并且可以选择性地修改某些属性。
 * componentN() 方法：自动生成的componentN()方法允许你使用解构声明（destructuring declaration），方便地提取属性值。
 * */
data class User(var firstName: String, var lastName: String, var age: Int)