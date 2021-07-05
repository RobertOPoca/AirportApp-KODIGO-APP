package com.kodigo.airportapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.kodigo.airportapp.R;
import com.kodigo.airportapp.item.ItemIncident;

import java.util.List;

public class IncidentAdapter extends RecyclerView.Adapter<IncidentAdapter.IncidentViewHolder> {

  Context context;
  List<ItemIncident> itemIncidentList;
  private SelectedItem selectedItem;

  public IncidentAdapter(Context context, List<ItemIncident> itemIncidentList,
      SelectedItem selectedItem) {
    this.context = context;
    this.itemIncidentList = itemIncidentList;
    this.selectedItem = selectedItem;
  }

  @NonNull
  @Override
  public IncidentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

    View view = LayoutInflater.from(context).inflate(R.layout.incident_item, parent, false);
    return new IncidentViewHolder(view);
  }

  @Override
  public void onBindViewHolder(IncidentViewHolder holder, final int position) {

    holder.description.setText(itemIncidentList.get(position).getDescription());
    holder.time.setText(
        itemIncidentList.get(position).getDate() + " " + itemIncidentList.get(position).getTime());

  }

  @Override
  public int getItemCount() {
    return itemIncidentList.size();
  }

  public interface SelectedItem {

    void selectedItem(ItemIncident itemIncident);
  }

  public class IncidentViewHolder extends RecyclerView.ViewHolder {

    TextView description;
    TextView time;

    public IncidentViewHolder(@NonNull View itemView) {
      super(itemView);
      description = itemView.findViewById(R.id.incidentDesc);
      time = itemView.findViewById(R.id.incidentTime);
      itemView.setOnClickListener(
          v -> selectedItem.selectedItem(itemIncidentList.get(getAdapterPosition())));

    }
  }

}

