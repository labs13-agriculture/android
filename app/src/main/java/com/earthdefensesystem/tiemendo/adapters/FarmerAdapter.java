package com.earthdefensesystem.tiemendo.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.earthdefensesystem.tiemendo.R;
import com.earthdefensesystem.tiemendo.model.Farmer;
import com.earthdefensesystem.tiemendo.model.Retailer;

import java.util.ArrayList;
import java.util.List;

public class FarmerAdapter  extends RecyclerView.Adapter<FarmerAdapter.FarmerViewHolder>
        implements Filterable {
    private List<Farmer> farmerList;
    private List<Farmer> farmerListFiltered;
    private FarmerAdapterListener listener;
    private Context context;

    public  FarmerAdapter(Context context, List<Farmer> farmerList, FarmerAdapterListener listener){
        this.context = context;
        this.farmerList = farmerList;
        this.farmerListFiltered = farmerList;
        this.listener = listener;

    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()) {
                    farmerListFiltered = farmerList;
                } else {
                    List<Farmer> filteredList = new ArrayList<>();
                    for (Farmer row : farmerList) {

                        // name match condition. this might differ depending on your requirement
                        // here we are looking for name or phone number match
                        if (row.getFarmercontact().getName().toLowerCase().contains(charString.toLowerCase())) {
                            filteredList.add(row);
                        }
                    }

                    farmerListFiltered = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = farmerListFiltered;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                farmerListFiltered = (ArrayList<Farmer>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    class FarmerViewHolder extends RecyclerView.ViewHolder{

        TextView farmerName;

        FarmerViewHolder(View itemView){
            super(itemView);

            farmerName = itemView.findViewById(R.id.farmer_name);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onFarmerSelected(farmerListFiltered.get(getAdapterPosition()));
                }
            });



        }

    }

    @NonNull
    @Override
    public FarmerViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        View view = layoutInflater.inflate(R.layout.farmer_row_item, viewGroup, false);
        return new FarmerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FarmerViewHolder viewHolder, int i) {
        viewHolder.farmerName.setText(farmerList.get(i).getFarmercontact().getName());

    }

    @Override
    public int getItemCount() {
        return farmerList.size();
    }

    public interface FarmerAdapterListener {
        void onFarmerSelected(Farmer farmer);
    }
}
