package com.altrovis.broducation;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

/**
 * Created by ANGGA on 4/6/2017.
 */

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        TextView loginButton = (TextView) findViewById(R.id.button_login);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(i);
            }
        });

        Button registerButton = (Button) findViewById(R.id.button_register);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ProgressDialog progressDialog = new ProgressDialog(RegisterActivity.this);
                progressDialog.setMessage("Authenticating...");
                progressDialog.setCancelable(false);
                progressDialog.show();
                Intent i = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(i);
                finish();
                progressDialog.dismiss();
            }
        });

        Spinner spinner_tingkat = (Spinner) findViewById(R.id.spinner_tingkat_pendidikan);
        ArrayAdapter<CharSequence> adapter_spinner_tingkat = ArrayAdapter.createFromResource(this,R.array.spinner_tingkat, R.layout.spinner_item);

        adapter_spinner_tingkat.setDropDownViewResource(R.layout.spinner_item);
        spinner_tingkat.setAdapter(adapter_spinner_tingkat);
    }

}
