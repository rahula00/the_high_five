package com.example.liftlog;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Workout {
    public long id;
    public String name;
    public String description;
    public LinkedList<ExerciseStats> statsList;

    public Workout(long id, String workoutName, String description,  LinkedList<ExerciseStats> exercisesStats)
    {
        //I hate this and I want to change it, but it creates bugs
        this.id=id;
        this.name = workoutName;
        this.description = description;

        this.statsList = new LinkedList<>();
        for(ExerciseStats i : exercisesStats)
            statsList.add(i.copy());
    }

    public Workout(long id, String workoutName, String description)
    {
        this.id = id;
        this.name = workoutName;
        this.description = description;
        this.statsList = new LinkedList<ExerciseStats>();
    }

    public Workout copy()
    {
        return new Workout(id, name, description, statsList);
    }

    @NonNull
    @Override
    public String toString() {
        return "Id:"+id + "\nName:"+ name+ "\nDesc:"+ description+ "\nArray:"+ statsList.toString();
    }
}
