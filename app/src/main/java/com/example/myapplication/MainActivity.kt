package com.example.myapplication

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.myapplication.data.Data
import com.example.myapplication.models.Repository
import com.example.myapplication.models.User

class MainActivity : AppCompatActivity() {

    private var text: EditText? = null
    private var btn: Button? = null
    private var result: TextView? = null
    private val TAG: String = "HSE-KOTLIN"

    @SuppressLint("ShowToast")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        text = findViewById(R.id.userName)
        btn = findViewById(R.id.user_btn)
        result = findViewById(R.id.result)

        btn?.setOnClickListener{
            // Проверка на пустое введенное имя пользователя
            if (text?.text?.trim()?.equals("")!!) {
                Toast.makeText(this, "Введите имя пользователя.", Toast.LENGTH_LONG).show()
                Log.i(TAG, "Empty queryll ")
            } else {
                // Создаем и сохраняем в лист данные
                val usersList: List<User> = Data().createUsers()

                // Строка, введенная пользователем. Уже точно != null
                val userName: String = text?.text.toString()

                // Фильтрация пользователей по имени
                val chosenUser: List<User> = usersList.filter { user -> user.name == userName }

                // Проверка на существование пользователей с таким именем
                if (chosenUser.isEmpty()) {
                    Toast.makeText(this, "Пользователей с таким именем не найдено.", Toast.LENGTH_LONG).show()
                } else {
                    Log.i(TAG, chosenUser[0].toString())

                    // Отбор всех пользователей по имени
                    var allUsers: String = ""
                    for (user in chosenUser) {
                        allUsers += user.name + ": "
                        for (repo in user.repositories) {
                            allUsers += repo.getInfo() + "\n"
                        }
                        allUsers += "\n\n"
                    }

                    result?.text = allUsers
                }
            }
        }

    }
}