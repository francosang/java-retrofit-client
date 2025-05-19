package com.jfranco;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface Github {

    @GET("users/{user}/repos")
    Call<List<ResponseGithub>> listRepos(@Path("user") String user);
    
}
