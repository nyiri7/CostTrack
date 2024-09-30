package com.example.costtrack;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.costtrack.Entity.CostCategoryEntity;
import com.example.costtrack.Entity.CostEntity;
import com.example.costtrack.Handlers.CostCategoryHandler;
import com.example.costtrack.Handlers.CostHandler;
import com.google.android.material.textfield.TextInputLayout;

public class CostCat extends Activity {
    Button UpdateButton;
    Button DeleteButton;
    TextInputLayout name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cost_cat);
        Intent intent = getIntent();
        name = findViewById(R.id.Name);


        UpdateButton = (Button) findViewById(R.id.UpdateButton);
        DeleteButton = (Button) findViewById(R.id.DeleteButton);
        int itemId = intent.getIntExtra("ITEM_ID", -1);
        if(itemId!=-1){
            CostEntity c = CostHandler.getCost(itemId);
            if(c != null){
                name.getEditText().setText(c.getName());
            }
        }

        UpdateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CostCategoryEntity c = new CostCategoryEntity(String.valueOf(name.getEditText().getText()),itemId);
                CostCategoryHandler.Update(c);
            }
        });
        DeleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CostCategoryHandler.Delete(itemId);
            }
        });
    }
}
