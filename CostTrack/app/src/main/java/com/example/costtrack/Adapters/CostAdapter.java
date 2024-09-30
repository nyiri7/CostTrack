package com.example.costtrack.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.costtrack.Entity.CostEntity;
import com.example.costtrack.R;

public class CostAdapter extends BaseAdapter {
    Context Ctx;
    CostEntity[] costEntities;
    LayoutInflater inflater;
    private OnItemClickListener listener;
    public CostAdapter(Context ctx, CostEntity[] costs){
        this.costEntities = costs;
        this.Ctx=ctx;
        inflater=LayoutInflater.from(ctx);
    }
    @Override
    public int getCount() {
        return costEntities.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = inflater.inflate(R.layout.cost_list_view,null);
        TextView txt = view.findViewById(R.id.Name);
        TextView amount = view.findViewById(R.id.amount);
        TextView cat = view.findViewById(R.id.cat);
        ImageView img = view.findViewById(R.id.imageView);
        txt.setText(costEntities[i].getName());
        amount.setText(""+costEntities[i].getAmount());
        cat.setText(costEntities[i].getType());
        CostEntity currentCost = costEntities[i];
        if(costEntities[i].getType().equalsIgnoreCase("Income")){
            img.setImageResource(R.drawable.plus_dollar);
        }else {
            img.setImageResource(R.drawable.minus_dollar);
        }
        view.setOnClickListener(v -> {
            if (listener != null) {
                listener.onItemClick(currentCost.getID());
            }
        });
        return view;
    }
    public interface OnItemClickListener {
        void onItemClick(int id);
    }
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }
}
