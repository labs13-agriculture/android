package com.earthdefensesystem.tiemendo.adapters;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.earthdefensesystem.tiemendo.R;
import com.earthdefensesystem.tiemendo.model.Installment;
import com.earthdefensesystem.tiemendo.model.ItemType;
import com.earthdefensesystem.tiemendo.network.RetrofitClientInstance;
import com.earthdefensesystem.tiemendo.network.TiemeService;

import java.text.NumberFormat;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.Context.MODE_PRIVATE;

public class InventoryAdapter extends RecyclerView.Adapter<InventoryAdapter.InventoryViewHolder>{

    private List<ItemType> dataList;
    private TiemeService service;
    private Context context;

    public InventoryAdapter(List<ItemType> dataList) {
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public InventoryAdapter.InventoryViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        View view = layoutInflater.inflate(R.layout.inventory_row_item, viewGroup, false);
        return new InventoryAdapter.InventoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final InventoryAdapter.InventoryViewHolder holder, final int i) {
        holder.itemName.setText(dataList.get(i).getName());
        holder.quantity.setText(String.valueOf(dataList.get(i).getQuantity()));
        holder.active.setText(dataList.get(i).getActive().toString());

        final String farmerId = dataList.get(i).getId().toString();

        holder.deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ItemType removedItem = dataList.get(i);
                dataList.remove(i);
                notifyItemRemoved(i);
            }
        });

    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    class InventoryViewHolder extends RecyclerView.ViewHolder{
        private TextView itemName, quantity, active;
        private ImageButton deleteBtn;

        InventoryViewHolder(View itemView){
            super(itemView);
            itemName = itemView.findViewById(R.id.item_name_tv);
            quantity = itemView.findViewById(R.id.item_quantity_tv);
            active = itemView.findViewById(R.id.item_active_tv);
            deleteBtn = itemView.findViewById(R.id.delete_inventory_btn);

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
