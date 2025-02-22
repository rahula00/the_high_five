package com.example.liftlog;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.LinkedList;

public class workout_view extends AppCompatActivity {

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        User myUser = MyApplication.user;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout_view);

        LinearLayout scroll = (LinearLayout) findViewById(R.id.linearInScroll);
        ArrayList<Integer> checkList = new ArrayList<Integer>();

        Integer arrayID = (int) getIntent().getLongExtra("WORKOUT_ID", 0);
        LinkedList<ExerciseStats> exerciseArray = myUser.user_workouts.get(arrayID).statsList;

        View.OnClickListener listener = new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @SuppressLint("UseCompatLoadingForColorStateLists")
            public void onClick(View view) {

                int viewID = (int) view.getTag();
                View parentView = (View) view.getParent();
                TextView triggerTagView = (TextView) parentView.findViewById(R.id.exerciseName);
                boolean trigger = (boolean) triggerTagView.getTag();

                if ((!checkList.contains(viewID)) && !trigger) {
                    checkList.add(viewID);
                    view.setBackgroundTintList(MyApplication.getContext().getResources().getColorStateList(R.color.red));
                    Button b = (Button) view;
                    b.setText("Confirm");
                    b.setTextSize(10);
                }
                else {
                    View v = scroll.getChildAt(0);
                    if (trigger) {
                        // create popup (set intent)
                        Intent intent = new Intent(MyApplication.getContext(), num_reps_popup.class);
                        intent.putExtra("workout_id", arrayID);
                        intent.putExtra("view_id", viewID);
                        startActivity(intent);
                        scroll.removeView(v);
                        exerciseArray.pop();
                    } else {
                        scroll.removeView(v);
                        exerciseArray.pop();
                    }
                    if (scroll.getChildCount() > 0) {
                        View v2 = scroll.getChildAt(0);
                        Button tempButton = (Button) v2.findViewById(R.id.btnDone);
                        tempButton.setVisibility(View.VISIBLE);
                    } else {
                        Workout toRemove = myUser.user_workouts.get(arrayID);
                        myUser.user_workouts.remove(toRemove);
                        if (myUser.user_workouts.size() == 0) {
                            Toast.makeText(workout_view.this, "Week is complete! Restarted Routine.", Toast.LENGTH_LONG).show();
                            MyApplication.user.setRoutine((int) MyApplication.user.routine_id, MyApplication.routineList.get(0).workouts);
                        }
                        Intent intent = new Intent(MyApplication.getContext(), MyWorkouts.class);
                        startActivity(intent);
                    }
                    myUser.updateToFirebase();
                }
            }
        };


        LayoutInflater inflater = getLayoutInflater();


        for(int i = 0; i < exerciseArray.size(); i++){
            ExerciseStats currentExercise = exerciseArray.get(i);
            Bitmap exImage = MyApplication.exerciseList.get(currentExercise.exercise).image;

            ConstraintLayout newLayout = (ConstraintLayout) inflater.inflate(R.layout.workout_template, scroll,false);
            newLayout.setTag(i);

            TextView exerciseName = (TextView) newLayout.findViewById(R.id.exerciseName);
            String exerciseNameTemp = MyApplication.exerciseList.get(currentExercise.exercise).name;
            exerciseName.setText(exerciseNameTemp);
            exerciseName.setTag(currentExercise.trigger_max_change);

            TextView exerciseWeight = (TextView) newLayout.findViewById(R.id.exerciseWeight);
            exerciseWeight.setText(String.valueOf(currentExercise.weight));
            Log.d("WEIGHT: ", String.valueOf(currentExercise.weight));

            TextView exerciseReps = (TextView) newLayout.findViewById(R.id.exerciseReps);
            if(!currentExercise.trigger_max_change) exerciseReps.setText(String.valueOf(currentExercise.reps));

            TextView exerciseSets = (TextView) newLayout.findViewById(R.id.exerciseSets);
            exerciseSets.setText(String.valueOf(currentExercise.sets));

            Button btnDone = (Button) newLayout.findViewById(R.id.btnDone);
            btnDone.setTag(i);
            if(!currentExercise.trigger_max_change) btnDone.setOnClickListener(listener);

            ImageView exerciseImage = (ImageView) newLayout.findViewById(R.id.exerciseImage);
            exerciseImage.setImageBitmap(exImage);

            // Set push exercise stats
            if(currentExercise.trigger_max_change){
                btnDone.setBackgroundTintList(MyApplication.getContext().getResources().getColorStateList(R.color.btn_yellow));
                btnDone.setTextColor(getResources().getColor(R.color.black));
                btnDone.setOnClickListener(listener);
                exerciseName.setTextColor(getResources().getColor(R.color.btn_yellow));
                exerciseReps.setHint("∞");
            }

            if(i != 0)btnDone.setVisibility(View.INVISIBLE);

            scroll.addView(newLayout);
        }
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(getApplicationContext(), MyWorkouts.class));
        finish();
    }
}