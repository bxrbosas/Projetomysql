package com.example.mysql.activity

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.mysql.R
import com.example.mysql.databinding.ActivityItemAlunoBinding
import com.example.mysql.databinding.ActivityItemTurmaBinding

class ItemTurmaActivity : AppCompatActivity() {


    //Configuração do viewBinding
    private val binding by lazy {
        ActivityItemTurmaBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}