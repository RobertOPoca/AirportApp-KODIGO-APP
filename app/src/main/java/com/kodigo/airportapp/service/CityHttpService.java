package com.kodigo.airportapp.service;

import com.kodigo.airportapp.listener.OnCityListenerRead;
import com.kodigo.airportapp.endpoints.CityEndpoint;
import com.kodigo.airportapp.item.ItemCity;
import com.kodigo.airportapp.response.ResponseApi;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CityHttpService {

  private ApiConnection connection;

  public CityHttpService(ApiConnection apiConnection) {
    this.connection = apiConnection;
  }

  public void getAllCities(OnCityListenerRead onCityListenerRead) {
    final ResponseApi<List<ItemCity>> responseApi = new ResponseApi<>();
    this.connection.createConnection();
    CityEndpoint endpoint = this.connection.getConnection().create(CityEndpoint.class);
    Call<ResponseApi<List<ItemCity>>> responseApiCall = endpoint.getAllCities();
    responseApiCall.enqueue(new Callback<ResponseApi<List<ItemCity>>>() {
      @Override
      public void onResponse(Call<ResponseApi<List<ItemCity>>> call,
          Response<ResponseApi<List<ItemCity>>> response) {
        if (response.isSuccessful()) {
          ResponseApi<List<ItemCity>> respuesta = response.body();
          responseApi.setSuccess(respuesta.getSuccess());
          responseApi.setMessage(respuesta.getMessage());
          responseApi.setData(respuesta.getData());
          onCityListenerRead.onGetAllCitiesSuccess(responseApi);
        }
      }

      @Override
      public void onFailure(Call<ResponseApi<List<ItemCity>>> call, Throwable t) {
        onCityListenerRead.onGetAllCitiesFailure(t.getMessage());
      }
    });
  }
}
