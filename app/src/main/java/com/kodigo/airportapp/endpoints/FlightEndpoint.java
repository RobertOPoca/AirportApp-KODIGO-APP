package com.kodigo.airportapp.endpoints;

import com.kodigo.airportapp.item.ItemFlight;
import com.kodigo.airportapp.model.Flight;
import com.kodigo.airportapp.response.ResponseApi;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface FlightEndpoint {

  @GET("flights")
  Call<ResponseApi<List<ItemFlight>>> getAllFlights();

  @POST("flights")
  Call<ResponseApi<ItemFlight>> createFlight(@Body Flight flight);

  @GET("flights/{id}")
  Call<ResponseApi<ItemFlight>> getFlightById(@Path("id") Integer id);

  @PUT("flights")
  Call<ResponseApi<ItemFlight>> updateFlight(@Body Flight flight);

  @GET("batch")
  Call<ResponseApi<String>> loadExcel();

  @POST("report")
  Call<ResponseApi<String>> reportByDate(@Body String date);

  @PUT("report/{id}")
  Call<ResponseApi<String>> reportById(@Path("id") Integer id);

}
