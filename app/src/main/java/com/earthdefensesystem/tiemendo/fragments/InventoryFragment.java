package com.earthdefensesystem.tiemendo.fragments;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.earthdefensesystem.tiemendo.FarmerDetailsActivity;
import com.earthdefensesystem.tiemendo.R;
import com.earthdefensesystem.tiemendo.adapters.InventoryAdapter;
import com.earthdefensesystem.tiemendo.adapters.TransactionAdapter;
import com.earthdefensesystem.tiemendo.model.ItemType;
import com.earthdefensesystem.tiemendo.model.Transaction;
import com.earthdefensesystem.tiemendo.network.RetrofitClientInstance;
import com.earthdefensesystem.tiemendo.network.TiemeService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.Context.MODE_PRIVATE;

public class InventoryFragment extends Fragment {
    RecyclerView recyclerView;
    InventoryAdapter inventoryAdapter;
    TiemeService service;
    public static final String TAG = "FragmentProblem";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.inventory_frag_layout, container, false);
        recyclerView = view.findViewById(R.id.inventory_recyclerView);

        service = RetrofitClientInstance.getRetrofitInstance().create(TiemeService.class);

        SharedPreferences sharedPreferences = this.getActivity().getSharedPreferences("mysettings", MODE_PRIVATE);
        final String accessToken = sharedPreferences.getString("mystring", "N/A");

        Call<List<ItemType>> transactionCall = service.getItemTypes("Bearer " + accessToken);

        transactionCall.enqueue(new Callback<List<ItemType>>() {
            @Override
            public void onResponse(Call<List<ItemType>> call, Response<List<ItemType>> response) {
                if (response.isSuccessful()) {
                    recyclerView.setHasFixedSize(true);

                    recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

                    inventoryAdapter = new InventoryAdapter(response.body());

                    recyclerView.setAdapter(inventoryAdapter);
                } else {
                    Log.e(TAG, "Oh no!" + response.code());
                }
            }

            @Override
            public void onFailure(Call<List<ItemType>> call, Throwable t) {

                Log.e(TAG, "Something went way wrong");
            }
        });


        return view;
    }

}
