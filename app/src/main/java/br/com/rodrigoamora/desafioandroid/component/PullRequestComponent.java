package br.com.rodrigoamora.desafioandroid.component;

import br.com.rodrigoamora.desafioandroid.module.PullRequestModule;
import br.com.rodrigoamora.desafioandroid.ui.fragment.PullRequestFramgnet;
import dagger.Component;

@Component(modules = PullRequestModule.class)
public interface PullRequestComponent {

    void inject(PullRequestFramgnet repositorioFragment);

}
