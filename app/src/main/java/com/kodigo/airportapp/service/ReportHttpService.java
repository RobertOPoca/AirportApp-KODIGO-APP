package com.kodigo.airportapp.service;

import com.kodigo.airportapp.listener.OnReportListenerRead;
import com.kodigo.airportapp.listener.OnReportListenerWrite;
import com.kodigo.airportapp.endpoints.FlightEndpoint;
import com.kodigo.airportapp.item.ItemFlight;
import com.kodigo.airportapp.response.ResponseApi;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ReportHttpService {

  private ApiConnection connection;

  public ReportHttpService(ApiConnection apiConnection) {
    this.connection = apiConnection;
  }

  public void loadFlightsByExcel(OnReportListenerRead onReportListenerRead) {
    final ResponseApi<ItemFlight> responseApi = new ResponseApi<>();
    this.connection.createConnection();
    FlightEndpoint endpoint = this.connection.getConnection().create(FlightEndpoint.class);
    Call<ResponseApi<String>> responseApiCall = endpoint.loadExcel();
    responseApiCall.enqueue(new Callback<ResponseApi<String>>() {
      @Override
      public void onResponse(Call<ResponseApi<String>> call, Response<ResponseApi<String>> response) {
        if (response.isSuccessful()) {
          ResponseApi<String> respuesta = response.body();
          responseApi.setSuccess(respuesta.getSuccess());
          responseApi.setMessage(respuesta.getMessage());
          onReportListenerRead.onLoadExcelSuccess(responseApi);
        }
      }
      @Override
      public void onFailure(Call<ResponseApi<String>> call, Throwable t) {
        onReportListenerRead.onLoadExcelFailure(t.getMessage());
      }
    });
  }


  public void reportById(OnReportListenerWrite onReportListenerWrite, Integer id) {
    final ResponseApi<ItemFlight> responseApi = new ResponseApi<>();
    this.connection.createConnection();
    FlightEndpoint endpoint = this.connection.getConnection().create(FlightEndpoint.class);
    Call<ResponseApi<String>> responseApiCall = endpoint.reportById(id);
    responseApiCall.enqueue(new Callback<ResponseApi<String>>() {
      @Override
      public void onResponse(Call<ResponseApi<String>> call, Response<ResponseApi<String>> response) {
        if (response.isSuccessful()) {
          ResponseApi<String> respuesta = response.body();
          responseApi.setSuccess(respuesta.getSuccess());
          responseApi.setMessage(respuesta.getMessage());
          onReportListenerWrite.onReportByIdSuccess(responseApi);
        }
      }

      @Override
      public void onFailure(Call<ResponseApi<String>> call, Throwable t) {
        onReportListenerWrite.onReportByIdFailure(t.getMessage());
      }
    });
  }

  public void reportByDate(OnReportListenerWrite onReportListenerWrite, String date) {
    final ResponseApi<ItemFlight> responseApi = new ResponseApi<>();
    this.connection.createConnection();
    FlightEndpoint endpoint = this.connection.getConnection().create(FlightEndpoint.class);
    Call<ResponseApi<String>> responseApiCall = endpoint.reportByDate(date);
    responseApiCall.enqueue(new Callback<ResponseApi<String>>() {
      @Override
      public void onResponse(Call<ResponseApi<String>> call,
          Response<ResponseApi<String>> response) {
        if (response.isSuccessful()) {
          ResponseApi<String> respuesta = response.body();
          responseApi.setSuccess(respuesta.getSuccess());
          responseApi.setMessage(respuesta.getMessage());
          onReportListenerWrite.onReportByDateSuccess(responseApi);
        }
      }

      @Override
      public void onFailure(Call<ResponseApi<String>> call, Throwable t) {
        onReportListenerWrite.onReportByDateFailure(t.getMessage());
      }
    });
  }
}
