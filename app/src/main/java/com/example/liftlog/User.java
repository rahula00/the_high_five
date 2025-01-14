package com.example.liftlog;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Build;
import android.util.Log;
import android.graphics.BitmapFactory;

import androidx.annotation.RequiresApi;
import androidx.core.util.Pair;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.regex.Pattern;

public class User{
    private Context context;
    public String email;
    public Bitmap profile_pic;
    public String name;
    public GregorianCalendar birthDate;
    public boolean sex;
    public Pair<Integer,Integer> height;
    public float weight;
    public float routine_id;
    public HashMap<String, Integer> user_max;
    public List<Workout> user_workouts;

    public User(String nEmail, String nName, GregorianCalendar nBirthDate, boolean nSex, Integer feet, Integer inches, float nWeight, List<Workout> queueWorkout){
        //random values
        this.context = MyApplication.getContext();
        this.email = nEmail;
        this.name = nName;
        this.birthDate = nBirthDate;
        this.sex = nSex;
        this.height = new Pair<>(feet, inches);
        this.weight = nWeight;
        this.user_max = new HashMap<>();

        initExerciseMaxes();
        this.user_workouts = queueWorkout;
        this.profile_pic = BitmapFactory.decodeResource(context.getResources(), R.drawable.resource_default);
   }

    public User(String nEmail){
        //random values
        this.context = MyApplication.getContext();
        this.email = nEmail;
        this.name = "";
        this.birthDate = new GregorianCalendar();
        this.sex = true;
        this.height = new Pair<Integer, Integer>(0, 0);
        this.weight = 0;
        this.user_max = new HashMap<String, Integer>();
        initExerciseMaxes();
        this.user_workouts = new ArrayList<>();
        this.profile_pic = null;
    }

    public User(DataSnapshot dataObj)
    {
        height = new Pair<>(0, 0);
        this.email = (String) dataObj.child("email").getValue();
        this.name = (String) dataObj.child("name").getValue();
        this.sex = (boolean) dataObj.child("sex").getValue();
        long H1 = (long) dataObj.child("height").child("first").getValue();
        long H2 = (long) dataObj.child("height").child("second").getValue();
        this.height = new Pair<>((int) H1, (int) H2);
        Object W1 = dataObj.child("weight").getValue();
        if (W1 instanceof Number) {
            weight = ((Number) W1).floatValue();
        }
        long R1 = (long)dataObj.child("routine_id").getValue();
        this.routine_id = (int) R1;

        this.user_workouts = new ArrayList<>();
        for(DataSnapshot workSnap : dataObj.child("user_workouts").getChildren())
        {
            Workout newWork = new Workout((long)workSnap.child("id").getValue(), (String) workSnap.child("name").getValue(), (String) workSnap.child("description").getValue());
            if(workSnap.child("statsList")!= null) {
                ArrayList<ExerciseStats> tempArrayList = new ArrayList<>();
                for(DataSnapshot exerSnap : workSnap.child("statsList").getChildren())
                {
                    long id = (long) exerSnap.child("exercise").getValue();
                    long weightassigned = (long) exerSnap.child("weight").getValue();
                    long numreps = (long) exerSnap.child("reps").getValue();
                    long numsets = (long) exerSnap.child("sets").getValue();
                    boolean trigger = (boolean) exerSnap.child("trigger_max_change").getValue();
                    ExerciseStats newExer = new ExerciseStats((int) id, (int) weightassigned, (int) numreps, (int) numsets, trigger);
                    tempArrayList.add(newExer);
                }
                newWork.statsList = new LinkedList<>(tempArrayList);
            }
            user_workouts.add(newWork);
        }
        long T1 = (long) dataObj.child("birthDate").child("timeInMillis").getValue();
        this.birthDate = new GregorianCalendar();
        this.birthDate.setTimeInMillis(T1);
        HashMap<String, Long> tempMap = (HashMap<String,Long>) dataObj.child("user_max").getValue();
        this.user_max = new HashMap<String, Integer>();
        initExerciseMaxes();
        for (HashMap.Entry<String, Long> entry : tempMap.entrySet()) {
            user_max.put(entry.getKey(), entry.getValue().intValue());
        }

    }

    public User(){}

    void initExerciseMaxes(){
        ArrayList<Exercise> exerciseArray = (MyApplication.exerciseList);
        user_max.put("0_k",0);
        for(Exercise tempEx : exerciseArray) {
            StringBuilder exID = new StringBuilder();
            exID.append(tempEx.ID);
            exID.append("_k");
            user_max.put(exID.toString(),0);
        }
    }

    boolean setEmail(String nEmail){
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$";
        Pattern p = Pattern.compile(emailRegex);
        if(p.matcher(nEmail).matches()){
            email = nEmail;
            return true;
        }
        return false;
    }

    void setName(String nName){
        name = nName;
    }

    boolean setDate(Integer year, Integer month, Integer day){
        GregorianCalendar cal = new GregorianCalendar();
        cal.set(year, month, day);
        //kind of a bad way to check the date. checks if the day they put in was less than the current date
        if(birthDate.get(Calendar.DAY_OF_YEAR) < cal.get(Calendar.DAY_OF_YEAR) && birthDate.get(Calendar.YEAR) < cal.get(Calendar.YEAR)){
            return false;
        }
        birthDate.set(year, month, day);
        return true;
    }

    void setSex(boolean nSex){
        sex = nSex;
    }

    boolean setHeight(Integer feet, Integer inches){
        //high doubts you are going to be less than 3 feet and working out
        if(feet > 3){
            height = new Pair<>(feet, inches);
            return true;
        }
        return false;
    }

    boolean setWeight(float nWeight) {
        if(nWeight>0) {
            weight = nWeight;
            return true;
        }
        return false;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    void setRoutine(Integer id, List<Workout> workoutQueue){
        this.routine_id = id;
        this.user_workouts = new ArrayList(workoutQueue);
        this.user_workouts.forEach(workout -> {

            NSuns.init_workout(workout, this.user_max);
        });
    }

    boolean setUser_max(String id, Integer weight){
        if(weight>0){
            this.user_max.put(id,weight);
            return true;
        }
        return false;
    }

    void setProfile_pic(Bitmap newImage){
        this.profile_pic = newImage;
    }

    void updateToFirebase()
    {
        String UID = FirebaseAuth.getInstance().getCurrentUser().getUid();
        DatabaseReference uploadRef = FirebaseDatabase.getInstance().getReference("Users").child(UID);
        uploadRef.setValue(this);
    }


    void printOut()
    {
        String printMe = "\nemail:" +email+"\nname:" +name+"\nbirthDate:" +birthDate+"\nsex:" +sex+"\nheight:" +height+
                "\nweight:" +weight+"\nroutine:" +routine_id+"\nuser_max:" +user_max+"\nuser_workouts:" +user_workouts;
        Log.i("printOut", printMe);
    }
}
