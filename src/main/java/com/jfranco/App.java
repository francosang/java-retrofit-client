package com.jfranco;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import com.google.gson.Gson;

import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) throws IOException {
        System.out.println("Hello World!");

        Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://api.github.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

        Github service = retrofit.create(Github.class);

        final Response<List<ResponseGithub>> response = service.listRepos(args[0]).execute();

        System.out.println("Respose del usuaio " + args[0]);
        response.body().forEach(repo -> {
            System.out.println("- " + repo);
    
        });
    }
}
