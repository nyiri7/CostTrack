package com.example.costtrack;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.example.costtrack.Entity.CostCategoryEntity;
import com.example.costtrack.Entity.CostEntity;
import com.example.costtrack.Handlers.CostCategoryHandler;
import com.example.costtrack.Handlers.CostHandler;
import com.google.android.material.textfield.TextInputLayout;


public class NewCost extends Activity {
    Button NewButton;
    Button CancelButton;
    TextInputLayout name;
    TextInputLayout amount;
    Spinner spin;
    Spinner type;
    CostCategoryEntity[] cats;
    String[] types = {"Income","Expense"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_cost);



        name = findViewById(R.id.Name);
        amount = findViewById(R.id.Amount);

        NewButton = (Button) findViewById(R.id.UpdateButton);
        CancelButton = (Button) findViewById(R.id.CancelButton);


        spin = findViewById(R.id.spinner);
        cats= CostCategoryHandler.getAll();

        type = findViewById(R.id.spinner3);

        ArrayAdapter<CostCategoryEntity> adapter = new ArrayAdapter<>(this, R.layout.spinner_item, cats);
        adapter.setDropDownViewResource(R.layout.spinner_item);

        spin.setAdapter(adapter);

        ArrayAdapter<String> adapter2 = new ArrayAdapter<>(this, R.layout.spinner_item, types);
        adapter.setDropDownViewResource(R.layout.spinner_item);

        type.setAdapter(adapter2);

        CancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(NewCost.this,MainActivity.class);
                startActivity(intent);
            }
        });

        NewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CostCategoryEntity s =(CostCategoryEntity) spin.getSelectedItem();

                CostEntity c = new CostEntity((String) type.getSelectedItem(),Integer.parseInt(String.valueOf(amount.getEditText().getText())),String.valueOf(name.getEditText().getText()),s.getId());
                CostHandler ch = new CostHandler();
                ch.Insert(c);
            }
        });




    }
}
