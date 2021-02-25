package com.example.liftlog;

import java.lang.reflect.Array;
import java.util.ArrayList;


public class Routine{
    public int id;
    public String name;
    public String description;
    public String imageUrl;
    public ArrayList<Workout> workouts;

    public Routine(int new_id, String new_routineName, String new_description, String new_imageUrl, ArrayList<Workout> new_workouts)
    {
        this.id = new_id;
        this.name = new_routineName;
        this.description = new_description;
        this.imageUrl = new_imageUrl;

        this.workouts = new ArrayList<Workout>();
        for(Workout i : new_workouts)
            this.workouts.add(i.copy());
    }

    Routine copy()
    {
        return new Routine(id, name, description, imageUrl, workouts);
    }

    ArrayList<Workout> copyWorkouts()
    {
        ArrayList<Workout> workout_queue = new ArrayList<>();
        for(Workout i : workouts)
            workout_queue.add(i.copy());
        return workout_queue;
    }
}
