package com.example.otp_v2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.Telephony;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.app.Dialog;

import static com.example.otp_v2.R.layout.activity_main;

public class MainActivity extends AppCompatActivity {
    private Button verify;
    EditText otp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(activity_main);
        otp=findViewById(R.id.editText);
        new MyReceiver().setEditText(otp);
        verify = (Button) findViewById(R.id.button);
        verify.setOnClickListener(new View.OnClickListener() {
         @Override
            public void onClick(View view) {
                validate(otp.getText().toString());
            }
        });
    }
    public void validate(String otp){
        if (otp.equals(data.OTP))
        {   Toast.makeText(this, "OTP Verification Success", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(MainActivity.this, SecondActivity.class);
            startActivity(intent);
        }
        else{
            Toast.makeText(this, "Invalid OTP", Toast.LENGTH_SHORT).show();
            Toast.makeText(this, "Please Enter Correct OTP", Toast.LENGTH_SHORT).show();
        }
    }


}
