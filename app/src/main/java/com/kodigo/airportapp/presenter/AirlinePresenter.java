package com.kodigo.airportapp.presenter;

import com.kodigo.airportapp.contract.AirlineContract;
import com.kodigo.airportapp.interactor.AirlineInteractor;
import com.kodigo.airportapp.item.ItemAirline;
import com.kodigo.airportapp.response.ResponseApi;

import java.util.List;

public class AirlinePresenter implements AirlineContract.Presenter,
    AirlineContract.OnAirlineListener {

  private AirlineContract.View mAirlineView;
  private AirlineContract.Model mAirlineInteractor;

  public AirlinePresenter(AirlineContract.View view) {
    this.mAirlineView = view;
    this.mAirlineInteractor = new AirlineInteractor(this);
    showAirlineP();
  }

  @Override
  public void showAirlineP() {
    this.mAirlineInteractor.getAllAirlines();

  }

  @Override
  public void onGetAllAirlineSuccess(ResponseApi<?> responseApi) {

    this.mAirlineView.showAirline((ResponseApi<List<ItemAirline>>) responseApi);
  }

  @Override
  public void onGetAllAirlineFailure(String message) {
    this.mAirlineView.onGetAllAirlineFailure(message);
  }
}
