package com.example.costtrack;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.costtrack.Entity.CostCategoryEntity;
import com.example.costtrack.Entity.CostEntity;
import com.example.costtrack.Handlers.CostCategoryHandler;
import com.example.costtrack.Handlers.CostHandler;
import com.google.android.material.textfield.TextInputLayout;

public class Cost extends Activity {
    Button UpdateButton;
    Button DeleteButton;
    TextInputLayout name;
    TextInputLayout amount;
    Spinner type;
    Spinner spin;
    CostCategoryEntity[] cats;
    String[] types = {"Income","Expense"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cost);
        Intent intent = getIntent();
        name = findViewById(R.id.Name);
        amount = findViewById(R.id.Amount);

        UpdateButton = (Button) findViewById(R.id.UpdateButton);
        DeleteButton = (Button) findViewById(R.id.DeleteButton);
        spin = findViewById(R.id.spinner);
        cats= CostCategoryHandler.getAll();
        type = findViewById(R.id.spinner3);

        ArrayAdapter<CostCategoryEntity> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, cats);
        adapter.setDropDownViewResource(R.layout.spinner_item);
        spin.setAdapter(adapter);

        ArrayAdapter<String> adapter2 = new ArrayAdapter<>(this, R.layout.spinner_item, types);
        adapter.setDropDownViewResource(R.layout.spinner_item);

        type.setAdapter(adapter2);
        int itemId = intent.getIntExtra("ITEM_ID", -1);
        if(itemId!=-1){
            CostEntity c = CostHandler.getCost(itemId);
            if(c != null){
                name.getEditText().setText(c.getName());
                if (c.getType().equalsIgnoreCase("Income")) {
                    type.setSelection(0);
                }else{
                    type.setSelection(1);
                }
                amount.getEditText().setText(String.valueOf(c.getAmount()));
                int position = getPositionByCategoryId(c.getCat());
                if (position != -1) {
                    spin.setSelection(position);
                }
            }
        }


        UpdateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CostCategoryEntity s =(CostCategoryEntity) spin.getSelectedItem();
                CostEntity c = new CostEntity(itemId,String.valueOf((String) type.getSelectedItem()),Integer.parseInt(String.valueOf(amount.getEditText().getText())),String.valueOf(name.getEditText().getText()),s.getId());
                CostHandler ch = new CostHandler();
                ch.Update(c);
            }
        });
        DeleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CostHandler.Delete(itemId);
            }
        });
    }
    private int getPositionByCategoryId(int categoryId) {
        for (int i = 0; i < cats.length; i++) {
            if (cats[i].getId() == categoryId) {
                return i;
            }
        }
        return -1;
    }
}
