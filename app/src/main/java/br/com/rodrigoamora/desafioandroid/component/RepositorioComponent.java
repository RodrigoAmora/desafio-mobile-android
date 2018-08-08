package br.com.rodrigoamora.desafioandroid.component;

import br.com.rodrigoamora.desafioandroid.module.RepositorioModule;
import br.com.rodrigoamora.desafioandroid.ui.fragment.RepositorioFragment;
import dagger.Component;

@Component(modules = RepositorioModule.class)
public interface RepositorioComponent {

    void inject(RepositorioFragment fragment);

}
