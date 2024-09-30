package com.example.costtrack;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.costtrack.Entity.CostCategoryEntity;
import com.example.costtrack.Handlers.CostCategoryHandler;
import com.google.android.material.textfield.TextInputLayout;

public class NewCostCategory extends Activity {
    Button newBtn;
    TextInputLayout name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_cost_category);
        newBtn = findViewById(R.id.DeleteButton);
        name = findViewById(R.id.Name);

        newBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CostCategoryEntity c = new CostCategoryEntity(String.valueOf(name.getEditText().getText()));
                CostCategoryHandler.Insert(c);
            }
        });

    }
}
