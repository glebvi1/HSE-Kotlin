package com.example.myapplication.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.R
import com.example.myapplication.data.Data
import com.example.myapplication.models.Repository
import com.example.myapplication.models.User

class RepositoryActivity : AppCompatActivity() {

    private var text: EditText? = null
    private var btn: Button? = null
    private var result: TextView? = null
    private var intentBtn: Button? = null
    private val TAG: String = "HSE-KOTLIN"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_repository)

        text = findViewById(R.id.reposName)
        btn = findViewById(R.id.user_btn)
        result = findViewById(R.id.result)
        intentBtn = findViewById(R.id.intent_btn)

        // Действия при нажатие на книпку "Найти"
        btn?.setOnClickListener{
            // Проверка на пустое введенное название репозитория
            if (text?.text?.trim()?.equals("")!!) {
                Toast.makeText(this, "Введите название репозитория.", Toast.LENGTH_LONG).show()
                Log.i(TAG, "Empty query")
            } else {
                // Создаем и сохраняем в лист данные
                val usersList: List<User> = Data().createUsers()

                // Строка, введенная пользователем. Уже точно != null
                val reposName: String = text?.text.toString()

                // Создаем список всех репозиториев
                val reposList: MutableList<Repository> = mutableListOf()
                for (user in usersList) {
                    for (repo in user.repositories) {
                        reposList.add(repo)
                    }
                }

                // Фильтруем их по названию
                val chosenRepos: List<Repository> = reposList.filter { it.name == reposName }

                // Проверка на существование пользователей с таким именем
                if (chosenRepos.isEmpty()) {
                    Toast.makeText(this, "Репозитория с таким названием не найдено.", Toast.LENGTH_LONG).show()
                } else {
                    Log.i(TAG, chosenRepos[0].toString())

                    // Отбор всех репозиториев по названию
                    var allRepos: String = ""
                    for (repo in chosenRepos) {
                        allRepos += repo.getInfo(true) + "\n"
                    }

                    result?.text = allRepos
                }
            }
        }

        // Действия при нажатие на кнопку "Пользователи"
        // Осуществляется переход на новое окно, в котором есть возможность искать репозитории по их названиям
        // Создаем Intent с переходом на активность MainActivity
        intentBtn?.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

    }

}
