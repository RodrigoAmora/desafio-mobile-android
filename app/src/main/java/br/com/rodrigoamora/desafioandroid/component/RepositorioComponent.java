package br.com.rodrigoamora.desafioandroid.component;

import br.com.rodrigoamora.desafioandroid.module.RepositoryModule;
import br.com.rodrigoamora.desafioandroid.ui.fragment.RepositoryFragment;
import dagger.Component;

@Component(modules = RepositoryModule.class)
public interface RepositorioComponent {

    void inject(RepositoryFragment repositoryFragment);

}
