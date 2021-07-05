package com.kodigo.airportapp.endpoints;

import com.kodigo.airportapp.item.ItemIncident;
import com.kodigo.airportapp.model.Incident;
import com.kodigo.airportapp.response.ResponseApi;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface IncidentEndpoint {

  @GET("incidents/flight/{id}")
  Call<ResponseApi<List<ItemIncident>>> getAllIncidents(@Path("id") Integer id);

  @POST("incidents")
  Call<ResponseApi<ItemIncident>> createIncident(@Body Incident incident);
}
