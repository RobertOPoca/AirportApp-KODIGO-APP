package com.kodigo.airportapp.service;

import android.util.Log;

import com.kodigo.airportapp.listener.OnAirplaneListenerRead;
import com.kodigo.airportapp.endpoints.AirplaneEndpoint;
import com.kodigo.airportapp.item.ItemAirplane;
import com.kodigo.airportapp.response.ResponseApi;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AirplaneHttpService {

  private ApiConnection connection;

  public AirplaneHttpService(ApiConnection apiConnection) {
    this.connection = apiConnection;
  }

  public void getAllAirplanes(OnAirplaneListenerRead onAirplaneListener) {
    final ResponseApi<List<ItemAirplane>> responseApi = new ResponseApi<>();
    this.connection.createConnection();
    AirplaneEndpoint endpoint = this.connection.getConnection().create(AirplaneEndpoint.class);
    Call<ResponseApi<List<ItemAirplane>>> responseApiCall = endpoint.getAllAirplanes();

    responseApiCall.enqueue(new Callback<ResponseApi<List<ItemAirplane>>>() {
      @Override
      public void onResponse(Call<ResponseApi<List<ItemAirplane>>> call,
          Response<ResponseApi<List<ItemAirplane>>> response) {
        if (response.isSuccessful()) {
          ResponseApi<List<ItemAirplane>> respuesta = response.body();
          responseApi.setSuccess(respuesta.getSuccess());
          responseApi.setMessage(respuesta.getMessage());
          responseApi.setData(respuesta.getData());
          onAirplaneListener.onGetAllAirplaneSuccess(responseApi);

        }
        Log.d("ERR_SERVICES", response.message());
      }

      @Override
      public void onFailure(Call<ResponseApi<List<ItemAirplane>>> call, Throwable t) {

        onAirplaneListener.onGetAllAirplaneFailure(t.getMessage());
        Log.d("ERR_SERVICES", t.getMessage());
      }
    });
  }
}
