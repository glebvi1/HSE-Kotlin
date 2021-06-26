package com.example.myapplication.data

import android.os.Bundle
import com.example.myapplication.R
import com.example.myapplication.models.Repository
import com.example.myapplication.models.User

class Data {

    // Ф-ия, создающая пользователей с репозиториями
    fun createUsers(): List<User> {
        val reposForUser1 = listOf(
            Repository(name = "HelloWorld", description = "I'll be a next Hokage of this programming world", lang = "Kotlin", userName = "User2"),
            Repository(name = "Cells", description = "I have got a new arm", lang = "Cobol", userName = "User1")
        )
        val reposForUser2 = listOf(
            Repository(name = "OldKey", description = "I want to know what are this cellar contains", lang = "Kotlin", userName = "User1"),
            Repository(name = "Sea", description = "I'll kill all titans and go to the sea, witch contains salt", lang = "Java", userName = "User2")
        )

        return listOf(User("User1", reposForUser1), User("User2", reposForUser2))
    }

/*    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Данные
        val usersList: List<User> = createUsers()

        // Заданые значения
        val userName: String = ""
        val reposName: String = ""

        // Фильтрация пользователей по имени
        val chosenUser: List<User> = usersList.filter { user -> user.name == userName }

        val chosenRepos: MutableList<Repository> = mutableListOf<Repository>()

        // Фильтрация репозиториев по названию
        for (user in usersList) {
            // Для каждого пользователя находим репозиторий с заданым названием (если он есть)
            for (repos in user.repositories.filter { repos -> repos.name == reposName }) {
                chosenRepos.add(repos)
            }
        }
    }*/


}