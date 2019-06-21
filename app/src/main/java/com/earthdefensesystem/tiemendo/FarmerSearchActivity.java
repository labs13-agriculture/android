package com.earthdefensesystem.tiemendo;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.telephony.PhoneNumberFormattingTextWatcher;
import android.telephony.PhoneNumberUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.Spinner;
import android.widget.Toast;

import com.earthdefensesystem.tiemendo.adapters.ClientAdapter;
import com.earthdefensesystem.tiemendo.adapters.FarmerAdapter;
import com.earthdefensesystem.tiemendo.model.Client;
import com.earthdefensesystem.tiemendo.network.NetworkAdapter;
import com.earthdefensesystem.tiemendo.network.RetrofitClientInstance;
import com.earthdefensesystem.tiemendo.network.TiemeService;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FarmerSearchActivity extends AppCompatActivity implements ClientAdapter.ClientAdapterListener {
    public static final String TAG = "Farmer";
    private RecyclerView recyclerView;
    private List<Client> farmerList;
    private FarmerAdapter farmerAdapter;
    private TiemeService service;
    private Context context;
    private ClientAdapter adapter;
    private SearchView searchView;
    private Spinner spinYear;
    private EditText farmerName, farmerEmail, farmerPhone, farmerAddress;
    private Button saveFarmerBtn;
    private FloatingActionButton newFarmerBtn;
    private PopupWindow farmerPopup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_farmer_search);
        context = this;

        Toolbar toolbar = findViewById(R.id.farmer_toolbar);
        newFarmerBtn = findViewById(R.id.farmer_fab);



        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(R.string.farmer_toolbar_title);

        SharedPreferences sharedPreferences = getSharedPreferences("mysettings", MODE_PRIVATE);
        final String accessToken = sharedPreferences.getString("mystring", "N/A");

        service = RetrofitClientInstance.getRetrofitInstance().create(TiemeService.class);

        Call<List<Client>> call = service.getFarmers("Bearer " + accessToken);

        call.enqueue(new Callback<List<Client>>() {
            @Override
            public void onResponse(Call<List<Client>> call, Response<List<Client>> response) {
                generateFarmerList(response.body());
            }

            @Override
            public void onFailure(Call<List<Client>> call, Throwable t) {
                Toast toast = Toast.makeText(
                        context, "Username or Password is incorrect",
                        Toast.LENGTH_SHORT);
                toast.show();
            }
        });


        newFarmerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LayoutInflater layoutInflater = (LayoutInflater) FarmerSearchActivity.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View customView = layoutInflater.inflate(R.layout.farmer_popup, null);
                farmerPopup = new PopupWindow(customView, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                farmerPopup.showAtLocation(recyclerView, Gravity.CENTER, 0, 0);
                farmerPopup.setFocusable(true);
                farmerPopup.update();

                spinYear = customView.findViewById(R.id.year_start_spinner);
                ArrayList<String> years = new ArrayList<>();
                int thisYear = Calendar.getInstance().get(Calendar.YEAR);
                for (int i = 1900; i <= thisYear; i++) {
                    years.add(Integer.toString(i));
                }
                final ArrayAdapter<String> spinneradapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_item, years);
                spinYear.setAdapter(spinneradapter);

                saveFarmerBtn = customView.findViewById(R.id.closePopupBtn);
                farmerName = customView.findViewById(R.id.farmer_name_edittext);
                farmerEmail = customView.findViewById(R.id.farmer_email_edittext);
                farmerPhone = customView.findViewById(R.id.farmer_phone_edittext);
                farmerAddress = customView.findViewById(R.id.farmer_address_edittext);

                farmerPhone.addTextChangedListener(new PhoneNumberFormattingTextWatcher());

                saveFarmerBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        final String email = farmerEmail.getText().toString();
                        final String name = farmerName.getText().toString();
                        final String address = farmerAddress.getText().toString();
                        final String phoneNumber = farmerPhone.getText().toString();
                        Client postClient = new Client(address, "community",
                                "dateofbirth", "district", "educationlevel",
                                email, name, "gender", null, "landmark", true, "nationality",
                                phoneNumber, "region", "region", "secondName", 2343, "title", "FARMER");

                        sendFarmer(accessToken, postClient);
                    }
                });


            }
        });


    }

    private void generateFarmerList(List<Client> clientDataList) {
        recyclerView = findViewById(R.id.farmerRecyclerView);
        recyclerView.setHasFixedSize(true);

        adapter = new ClientAdapter(clientDataList, this);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(FarmerSearchActivity.this);

        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onClientSelected(Client farmer) {
        Intent intent = new Intent(FarmerSearchActivity.this, FarmerDetailsActivity.class);
        intent.putExtra("farmerObject", farmer);
        startActivity(intent);
    }

    public void sendFarmer(String accessToken, Client client){
        service.addFarmer("Bearer " + accessToken, client).enqueue(new Callback<Client>() {
            @Override
            public void onResponse(Call<Client> call, Response<Client> response) {

                if(response.isSuccessful()) {
                    Log.e(TAG, "post submitted to API." + response.body().toString());
                } else {
                    Log.e(TAG, "it's MESSING UP AGAIN" + response.code());
                }
            }

            @Override
            public void onFailure(Call<Client> call, Throwable t) {
                Log.e(TAG, "Unable to submit post to API.");
            }
        });
    }


//
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.menu_farmer, menu);
//
//
//        // Associate searchable configuration with the SearchView
//        searchView = (SearchView) menu.findItem(R.id.farmer_search).getActionView();
//
//        // listening to search query text change
//        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String query) {
//                // filter recycler view when query submitted
//                adapter.getFilter().filter(query);
//                return false;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String query) {
//                // filter recycler view when text is changed
//                adapter.getFilter().filter(query);
//                return true;
//            }
//        });
//        return true;
//    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

}