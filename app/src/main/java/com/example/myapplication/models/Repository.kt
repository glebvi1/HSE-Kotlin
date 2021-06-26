package com.example.myapplication.models

data class Repository(val name: String, val description: String, val lang: String, val userName: String) {
    fun getInfo(): String {
        return "$name, $description, $lang"
    }
}