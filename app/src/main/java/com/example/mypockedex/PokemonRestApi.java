package com.example.mypockedex;

import com.example.mypockedex.model.RestPokemonResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface PokemonRestApi {

    @GET("pokemon")
    Call<RestPokemonResponse> getListPokemon();
}
