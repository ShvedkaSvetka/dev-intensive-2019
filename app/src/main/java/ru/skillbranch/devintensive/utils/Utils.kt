package ru.skillbranch.devintensive.utils

object Utils{
    fun parseFullName(fullName:String?): Pair<String, String> {
        val strFullName = fullName?.trim()?.ifEmpty { null }
        val parts :List<String>? = strFullName?.split(" ")

        val firstName = parts?.getOrNull(0)?:"null"
        val lastName = parts?.getOrNull(1)?:"null"

        return firstName to lastName
    }
}