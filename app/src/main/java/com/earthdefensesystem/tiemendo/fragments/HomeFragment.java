package com.earthdefensesystem.tiemendo.fragments;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.telephony.PhoneNumberFormattingTextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.Spinner;

import com.earthdefensesystem.tiemendo.FarmerSearchActivity;
import com.earthdefensesystem.tiemendo.OrganizationSearchActivity;
import com.earthdefensesystem.tiemendo.R;
import com.earthdefensesystem.tiemendo.RetailerSearchActivity;
import com.earthdefensesystem.tiemendo.model.Client;
import com.earthdefensesystem.tiemendo.network.RetrofitClientInstance;
import com.earthdefensesystem.tiemendo.network.TiemeService;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.Context.MODE_PRIVATE;

public class HomeFragment extends Fragment {
    public static final String TAG = "AccountFragment";

    private Button btnFarmerSearch;
    private Button btnRetailerSearch;
    private Button btnOrganizationSearch;
    private Button btnFarmerAdd;
    private Button saveFarmerBtn;
    private EditText farmerFirstName,farmerSecondName, farmerEmail, farmerPhone, farmerAddress;
    private TiemeService service;
    private PopupWindow farmerPopup;
    private Spinner spinYear;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_frag_layout, container, false);
        btnFarmerSearch = view.findViewById(R.id.farmer_search_btn);
        btnRetailerSearch = view.findViewById(R.id.retailer_search_btn);
        btnOrganizationSearch = view.findViewById(R.id.organizaiton_search_btn);
        btnFarmerAdd = view.findViewById(R.id.farmer_add_btn);

        SharedPreferences sharedPreferences = this.getActivity().getSharedPreferences("mysettings", MODE_PRIVATE);
        final String accessToken = sharedPreferences.getString("mystring", "N/A");
        Log.e(TAG, accessToken);
        service = RetrofitClientInstance.getRetrofitInstance().create(TiemeService.class);
        Call<List<Client>> call = service.getFarmers("Bearer " + accessToken);

        btnRetailerSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), RetailerSearchActivity.class);
                startActivity(i);
            }
        });
        btnFarmerSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent o = new Intent(getActivity(), FarmerSearchActivity.class);
                startActivity(o);
            }
        });
        btnOrganizationSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent p = new Intent(getActivity(), OrganizationSearchActivity.class);
                startActivity(p);
            }
        });

        btnFarmerAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View customView = LayoutInflater.from(getActivity()).inflate(R.layout.farmer_popup, null);
                farmerPopup = new PopupWindow(customView, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                farmerPopup.showAtLocation(btnRetailerSearch, Gravity.CENTER, 0, 0);
                farmerPopup.setFocusable(true);
                farmerPopup.update();

                spinYear = customView.findViewById(R.id.year_start_spinner);
                ArrayList<String> years = new ArrayList<>();
                int thisYear = Calendar.getInstance().get(Calendar.YEAR);
                for (int i = 1900; i <= thisYear; i++) {
                    years.add(Integer.toString(i));
                }
                final ArrayAdapter<String> spinneradapter = new ArrayAdapter<>(getActivity().getApplicationContext(), android.R.layout.simple_spinner_item, years);
                spinYear.setAdapter(spinneradapter);

                saveFarmerBtn = customView.findViewById(R.id.closePopupBtn);
                farmerFirstName = customView.findViewById(R.id.farmer_first_name_edittext);
                farmerSecondName = customView.findViewById(R.id.farmer_second_name_edittexr);
                farmerEmail = customView.findViewById(R.id.farmer_email_edittext);
                farmerPhone = customView.findViewById(R.id.farmer_phone_edittext);
                farmerAddress = customView.findViewById(R.id.farmer_address_edittext);

                farmerPhone.addTextChangedListener(new PhoneNumberFormattingTextWatcher());

                saveFarmerBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        final String email = farmerEmail.getText().toString();
                        final String firstname = farmerFirstName.getText().toString();
                        final String secondname = farmerSecondName.getText().toString();
                        final String address = farmerAddress.getText().toString();
                        final String phoneNumber = farmerPhone.getText().toString();
                        Client postClient = new Client(address, "community",
                                "dateofbirth", "district", "educationlevel",
                                email, firstname, "gender", null, "landmark", true, "nationality",
                                phoneNumber, "region", "region", secondname, 1994, "title", "FARMER", null);

                        sendFarmer(accessToken, postClient);
                    }
                });
            }
        });

        return view;
    }
    public void sendFarmer(String accessToken, Client client) {
        service.addFarmer("Bearer " + accessToken, client).enqueue(new Callback<Client>() {
            @Override
            public void onResponse(Call<Client> call, Response<Client> response) {

                if (response.isSuccessful()) {
                    Log.e(TAG, "post submitted to API." + response.body().toString());
                    farmerPopup.dismiss();
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

}
