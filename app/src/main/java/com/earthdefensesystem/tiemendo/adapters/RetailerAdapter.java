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

import java.util.ArrayList;
import java.util.List;

//public class RetailerAdapter  extends RecyclerView.Adapter<RetailerAdapter.RetailerViewHolder>
//        implements Filterable {
//    private List<Client> retailerList;
//    private List<Client> retailerListFiltered;
//    private Context context;
//
//    public  RetailerAdapter(Context context, List<Retailer> retailerList){
//        this.context = context;
//        this.retailerList = retailerList;
//        this.retailerListFiltered = retailerList;
//
//    }
//
//    @Override
//    public Filter getFilter() {
//        return new Filter() {
//            @Override
//            protected FilterResults performFiltering(CharSequence charSequence) {
//                String charString = charSequence.toString();
//                if (charString.isEmpty()) {
//                    retailerListFiltered = retailerList;
//                } else {
//                    List<Retailer> filteredList = new ArrayList<>();
//                    for (Retailer row : retailerList) {
//
//                        // name match condition. this might differ depending on your requirement
//                        // here we are looking for name or phone number match
//                        if (row.getName().toLowerCase().contains(charString.toLowerCase())) {
//                            filteredList.add(row);
//                        }
//                    }
//
//                    retailerListFiltered = filteredList;
//                }
//
//                FilterResults filterResults = new FilterResults();
//                filterResults.values = retailerListFiltered;
//                return filterResults;
//            }
//
//            @Override
//            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
//                retailerListFiltered = (ArrayList<Retailer>) filterResults.values;
//                notifyDataSetChanged();
//            }
//        };
//    }
//
//    class RetailerViewHolder extends RecyclerView.ViewHolder{
//        public final View mView;
//
//        TextView retailerName;
//
//        RetailerViewHolder(View itemView){
//            super(itemView);
//            mView=itemView;
//
//            retailerName = mView.findViewById(R.id.retailer_name);
//
//        }
//
//    }
//
//    @NonNull
//    @Override
//    public RetailerViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
//        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
//        View view = layoutInflater.inflate(R.layout.retailer_row_item, viewGroup, false);
//        return new RetailerViewHolder(view);
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull RetailerViewHolder viewHolder, int i) {
//        viewHolder.retailerName.setText(retailerList.get(i).getRetailercontact().getName());
//
//    }
//
//    @Override
//    public int getItemCount() {
//        return retailerList.size();
//    }
//}