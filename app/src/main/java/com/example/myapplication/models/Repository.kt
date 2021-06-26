package com.example.myapplication.models

data class Repository(val name: String, val description: String, val lang: String, val userName: String) {
    fun getInfo(ifs: Boolean = false): String {
        val start: String = if (ifs) "$userName: " else "$name: "
        return start + "$description, $lang"
    }
}