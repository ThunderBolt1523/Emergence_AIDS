package com.example.emergence_aids;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.Toast;

import java.util.ArrayList;

public class settings extends AppCompatActivity implements AdapterView.OnItemClickListener, View.OnClickListener {

    ArrayList<Sensor> sensors;
    ListView lvSensors;
    ArrayAdapter<Sensor> adapter;
    Button btnReturn;
    int position, sensetivity;
    SharedPref sp;
    Switch superSwitch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        lvSensors = findViewById(R.id.lvSensors);
        btnReturn = (Button) findViewById(R.id.btnReturn);
        btnReturn.setOnClickListener(this);
        superSwitch = (Switch)findViewById(R.id.superSwitch);
        sp = new SharedPref(this);
        if (sp.LoadState())
            superSwitch.setChecked(true);
        else
            superSwitch.setChecked(false);
        superSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                sp.setState(isChecked);
            }
        });

        fillData();
        adapter = new ArrayAdapter<Sensor>(this, android.R.layout.simple_list_item_1, sensors);
        lvSensors.setAdapter(adapter);
        lvSensors.setOnItemClickListener(this);
        
    }

    public void fillData() {
        sensors = new ArrayList<>();
        sensors.add(new Sensor("proximity", 42, false));
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
        Intent intent = new Intent(this, calibration.class);
        intent.putExtra("Position", position);
        intent.putExtra("Sensetivity", String.valueOf(sensors.get(position).getSensetivity()));
        intent.putExtra("State", sensors.get(position).isWorking());
        startActivityForResult(intent, 0);
    }

    @Override
    public void onClick(View v) {
        if (v == btnReturn) {
            finish();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK && requestCode == 0) {
            position = data.getIntExtra("Position", 0);
            sensetivity = Integer.parseInt(data.getStringExtra("Return"));
            sensors.get(position).setSensetivity(sensetivity);
            adapter.notifyDataSetChanged();
            //lvSensors.setAdapter(adapter);
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

