package com.example.geoquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class CheatActivity extends AppCompatActivity {
public boolean mAnswer;
private Button mshowAnswer;
private TextView mgetAnswer;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mshowAnswer=(Button) findViewById(R.id.showanswer) ;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheat);
        mAnswer=getIntent().getBooleanExtra("ANSWER", false);
        mshowAnswer=(Button) findViewById(R.id.showanswer) ;
        mgetAnswer=(TextView) findViewById(R.id.answer);

        mshowAnswer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            mgetAnswer.setText( Boolean.toString(mAnswer));
            Intent data= new Intent();
            data.putExtra("ANSWER_SHOWN",true);
            setResult(RESULT_OK,data);
            }
        });


    }
}