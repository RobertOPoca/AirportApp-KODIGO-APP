package com.kodigo.airportapp.service;


import com.kodigo.airportapp.contract.IncidentContract;
import com.kodigo.airportapp.listener.OnIncidentListenerRead;
import com.kodigo.airportapp.endpoints.IncidentEndpoint;
import com.kodigo.airportapp.item.ItemIncident;
import com.kodigo.airportapp.model.Incident;
import com.kodigo.airportapp.response.ResponseApi;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class IncidentHttpService {

  private ApiConnection connection;

  public IncidentHttpService(ApiConnection apiConnection) {
    this.connection = apiConnection;
  }

  public void getAllIncidents(OnIncidentListenerRead onIncidentListener, Integer id) {
    final ResponseApi<List<ItemIncident>> responseApi = new ResponseApi<>();
    this.connection.createConnection();
    IncidentEndpoint endpoint = this.connection.getConnection().create(IncidentEndpoint.class);
    Call<ResponseApi<List<ItemIncident>>> responseApiCall = endpoint.getAllIncidents(id);
    responseApiCall.enqueue(new Callback<ResponseApi<List<ItemIncident>>>() {
      @Override
      public void onResponse(Call<ResponseApi<List<ItemIncident>>> call,
          Response<ResponseApi<List<ItemIncident>>> response) {
        if (response.isSuccessful()) {
          ResponseApi<List<ItemIncident>> respuesta = response.body();
          responseApi.setSuccess(respuesta.getSuccess());
          responseApi.setMessage(respuesta.getMessage());
          responseApi.setData(respuesta.getData());
          onIncidentListener.onGetAllIncidentSuccess(responseApi);
        }
      }
      @Override
      public void onFailure(Call<ResponseApi<List<ItemIncident>>> call, Throwable t) {
        onIncidentListener.onGetAllIncidentFailure(t.getMessage());
      }
    });
  }

  public void createIncident(IncidentContract.OnIncidentListener onIncidentListener,
      Incident incident) {
    final ResponseApi<ItemIncident> responseApi = new ResponseApi<>();
    this.connection.createConnection();
    IncidentEndpoint endpoint = this.connection.getConnection().create(IncidentEndpoint.class);
    Call<ResponseApi<ItemIncident>> responseApiCall = endpoint.createIncident(incident);
    responseApiCall.enqueue(new Callback<ResponseApi<ItemIncident>>() {
      @Override
      public void onResponse(Call<ResponseApi<ItemIncident>> call,
          Response<ResponseApi<ItemIncident>> response) {
        if (response.isSuccessful()) {
          ResponseApi<ItemIncident> respuesta = response.body();
          responseApi.setSuccess(respuesta.getSuccess());
          responseApi.setMessage(respuesta.getMessage());
          responseApi.setData(respuesta.getData());
          onIncidentListener.onCreateIncidentSuccess(responseApi);
        }
      }
      @Override
      public void onFailure(Call<ResponseApi<ItemIncident>> call, Throwable t) {
        onIncidentListener.onCreateIncidentFailure(t.getMessage());
      }
    });
  }
}
