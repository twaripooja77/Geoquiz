package com.example.geoquiz;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    private Button mYesBtn;
    private Button mNoBtn;
//    private Button mNextBtn;
//    private Button mPreviousBtn;

    private ImageButton mPrevBtn;

    private ImageButton mNexttBtn;
    private TextView mQusText;
    private Button mCheatBtn;

    private boolean mIsCheater;

    private Button mPhoneBtn;

    private boolean mCalled;


    private Questions[] mQuestionBank= new Questions[]{
            new Questions(R.string.question_1,true),
            new Questions(R.string.question_2,true),
            new Questions(R.string.question_3,true),
            new Questions(R.string.question_4, false)
    };

    private int mCurrentIndex=0;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode!= Activity.RESULT_OK){
            return;
        }
        if(requestCode==0){
            if(data==null){
                return;
            }
            mIsCheater=data.getBooleanExtra("ANSWER_SHOWN",false);
        }
        if(requestCode==1){
            if(data==null){
                return;
            }
            mCalled=data.getBooleanExtra("CALLED_FRIEND",false);

        }
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("CURRENT_INDEX", mCurrentIndex);
        Log.d("Geoquiz", "OnSave Instance called");

    }





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState != null){
            mCurrentIndex = savedInstanceState.getInt("CURRENT_INDEX");
        }

        Log.d("Geoquiz","OnCreate method called");

        setContentView(R.layout.activity_main);
        mYesBtn=(Button) findViewById(R.id.yesBtn);
        mNoBtn=(Button) findViewById(R.id.noBtn);
//        mNextBtn=(Button) findViewById(R.id.nextBtn);
//        mPreviousBtn=(Button) findViewById(R.id.previousBtn);
        mPrevBtn=(ImageButton) findViewById(R.id.prevBtn);
        mNexttBtn=(ImageButton) findViewById(R.id.nexttBtn) ;
        mQusText=(TextView) findViewById(R.id.qusText);
        mCheatBtn=(Button) findViewById(R.id.cheatBtn) ;
        mPhoneBtn=(Button) findViewById(R.id.phoneBtn);


        mQusText.setText(mQuestionBank[mCurrentIndex].getQusTestResId());
        mYesBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
//                Toast.makeText(MainActivity.this, R.string.correct_msg, Toast.LENGTH_SHORT).show();
                checkAnswer(true);
            }
        });
        mNoBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
//                Toast.makeText(MainActivity.this, R.string.incorrect_msg, Toast.LENGTH_SHORT).show();
                checkAnswer(false);
            }
        });

//        mNextBtn.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View view){
//                mCurrentIndex=( mCurrentIndex+1)% mQuestionBank.length;
//                mQusText.setText(mQuestionBank[mCurrentIndex].getQusTestResId());
//            }
//        });
//
//        mPreviousBtn.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View view){
//                mCurrentIndex=( mCurrentIndex+mQuestionBank.length-1)% mQuestionBank.length;
//                mQusText.setText(mQuestionBank[mCurrentIndex].getQusTestResId());
//            }
//        });

        mPrevBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                mCurrentIndex=( mCurrentIndex+mQuestionBank.length-1)% mQuestionBank.length;
                mQusText.setText(mQuestionBank[mCurrentIndex].getQusTestResId());
                mIsCheater=false;
                mCalled=false;
            }
        });

        mNexttBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                mCurrentIndex=( mCurrentIndex+1)% mQuestionBank.length;
                mQusText.setText(mQuestionBank[mCurrentIndex].getQusTestResId());
                mIsCheater=false;
                mCalled=false;
            }
        });

        mQusText.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                mCurrentIndex=( mCurrentIndex+1)% mQuestionBank.length;
                mQusText.setText(mQuestionBank[mCurrentIndex].getQusTestResId());
            }
        });
      mCheatBtn.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              Intent intent=new Intent(MainActivity.this,CheatActivity.class);
              boolean answer= mQuestionBank[mCurrentIndex].isAnsTrue();
              intent.putExtra("ANSWER", answer);
              startActivityForResult(intent,0);


          }
      });

mPhoneBtn.setOnClickListener((new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        Intent intent=new Intent(MainActivity.this, PhoneActivity.class);
        boolean answer= mQuestionBank[mCurrentIndex].isAnsTrue();
        intent.putExtra("CALLED_FRIEND", answer);
        startActivityForResult(intent,1);
    }
}));

        }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("GeoQuiz","OnStart method called");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("Geoquiz", "OnResume method called");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("Geoquiz", "OnPause method called");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("Geoquiz", "OnStop method called");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("Geoquiz", "OnDestroy method called");
    }

    private void checkAnswer(boolean userPressedTrue){
        boolean isAnswerTrue=mQuestionBank[mCurrentIndex].isAnsTrue();
        if(mIsCheater){
            Toast.makeText(this, "HI THERE! CHEEAAATTEERRR.", Toast.LENGTH_SHORT).show();
        }else if(mCalled){
            Toast.makeText(this, "YOU CALLED A FRIEND", Toast.LENGTH_SHORT).show();
        }
        else if(userPressedTrue==isAnswerTrue){
            Toast.makeText(this, R.string.correct_msg, Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, R.string.incorrect_msg, Toast.LENGTH_SHORT).show();
        }
    }
}