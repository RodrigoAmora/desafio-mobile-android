package br.com.rodrigoamora.desafioandroid.delegate;

public interface Delegate<T> {

    void error();
    void success(T t);

}
