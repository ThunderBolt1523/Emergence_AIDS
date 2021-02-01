package com.example.emergence_aids;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class patients extends AppCompatActivity implements View.OnClickListener{

    Button btnReturn;
    ListView lvPatients;
    ArrayAdapter<Patient> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patients);
        //DataModel.patients = (ArrayList<Patient>) getIntent().getSerializableExtra("Patients");
        btnReturn =(Button)findViewById(R.id.btnReturn);
        btnReturn.setOnClickListener(this);
        adapter = new ArrayAdapter<Patient>(this,android.R.layout.simple_list_item_1,DataModel.patients);
        lvPatients = findViewById(R.id.lvPatients);
        lvPatients.setAdapter(adapter);
    }

    @Override
    public void onClick(View v) {
        if(v == btnReturn){
            Intent intent = new Intent(this, com.example.emergence_aids.MainActivity.class);
            setResult(Activity.RESULT_OK, intent);
            finish();
        }
    }
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_home:
                Toast.makeText(this, "home", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.action_patients:
                Toast.makeText(this, "patients", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.action_settings:
                Toast.makeText(this, "settings", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
