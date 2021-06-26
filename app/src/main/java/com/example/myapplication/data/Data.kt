package com.example.myapplication.data

import com.example.myapplication.models.Repository
import com.example.myapplication.models.User

class Data {

    // Создание пользователей с репозиториями
    fun createUsers(): List<User> {
        val reposForUser1 = listOf(
            Repository(name = "HelloWorld", description = "I'll be a next Hokage of this programming world", lang = "Kotlin", userName = "User1"),
            Repository(name = "Cells", description = "I have got a new arm", lang = "Cobol", userName = "User1")
        )
        val reposForUser2 = listOf(
            Repository(name = "OldKey", description = "I want to know what are this cellar contains", lang = "Kotlin", userName = "User2"),
            Repository(name = "Sea", description = "I'll kill all titans and go to the sea, witch contains salt", lang = "Java", userName = "User2")
        )
        val reposForUser3 = listOf(
            Repository("HelloWorld", description = "Cool project", lang = "Java", userName = "User3")
        )

        return listOf(User("User1", reposForUser1), User("User2", reposForUser2), User("User3", reposForUser3))
    }

    // Получить все репозитории
    fun getAllRepositories(): MutableList<Repository> {
        val reposList: MutableList<Repository> = mutableListOf()
        for (user in Data().createUsers()) {
            for (repo in user.repositories) {
                reposList.add(repo)
            }
        }

        return reposList
    }
}