package com.example.myapplication.activity

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.R
import com.example.myapplication.data.Data
import com.example.myapplication.models.User


class UserActivity : AppCompatActivity() {

    private val TAG: String = "HSE-KOTLIN"

    @SuppressLint("ShowToast")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user)

        val text: EditText = findViewById(R.id.userName)
        val btn: Button = findViewById(R.id.user_btn)
        val listView: ListView = findViewById(R.id.list_users)

        // Действия при нажатие на книпку "Найти"
        btn.setOnClickListener{
            // Проверка на пустое введенное имя пользователя
            if (text.text.trim().equals("")) {
                Toast.makeText(this, "Введите имя пользователя.", Toast.LENGTH_LONG).show()
                Log.i(TAG, "Empty query")
            } else {
                // Создаем и сохраняем в лист данные
                val usersList: List<User> = Data().createUsers()

                // Строка, введенная пользователем. Уже точно != ""
                val userName: String = text.text.toString()

                // Фильтрация пользователей по имени (имя/логин каждого пользователя уникальны)
                val chosenUserList: List<User> = usersList.filter { user -> user.name == userName }

                // Проверка на существование пользователей с таким именем
                if (chosenUserList.isEmpty()) {
                    Toast.makeText(this, "Пользователей с таким именем не найдено.", Toast.LENGTH_LONG).show()
                } else {

                    val chosenUser: User = usersList[0]

                    Log.i(TAG, chosenUser.toString())

                    val chosenUserString: MutableList<String> = mutableListOf()

                    // Отбор репозиториев пользователя
                    for (repo in chosenUser.repositories) {
                        chosenUserString.add(repo.name)
                    }

                    // Создания адаптера
                    val arrayAdapter: ArrayAdapter<String> = ArrayAdapter(
                        this, android.R.layout.simple_list_item_1, chosenUserString
                    )

                    listView.adapter = arrayAdapter

                    // Доп. информация по нажатию на ячейку (item)
                    listView.setOnItemClickListener{
                        adapterView, view, i, l -> createAlertDialog(
                        chosenUser.repositories[i].lang,
                        chosenUser.repositories[i].description,
                        this)
                    }

                }
            }
        }
    }

    // Создания диалогового окна
    fun createAlertDialog(theme: String?, rule: String?, context: Context) {
        val builder = AlertDialog.Builder(context)
        builder.setTitle(theme)
        builder.setMessage(rule)
        builder.setPositiveButton(
            "Ок"
        ) { dialog, which -> }
        builder.show()
    }
}