package br.com.rodrigoamora.desafioandroid.module;

import br.com.rodrigoamora.desafioandroid.factory.RetrofitFactory;
import br.com.rodrigoamora.desafioandroid.service.RepositorioService;
import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module
public class RepositorioModule {

    @Provides
    public RepositorioService getRepositorioService() {
        Retrofit retrofit = RetrofitFactory.createRetrofit();
        RepositorioService service = retrofit.create(RepositorioService.class);
        return service;
    }

}
