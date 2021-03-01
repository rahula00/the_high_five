package com.example.liftlog;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.util.ArrayList;
import java.util.LinkedList;


public class NSuns extends Routine{
    static final int divisor = 5;
    static final int bench = 0;
    static final int c_g_bench = 1;
    static final int deadlift = 2;
    static final int front_squat = 3;
    static final int oh_press = 4;
    static final int squat = 5;
    static final int s_deadlift = 6;
    static final boolean push = true;

    static final String nsun_descr = "nSuns 5/3/1 is a linear progression powerlifting program" +
            " that was inspired by Jim Wendler’s 5/3/1 strength program. It progresses on a" +
            " weekly basis, making it well suited for late stage novice and early intermediate" +
            " lifters. It is known for its challenging amount of volume. Those who stick with " +
            "it tend to find great results from the additional work capacity." +
            "EXPERIENCE LEVEL: BEGINNER, INTERMEDIATE\n" +
            "WEEKS: INDEFINITE\n" +
            "PERIODIZATION: LINEAR PERIODIZATION\n" +
            "POWERLIFTING MEET PREP PROGRAM: NO\n" +
            "PROGRAM GOAL: HIGH VOLUME, POWERLIFTING, STRENGTH\n" +
            "USES RPE:NO\n" +
            "USES 1RM PERCENTAGE(%):YES";
    static final ArrayList<LinkedList<ExerciseStats>> exercises = new ArrayList<LinkedList<ExerciseStats>>() {
        {
            add(new LinkedList<ExerciseStats>() {
                {
                    add(new ExerciseStats(bench, 0, 8, 1));
                    add(new ExerciseStats(bench, 0, 6, 1));
                    add(new ExerciseStats(bench, 0, 4, 1));
                    add(new ExerciseStats(bench, 0, 4, 1));
                    add(new ExerciseStats(bench, 0, 4, 1));
                    add(new ExerciseStats(bench, 0, 5, 1));
                    add(new ExerciseStats(bench, 0, 6, 1));
                    add(new ExerciseStats(bench, 0, 7, 1));
                    add(new ExerciseStats(bench, 0, 8, 1));
                    add(new ExerciseStats(oh_press, 0, 6, 1));
                    add(new ExerciseStats(oh_press, 0, 5, 1));
                    add(new ExerciseStats(oh_press, 0, 3, 1));
                    add(new ExerciseStats(oh_press, 0, 5, 1));
                    add(new ExerciseStats(oh_press, 0, 7, 1));
                    add(new ExerciseStats(oh_press, 0, 4, 1));
                    add(new ExerciseStats(oh_press, 0, 6, 1));
                    add(new ExerciseStats(oh_press, 0, 8, 1));
                }
            });
            add(new LinkedList<ExerciseStats>() {
                {
                    add(new ExerciseStats(squat, 0, 5, 1));
                    add(new ExerciseStats(squat, 0, 3, 1));
                    add(new ExerciseStats(squat, 0, 1, 1, push));
                    add(new ExerciseStats(squat, 0, 3, 1));
                    add(new ExerciseStats(squat, 0, 3, 1));
                    add(new ExerciseStats(squat, 0, 3, 1));
                    add(new ExerciseStats(squat, 0, 5, 1));
                    add(new ExerciseStats(squat, 0, 5, 1));
                    add(new ExerciseStats(squat, 0, 5, 1));
                    add(new ExerciseStats(s_deadlift, 0, 5, 1));
                    add(new ExerciseStats(s_deadlift, 0, 5, 1));
                    add(new ExerciseStats(s_deadlift, 0, 3, 1));
                    add(new ExerciseStats(s_deadlift, 0, 5, 1));
                    add(new ExerciseStats(s_deadlift, 0, 7, 1));
                    add(new ExerciseStats(s_deadlift, 0, 4, 1));
                    add(new ExerciseStats(s_deadlift, 0, 6, 1));
                    add(new ExerciseStats(s_deadlift, 0, 8, 1));
                }
            });
            add(new LinkedList<ExerciseStats>() {
                {
                    add(new ExerciseStats(bench, 0, 5, 1));
                    add(new ExerciseStats(bench, 0, 3, 1));
                    add(new ExerciseStats(bench, 0, 1, 1, push));
                    add(new ExerciseStats(bench, 0, 3, 1));
                    add(new ExerciseStats(bench, 0, 3, 1));
                    add(new ExerciseStats(bench, 0, 3, 1));
                    add(new ExerciseStats(bench, 0, 5, 1));
                    add(new ExerciseStats(bench, 0, 5, 1));
                    add(new ExerciseStats(bench, 0, 5, 1));
                    add(new ExerciseStats(c_g_bench, 0, 6, 1));
                    add(new ExerciseStats(c_g_bench, 0, 5, 1));
                    add(new ExerciseStats(c_g_bench, 0, 3, 1));
                    add(new ExerciseStats(c_g_bench, 0, 5, 1));
                    add(new ExerciseStats(c_g_bench, 0, 7, 1));
                    add(new ExerciseStats(c_g_bench, 0, 4, 1));
                    add(new ExerciseStats(c_g_bench, 0, 6, 1));
                    add(new ExerciseStats(c_g_bench, 0, 8, 1));
                }
            });
            add(new LinkedList<ExerciseStats>() {
                {
                    add(new ExerciseStats(deadlift, 0, 5, 1));
                    add(new ExerciseStats(deadlift, 0, 3, 1));
                    add(new ExerciseStats(deadlift, 0, 1, 1, push));
                    add(new ExerciseStats(deadlift, 0, 3, 1));
                    add(new ExerciseStats(deadlift, 0, 5, 1));
                    add(new ExerciseStats(deadlift, 0, 3, 1));
                    add(new ExerciseStats(deadlift, 0, 5, 1));
                    add(new ExerciseStats(deadlift, 0, 3, 1));
                    add(new ExerciseStats(deadlift, 0, 5, 1));
                    add(new ExerciseStats(front_squat, 0, 5, 1));
                    add(new ExerciseStats(front_squat, 0, 5, 1));
                    add(new ExerciseStats(front_squat, 0, 3, 1));
                    add(new ExerciseStats(front_squat, 0, 5, 1));
                    add(new ExerciseStats(front_squat, 0, 7, 1));
                    add(new ExerciseStats(front_squat, 0, 4, 1));
                    add(new ExerciseStats(front_squat, 0, 6, 1));
                    add(new ExerciseStats(front_squat, 0, 8, 1));
                }
            });
        }
    };
    static final String[] names = {"Monday","Tuesday","Thursday","Friday"};
    static final String[] descrs = {
            "Bench Press and Overhead Press",
            "Squat and Sumo Deadlift",
            "Bench Press and Close Grip Bench Press",
            "Deadlift and Front Squat"
    };

    public NSuns() {
        super();
        this.name = "nSuns 5/3/1";
        this.description = nsun_descr;
        init_workouts();
    }

    public NSuns(int new_id, String n_routine, String n_descr, ArrayList<Workout> n_wkouts) {
        super(new_id, n_routine, n_descr, n_wkouts);
    }

    public void init_workouts() {
        for (int i = 0; i < 4; ++i) {
            this.workouts.add(new Workout(0, names[i], descrs[i], exercises.get(i)));
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void init_workout(Workout to_init, int usr_max) {
        to_init.statsList.forEach( exercise -> {
            exercise.weight = (int) Math.floor(.90 * usr_max);
            //ensure that weight set is divisble evenly by 5
            int remainder = exercise.weight % divisor;
            if (remainder != 0) {
                exercise.weight = exercise.weight + (divisor - remainder);
            }
        });
    }

    public int suggest_increase(int reps_completed) {
        switch (reps_completed) {
            case 0:
            case 1: {
                return 0;
            }
            case 2:
            case 3: {
                return 5;
            }
            case 4:
            case 5: {
                return 10;
            }
            default : {
                return 15;
            }
        }
    }
}
