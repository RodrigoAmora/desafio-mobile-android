package br.com.rodrigoamora.desafioandroid.delegate;

public interface RepositorioDelegate<T> {

    void error();
    void success(T t);

}
