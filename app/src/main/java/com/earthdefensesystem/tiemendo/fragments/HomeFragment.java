package com.earthdefensesystem.tiemendo.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.earthdefensesystem.tiemendo.DashboardActivity;
import com.earthdefensesystem.tiemendo.FarmerSearchActivity;
import com.earthdefensesystem.tiemendo.LoginActivity;
import com.earthdefensesystem.tiemendo.OrganizationSearchActivity;
import com.earthdefensesystem.tiemendo.R;
import com.earthdefensesystem.tiemendo.RetailerSearchActivity;

public class HomeFragment extends Fragment {
    public static final String TAG = "AccountFragment";

    private Button btnFarmerSearch;
    private Button btnRetailerSearch;
    private Button btnOrganizationSearch;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_frag_layout, container, false);
        btnFarmerSearch = view.findViewById(R.id.farmer_search_btn);
        btnRetailerSearch = view.findViewById(R.id.retailer_search_btn);
        btnOrganizationSearch = view.findViewById(R.id.organizaiton_search_btn);

        btnRetailerSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), RetailerSearchActivity.class);
                startActivity(i);
            }
        });
        btnFarmerSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent o = new Intent(getActivity(), FarmerSearchActivity.class);
                startActivity(o);
            }
        });
        btnOrganizationSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent p = new Intent(getActivity(), OrganizationSearchActivity.class);
                startActivity(p);
            }
        });

        return view;
    }

}
