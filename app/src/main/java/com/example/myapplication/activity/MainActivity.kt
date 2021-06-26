package com.example.myapplication.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.myapplication.R

class MainActivity : AppCompatActivity() {

    private var intentToRepository: Button? = null
    private var intentToUser: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        intentToRepository = findViewById(R.id.intent_repository_btn)
        intentToUser = findViewById(R.id.intent_user_btn)

        // Действия при нажатие на кнопку "Репозитории"
        // Осуществляется переход на новое окно, в котором есть возможность искать репозитории по их названиям
        // Создаем Intent с переходом на активность RepositoryActivity
        intentToRepository?.setOnClickListener {
            val intent = Intent(this, RepositoryActivity::class.java)
            startActivity(intent)
        }

        // Тоже самое для кнопки "Пользователи"
        intentToUser?.setOnClickListener {
            val intent = Intent(this, UserActivity::class.java)
            startActivity(intent)
        }

    }
}