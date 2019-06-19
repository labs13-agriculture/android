package com.earthdefensesystem.tiemendo;

import android.app.SearchManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;

import com.earthdefensesystem.tiemendo.adapters.RetailerAdapter;
import com.earthdefensesystem.tiemendo.network.NetworkAdapter;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class RetailerSearchActivity extends AppCompatActivity {
    public static final String TAG = "Retail";
    private RecyclerView recyclerView;
    private List<Retailer> retailerList;
    private RetailerAdapter retailerAdapter;
    private SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retailer_search);

        recyclerView = findViewById(R.id.retailerRecyclerView);
        Toolbar toolbar = findViewById(R.id.retailer_toolbar);
        setSupportActionBar(toolbar);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(R.string.retail_toolbar_title);

        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(llm);


        new RetailerSearchActivity.GetRetailersAsync().execute(this);

    }


    private class GetRetailersAsync extends AsyncTask<Context, Void, List<Retailer>>{

        private Context context;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected List<Retailer> doInBackground(Context... contexts) {
            context = contexts[0];
            Log.e(TAG, "start aynctask to get retailers");
            return getRetailers();
        }

        @Override
        protected void onPostExecute(List<Retailer> retailers) {
            super.onPostExecute(retailers);

            if(retailers != null){
                Log.e(TAG, "populate UI recycler view with gson converted data");

                retailerAdapter = new RetailerAdapter(context, retailers);
                recyclerView.setAdapter(retailerAdapter);
            }
        }
    }

    public List<Retailer> getRetailers(){
        String retailerUrl = "https://tieme-ndo-backend.herokuapp.com/retailer/retailers";
        SharedPreferences sharedPreferences = getSharedPreferences("mysettings", MODE_PRIVATE);
        String accessToken = sharedPreferences.getString("mystring", "N/A");

        Map<String, String> headerProperties = new HashMap<>();
        headerProperties.put("Authorization", "Bearer " + accessToken);

        String tokenRequest = null;
        try {
            tokenRequest = NetworkAdapter.httpRequest(
                    retailerUrl, "GET", null, headerProperties);

            return convertJsonToObject(tokenRequest);
        } catch (Exception e) {
            Log.e(TAG, "error in getting and parsing response");
        }
        return null;
    }

    public List<Retailer> convertJsonToObject(String tokenRequest){
        //instantiate Gson
        final Gson gson = new Gson();
        Type retailerListType = new TypeToken<ArrayList<Retailer>>(){}.getType();

        //pass root element type to fromJson method along with input stream
        List<Retailer> retailerList = gson.fromJson(tokenRequest, retailerListType);


        return retailerList;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);

        // Associate searchable configuration with the SearchView
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        searchView = (SearchView) menu.findItem(R.id.action_search)
                .getActionView();
        searchView.setSearchableInfo(searchManager
                .getSearchableInfo(getComponentName()));
        searchView.setMaxWidth(Integer.MAX_VALUE);

        // listening to search query text change
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                // filter recycler view when query submitted
                retailerAdapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                // filter recycler view when text is changed
                retailerAdapter.getFilter().filter(query);
                return false;
            }
        });
        return true;
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
