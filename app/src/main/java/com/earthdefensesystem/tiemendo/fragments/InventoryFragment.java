package com.earthdefensesystem.tiemendo.fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.PopupWindow;

import com.earthdefensesystem.tiemendo.FarmerDetailsActivity;
import com.earthdefensesystem.tiemendo.FarmerSearchActivity;
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
    ImageButton addItem;
    EditText inventoryQuantity, inventoryName;
    Button saveItemBtn;

    private PopupWindow inventoryPopup;
    public static final String TAG = "FragmentProblem";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.inventory_frag_layout, container, false);
        recyclerView = view.findViewById(R.id.inventory_recyclerView);
        addItem = view.findViewById(R.id.imageButton);

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

        addItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LayoutInflater layoutInflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View customView = layoutInflater.inflate(R.layout.inventory_popup, null);
                inventoryPopup = new PopupWindow(customView, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                inventoryPopup.showAtLocation(recyclerView, Gravity.CENTER, 0, 0);
                inventoryPopup.setFocusable(true);
                inventoryPopup.update();

                inventoryName = customView.findViewById(R.id.popup_item_name);
                inventoryQuantity = customView.findViewById(R.id.popup_item_quantity);
                saveItemBtn = customView.findViewById(R.id.closeInventoryPopupBtn);

                saveItemBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        final String itemName = inventoryName.getText().toString();
                        final String itemQuantityStr = inventoryQuantity.getText().toString();
                        int itemQuantity=Integer.parseInt(itemQuantityStr);

                        ItemType newItem = new ItemType(null,itemName,true,itemQuantity);

                        Call<ItemType> addItemCall = service.addItemType("Bearer " + accessToken, newItem);
                        addItemCall.enqueue(new Callback<ItemType>() {
                            @Override
                            public void onResponse(Call<ItemType> call, Response<ItemType> response) {
                                if(response.isSuccessful()) {
                                    Log.e(TAG, "post submitted to API." + response.body().toString());
                                    inventoryPopup.dismiss();
                                    inventoryAdapter.notifyDataSetChanged();
                                } else {
                                    Log.e(TAG, "it's MESSING UP AGAIN" + response.code());
                                }
                            }

                            @Override
                            public void onFailure(Call<ItemType> call, Throwable t) {
                                Log.e(TAG, "Unable to submit post to API." + t.getCause());
                            }
                        });

                    }
                });
            }
        });

        return view;
    }

}
