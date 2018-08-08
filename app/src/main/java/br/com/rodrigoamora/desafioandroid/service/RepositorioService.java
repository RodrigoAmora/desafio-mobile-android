package br.com.rodrigoamora.desafioandroid.service;


import java.util.List;

import br.com.rodrigoamora.desafioandroid.model.Repositorio;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RepositorioService {

    @GET("search/repositories")
    Call<List<Repositorio>> listarRepositorios(@Query("q") String linguagem, @Query("sort") String sort, @Query("page") Integer page);

}
