package com.earthdefensesystem.tiemendo.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.earthdefensesystem.tiemendo.fragments.DetailsFragment;
import com.earthdefensesystem.tiemendo.fragments.InstallmentFragment;
import com.earthdefensesystem.tiemendo.fragments.TransactionFragment;

public class ClientPagerAdapter extends FragmentStatePagerAdapter {
    int mTabNum;

    public ClientPagerAdapter(FragmentManager fm, int tabNum) {
        super(fm);
        this.mTabNum = tabNum;
    }

    @Override
    public Fragment getItem(int i) {
        switch (i) {
//            case 0:
//                DetailsFragment detailsFragment = new DetailsFragment();
//            return detailsFragment;
            case 0:
                InstallmentFragment installmentFragment = new InstallmentFragment();
                return installmentFragment;
            case 1:
                TransactionFragment transactionFragment = new TransactionFragment();
                return transactionFragment;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mTabNum;
    }
}