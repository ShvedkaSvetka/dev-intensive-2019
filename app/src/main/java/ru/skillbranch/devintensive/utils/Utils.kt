package ru.skillbranch.devintensive.utils

import android.provider.SyncStateContract
import android.webkit.URLUtil

object Utils{
    fun parseFullName(fullName:String?): Pair<String?, String?> {
        val strFullName = fullName?.trim()?.ifEmpty { null }
        val parts :List<String>? = strFullName?.split(" ")

        val firstName = parts?.getOrNull(0)?:null
        val lastName = parts?.getOrNull(1)?:null

        return firstName to lastName
    }

    fun transliteration(payload:String, devider:String = " "):String{
        var result=""

        for(c in payload){
            val symbol = c.toString()
            val isUpper = c.isUpperCase()

            if(symbol?.trim()?.isBlank()){
                result += devider
            }else if(symbol?.toLowerCase() in transliterationMap){
                var transpilledSymbol = transliterationMap?.getValue(symbol.toLowerCase())
                result+= (if(isUpper) transpilledSymbol?.capitalize() else transpilledSymbol)
            }else{
                result+=symbol
            }

        }

        return result
    }

    private fun getFirstLetter(str:String?)= str?.trim()?.take(1)?.toUpperCase()?:""

    fun toInitials(firstName:String?, lastName:String?): String? {
        if((firstName is String && firstName?.trim().isNotEmpty())
            || (lastName is String && lastName?.trim().isNotEmpty())){
            return getFirstLetter(firstName) + getFirstLetter(lastName)
        }
        return null
    }

    fun plurarizeTime(value: Int, pluralForms:String):String{
        val forms = pluralForms.split(";")

        when(value % 10){
            1 -> if(value % 100 != 11)
               return  "$value ${forms[0]}"
            in 2..4 -> if (value % 100 !in 12..14){
                return "$value ${forms[1]}"
            }
        }
        return "$value ${forms[2]}"
    }

    fun isValidUrl(url: String): Boolean {
        val constraints = urlConstrains.joinToString("|")
        val pattern = Regex("""^(https://)?(www\.)?github\.com/(?!($constraints)/?$)[\-\w]+/?$""")
        return url.isNullOrBlank() || pattern.matches(url)
    }
}