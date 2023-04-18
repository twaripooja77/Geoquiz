package com.example.geoquiz;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class PhoneActivity extends AppCompatActivity {

 private Button mCall;

 private Boolean mCalled = false;
 private TextView mHasCalled;


    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean("CALLED_FRIEND",mCalled);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone);

        if(savedInstanceState !=null){
            mCalled=savedInstanceState.getBoolean("CALLED_FRIEND");
        }

        mCall=(Button) findViewById(R.id.callBtn);

        mCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(PhoneActivity.this, "Calling....", Toast.LENGTH_SHORT).show();
                Intent data= new Intent();
                data.putExtra("CALLED_FRIEND",true);
                setResult(RESULT_OK,data);
            }
        });
    }
}