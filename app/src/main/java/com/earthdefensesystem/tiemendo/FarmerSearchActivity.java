package com.earthdefensesystem.tiemendo;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.earthdefensesystem.tiemendo.adapters.FarmerAdapter;
import com.earthdefensesystem.tiemendo.model.Client;
import com.earthdefensesystem.tiemendo.network.NetworkAdapter;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FarmerSearchActivity extends AppCompatActivity implements FarmerAdapter.FarmerAdapterListener{
    public static final String TAG = "Farmer";
    private RecyclerView recyclerView;
    private List<Client> farmerList;
    private FarmerAdapter farmerAdapter;
    private String farmerJson;
    private SearchView searchView;
    private EditText farmerName, farmerEmail, farmerPhone, farmerAddress;
    private Button saveFarmerBtn;
    private FloatingActionButton newFarmerBtn;
    private PopupWindow farmerPopup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_farmer_search);

        recyclerView = findViewById(R.id.farmerRecyclerView);
        Toolbar toolbar = findViewById(R.id.farmer_toolbar);
        newFarmerBtn = findViewById(R.id.farmer_fab);

        setSupportActionBar(toolbar);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(R.string.farmer_toolbar_title);

        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(llm);


        new FarmerSearchActivity.GetFarmersAsync().execute(this);

        newFarmerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LayoutInflater layoutInflater = (LayoutInflater) FarmerSearchActivity.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View customView = layoutInflater.inflate(R.layout.farmer_popup,null);
                farmerPopup = new PopupWindow(customView, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                farmerPopup.showAtLocation(recyclerView, Gravity.CENTER, 0, 0);
                farmerPopup.setFocusable(true);
                farmerPopup.update();

                saveFarmerBtn = customView.findViewById(R.id.closePopupBtn);
                farmerName = customView.findViewById(R.id.farmer_name_edittext);
                farmerEmail = customView.findViewById(R.id.farmer_email_edittext);
                farmerPhone = customView.findViewById(R.id.farmer_phone_edittext);
                farmerAddress = customView.findViewById(R.id.farmer_address_edittext);

                saveFarmerBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        final String email = farmerEmail.getText().toString();
                        final String name = farmerName.getText().toString();
                        final String address = farmerAddress.getText().toString();
                        final String phoneNumber = farmerPhone.getText().toString();
                        new Thread(new Runnable() {
                            @Override
                            public void run() {

                        JSONObject farmerJson = new JSONObject();
                        JSONObject farmerContactJson = new JSONObject();
                        try{
                        farmerContactJson.put("email", email);
                        farmerContactJson.put("phone", phoneNumber);
                        farmerContactJson.put("nationality", address);

                        farmerJson.put("name", name);
                        farmerJson.put("farmercontact", farmerContactJson);
                        } catch (JSONException e) {
                        }
                        SharedPreferences sharedPreferences = getSharedPreferences("mysettings", MODE_PRIVATE);
                        String accessToken = sharedPreferences.getString("mystring", "N/A");

                        Map<String, String> headerProperties = new HashMap<>();
                        headerProperties.put("Authorization", "Bearer " + accessToken);

                                try {
                                    NetworkAdapter.httpRequest("https://tieme-ndo-backend.herokuapp.com/farmers/add", "POST", farmerJson, headerProperties);
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                        }).start();
                        farmerPopup.dismiss();
                    }
                });



            }
        });


    }

    @Override
    public void onFarmerSelected(Client farmer) {
        Toast.makeText(getApplicationContext(), "Selected: " + farmer.getFirstName(), Toast.LENGTH_LONG).show();
        Intent intent = new Intent(FarmerSearchActivity.this, FarmerDetailsActivity.class);
        intent.putExtra("farmerObject", farmer);
        startActivity(intent);
    }




    private class GetFarmersAsync extends AsyncTask<Context, Void, List<Client>> {

        private Context context;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected List<Client> doInBackground(Context... contexts) {
            context = contexts[0];
            Log.e(TAG, "start aynctask to get farmers");
            return getFarmers();
        }

        @Override
        protected void onPostExecute(List<Client> farmers) {
            super.onPostExecute(farmers);

            if(farmers != null){
                Log.e(TAG, "populate UI recycler view with gson converted data");

                farmerAdapter = new FarmerAdapter(context, farmers, FarmerSearchActivity.this);
                recyclerView.setAdapter(farmerAdapter);
            }
        }
    }

    public List<Client> getFarmers(){
        String farmerUrl = "https://tieme-ndo-backend.herokuapp.com/farmers/all";
        SharedPreferences sharedPreferences = getSharedPreferences("mysettings", MODE_PRIVATE);
        String accessToken = sharedPreferences.getString("mystring", "N/A");

        Map<String, String> headerProperties = new HashMap<>();
        headerProperties.put("Authorization", "Bearer " + accessToken);

        String tokenRequest = null;
        try {
            tokenRequest = NetworkAdapter.httpRequest(
                    farmerUrl, "GET", null, headerProperties);

            return convertJsonToObject(tokenRequest);
        } catch (Exception e) {
            Log.e(TAG, "error in getting and parsing response");
        }
        return null;
    }

    public List<Client> convertJsonToObject(String tokenRequest){
        //instantiate Gson
        final Gson gson = new Gson();
        Type farmerListType = new TypeToken<ArrayList<Client>>(){}.getType();

        //pass root element type to fromJson method along with input stream
        List<Client> farmerList = gson.fromJson(tokenRequest, farmerListType);


        return farmerList;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_farmer, menu);

        // Associate searchable configuration with the SearchView
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        searchView = (SearchView) menu.findItem(R.id.farmer_search)
                .getActionView();
        searchView.setSearchableInfo(searchManager
                .getSearchableInfo(getComponentName()));
        searchView.setMaxWidth(Integer.MAX_VALUE);

        // listening to search query text change
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                // filter recycler view when query submitted
                farmerAdapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                // filter recycler view when text is changed
                farmerAdapter.getFilter().filter(query);
                return false;
            }
        });
        return true;
    }

    @Override
    public void onBackPressed() {
        // close search view on back button pressed
        if (!searchView.isIconified()) {
            searchView.setIconified(true);
            return;
        }
        super.onBackPressed();
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_search) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}