package com.example.costtrack;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.example.costtrack.Adapters.CategoryAdapter;
import com.example.costtrack.Adapters.CostAdapter;
import com.example.costtrack.Entity.CostCategoryEntity;
import com.example.costtrack.Handlers.CostCategoryHandler;

public class CostCategory extends Activity {
    Button NewCostButton;
    ListView list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cost_category);
        list = findViewById(R.id.listView);

        CostCategoryEntity[] c = CostCategoryHandler.getAll();
        CategoryAdapter adapter = new CategoryAdapter(getApplicationContext(),c);
        list.setAdapter(adapter);
        adapter.setOnItemClickListener(id -> {
            Intent intent = new Intent(CostCategory.this, CostCat.class);
            intent.putExtra("ITEM_ID", id);
            startActivity(intent);
        });
        NewCostButton = findViewById(R.id.NewButton);
        NewCostButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CostCategory.this,NewCostCategory.class);
                startActivity(intent);
            }
        });
    }
}
