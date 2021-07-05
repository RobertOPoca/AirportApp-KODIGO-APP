package com.kodigo.airportapp.endpoints;

import com.kodigo.airportapp.item.ItemAirline;
import com.kodigo.airportapp.item.ItemAirplane;
import com.kodigo.airportapp.response.ResponseApi;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface AirplaneEndpoint {

  @GET("airplanes")
  Call<ResponseApi<List<ItemAirplane>>> getAllAirplanes();
}
