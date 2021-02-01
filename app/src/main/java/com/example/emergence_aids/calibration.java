package com.example.emergence_aids;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class calibration extends AppCompatActivity implements View.OnClickListener {

        TextView tvSensetivity;
        TextView tvState;
        EditText etSensetivity;
        Switch swState;
        String sensetivity, updSensetivity;

        int position;
        boolean state;
        Button btnSubmit;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_calibration);
                position = getIntent().getIntExtra("Position", 0);
                sensetivity = getIntent().getStringExtra("Sensetivity");
                etSensetivity = (EditText)findViewById(R.id.etSensetivity);
                tvSensetivity = (TextView)findViewById(R.id.tvSensetivity);
                btnSubmit = (Button)findViewById(R.id.btnSubmit);
                tvSensetivity.setText("sensetivity: " + sensetivity);
                btnSubmit.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
                if(v == btnSubmit){
                        updSensetivity = etSensetivity.getText().toString();
                        Intent intent = new Intent(this, settings.class);
                        intent.putExtra("Position", position);
                        intent.putExtra("Return", updSensetivity);
                        setResult(Activity.RESULT_OK,intent);
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
