package com.example.emergence_aids;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Person;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.annotations.Nullable;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnSettings, btnSubmit, btnList, btnDialog;
    EditText etLevel;
    TextView tvPatient;
    int index, level;
    boolean isPatient;
    FirebaseDatabase database;
    DatabaseReference dbRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnSettings = (Button) findViewById(R.id.btnSettings);
        btnList = (Button) findViewById(R.id.btnList);
        btnSubmit = (Button) findViewById(R.id.btnSubmit);
        btnDialog = (Button) findViewById(R.id.btnDialog);
        tvPatient = (TextView) findViewById(R.id.tvPatient);
        etLevel = (EditText) findViewById(R.id.etLevel);
        btnSettings.setOnClickListener(this);
        btnList.setOnClickListener(this);
        btnSubmit.setOnClickListener(this);
        btnDialog.setOnClickListener(this);
        Intent intent = new Intent(this, BlackService.class);
        startService(intent);
        boolean flag = true;
        for(int i = 0; i < DataModel.patients.size() && flag; i++){
            index = i;
            isPatient = true;
            if(!DataModel.patients.get(i).HasLevel()){
                flag = false;
                tvPatient.setText(DataModel.patients.get(i).beforeView());
            }
        }
        if(flag){
            isPatient = false;
            tvPatient.setText("No patients at the moment");
        }
    }

    @Override
    public void onClick(View v) {
        if (v == btnSettings) {
            Intent intent = new Intent(this, settings.class);
            startActivityForResult(intent, 0);
        }
        else if (v == btnList) {
            Intent intent = new Intent(this, patients.class);
            intent.putExtra("Patients", DataModel.patients);
            startActivityForResult(intent, 0);
        }
        else if (v == btnSubmit){
            if (isPatient){
                if(etLevel.getText().length()>0){
                    try {
                        level = Integer.parseInt(etLevel.getText().toString());
                        if (level > 0 && level <= 3){
                            Intent intent = new Intent(this, patients.class);
                            DataModel.patients.get(index).setLevel(level);
                            DataModel.save();
                            intent.putExtra("Patients", DataModel.patients);
                            startActivityForResult(intent, 0);
                        }
                        else{
                            Toast.makeText(this, "Enter valid level", Toast.LENGTH_LONG).show();
                        }
                    }
                    catch (Exception e){
                        Toast.makeText(this, "Enter valid level", Toast.LENGTH_LONG).show();
                    }
                }
                else{
                    Toast.makeText(this, "Enter valid level", Toast.LENGTH_LONG).show();
                }
            }
            else {
                Toast.makeText(this, "No patients at the moment", Toast.LENGTH_LONG).show();
            }
        }
        else if (v == btnDialog){
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Dialog");
            builder.setMessage("wow a dialog");
            builder.setCancelable(true);
            builder.setPositiveButton("Yes wow a dialog", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Toast.makeText(MainActivity.this, "A kind of magic", Toast.LENGTH_SHORT).show();
                }
            });
            builder.setNegativeButton("OH snap a dialog", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Toast.makeText(MainActivity.this, "A kind of magic", Toast.LENGTH_SHORT).show();
                }
            });
            AlertDialog dialog = builder.create();
            dialog.show();
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == Activity.RESULT_OK && requestCode == 0){
            boolean flag = true;
            for(int i = index; i < DataModel.patients.size() && flag; i++){
                index = i;
                isPatient = true;
                if(!DataModel.patients.get(i).HasLevel()){
                    flag = false;
                    tvPatient.setText(DataModel.patients.get(i).beforeView());
                }
            }
            if(flag){
                isPatient = false;
                tvPatient.setText("No patients at the moment");
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }
    @Override
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
