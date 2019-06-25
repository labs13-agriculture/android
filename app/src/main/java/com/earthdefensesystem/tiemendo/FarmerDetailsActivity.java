package com.earthdefensesystem.tiemendo;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.earthdefensesystem.tiemendo.adapters.ClientAdapter;
import com.earthdefensesystem.tiemendo.adapters.InstallmentAdapter;
import com.earthdefensesystem.tiemendo.adapters.TransactionAdapter;
import com.earthdefensesystem.tiemendo.model.Client;
import com.earthdefensesystem.tiemendo.model.Installment;
import com.earthdefensesystem.tiemendo.model.Transaction;
import com.earthdefensesystem.tiemendo.network.NetworkAdapter;
import com.earthdefensesystem.tiemendo.network.RetrofitClientInstance;
import com.earthdefensesystem.tiemendo.network.TiemeService;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FarmerDetailsActivity extends AppCompatActivity {
    private TextView farmerName, farmerEmail;
    private RecyclerView recyclerView;
    private List<Transaction> transactionList;
    private List<Installment> installmentList;
    private TransactionAdapter tAdapter;
    private InstallmentAdapter iAdapter;
    private EditText paymentAmount, paymentDate;
    private TiemeService service;
    private Context context;
    private PopupWindow detailsWindow;

    public static final String TAG = "Whoopsie";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_farmer_details);

        farmerName = findViewById(R.id.farmer_detail_name);
        farmerEmail = findViewById(R.id.farmer_detail_email);
        paymentAmount = findViewById(R.id.farmer_payment_amount_edittext);
        paymentDate =findViewById(R.id.farmer_payment_date_edittext);
        context = this;

        Intent intent = getIntent();
        final Client farmer = (Client)intent.getSerializableExtra("farmerObject");


        service = RetrofitClientInstance.getRetrofitInstance().create(TiemeService.class);

        SharedPreferences sharedPreferences = getSharedPreferences("mysettings", MODE_PRIVATE);
        final String accessToken = sharedPreferences.getString("mystring", "N/A");

        farmerName.setText(farmer.getFirstName());
        farmerEmail.setText(farmer.getAddress());

        String farmerId = farmer.getId().toString();
        Log.e(TAG, farmerId);

        Call<List<Transaction>> transactionCall = service.getTransactionById(farmerId, "Bearer " + accessToken);
        transactionCall.enqueue(new Callback<List<Transaction>>() {
            @Override
            public void onResponse(Call<List<Transaction>> call, Response<List<Transaction>> response) {
                if (response.isSuccessful()){
                    generateTransactionList(response.body());
                    transactionList = response.body();
                }else{
                    Log.e(TAG,"Auggh!"+ response.code());
                }
            }

            @Override
            public void onFailure(Call<List<Transaction>> call, Throwable t) {
                Toast toast = Toast.makeText(
                        context, "Something way wrong happened",
                        Toast.LENGTH_SHORT);
                toast.show();
            }
        });

        Call<List<Installment>> installmentCall = service.getInstallmentbyClientId(farmerId, "Bearer "+ accessToken);
        installmentCall.enqueue(new Callback<List<Installment>>() {
            @Override
            public void onResponse(Call<List<Installment>> call, Response<List<Installment>> response) {
                if (response.isSuccessful()){
                    generateInstallmentList(response.body());
                    installmentList = response.body();

                } else{
                    Log.e(TAG, "Hoo Boy" + response.body());
                }
            }

            @Override
            public void onFailure(Call<List<Installment>> call, Throwable t) {
                Toast toast = Toast.makeText(
                        context, "Something way wrong happened",
                        Toast.LENGTH_SHORT);
                toast.show();
            }
        });






//        List<Installment> farmerInstallments = farmer.getDateofbirth();
//
//
//        for (int i = 0; i <farmerInstallments.size(); i++){
//            TextView tv = new TextView(context);
//            final Installment getInstallment = farmerInstallments.get(i);
//            tv.setText(getInstallment.getDatepaid() + "  " + getInstallment.getAmountpaid());
//            tv.setTextSize(18);
//            linearLayout.addView(tv);

//        }

//        floatingActionButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                LayoutInflater layoutInflater = (LayoutInflater) FarmerDetailsActivity.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//                View customView = layoutInflater.inflate(R.layout.farmer_detail_popup,null);
//                detailsWindow = new PopupWindow(customView, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
//                detailsWindow.showAtLocation(linearLayout, Gravity.CENTER, 0, 0);
//                detailsWindow.setFocusable(true);
//                detailsWindow.update();
//
//                final String paymentDateStr = paymentAmount.getText().toString();
//                final String paymentAmountStr = paymentDate.getText().toString();
//
//                new Thread(new Runnable() {
//                    @Override
//                    public void run() {
//
//                        JSONObject installmentJson = new JSONObject();
//                        JSONObject farmerJson = new JSONObject();
//                        try{
//                            installmentJson.put("amountPaid", paymentAmountStr);
//                            installmentJson.put("datePaid", paymentDateStr);
//
//                            farmerJson.put("installments", farmerJson);
//                        } catch (JSONException e) {
//                        }
//                        SharedPreferences sharedPreferences = getSharedPreferences("mysettings", MODE_PRIVATE);
//                        String accessToken = sharedPreferences.getString("mystring", "N/A");
//
//                        Map<String, String> headerProperties = new HashMap<>();
//                        headerProperties.put("Authorization", "Bearer " + accessToken);
//
//                        try {
//                            NetworkAdapter.httpRequest("https://tieme-ndo-backend.herokuapp.com/new-installment/1","POST", farmerJson, headerProperties);
//                        } catch (IOException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                }).start();
//                detailsWindow.dismiss();
//            }
//        });
    }

    private void generateTransactionList(List<Transaction> transactionsDataList) {
        recyclerView = findViewById(R.id.farmer_transaction_recyclerview);
        recyclerView.setHasFixedSize(true);

        tAdapter = new TransactionAdapter(transactionsDataList);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(FarmerDetailsActivity.this);

        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setAdapter(tAdapter);
    }
    private void generateInstallmentList(List<Installment> installmentsDataList) {
        recyclerView = findViewById(R.id.farmer_installment_recyclerview);
        recyclerView.setHasFixedSize(true);

        iAdapter = new InstallmentAdapter(installmentsDataList);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(FarmerDetailsActivity.this);

        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setAdapter(iAdapter);
    }
}
