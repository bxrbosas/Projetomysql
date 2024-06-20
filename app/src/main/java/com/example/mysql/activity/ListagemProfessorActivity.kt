package com.example.mysql.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mysql.adapter.ProfessorAdapter
import com.example.mysql.api.EnderecoAPI
import com.example.mysql.api.RetrofitHelper
import com.example.mysql.databinding.ActivityListagemProfessorBinding
import com.example.mysql.model.Aluno
import com.example.mysql.model.Professor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ListagemProfessorActivity : AppCompatActivity() {
    private lateinit var binding: ActivityListagemProfessorBinding
    private lateinit var professorAdapter: ProfessorAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListagemProfessorBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecyclerView()
        loadProfessor()

        binding.btnCadastroProfessor.setOnClickListener {
            val intent = Intent(this, CadastroProfessorActivity::class.java)
            cadastroProfessorActivityResultLauncher.launch(intent)
        }
    }

    private val cadastroProfessorActivityResultLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == RESULT_OK) {
                // Atualizar a lista de alunos após cadastro/edição
                loadProfessor()
            }
        }

    private fun setupRecyclerView() {
        professorAdapter = ProfessorAdapter(this) { professorId ->
            deleteProfessor(professorId)
        }
        binding.professorRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.professorRecyclerView.adapter = professorAdapter
    }

    private fun loadProfessor() {
        val retrofit = RetrofitHelper.getRetrofitInstance()
        val service = retrofit.create(EnderecoAPI::class.java)
        val call = service.getProfessor()

        call.enqueue(object : Callback<List<Professor>> {
            override fun onResponse(call: Call<List<Professor>>, response: Response<List<Professor>>) {
                if (response.isSuccessful) {
                    response.body()?.let { professores ->
                        professorAdapter.setData(professores)
                    }
                } else {
                    Toast.makeText(
                        this@ListagemProfessorActivity,
                        "Falha ao carregar alunos",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

            override fun onFailure(call: Call<List<Professor>>, t: Throwable) {
                Toast.makeText(this@ListagemProfessorActivity, "Erro: ${t.message}", Toast.LENGTH_SHORT)
                    .show()
            }
        })
    }

    private fun deleteProfessor(professorId: Int) {
        val retrofit = RetrofitHelper.getRetrofitInstance()
        val service = retrofit.create(EnderecoAPI::class.java)
        val call = service.excluirAluno(professorId)

        call.enqueue(object : Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                if (response.isSuccessful) {
                    Toast.makeText(
                        this@ListagemProfessorActivity,
                        "Professor excluído com sucesso",
                        Toast.LENGTH_SHORT
                    ).show()
                    loadProfessor() // Atualizar a lista de alunos após exclusão
                } else {
                    Toast.makeText(
                        this@ListagemProfessorActivity,
                        "Falha ao excluir professor",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                Toast.makeText(this@ListagemProfessorActivity, "Erro: ${t.message}", Toast.LENGTH_SHORT)
                    .show()
            }
        })
    }
}
