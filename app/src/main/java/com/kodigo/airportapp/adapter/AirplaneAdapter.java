package com.kodigo.airportapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.kodigo.airportapp.R;
import com.kodigo.airportapp.item.ItemAirplane;

import java.util.List;

public class AirplaneAdapter extends RecyclerView.Adapter<AirplaneAdapter.AirplaneViewHolder> {

  Context context;
  List<ItemAirplane> itemAirplaneList;
  private SelectedItem selectedItem;

  public AirplaneAdapter(Context context, List<ItemAirplane> itemAirplaneList,
      SelectedItem selectedItem) {
    this.context = context;
    this.itemAirplaneList = itemAirplaneList;
    this.selectedItem = selectedItem;
  }

  @NonNull
  @Override
  public AirplaneViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

    View view = LayoutInflater.from(context).inflate(R.layout.airplane_item, parent, false);
    return new AirplaneViewHolder(view);
  }

  @Override
  public void onBindViewHolder(AirplaneViewHolder holder, final int position) {

    holder.model.setText(itemAirplaneList.get(position).getModel());
    holder.reach.setText(itemAirplaneList.get(position).getReach().toString());

  }

  @Override
  public int getItemCount() {
    return itemAirplaneList.size();
  }

  public interface SelectedItem {

    void selectedItem(ItemAirplane itemAirplane);
  }

  public class AirplaneViewHolder extends RecyclerView.ViewHolder {

    TextView model;
    TextView reach;

    public AirplaneViewHolder(@NonNull View itemView) {
      super(itemView);
      model = itemView.findViewById(R.id.modelAirplane);
      reach = itemView.findViewById(R.id.reachAirplane);
      itemView.setOnClickListener(
          v -> selectedItem.selectedItem(itemAirplaneList.get(getAdapterPosition())));

    }
  }

}


