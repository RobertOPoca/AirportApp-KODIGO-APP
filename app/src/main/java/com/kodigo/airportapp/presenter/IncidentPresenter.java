package com.kodigo.airportapp.presenter;

import com.kodigo.airportapp.contract.IncidentContract;
import com.kodigo.airportapp.interactor.IncidentInteractor;
import com.kodigo.airportapp.item.ItemIncident;
import com.kodigo.airportapp.model.Incident;
import com.kodigo.airportapp.response.ResponseApi;

import java.util.List;

public class IncidentPresenter implements IncidentContract.Presenter,
    IncidentContract.OnIncidentListener {


  private IncidentContract.View mIncidentView;
  private IncidentContract.Model mIncidentInteractor;

  public IncidentPresenter(IncidentContract.View mIncidentView) {
    this.mIncidentView = mIncidentView;
    this.mIncidentInteractor = new IncidentInteractor(this);
  }

  @Override
  public void showIncidentList(Integer id) {
    this.mIncidentInteractor.getIncidentList(id);
  }

  @Override
  public void createIncident(Incident incident) {
    this.mIncidentInteractor.createIncident(incident);
  }

  @Override
  public void onGetAllIncidentSuccess(ResponseApi<?> responseApi) {

    this.mIncidentView.incidentList((ResponseApi<List<ItemIncident>>) responseApi);
  }

  @Override
  public void onGetAllIncidentFailure(String message) {

  }

  @Override
  public void onCreateIncidentSuccess(ResponseApi<?> responseApi) {
    this.mIncidentView.saveIncidentSuccess(responseApi.getMessage());
  }

  @Override
  public void onCreateIncidentFailure(String message) {

  }
}
