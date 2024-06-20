package com.example.mysql.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mysql.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashActivity : AppCompatActivity() {

    private val splashTime: Long = 2000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        supportActionBar?.hide()

        //Gerando um atraso com coroutine
        CoroutineScope(Dispatchers.Main).launch {
            delay(splashTime)

            //Ao finalizar o Splash ir para pagina
            val intent = Intent(this@SplashActivity,
                ListagemAlunoActivity::class.java)
            startActivity(intent)
            finish()
        }

        /*//Splash - primeira forma
        Handler().postDelayed({
            val intent = Intent(this, ListagemAlunoActivity::class.java)
            startActivity(intent)
            finish()
        }, 2000)*/
    }
}