package com.example.collegedemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    Button btnadd, btnviewall;
    EditText etname, etage;
    Switch swbool;
    ListView costls;
    ArrayAdapter costArrayAdapter;



    mydatabase mydatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnadd = findViewById(R.id.btnadd);
        btnviewall = findViewById(R.id.btnviewall);
        etage = findViewById(R.id.etage);
        etname = findViewById(R.id.etname);
        swbool = findViewById(R.id.swbool);
        costls = findViewById(R.id.costls);

        mydatabase = new mydatabase(MainActivity.this);

        ShowCostList(mydatabase);

        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                customerls customerls;

                try {
                    customerls = new customerls(-1,etname.getText().toString(),Integer.parseInt(etage.getText().toString()),swbool.isChecked());
                    Toast.makeText(MainActivity.this,customerls.toString(), Toast.LENGTH_SHORT).show();

                }
                catch (Exception e){
                    Toast.makeText(MainActivity.this,"good it's working", Toast.LENGTH_SHORT).show();
                    customerls = new customerls(-1,"Error",0,false);
                }

                mydatabase mydatabase = new mydatabase(MainActivity.this);
                boolean success = mydatabase.addOne(customerls);
                Toast.makeText(MainActivity.this,"add success", Toast.LENGTH_SHORT).show();

                ShowCostList(mydatabase);

            }
        });

        btnviewall.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                mydatabase mydatabase = new mydatabase(MainActivity.this);
                List<customerls> everyone = mydatabase.getEveryone();

                ShowCostList(mydatabase);

                //Toast.makeText(MainActivity.this,everyone.toString(), Toast.LENGTH_SHORT).show();

            }
        });



    }

    private void ShowCostList(mydatabase mydatabase2) {
        costArrayAdapter = new ArrayAdapter<customerls>(MainActivity.this, android.R.layout.simple_list_item_1, mydatabase2.getEveryone());
        costls.setAdapter(costArrayAdapter);
    }
}