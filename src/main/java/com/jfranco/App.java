package com.jfranco;

import java.io.IOException;
import java.util.List;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.GsonBuilder;

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
                .addConverterFactory(
                        GsonConverterFactory.create(new GsonBuilder()
                                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                                .create()))
                .build();

        Github service = retrofit.create(Github.class);

        final String userName = args[0];

        final List<RepoResponse> response = service.listRepos(userName).execute().body();

        System.out.println("Respose del usuaio " + userName);
        response.forEach(repo -> {
            System.out.println("ID: " + repo.getId());
            System.out.println("Nombre: " + repo.getFullName());
            System.out.println("Url: " + repo.getHtmlUrl());
        
            System.out.println();
        });
    }
}
