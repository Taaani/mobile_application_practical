package com.example.final_prepration_project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class listActivity extends AppCompatActivity {
 ListView list11;
 String[] cities = new String[]{"karachi", "fasialabad", "lahore", "mirpur","islamabad","koyta","pashawar","multan","pindi","dara_ismalil khan","fasialabad", "lahore", "mirpur","islamabad","koyta","pashawar" };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        list11 = findViewById(R.id.list11);
        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line,cities);
        list11.setAdapter(myAdapter);


        list11.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(listActivity.this, "You click on "+cities[i], Toast.LENGTH_SHORT).show();
            }
        });
    }


}