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

import com.earthdefensesystem.tiemendo.R;
import com.earthdefensesystem.tiemendo.adapters.InstallmentAdapter;
import com.earthdefensesystem.tiemendo.adapters.TransactionAdapter;
import com.earthdefensesystem.tiemendo.model.Installment;
import com.earthdefensesystem.tiemendo.model.Transaction;
import com.earthdefensesystem.tiemendo.network.RetrofitClientInstance;
import com.earthdefensesystem.tiemendo.network.TiemeService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.Context.MODE_PRIVATE;

public class InstallmentFragment extends Fragment {
    public static final String TAG = "ClientFragment";
    private RecyclerView recyclerView;
    private InstallmentAdapter installmentAdapter;
    private TiemeService service;
    private String farmerIdStr;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.client_installment_tab, container, false);
        recyclerView = view.findViewById(R.id.installment_tab_rv);

        service = RetrofitClientInstance.getRetrofitInstance().create(TiemeService.class);

        SharedPreferences sharedPreferences = this.getActivity().getSharedPreferences("mysettings", MODE_PRIVATE);
        final String accessToken = sharedPreferences.getString("mystring", "N/A");
        final String farmerIdStr= sharedPreferences.getString("farmerId", "N/A");


        Log.e(TAG, "clientid "+ farmerIdStr + accessToken);

        Call<List<Installment>> transactionCall = service.getInstallmentbyClientId(farmerIdStr, "Bearer " + accessToken);

        transactionCall.enqueue(new Callback<List<Installment>>() {
            @Override
            public void onResponse(Call<List<Installment>> call, Response<List<Installment>> response) {
                if (response.isSuccessful()){
                    recyclerView.setHasFixedSize(true);
                    recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                    installmentAdapter = new InstallmentAdapter((response.body()));
                    recyclerView.setAdapter(installmentAdapter);
                } else {
                    Log.e(TAG, "Oh no!" + response.code());
                }
            }

            @Override
            public void onFailure(Call<List<Installment>> call, Throwable t) {
                Log.e(TAG, "Something went way wrong");
            }
        });

        return view;
    }

}

