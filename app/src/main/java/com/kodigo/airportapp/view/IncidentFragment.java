package com.kodigo.airportapp.view;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import com.kodigo.airportapp.R;
import com.kodigo.airportapp.adapter.IncidentAdapter;
import com.kodigo.airportapp.contract.IncidentContract;
import com.kodigo.airportapp.item.ItemIncident;
import com.kodigo.airportapp.model.Incident;
import com.kodigo.airportapp.presenter.IncidentPresenter;
import com.kodigo.airportapp.response.ResponseApi;

import java.util.Calendar;
import java.util.List;


public class IncidentFragment extends Fragment implements IncidentContract.View,
    IncidentAdapter.SelectedItem, View.OnClickListener {

  @BindView(R.id.btnSaveIncident)
  Button btnSaveIncident;
  @BindView(R.id.txtIncidentDes)
  EditText txtIncidentDes;
  @BindView(R.id.txtIncidentTime)
  EditText txtIncidentTime;
  @BindView(R.id.txtIncidentDate)
  EditText txtIncidentDate;
  @BindView(R.id.txvFlightIncident)
  TextView txvNumberFlight;
  @BindView(R.id.btnIncidentTime)
  Button btnIncidentTime;
  @BindView(R.id.btnIncidentDate)
  Button btnIncidentDate;
  @BindView(R.id.incidentRecycler)
  RecyclerView incidentRecycler;

  private IncidentContract.Presenter incidentPresenter;
  private IncidentAdapter incidentAdapter;
  private Integer numberFlight;

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    View view = inflater.inflate(R.layout.fragment_incident, container, false);
    ButterKnife.bind(this, view);
    this.incidentPresenter = new IncidentPresenter(this);
    Bundle bundle = getArguments();
    if (bundle != null) {
      numberFlight = bundle.getInt("idFlight");
      getIncidentsByIdFlight(numberFlight);
      txvNumberFlight.setText(numberFlight.toString());
    }
    this.btnSaveIncident.setOnClickListener(this);
    this.btnIncidentTime.setOnClickListener(this);
    this.btnIncidentDate.setOnClickListener(this);
    return view;
  }

  private void save() {
    Incident incident = new Incident();
    incident.setDescription(txtIncidentDes.getText().toString());
    incident.setDateTime(
        txtIncidentDate.getText().toString() + " " + txtIncidentTime.getText().toString());
    incident.setIdIncident(1);
    incident.setIdFlight(numberFlight);
    saveIncident(incident);
  }

  private void showDate(EditText txt) {
    final Calendar c = Calendar.getInstance();

    DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(),
        (view, year, monthOfYear, dayOfMonth) -> txt
            .setText(year + "-" + (monthOfYear + 1) + "-" + dayOfMonth), c.get(Calendar.YEAR),
        c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH));
    datePickerDialog.show();
  }

  private void showTime(EditText txt) {
    final Calendar c = Calendar.getInstance();

    TimePickerDialog timePickerDialog = new TimePickerDialog(getActivity(),
        (view, hourOfDay, minute) -> txt.setText(hourOfDay + ":" + minute + ":00"),
        c.get(Calendar.HOUR_OF_DAY), c.get(Calendar.MINUTE), false);
    timePickerDialog.show();

  }

  private void setIncidentRecycler(List<ItemIncident> itemIncidentList) {

    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(),
        RecyclerView.VERTICAL, false);
    incidentRecycler.setLayoutManager(layoutManager);
    incidentAdapter = new IncidentAdapter(getContext(), itemIncidentList, this);
    incidentRecycler.setAdapter(incidentAdapter);

  }

  @Override
  public void selectedItem(ItemIncident itemIncident) {
    Toast.makeText(getActivity(), itemIncident.getDescription(), Toast.LENGTH_SHORT).show();

  }

  @Override
  public void getIncidentsByIdFlight(Integer id) {
    this.incidentPresenter.showIncidentList(id);
  }

  @Override
  public void incidentList(ResponseApi<List<ItemIncident>> incidents) {
    setIncidentRecycler(incidents.getData());
  }

  @Override
  public void saveIncident(Incident incident) {
    this.incidentPresenter.createIncident(incident);
  }

  @Override
  public void saveIncidentSuccess(String message) {
    Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    getIncidentsByIdFlight(numberFlight);
  }

  @Override
  public void onClick(View view) {
    switch (view.getId()) {
      case R.id.btnSaveIncident:
        save();
        break;

      case R.id.btnIncidentDate:
        showDate(txtIncidentDate);
        break;

      case R.id.btnIncidentTime:
        showTime(txtIncidentTime);
        break;
      default:
        break;
    }
  }
}