package com.kodigo.airportapp.interactor;

import com.kodigo.airportapp.contract.IncidentContract;
import com.kodigo.airportapp.model.Incident;
import com.kodigo.airportapp.service.ApiAirport;
import com.kodigo.airportapp.service.IncidentHttpService;

public class IncidentInteractor implements IncidentContract.Model {

  private IncidentContract.OnIncidentListener onIncidentListener;

  public IncidentInteractor(IncidentContract.OnIncidentListener onIncidentListener) {
    this.onIncidentListener = onIncidentListener;
  }

  @Override
  public void getIncidentList(Integer id) {

    IncidentHttpService incidentHttpService = new IncidentHttpService(new ApiAirport());
    incidentHttpService.getAllIncidents(onIncidentListener, id);
  }

  @Override
  public void createIncident(Incident incident) {

    IncidentHttpService incidentHttpService = new IncidentHttpService(new ApiAirport());
    incidentHttpService.createIncident(onIncidentListener, incident);
  }
}
