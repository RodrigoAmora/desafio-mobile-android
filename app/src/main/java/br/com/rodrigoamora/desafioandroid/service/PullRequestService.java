package br.com.rodrigoamora.desafioandroid.service;

import java.util.List;

import br.com.rodrigoamora.desafioandroid.model.PullRequest;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface PullRequestService {

    @GET("repos/{owner}/{repo}/pulls")
    Call<List<PullRequest>> listarPullRequests(@Path("owner") String owner, @Path("repo") String repo);

}
