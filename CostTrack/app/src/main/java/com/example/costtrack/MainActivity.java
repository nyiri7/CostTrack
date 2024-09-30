package com.example.costtrack;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.costtrack.Adapters.CostAdapter;
import com.example.costtrack.Entity.CostEntity;
import com.example.costtrack.Handlers.CostHandler;

public class MainActivity extends AppCompatActivity {
    Button NewCostButton;
    Button Categories;
    ListView list;
    TextView incomeT;
    TextView expenseT;
    TextView total;
    int Income=0;
    int Expense=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        NewCostButton = findViewById(R.id.NewButton);
        Categories = findViewById(R.id.Categorybutton);

        list = findViewById(R.id.listView);

        CostEntity[] costs = CostHandler.getCosts();

        incomeT=findViewById(R.id.income);
        expenseT=findViewById(R.id.expense);
        total=findViewById(R.id.total);

        for (CostEntity co:costs )
        {
            if(co.getType().equalsIgnoreCase("Income")){
                Income+=co.getAmount();
            }else{
                Expense+=co.getAmount();
            }
        }

        incomeT.setText(""+Income);
        expenseT.setText(""+Expense);
        int a = Income-Expense;
        total.setText(""+a);

        CostAdapter adapter = new CostAdapter(getApplicationContext(),costs);
        list.setAdapter(adapter);
        adapter.setOnItemClickListener(id -> {
            Intent intent = new Intent(MainActivity.this, Cost.class);
            intent.putExtra("ITEM_ID", id);
            startActivity(intent);
        });
        NewCostButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,NewCost.class);
                startActivity(intent);
            }
        });
        Categories.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,CostCategory.class);
                startActivity(intent);
            }
        });


    }
}