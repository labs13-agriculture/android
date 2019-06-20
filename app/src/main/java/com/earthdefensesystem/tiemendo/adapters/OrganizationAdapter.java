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
import com.earthdefensesystem.tiemendo.model.Organization;

import java.util.ArrayList;
import java.util.List;

//public class OrganizationAdapter extends RecyclerView.Adapter<OrganizationAdapter.OrganizationViewHolder>
//        implements Filterable {
//    private List<Organization> organizationList;
//    private List<Organization> organizationListFiltered;
//    private Context context;
//
//    public  OrganizationAdapter(Context context, List<Organization> organizationList){
//        this.context = context;
//        this.organizationList = organizationList;
//        this.organizationListFiltered = organizationList;
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
//                    organizationListFiltered = organizationList;
//                } else {
//                    List<Organization> filteredList = new ArrayList<>();
//                    for (Organization row : organizationList) {
//
//                        // name match condition. this might differ depending on your requirement
//                        // here we are looking for name or phone number match
//                        if (row.getName().toLowerCase().contains(charString.toLowerCase())) {
//                            filteredList.add(row);
//                        }
//                    }
//
//                    organizationListFiltered = filteredList;
//                }
//
//                FilterResults filterResults = new FilterResults();
//                filterResults.values = organizationListFiltered;
//                return filterResults;
//            }
//
//            @Override
//            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
//                organizationListFiltered = (ArrayList<Organization>) filterResults.values;
//                notifyDataSetChanged();
//            }
//        };
//    }
//
//    class OrganizationViewHolder extends RecyclerView.ViewHolder{
//        public final View mView;
//
//        TextView organizationName;
//
//        OrganizationViewHolder(View itemView){
//            super(itemView);
//            mView=itemView;
//
//            organizationName = mView.findViewById(R.id.organization_name);
//
//        }
//
//    }
//
//    @NonNull
//    @Override
//    public OrganizationAdapter.OrganizationViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
//        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
//        View view = layoutInflater.inflate(R.layout.organization_row_item, viewGroup, false);
//        return new OrganizationAdapter.OrganizationViewHolder(view);
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull OrganizationAdapter.OrganizationViewHolder viewHolder, int i) {
//        viewHolder.organizationName.setText(organizationList.get(i).getName());
//
//    }
//
//    @Override
//    public int getItemCount() {
//        return organizationList.size();
//    }
//}