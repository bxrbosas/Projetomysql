package com.example.mysql.adapter

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mysql.activity.CadastroTurmaActivity
import com.example.mysql.databinding.ActivityItemTurmaBinding
import com.example.mysql.model.Turma

class TurmaAdapter (
    private val context: Context,
    private val deleteCallback: (Int) -> Unit
) : RecyclerView.Adapter<TurmaAdapter.TurmaViewHolder>() {

    private var turma: List<Turma> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TurmaViewHolder {
        val binding = ActivityItemTurmaBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TurmaViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TurmaViewHolder, position: Int) {
        val turma = turma[position]
        holder.bind(turma)

        holder.binding.btnDeletar.setOnClickListener {
            AlertDialog.Builder(holder.itemView.context)
                .setTitle("Excluir Turma")
                .setMessage("Deseja realmente excluir a turma ${turma.curso}?")
                .setPositiveButton("Sim") { _, _ ->
                    deleteCallback(turma.id)
                }
                .setNegativeButton("Não", null)
                .show()
        }

        holder.binding.btnEditar.setOnClickListener {
            val intent = Intent(context, CadastroTurmaActivity::class.java)
            intent.putExtra("TURMA_ID", turma.id)
            intent.putExtra("TURMA_CURSO", turma.curso)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return turma.size
    }

    fun setData(turma: List<Turma>) {
        this.turma = turma
        notifyDataSetChanged()
    }

    inner class TurmaViewHolder(val binding: ActivityItemTurmaBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(turma: Turma) {
            binding.apply {
                nomeTextView.text = turma.curso
            }
        }
    }
}