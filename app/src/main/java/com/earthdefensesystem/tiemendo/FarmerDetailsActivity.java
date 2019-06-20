package com.earthdefensesystem.tiemendo;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.earthdefensesystem.tiemendo.model.Client;
import com.earthdefensesystem.tiemendo.model.Installment;
import com.earthdefensesystem.tiemendo.network.NetworkAdapter;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FarmerDetailsActivity extends AppCompatActivity {
    private TextView farmerName, farmerEmail;
    private EditText paymentAmount, paymentDate;
    private LinearLayout linearLayout;
    private FloatingActionButton floatingActionButton;
    private Context context;
    private PopupWindow detailsWindow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_farmer_details);

        farmerName = findViewById(R.id.farmer_detail_name);
        farmerEmail = findViewById(R.id.farmer_detail_email);
        linearLayout = findViewById(R.id.installments_layout);
        floatingActionButton = findViewById(R.id.farmer_details_fab);
        paymentAmount = findViewById(R.id.farmer_payment_amount_edittext);
        paymentDate =findViewById(R.id.farmer_payment_date_edittext);
        context = this;

        Intent intent = getIntent();
        final Client farmer = (Client)intent.getSerializableExtra("farmerObject");

        farmerName.setText(farmer.getFirstName());
        farmerEmail.setText(farmer.getAddress());

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

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LayoutInflater layoutInflater = (LayoutInflater) FarmerDetailsActivity.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View customView = layoutInflater.inflate(R.layout.farmer_detail_popup,null);
                detailsWindow = new PopupWindow(customView, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                detailsWindow.showAtLocation(linearLayout, Gravity.CENTER, 0, 0);
                detailsWindow.setFocusable(true);
                detailsWindow.update();

                final String paymentDateStr = paymentAmount.getText().toString();
                final String paymentAmountStr = paymentDate.getText().toString();

                new Thread(new Runnable() {
                    @Override
                    public void run() {

                        JSONObject installmentJson = new JSONObject();
                        JSONObject farmerJson = new JSONObject();
                        try{
                            installmentJson.put("amountPaid", paymentAmountStr);
                            installmentJson.put("datePaid", paymentDateStr);

                            farmerJson.put("installments", farmerJson);
                        } catch (JSONException e) {
                        }
                        SharedPreferences sharedPreferences = getSharedPreferences("mysettings", MODE_PRIVATE);
                        String accessToken = sharedPreferences.getString("mystring", "N/A");

                        Map<String, String> headerProperties = new HashMap<>();
                        headerProperties.put("Authorization", "Bearer " + accessToken);

                        try {
                            NetworkAdapter.httpRequest("https://tieme-ndo-backend.herokuapp.com/new-installment/1","POST", farmerJson, headerProperties);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
                detailsWindow.dismiss();
            }
        });
    }
}
