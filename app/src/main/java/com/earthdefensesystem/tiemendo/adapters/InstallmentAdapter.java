package com.earthdefensesystem.tiemendo.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.earthdefensesystem.tiemendo.R;
import com.earthdefensesystem.tiemendo.model.Installment;

import java.text.NumberFormat;
import java.util.List;

public class InstallmentAdapter extends RecyclerView.Adapter<InstallmentAdapter.InstallmentViewHolder>{

    private List<Installment> dataList;

    public InstallmentAdapter(List<Installment> dataList) {
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public InstallmentViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        View view = layoutInflater.inflate(R.layout.installments_row_item, viewGroup, false);
        return new InstallmentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull InstallmentViewHolder holder, int i) {
        NumberFormat format = NumberFormat.getCurrencyInstance();
        holder.amount.setText(format.format(dataList.get(i).getAmountPaid()));
        holder.mode.setText(dataList.get(i).getMode());
        holder.date.setText(truncate(dataList.get(i).getDatePaid(), 10));
        holder.officer.setText(truncateElipsis(dataList.get(i).getOfficer(), 9));

    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    class InstallmentViewHolder extends RecyclerView.ViewHolder{
        private TextView amount, mode, date, officer;

        InstallmentViewHolder(View itemView){
            super(itemView);
            amount = itemView.findViewById(R.id.installment_amount_tv);
            mode = itemView.findViewById(R.id.installment_mode_tv);
            date = itemView.findViewById(R.id.installment_date_tv);
            officer = itemView.findViewById(R.id.installment_officer_tv);

        }
    }

    public static String truncate(String value, int length) {
        if (value.length() > length) {
            return value.substring(0, length);
        } else {
            return value;
        }
    }

    public static String truncateElipsis(String value, int length)
    {
        if (value != null && value.length() > length)
            value = value.substring(0, length)+"...";
        return value;
    }
}