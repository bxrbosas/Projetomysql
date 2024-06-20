package com.example.mysql.activity


import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.mysql.api.EnderecoAPI
import com.example.mysql.api.RetrofitHelper
import com.example.mysql.databinding.ActivityCadastroAlunoBinding
import com.example.mysql.model.Aluno
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class
CadastroAlunoActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCadastroAlunoBinding
    private var alunoId: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCadastroAlunoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        alunoId = intent.getIntExtra("ALUNO_ID", -1)
        if (alunoId != -1) {
            binding.edtNome.setText(intent.getStringExtra("ALUNO_NOME"))
            binding.edtCpf.setText(intent.getStringExtra("ALUNO_CPF"))
            binding.edtEmail.setText(intent.getStringExtra("ALUNO_EMAIL"))
            binding.edtMatricula.setText(intent.getStringExtra("ALUNO_MATRICULA"))
        }

        binding.btnSave.setOnClickListener {
            val nome = binding.edtNome.text.toString()
            val cpf = binding.edtCpf.text.toString()
            val email = binding.edtEmail.text.toString()
            val matricula = binding.edtMatricula.text.toString()

            if (nome.isNotEmpty() && cpf.isNotEmpty() && email.isNotEmpty() && matricula.isNotEmpty()) {
                val aluno = Aluno(alunoId ?: 0, nome, cpf, email, matricula)
                if (alunoId != null && alunoId != -1) {
                    alterarAluno(aluno)
                } else {
                    salvarAluno(aluno)
                }
            } else {
                Toast.makeText(this, "Por favor, preencha todos os campos.", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun salvarAluno(aluno: Aluno) {
        val retrofit = RetrofitHelper.getRetrofitInstance()
        val service = retrofit.create(EnderecoAPI::class.java)
        val call = service.inserirAluno(aluno)

        call.enqueue(object : Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                if (response.isSuccessful) {
                    setResult(Activity.RESULT_OK)
                    finish()
                } else {
                    Toast.makeText(this@CadastroAlunoActivity, "Erro ao salvar aluno.", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                Toast.makeText(this@CadastroAlunoActivity, "Erro de rede: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun alterarAluno(aluno: Aluno) {
        val retrofit = RetrofitHelper.getRetrofitInstance()
        val service = retrofit.create(EnderecoAPI::class.java)
        val call = service.alterarAluno(aluno)

        call.enqueue(object : Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                if (response.isSuccessful) {
                    setResult(Activity.RESULT_OK, Intent().putExtra("ALUNO_ALTERADO", true))
                    finish()
                } else {
                    Toast.makeText(this@CadastroAlunoActivity, "Erro ao alterar aluno.", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                Toast.makeText(this@CadastroAlunoActivity, "Erro de rede: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }
}

