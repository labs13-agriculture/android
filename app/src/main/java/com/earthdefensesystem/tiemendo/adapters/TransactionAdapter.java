package com.earthdefensesystem.tiemendo.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.earthdefensesystem.tiemendo.R;
import com.earthdefensesystem.tiemendo.model.Client;
import com.earthdefensesystem.tiemendo.model.Transaction;

import java.text.NumberFormat;
import java.util.List;

public class TransactionAdapter extends RecyclerView.Adapter<TransactionAdapter.TransactionViewHolder>{

    private List<Transaction> dataList;

    public TransactionAdapter(List<Transaction> dataList) {
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public TransactionViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        View view = layoutInflater.inflate(R.layout.transaction_row_item, viewGroup, false);
        return new TransactionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TransactionViewHolder holder, int i) {
        NumberFormat format = NumberFormat.getCurrencyInstance();
        holder.amount.setText(format.format(dataList.get(i).getTotal()));
        holder.mode.setText(dataList.get(i).getType());
        holder.date.setText(truncate(dataList.get(i).getDate(), 10));
        holder.officer.setText(dataList.get(i).getPersonnel());

    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    class TransactionViewHolder extends RecyclerView.ViewHolder{
        TextView amount, mode, date, officer;

        TransactionViewHolder(View itemView){
            super(itemView);
            amount = itemView.findViewById(R.id.amount_tv);
            mode = itemView.findViewById(R.id.mode_tv);
            date = itemView.findViewById(R.id.date_tv);
            officer = itemView.findViewById(R.id.officer_tv);

        }
    }

    public static String truncate(String value, int length) {
        if (value.length() > length) {
            return value.substring(0, length);
        } else {
            return value;
        }
    }
}
