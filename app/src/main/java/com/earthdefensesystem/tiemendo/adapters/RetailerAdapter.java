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

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.earthdefensesystem.tiemendo.R;
import com.earthdefensesystem.tiemendo.model.Retailer;

import java.util.ArrayList;
import java.util.List;

public class RetailerAdapter  extends RecyclerView.Adapter<RetailerAdapter.MyViewHolder>
        implements Filterable {
    private Context context;
    private List<Retailer> retailerList;
    private List<Retailer> filteredRetailerList;
    private RetailerAdapterListener listener;

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
                    listener.onRetailerSelected(filteredRetailerList.get(getAdapterPosition()));
                }
            });
        }
    }


    public interface RetailerAdapterListener {
        void onRetailerSelected(Retailer retailer);
    }

    public RetailerAdapter(Context context, List<Retailer> retailerList, RetailerAdapterListener listener) {
        this.context = context;
        this.retailerList = retailerList;
        this.filteredRetailerList = retailerList;
        this.listener = listener;
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.user_row_item, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        final Retailer retailer = filteredRetailerList.get(position);
        holder.name.setText(retailer.getName());
        holder.address.setText(retailer.getStartyear());

    }

    @Override
    public int getItemCount() {
        return filteredRetailerList.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()) {
                    filteredRetailerList = retailerList;
                } else {
                    List<Retailer> filteredList = new ArrayList<>();
                    for (Retailer row : retailerList) {

                        // name match condition. this might differ depending on your requirement
                        // here we are looking for name or phone number match
                        if (row.getName().toLowerCase().contains(charString.toLowerCase()) || row.getStartyear().equals(charSequence)) {
                            filteredList.add(row);
                        }
                    }

                    filteredRetailerList = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = filteredRetailerList;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                filteredRetailerList = (ArrayList<Retailer>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }
}