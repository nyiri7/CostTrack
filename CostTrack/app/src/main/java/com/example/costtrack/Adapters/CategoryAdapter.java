package com.example.costtrack.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.costtrack.Entity.CostCategoryEntity;
import com.example.costtrack.Entity.CostEntity;
import com.example.costtrack.R;

public class CategoryAdapter extends BaseAdapter {
    CostCategoryEntity[] cats;
    Context Ctx;
    LayoutInflater inflater;
    private CostAdapter.OnItemClickListener listener;
    public CategoryAdapter(Context ctx, CostCategoryEntity[] cat){
        this.Ctx=ctx;
        this.cats=cat;
        inflater=LayoutInflater.from(ctx);
    }

    @Override
    public int getCount() {
        return cats.length;
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
        view = inflater.inflate(R.layout.category_list_item,null);
        TextView txt = view.findViewById(R.id.Name);
        txt.setText(cats[i].getName());
        CostCategoryEntity c = cats[i];
        view.setOnClickListener(v -> {
            if (listener != null) {
                listener.onItemClick(c.getId());
            }
        });
        return view;
    }
    public interface OnItemClickListener {
        void onItemClick(int id);
    }
    public void setOnItemClickListener(CostAdapter.OnItemClickListener listener) {
        this.listener = listener;
    }
}
