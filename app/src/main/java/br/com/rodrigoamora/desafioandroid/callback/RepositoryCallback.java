package br.com.rodrigoamora.desafioandroid.callback;

import java.util.List;

import br.com.rodrigoamora.desafioandroid.delegate.Delegate;
import br.com.rodrigoamora.desafioandroid.model.Repository;
import br.com.rodrigoamora.desafioandroid.model.callback.ListaRepositorios;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RepositoryCallback implements Callback<ListaRepositorios> {

    private Delegate delegate;

    public RepositoryCallback(Delegate delegate) {
        this.delegate = delegate;
    }

    @Override
    public void onResponse(Call<ListaRepositorios> call, Response<ListaRepositorios> response) {
        if (response.isSuccessful()) {
            List<Repository> repositorios = response.body().getItems();
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
    public void onFailure(Call<ListaRepositorios> call, Throwable t) {
        delegate.error();
    }

}
