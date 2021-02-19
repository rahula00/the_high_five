package com.example.liftlog;

import android.graphics.Bitmap;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.util.Pair;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Hashtable;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.regex.Pattern;

public class User{
    public String email;
    public Bitmap profile_pic;
    public String name;
    public Calendar birthDate;
    public boolean sex;
    public Pair<Integer,Integer> height;
    public float weight;
    public float routine_id;
    public Hashtable<Integer, ArrayList<Pair<Calendar,Integer>>> user_max;
    public Queue<Workout> user_workout;

    public User(String nEmail, String nName, Calendar nBirthDate, boolean nSex, Integer feet, Integer inches, float nWeight, Queue<Workout> queueWorkout){
        //random values
        this.email = nEmail;
        this.name = nName;
        this.birthDate = nBirthDate;
        this.sex = nSex;
        this.height = new Pair<>(feet, inches);
        this.weight = nWeight;
        ArrayList<Pair<Calendar,Integer>> init_max_list = new ArrayList<Pair<Calendar,Integer>>();
        Pair<Calendar, Integer> init_max = new Pair<Calendar,Integer> (Calendar.getInstance(), 0);
        init_max_list.add(init_max);
        this.user_max = new Hashtable<Integer, ArrayList<Pair<Calendar, Integer>>>();
        this.user_max.put(0,init_max_list); //0 because the initial id is 0 probably needs a fix
        this.user_workout = queueWorkout;
        this.profile_pic = null;
   }

    public User(String nEmail){
        //random values
        this.email = nEmail;
        this.name = "";
        Calendar date = Calendar.getInstance();
        this.birthDate = date;
        this.sex = true;
        this.height = new Pair<>(0, 0);
        this.weight = 0;
        ArrayList<Pair<Calendar,Integer>> init_max_list = new ArrayList<Pair<Calendar,Integer>>();
        Pair<Calendar, Integer> init_max = new Pair<Calendar,Integer> (Calendar.getInstance(), 0);
        init_max_list.add(init_max);
        this.user_max = new Hashtable<Integer, ArrayList<Pair<Calendar, Integer>>>();
        //Creates max of "0" for each exercise in array
        ArrayList<Exercise> exerciseArray = (MyApplication.exerciseList);

        for(int aI = 0; aI < exerciseArray.size(); aI++) {
            Pair<Calendar, Integer> temp_max = new Pair<Calendar,Integer> (Calendar.getInstance(), 0);
            ArrayList<Pair<Calendar,Integer>> tempList = new ArrayList<Pair<Calendar,Integer>>();
            tempList.add(temp_max);
            int exId = exerciseArray.get(aI).ID;
            this.user_max.put(exId,tempList);
        }

        this.user_workout = new PriorityQueue<>();
        this.profile_pic = null;
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
        Calendar cal = Calendar.getInstance();
        birthDate.set(year, month, day);
        //kind of a bad way to check the date. checks if the day they put in was less than the current date
        if(birthDate.get(Calendar.DAY_OF_YEAR) < cal.get(Calendar.DAY_OF_YEAR) && birthDate.get(Calendar.YEAR) < cal.get(Calendar.YEAR)){
            return true;
        }
        return false;
    }

    void setSex(boolean nSex){
        sex = nSex;
    }

    boolean setHeight(Integer feet, Integer inches){
        //high doubts you are going to be less than 3 feet and working out
        if(feet > 3){
            height = new Pair<Integer,Integer>(feet,inches);
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

    void setRoutine_id(Integer id){
        routine_id = id;
    }

    boolean setUser_max(Integer id, Integer weight){
        ArrayList<Pair<java.util.Calendar, Integer>> currentWeightArray = this.user_max.get(id);
        if(weight == currentWeightArray.get(currentWeightArray.size()-1).second){
            return true;
        }
        if(weight>0){
            Pair<Calendar, Integer> new_max = new Pair<Calendar,Integer> (Calendar.getInstance(), weight);
            ArrayList<Pair<Calendar,Integer>> current_max_list = user_max.get(id);
            current_max_list.add(new_max);
            user_max.put(id,current_max_list);
            return true;
        }
        return false;
    }

    void setProfile_pic(Bitmap newImage){
        this.profile_pic = newImage;
    }
}
