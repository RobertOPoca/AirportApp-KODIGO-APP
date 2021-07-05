package com.kodigo.airportapp.service;

import retrofit2.Retrofit;

public interface ApiConnection {

  void createConnection();

  Retrofit getConnection();
}
