package br.com.rodrigoamora.desafioandroid.module;

import br.com.rodrigoamora.desafioandroid.BuildConfig;
import br.com.rodrigoamora.desafioandroid.factory.RetrofitFactory;
import br.com.rodrigoamora.desafioandroid.service.RepositorioService;
import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module
public class RepositoryModule {

    @Provides
    public RepositorioService getRepositoryService() {
        Retrofit retrofit = RetrofitFactory.createRetrofit(BuildConfig.API_GITHUB);
        RepositorioService service = retrofit.create(RepositorioService.class);
        return service;
    }

}
