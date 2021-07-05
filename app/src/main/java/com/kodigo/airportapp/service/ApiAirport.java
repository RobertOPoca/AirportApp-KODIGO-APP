package com.kodigo.airportapp.service;

import com.kodigo.airportapp.utils.Parameter;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiAirport implements ApiConnection {

  private Retrofit connection;

  @Override
  public void createConnection() {
    this.connection = new Retrofit
        .Builder()
        .baseUrl(Parameter.HOST + Parameter.PORT)
        .addConverterFactory(GsonConverterFactory.create())
        .build();

  }

  @Override
  public Retrofit getConnection() {
    return this.connection;
  }
}
