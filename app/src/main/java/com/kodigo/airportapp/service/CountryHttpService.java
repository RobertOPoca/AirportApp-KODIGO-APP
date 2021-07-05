package com.kodigo.airportapp.service;

import com.kodigo.airportapp.listener.OnCountryListenerRead;
import com.kodigo.airportapp.endpoints.CountryEndpoint;
import com.kodigo.airportapp.item.ItemCountry;
import com.kodigo.airportapp.response.ResponseApi;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CountryHttpService {

  private ApiConnection connection;

  public CountryHttpService(ApiConnection apiConnection) {
    this.connection = apiConnection;
  }

  public void getAllCountries(OnCountryListenerRead onCountryListenerRead) {
    final ResponseApi<List<ItemCountry>> responseApi = new ResponseApi<>();
    this.connection.createConnection();
    CountryEndpoint endpoint = this.connection.getConnection().create(CountryEndpoint.class);
    Call<ResponseApi<List<ItemCountry>>> responseApiCall = endpoint.getAllCountries();

    responseApiCall.enqueue(new Callback<ResponseApi<List<ItemCountry>>>() {
      @Override
      public void onResponse(Call<ResponseApi<List<ItemCountry>>> call,
          Response<ResponseApi<List<ItemCountry>>> response) {
        if (response.isSuccessful()) {
          ResponseApi<List<ItemCountry>> respuesta = response.body();
          responseApi.setSuccess(respuesta.getSuccess());
          responseApi.setMessage(respuesta.getMessage());
          responseApi.setData(respuesta.getData());
          onCountryListenerRead.onGetAllCountriesSuccess(responseApi);

        }
      }

      @Override
      public void onFailure(Call<ResponseApi<List<ItemCountry>>> call, Throwable t) {

        onCountryListenerRead.onGetAllCountriesFailure(t.getMessage());
      }
    });
  }
}
