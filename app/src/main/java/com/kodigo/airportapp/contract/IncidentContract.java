package com.kodigo.airportapp.contract;

import com.kodigo.airportapp.item.ItemIncident;
import com.kodigo.airportapp.listener.OnIncidentListenerRead;
import com.kodigo.airportapp.listener.OnIncidentListenerWrite;
import com.kodigo.airportapp.model.Incident;
import com.kodigo.airportapp.response.ResponseApi;

import java.util.List;

public interface IncidentContract {

  interface View {

    void getIncidentsByIdFlight(Integer id);

    void incidentList(ResponseApi<List<ItemIncident>> incidents);

    void saveIncident(Incident incident);

    void saveIncidentSuccess(String message);
  }

  interface Presenter {

    void showIncidentList(Integer id);

    void createIncident(Incident incident);
  }

  interface Model {

    void getIncidentList(Integer id);

    void createIncident(Incident incident);
  }

  interface OnIncidentListener extends OnIncidentListenerRead, OnIncidentListenerWrite {

  }
}
