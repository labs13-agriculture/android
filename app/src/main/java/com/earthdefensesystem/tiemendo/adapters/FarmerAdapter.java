package com.earthdefensesystem.tiemendo.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.earthdefensesystem.tiemendo.R;
import com.earthdefensesystem.tiemendo.model.Farmer;

import java.util.ArrayList;
import java.util.List;

public class FarmerAdapter extends RecyclerView.Adapter<FarmerAdapter.MyViewHolder>
        implements Filterable {
    private Context context;
    private List<Farmer> farmerList;
    private List<Farmer> filteredFarmerList;
    private FarmerAdapter.RetailerAdapterListener listener;

    public class MyViewHolder extends RecyclerView.ViewHolder{
        public TextView name, address;
        public ImageView samplepic;

        public MyViewHolder(View view) {
            super(view);
            name = view.findViewById(R.id.name);
            address = view.findViewById(R.id.phone);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // send selected contact in callback
                    listener.onFarmerSelected(filteredFarmerList.get(getAdapterPosition()));
                }
            });
        }
    }


    public interface FarmerAdapterListener {
        void onRetailerSelected(Farmer farmer);
    }

    public FarmerAdapter(Context context, List<Farmer> retailerList, FarmerAdapter.FarmerAdapterListener listener) {
        this.context = context;
        this.farmerList = retailerList;
        this.filteredFarmerList = retailerList;
        this.listener = listener;
    }
    @Override
    public FarmerAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.user_row_item, parent, false);

        return new FarmerAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(FarmerAdapter.MyViewHolder holder, final int position) {
        final Farmer retailer = filteredFarmerList.get(position);
        holder.name.setText(retailer.getFarmercontact().getName());
        holder.address.setText(retailer.getFarmercontact().getPhone());

    }

    @Override
    public int getItemCount() {
        return filteredFarmerList.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()) {
                    filteredFarmerList = farmerList;
                } else {
                    List<Farmer> filteredList = new ArrayList<>();
                    for (Farmer row : farmerList) {

                        // name match condition. this might differ depending on your requirement
                        // here we are looking for name or phone number match
                        if (row.getFarmercontact().getName().toLowerCase().contains(charString.toLowerCase()) || row.getFarmercontact().getPhone().equals(charSequence)) {
                            filteredList.add(row);
                        }
                    }

                    filteredFarmerList = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = filteredFarmerList;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                filteredFarmerList = (ArrayList<Farmer>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }
}
