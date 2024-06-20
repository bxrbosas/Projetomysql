package com.example.mysql.api

import com.example.mysql.model.Aluno
import com.example.mysql.model.Professor
import com.example.mysql.model.Turma
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface EnderecoAPI {

    //Listagens
    @GET("aluno/listar")
    fun getAlunos(): Call<List<Aluno>>

    @GET("professor/listar")
    fun getProfessor(): Call<List<Professor>>

    @GET("turma/listar")
    fun getTurma(): Call<List<Turma>>


    //Cadastros
    @POST("aluno/inserir")
    fun inserirAluno(@Body aluno: Aluno): Call<Void>

    @POST("professor/inserir")
    fun inserirProfessor(@Body professor: Professor): Call<Void>

    @POST("turma/inserir")
    fun inserirTurma(@Body turma: Turma): Call<Void>


    //Excluir
    @DELETE("aluno/excluir/{id}")
    fun excluirAluno(@Path("id") id: Int): Call<Void>

    @DELETE("professor/excluir/{id}")
    fun excluirProfessor(@Path("id") id: Int): Call<Void>

    @DELETE("turma/excluir/{id}")
    fun excluirTurma(@Path("id") id: Int): Call<Void>


    //alterar
    @PUT("aluno/alterar")
    fun alterarAluno(@Body aluno: Aluno): Call<Void>

    @PUT("professor/alterar")
    fun alterarProfessor(@Body professor: Professor): Call<Void>

    @PUT("turma/alterar")
    fun alterarTurma(@Body turma: Turma): Call<Void>
}