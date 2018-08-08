package br.com.rodrigoamora.desafioandroid.callback;

import java.util.List;

import br.com.rodrigoamora.desafioandroid.delegate.RepositorioDelegate;
import br.com.rodrigoamora.desafioandroid.model.Repositorio;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RepositorioCallback implements Callback<List<Repositorio>> {

    private RepositorioDelegate delegate;

    public RepositorioCallback(RepositorioDelegate delegate) {
        this.delegate = delegate;
    }

    @Override
    public void onResponse(Call<List<Repositorio>> call, Response<List<Repositorio>> response) {
        if (response.isSuccessful()) {
            List<Repositorio> repositorios = response.body();
            if (repositorios != null) {
                delegate.success(repositorios);
            } else {
                delegate.error();
            }
        } else {
            delegate.error();
        }
    }

    @Override
    public void onFailure(Call<List<Repositorio>> call, Throwable t) {
        delegate.error();
    }

}
