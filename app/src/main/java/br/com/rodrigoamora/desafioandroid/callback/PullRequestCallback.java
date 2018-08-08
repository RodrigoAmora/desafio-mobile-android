package br.com.rodrigoamora.desafioandroid.callback;

import java.util.List;

import br.com.rodrigoamora.desafioandroid.delegate.Delegate;
import br.com.rodrigoamora.desafioandroid.model.PullRequest;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PullRequestCallback implements Callback<List<PullRequest>> {

    private Delegate delegate;

    public PullRequestCallback(Delegate delegate) {
        this.delegate = delegate;
    }

    @Override
    public void onResponse(Call<List<PullRequest>> call, Response<List<PullRequest>> response) {
        if (response.isSuccessful()) {
            List<PullRequest> pullRequests = response.body();
            if (pullRequests != null) {
                delegate.success(pullRequests);
            } else {
                delegate.error();
            }
        } else {
            delegate.error();
        }
    }

    @Override
    public void onFailure(Call<List<PullRequest>> call, Throwable t) {
        delegate.error();
    }

}
