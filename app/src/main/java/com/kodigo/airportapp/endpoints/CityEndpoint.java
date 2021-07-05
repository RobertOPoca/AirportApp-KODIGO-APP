package com.kodigo.airportapp.endpoints;

import com.kodigo.airportapp.item.ItemCity;
import com.kodigo.airportapp.response.ResponseApi;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface CityEndpoint {

  @GET("cities")
  Call<ResponseApi<List<ItemCity>>> getAllCities();
}
