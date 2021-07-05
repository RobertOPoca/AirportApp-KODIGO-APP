package com.kodigo.airportapp.service;


import com.kodigo.airportapp.listener.OnAirlineListenerRead;
import com.kodigo.airportapp.endpoints.AirlineEndpoint;
import com.kodigo.airportapp.item.ItemAirline;
import com.kodigo.airportapp.response.ResponseApi;

import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AirlineHttpService {

  private ApiConnection connection;

  public AirlineHttpService(ApiConnection apiConnection) {
    this.connection = apiConnection;
  }

  public void getAllAirlines(OnAirlineListenerRead onAirlineListener) {
    final ResponseApi<List<ItemAirline>> responseApi = new ResponseApi<>();
    this.connection.createConnection();
    AirlineEndpoint endpoint = this.connection.getConnection().create(AirlineEndpoint.class);
    Call<ResponseApi<List<ItemAirline>>> responseApiCall = endpoint.getAllAirlines();
    responseApiCall.enqueue(new Callback<ResponseApi<List<ItemAirline>>>() {
      @Override
      public void onResponse(Call<ResponseApi<List<ItemAirline>>> call,
          Response<ResponseApi<List<ItemAirline>>> response) {
        if (response.isSuccessful()) {
          ResponseApi<List<ItemAirline>> respuesta = response.body();
          responseApi.setSuccess(respuesta.getSuccess());
          responseApi.setMessage(respuesta.getMessage());
          responseApi.setData(respuesta.getData());
          onAirlineListener.onGetAllAirlineSuccess(responseApi);
        }
      }

      @Override
      public void onFailure(Call<ResponseApi<List<ItemAirline>>> call, Throwable t) {
        onAirlineListener.onGetAllAirlineFailure(t.getMessage());
      }
    });
  }


}
