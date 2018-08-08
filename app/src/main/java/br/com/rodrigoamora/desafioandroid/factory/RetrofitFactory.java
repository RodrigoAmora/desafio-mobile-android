package br.com.rodrigoamora.desafioandroid.factory;


import br.com.rodrigoamora.desafioandroid.BuildConfig;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitFactory {

    public static Retrofit createRetrofit() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BuildConfig.API_GITHUB)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit;
    }

}
