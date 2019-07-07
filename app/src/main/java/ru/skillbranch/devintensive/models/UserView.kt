package ru.skillbranch.devintensive.models

class UserView(
    val id: String,
    val fullName: String,
    val nickName: String,
    val initials: String,
    val avatar: String? = null,
    val status: String? = "offline"
){
    fun printMe(){
        println("""
            USERVIEW:
            id: $id,
            fullName: $fullName,
            nickName: $nickName,
            initials: $initials,
            avatar: $avatar,
            status: $status
        """.trimIndent())
    }
}