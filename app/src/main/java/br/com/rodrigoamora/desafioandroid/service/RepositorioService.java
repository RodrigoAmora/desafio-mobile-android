package br.com.rodrigoamora.desafioandroid.service;


import br.com.rodrigoamora.desafioandroid.module.callback.ListaRepositorios;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RepositorioService {

    @GET("search/repositories")
    Call<ListaRepositorios> listarRepositorios(@Query("q") String linguagem, @Query("sort") String sort, @Query("page") Integer page);

}
