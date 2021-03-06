package br.com.rodrigoamora.desafioandroid.app;

import android.app.Application;

import com.orhanobut.hawk.Hawk;

import br.com.rodrigoamora.desafioandroid.component.DaggerPullRequestComponent;
import br.com.rodrigoamora.desafioandroid.component.DaggerRepositorioComponent;
import br.com.rodrigoamora.desafioandroid.component.PullRequestComponent;
import br.com.rodrigoamora.desafioandroid.component.RepositorioComponent;

public class MyApplication extends Application {

    private PullRequestComponent pullRequestComponent;
    private RepositorioComponent repositorioComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        createComponents();
        initHawk();
    }

    private void createComponents() {
        pullRequestComponent = DaggerPullRequestComponent.builder().build();
        repositorioComponent = DaggerRepositorioComponent.builder().build();
    }

    private void initHawk() {
        Hawk.init(this).build();
    }

    public PullRequestComponent getPullRequestComponent() {
        return pullRequestComponent;
    }

    public RepositorioComponent getRepositorioComponent() {
        return repositorioComponent;
    }
}
