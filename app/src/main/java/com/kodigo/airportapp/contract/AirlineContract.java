package com.kodigo.airportapp.contract;

import com.kodigo.airportapp.item.ItemAirline;
import com.kodigo.airportapp.listener.OnAirlineListenerRead;
import com.kodigo.airportapp.response.ResponseApi;

import java.util.List;

public interface AirlineContract {

  interface View {

    void showAirline(ResponseApi<List<ItemAirline>> airlines);

    void onGetAllAirlineFailure(String message);
  }

  interface Presenter {

    void showAirlineP();
  }

  interface Model {

    void getAllAirlines();
  }

  interface OnAirlineListener extends OnAirlineListenerRead {

  }
}
