package com.example.mypockedex.Controler;

import android.util.Log;

import com.example.mypockedex.PokemonRestApi;
import com.example.mypockedex.model.Pokemon;
import com.example.mypockedex.model.RestPokemonResponse;
import com.example.mypockedex.view.PokemonApiList;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Controler {

    private final PokemonApiList pokemonApiList;

    private static Controler instance = null;

    //Exemple Singleton
    public static Controler getInstance(PokemonApiList pokemonApiList){
        if(instance == null){
            instance = new Controler(pokemonApiList);
        }
        return instance;

    }
    public Controler(PokemonApiList pokemonApiList) {
        this.pokemonApiList = pokemonApiList;
    }

    public void onCreate() {


        Gson gson = new GsonBuilder()
                .setLenient()
                .create();


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://pokeapi.co/api/v2/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        PokemonRestApi pokemonRestApi = retrofit.create(PokemonRestApi.class);


        Call<RestPokemonResponse> call = pokemonRestApi.getListPokemon();

        call.enqueue(new Callback<RestPokemonResponse>() {
            @Override
            public void onResponse(Call<RestPokemonResponse> call, Response<RestPokemonResponse> response) {
                RestPokemonResponse restPokemonResponse = response.body();
                List<Pokemon> listPokemon = restPokemonResponse.getResults();
                pokemonApiList.showList(listPokemon);
            }

            @Override
            public void onFailure(Call<RestPokemonResponse> call, Throwable t) {
                Log.d("Erreur", "API ERROR");
            }
        });
    }


}
