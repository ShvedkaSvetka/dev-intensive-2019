package ru.skillbranch.devintensive.models

import ru.skillbranch.devintensive.utils.Utils
import java.util.*

data class User(
    val id : String,
    var firstName : String?,
    var lastName : String?,
    var avatar : String?,
    var rating : Int = 0,
    var respect : Int = 0,
    var lastVisit : Date? = Date(),
    var isOnline : Boolean = false
){
    constructor(id:String, firstName: String?, lastName: String?):this(
        id = id,
        firstName= firstName,
        lastName=lastName,
        avatar=null)

    constructor(id:String):this(
        id=id,
        firstName="Sveta",
        lastName="Shulzhytskaya $id")

    init{}

    fun printMe(): Pair<String?, String?> {
        return firstName to lastName
    }

    companion object Factory{
        private var lastId: Int = -1

        fun makeUser(fullName:String?):User{
            lastId+=1
            val (firstName ,lastName) = Utils.parseFullName(fullName)

            return User(id="$lastId", firstName = "$firstName", lastName = "$lastName")
        }
    }
}