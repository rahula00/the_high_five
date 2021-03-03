package com.example.liftlog;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.LinkedList;

public class workout_view extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout_view);
        LinearLayout scroll = (LinearLayout) findViewById(R.id.linearInScroll);

        Workout testW = new Workout(1, "myWorkout", "get big or die trying");
        ExerciseStats bench = new ExerciseStats(0, 150, 5, 1);
        ExerciseStats squat = new ExerciseStats(1, 225, 5, 1);
        ExerciseStats deadlift = new ExerciseStats(2, 300, 5, 1);
        ExerciseStats overheadpress = new ExerciseStats(3, 100, 5, 1);


        LinkedList<ExerciseStats> exerciseArray = testW.statsList;
        exerciseArray.add(bench);
        exerciseArray.add(squat);
        exerciseArray.add(deadlift);
        exerciseArray.add(overheadpress);


        View.OnClickListener listener = new View.OnClickListener() {
            public void onClick(View view) {
                TextView text = (TextView) view.getTag();

                if(((Button) view).getText().equals("+"))
                    text.setText(Integer.parseInt(text.getText().toString()) + 1 + "");
                else
                    text.setText(Integer.parseInt(text.getText().toString()) - 1 + "");
            }
        };

        LayoutInflater inflater = getLayoutInflater();
        for(ExerciseStats currentExercise : exerciseArray) {
            int exID = currentExercise.exercise;
            int exWeight = currentExercise.weight;
            int exReps = currentExercise.reps;
            int exSets = currentExercise.sets;


            ConstraintLayout newLayout = (ConstraintLayout) inflater.inflate(R.layout.workout_template, scroll,false);

            TextView exerciseName = (TextView) newLayout.findViewById(R.id.exerciseName);
            exerciseName.setText("ID: " + exID);

            TextView exerciseWeight = (TextView) newLayout.findViewById(R.id.exerciseWeight);
            exerciseWeight.setText(String.valueOf(exWeight));

            TextView exerciseReps = (TextView) newLayout.findViewById(R.id.exerciseReps);
            exerciseReps.setText(String.valueOf(exReps));

            TextView exerciseSets = (TextView) newLayout.findViewById(R.id.exerciseSets);
            exerciseSets.setText(String.valueOf(exSets));


            scroll.addView(newLayout);
        }




    }
}