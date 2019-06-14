package com.earthdefensesystem.tiemendo;

import android.app.SearchManager;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.earthdefensesystem.tiemendo.adapters.MyApplication;
import com.earthdefensesystem.tiemendo.adapters.MyDividerItemDecoration;
import com.earthdefensesystem.tiemendo.adapters.RetailerAdapter;
import com.earthdefensesystem.tiemendo.model.Retailer;
import com.earthdefensesystem.tiemendo.network.NetworkAdapter;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RetailerSearchActivity extends AppCompatActivity implements RetailerAdapter.RetailerAdapterListener {
    public static final String TAG = "Retail";
    private RecyclerView recyclerView;
    private List<Retailer> contactList;
    private RetailerAdapter mAdapter;
    private SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retailer_search);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(R.string.toolbar_title);

        recyclerView = findViewById(R.id.recycler_view);
        contactList = new ArrayList<>();
        mAdapter = new RetailerAdapter(this, contactList, this);

        whiteNotificationBar(recyclerView);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new MyDividerItemDecoration(this, DividerItemDecoration.VERTICAL, 36));
        recyclerView.setAdapter(mAdapter);

        fetchContacts();
    }
    private void fetchContacts() {
        final ArrayList<Retailer> data = new ArrayList<>();
        new Thread(new Runnable() {
            @Override
            public void run() {

                String auth = Base64.encodeToString("lambda-client:lambda-secret".getBytes(), Base64.DEFAULT);

                Map<String, String> headerProperties = new HashMap<>();
                headerProperties.put("Authorization", "Basic " + auth);

                String tokenUrl= "https://tieme-ndo-backend.herokuapp.com/oauth/token?grant_type=password&username=admin&password=password&scope=";

                String tokenRequest = null;
                try {
                    tokenRequest = NetworkAdapter.httpRequest(
                            tokenUrl, "POST", null, headerProperties);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                Log.i(TAG, tokenRequest);
                try {
                    String token = new JSONObject(tokenRequest).getString("access_token");

                    headerProperties.clear();
                    headerProperties.put("Authorization", "Bearer " + token);
                    try {
                        String result = null;
                        try {
                            result = NetworkAdapter.httpRequest("https://tieme-ndo-backend.herokuapp.com/retailer/retailers", "GET", null, headerProperties);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        JSONArray dataJsonArray = new JSONArray(result);

                        for (int i = 0; i < dataJsonArray.length(); ++i) {
                            Retailer items = new Retailer(dataJsonArray.getJSONObject(i));
                            data.add(items);
                            contactList.clear();
                            contactList.addAll(data);
//
//                            // refreshing recycler view
//                            mAdapter.notifyDataSetChanged();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }).start();
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
                mAdapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                // filter recycler view when text is changed
                mAdapter.getFilter().filter(query);
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

    @Override
    public void onBackPressed() {
        // close search view on back button pressed
        if (!searchView.isIconified()) {
            searchView.setIconified(true);
            return;
        }
        super.onBackPressed();
    }

    private void whiteNotificationBar(View view) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            int flags = view.getSystemUiVisibility();
            flags |= View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
            view.setSystemUiVisibility(flags);
            getWindow().setStatusBarColor(Color.WHITE);
        }
    }

    @Override
    public void onRetailerSelected(Retailer retailer) {

        Toast.makeText(getApplicationContext(), "Selected: " + retailer.getName() + ", " + retailer.getStartyear(), Toast.LENGTH_LONG).show();
    }
}
