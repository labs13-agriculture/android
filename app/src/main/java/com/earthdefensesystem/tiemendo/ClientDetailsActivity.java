package com.earthdefensesystem.tiemendo;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.earthdefensesystem.tiemendo.adapters.ClientPagerAdapter;
import com.earthdefensesystem.tiemendo.fragments.InstallmentFragment;
import com.earthdefensesystem.tiemendo.fragments.TransactionFragment;
import com.earthdefensesystem.tiemendo.model.Client;

import java.text.NumberFormat;

public class ClientDetailsActivity extends AppCompatActivity {
    public static final String TAG = "ClientDetail";
    private TabLayout tabLayout;
    private ClientPagerAdapter adapter;
    private TextView fullName, address, initial, balance;
    private ViewPager viewPager;
    private ImageView callBtn, emailBtn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.client_detail_dashboard);

        fullName = findViewById(R.id.dashboard_name_tv);
        address = findViewById(R.id.dashboard_address_tv);
        initial = findViewById(R.id.dashboard_profile_pic_tv);
        balance = findViewById(R.id.dashboard_balance_amount_tv);
        callBtn = findViewById(R.id.client_call_btn);
        emailBtn = findViewById(R.id.client_email_btn);

        tabLayout = findViewById(R.id.client_detail_tab_layout);
//        tabLayout.addTab(tabLayout.newTab().setText("Details"));
        tabLayout.addTab(tabLayout.newTab().setText("Installments"));
        tabLayout.addTab(tabLayout.newTab().setText("Transactions"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        Intent intent = getIntent();
        final Client farmer = (Client) intent.getSerializableExtra("farmerObject");
        final String farmerId = farmer.getId().toString();
        SharedPreferences settings = getSharedPreferences(
                "mysettings", MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString(
                "farmerId", farmerId);
        editor.apply();

        SharedPreferences sharedPreferences = getSharedPreferences("mysettings", MODE_PRIVATE);
        final String accessToken = sharedPreferences.getString("mystring", "N/A");

        String fullNameStr = farmer.getFirstName()+ " " + farmer.getSecondName();
        NumberFormat format = NumberFormat.getCurrencyInstance();
        fullName.setText(fullNameStr);
        address.setText(farmer.getAddress());
        initial.setText(String.valueOf(fullNameStr.charAt(0)));
        balance.setText(format.format(farmer.getTotalowed()));



        viewPager = findViewById(R.id.client_detail_container);
        adapter = new ClientPagerAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.BaseOnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        callBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent callIntent = new Intent(Intent.ACTION_DIAL);
                callIntent.setData(Uri.parse("tel:" +farmer.getPhone()));
                startActivity(callIntent);
            }
        });

        emailBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                        "mailto",farmer.getEmail(), null));
                startActivity(Intent.createChooser(intent, "Choose an Email client :"));
            }
        });

    }
}
