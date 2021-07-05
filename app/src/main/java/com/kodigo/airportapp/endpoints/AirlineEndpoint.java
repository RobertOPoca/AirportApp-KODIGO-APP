package com.kodigo.airportapp.endpoints;

import com.kodigo.airportapp.item.ItemAirline;
import com.kodigo.airportapp.model.Airline;
import com.kodigo.airportapp.response.ResponseApi;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface AirlineEndpoint {

  @GET("airlines")
  Call<ResponseApi<List<ItemAirline>>> getAllAirlines();
}
