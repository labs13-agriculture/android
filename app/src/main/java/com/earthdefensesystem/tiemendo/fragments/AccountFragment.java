package com.earthdefensesystem.tiemendo.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.earthdefensesystem.tiemendo.R;

public class AccountFragment extends Fragment {
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

        return view;
    }
}
