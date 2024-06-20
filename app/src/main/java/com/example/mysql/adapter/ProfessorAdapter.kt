package com.example.mysql.adapter

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mysql.activity.CadastroProfessorActivity
import com.example.mysql.databinding.ActivityItemProfessorBinding
import com.example.mysql.model.Professor

class ProfessorAdapter (
    private val context: Context,
    private val deleteCallback: (Int) -> Unit
) : RecyclerView.Adapter<ProfessorAdapter.ProfessorViewHolder>() {

    private var professor: List<Professor> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProfessorViewHolder {
        val binding = ActivityItemProfessorBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProfessorViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProfessorViewHolder, position: Int) {
        val professor = professor[position]
        holder.bind(professor)

        holder.binding.btnDeletar.setOnClickListener {
            AlertDialog.Builder(holder.itemView.context)
                .setTitle("Excluir Professor")
                .setMessage("Deseja realmente excluir o professor ${professor.nome}?")
                .setPositiveButton("Sim") { _, _ ->
                    deleteCallback(professor.id)
                }
                .setNegativeButton("Não", null)
                .show()
        }

        holder.binding.btnEditar.setOnClickListener {
            val intent = Intent(context, CadastroProfessorActivity::class.java)
            intent.putExtra("PROFESSOR_ID", professor.id)
            intent.putExtra("PROFESSOR_NOME", professor.nome)
            intent.putExtra("PROFESSOR_CPF", professor.cpf)
            intent.putExtra("PROFESSOR_EMAIL", professor.email)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return professor.size
    }

    fun setData(professor: List<Professor>) {
        this.professor = professor
        notifyDataSetChanged()
    }

    inner class ProfessorViewHolder(val binding: ActivityItemProfessorBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(professor: Professor) {
            binding.apply {
                nomeTextView.text = professor.nome
                cpfTextView.text = professor.cpf
                emailTextView.text = professor.email
            }
        }
    }
}