package com.example.mysql.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mysql.R
import com.example.mysql.databinding.ActivityItemAlunoBinding
import com.example.mysql.databinding.ActivityListagemAlunoBinding

class ItemAlunoActivity : AppCompatActivity() {

    //Configuração do viewBinding
    private val binding by lazy {
        ActivityItemAlunoBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}