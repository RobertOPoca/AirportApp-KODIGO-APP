package com.kodigo.airportapp.endpoints;

import com.kodigo.airportapp.item.ItemCountry;
import com.kodigo.airportapp.response.ResponseApi;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface CountryEndpoint {

  @GET("countries")
  Call<ResponseApi<List<ItemCountry>>> getAllCountries();
}
