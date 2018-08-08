package br.com.rodrigoamora.desafioandroid.module;

import br.com.rodrigoamora.desafioandroid.factory.RetrofitFactory;
import br.com.rodrigoamora.desafioandroid.service.PullRequestService;
import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module
public class PullRequestModule {

    @Provides
    public PullRequestService getPullRequestService() {
        Retrofit retrofit = RetrofitFactory.createRetrofit();
        PullRequestService service = retrofit.create(PullRequestService.class);
        return service;
    }

}
