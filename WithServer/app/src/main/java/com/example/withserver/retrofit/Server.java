package com.example.withserver.retrofit;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Server {

    private static Server instance;
    private ServerAPI api;

    private Server() {
        String url="https://260bf699-339b-4ffc-a6bf-16d0941c4cc8.mock.pstmn.io";

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        api = retrofit.create(ServerAPI.class);
    }

    public static Server getInstance() {
        if(instance == null) instance = new Server();

        return instance;
    }

    public ServerAPI getAPI() { return api; }
}
