package com.kodigo.airportapp.service;

import com.kodigo.airportapp.contract.FlightContract;
import com.kodigo.airportapp.endpoints.FlightEndpoint;
import com.kodigo.airportapp.item.ItemFlight;
import com.kodigo.airportapp.model.Flight;
import com.kodigo.airportapp.response.ResponseApi;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FlightHttpService {

  private ApiConnection connection;

  public FlightHttpService(ApiConnection apiConnection) {
    this.connection = apiConnection;
  }

  public void getAllFlights(FlightContract.OnFlightListener flightListener) {
    final ResponseApi<List<ItemFlight>> responseApi = new ResponseApi<>();
    this.connection.createConnection();
    FlightEndpoint endpoint = this.connection.getConnection().create(FlightEndpoint.class);
    Call<ResponseApi<List<ItemFlight>>> responseApiCall = endpoint.getAllFlights();
    responseApiCall.enqueue(new Callback<ResponseApi<List<ItemFlight>>>() {
      @Override
      public void onResponse(Call<ResponseApi<List<ItemFlight>>> call,
          Response<ResponseApi<List<ItemFlight>>> response) {
        if (response.isSuccessful()) {
          ResponseApi<List<ItemFlight>> respuesta = response.body();
          responseApi.setSuccess(respuesta.getSuccess());
          responseApi.setMessage(respuesta.getMessage());
          responseApi.setData(respuesta.getData());
          flightListener.onGetAllFlightsSuccess(responseApi);
        }
      }
      @Override
      public void onFailure(Call<ResponseApi<List<ItemFlight>>> call, Throwable t) {
        flightListener.onGetAllFlightsFailure(t.getMessage());
      }
    });
  }

  public void createFlight(FlightContract.OnFlightListener onListener, Flight flight) {
    final ResponseApi<ItemFlight> responseApi = new ResponseApi<>();
    this.connection.createConnection();
    FlightEndpoint endpoint = this.connection.getConnection().create(FlightEndpoint.class);
    Call<ResponseApi<ItemFlight>> responseApiCall = endpoint.createFlight(flight);
    responseApiCall.enqueue(new Callback<ResponseApi<ItemFlight>>() {
      @Override
      public void onResponse(Call<ResponseApi<ItemFlight>> call,
          Response<ResponseApi<ItemFlight>> response) {
        if (response.isSuccessful()) {
          ResponseApi<ItemFlight> respuesta = response.body();
          responseApi.setSuccess(respuesta.getSuccess());
          responseApi.setMessage(respuesta.getMessage());
          responseApi.setData(respuesta.getData());
          onListener.onCreateFlightSuccess(responseApi);
        }
      }

      @Override
      public void onFailure(Call<ResponseApi<ItemFlight>> call, Throwable t) {
        onListener.onCreateFlightFailure(t.getMessage());
      }
    });
  }

  public void getFlightById(FlightContract.OnFlightListener onFlightListener, Integer id) {
    final ResponseApi<ItemFlight> responseApi = new ResponseApi<>();
    this.connection.createConnection();
    FlightEndpoint endpoint = this.connection.getConnection().create(FlightEndpoint.class);
    Call<ResponseApi<ItemFlight>> responseApiCall = endpoint.getFlightById(id);
    responseApiCall.enqueue(new Callback<ResponseApi<ItemFlight>>() {
      @Override
      public void onResponse(Call<ResponseApi<ItemFlight>> call,
          Response<ResponseApi<ItemFlight>> response) {
        if (response.isSuccessful()) {
          ResponseApi<ItemFlight> respuesta = response.body();
          responseApi.setSuccess(respuesta.getSuccess());
          responseApi.setMessage(respuesta.getMessage());
          responseApi.setData(respuesta.getData());
          onFlightListener.onGetFlightSuccess(responseApi);
        }
      }

      @Override
      public void onFailure(Call<ResponseApi<ItemFlight>> call, Throwable t) {
        onFlightListener.onGetFlightFailure(t.getMessage());
      }
    });
  }

  public void updateFlight(FlightContract.OnFlightListener onFlightListener, Flight flight) {
    final ResponseApi<ItemFlight> responseApi = new ResponseApi<>();
    this.connection.createConnection();
    FlightEndpoint endpoint = this.connection.getConnection().create(FlightEndpoint.class);
    Call<ResponseApi<ItemFlight>> responseApiCall = endpoint.updateFlight(flight);
    responseApiCall.enqueue(new Callback<ResponseApi<ItemFlight>>() {
      @Override
      public void onResponse(Call<ResponseApi<ItemFlight>> call,
          Response<ResponseApi<ItemFlight>> response) {
        if (response.isSuccessful()) {
          ResponseApi<ItemFlight> respuesta = response.body();
          responseApi.setSuccess(respuesta.getSuccess());
          responseApi.setMessage(respuesta.getMessage());
          responseApi.setData(respuesta.getData());
          onFlightListener.onUpdateFlightSuccess(responseApi);
        }
      }
      @Override
      public void onFailure(Call<ResponseApi<ItemFlight>> call, Throwable t) {
        onFlightListener.onUpdateFlightFailure(t.getMessage());
      }
    });
  }
}
