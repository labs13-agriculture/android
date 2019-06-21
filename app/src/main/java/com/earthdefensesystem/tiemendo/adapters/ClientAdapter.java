package com.earthdefensesystem.tiemendo.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.earthdefensesystem.tiemendo.R;
import com.earthdefensesystem.tiemendo.model.Client;

import java.util.ArrayList;
import java.util.List;

public class ClientAdapter extends RecyclerView.Adapter<ClientAdapter.ClientViewHolder> implements Filterable {

    private List<Client> dataList;
    private List<Client> filteredDataList;
    private ClientAdapterListener listener;

    public ClientAdapter(List<Client> dataList, ClientAdapterListener listener) {
        this.dataList = dataList;
        this.filteredDataList = dataList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ClientViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        View view = layoutInflater.inflate(R.layout.farmer_row_item, viewGroup, false);
        return new ClientViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ClientViewHolder holder, int i) {
        holder.farmerName.setText(dataList.get(i).getFirstName());


    }

    @Override
    public Filter getFilter() {

        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {

                String charString = charSequence.toString();

                if (charString.isEmpty()) {

                    filteredDataList = dataList;
                } else {

                    List<Client> filteredList = new ArrayList<>();

                    for (Client androidVersion : dataList) {

                        if (androidVersion.getFirstName().toLowerCase().contains(charString)) {

                            filteredList.add(androidVersion);
                        }
                    }

                    filteredDataList = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = filteredDataList;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                filteredDataList = (List<Client>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    class ClientViewHolder extends RecyclerView.ViewHolder {
        TextView farmerName;

        ClientViewHolder(View itemView) {
            super(itemView);
            farmerName = itemView.findViewById(R.id.farmer_name);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onClientSelected(dataList.get(getAdapterPosition()));
                }
            });

        }
    }

    public interface ClientAdapterListener {
        void onClientSelected(Client client);
    }
}
