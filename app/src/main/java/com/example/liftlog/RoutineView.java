package com.example.liftlog;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class RoutineView extends AppCompatActivity {
    Button mStartBtn;
    TextView mName, mDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_routine);
        MyApplication.fAuth = FirebaseAuth.getInstance();
        mStartBtn = findViewById(R.id.startButton);

        mName = findViewById(R.id.routineName);
        mDescription = findViewById(R.id.routineDescription);

        String nameText = MyApplication.routine.name;
        String DescriptionText = MyApplication.routine.description;

        mName.setText(nameText);
        mDescription.setText(DescriptionText);

        mDescription.setMovementMethod(new ScrollingMovementMethod());
        mStartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                MyApplication.user.setRoutine(MyApplication.routine.id, MyApplication.routine.workouts);
                //This will change to becoming Routine.class instead of MainActivity.class
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
                finish();
            }
        });
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
        finish();
    }
}