package com.example.myapplication.activity

import android.os.Bundle
import android.util.Log
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.R
import com.example.myapplication.data.Data
import com.example.myapplication.models.Repository
import com.example.myapplication.models.User

class RepositoryActivity : AppCompatActivity() {

    private val TAG: String = "HSE-KOTLIN"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_repository)

        val text: EditText = findViewById(R.id.reposName)
        val btn: Button = findViewById(R.id.user_btn)
        val listView: ListView = findViewById(R.id.list_repos)

        // Создаем список всех репозиториев
        val reposList: MutableList<Repository> = Data().getAllRepositories()

        // Действия при нажатие на книпку "Найти"
        btn.setOnClickListener{
            // Проверка на пустое введенное название репозитория
            if (text.text.trim().equals("")) {
                Toast.makeText(this, "Введите название репозитория.", Toast.LENGTH_LONG).show()
                Log.i(TAG, "Empty query")
            } else {
                // Строка, введенная пользователем. Уже точно != ""
                val reposName: String = text.text.toString()

                // Фильтруем их по названию
                val chosenRepos: List<Repository> = reposList.filter { it.name == reposName }

                // Проверка на существование репозиториев с таким названием
                if (chosenRepos.isEmpty()) {
                    Toast.makeText(this, "Репозитория с таким названием не найдено.", Toast.LENGTH_LONG).show()
                } else {
                    Log.i(TAG, chosenRepos[0].toString())

                    // Названия выбранных репозиториев
                    val chosenReposString: MutableList<String> = mutableListOf()

                    for (repo in chosenRepos) {
                        chosenReposString.add(repo.name)
                    }

                    // Создания адаптера
                    val arrayAdapter: ArrayAdapter<String> = ArrayAdapter(
                        this, android.R.layout.simple_list_item_1, chosenReposString
                    )

                    listView.adapter = arrayAdapter

                    // Доп. информация по нажатию на ячейку (item)
                    listView.setOnItemClickListener{ adapterView, view, i, l ->
                        UserActivity().createAlertDialog(
                            chosenRepos[i].lang,
                            chosenRepos[i].description + "\n" + chosenRepos[i].userName,
                        this)
                    }
                }
            }
        }
    }
}
