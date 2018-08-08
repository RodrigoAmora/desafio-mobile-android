package br.com.rodrigoamora.desafioandroid.app;

import android.app.Application;

import br.com.rodrigoamora.desafioandroid.component.DaggerRepositorioComponent;
import br.com.rodrigoamora.desafioandroid.component.RepositorioComponent;

public class MyApplication extends Application {

    private RepositorioComponent repositorioComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        createComponents();
    }

    private void createComponents() {
        repositorioComponent = DaggerRepositorioComponent.builder().build();
    }

    public RepositorioComponent getRepositorioComponent() {
        return repositorioComponent;
    }

}
