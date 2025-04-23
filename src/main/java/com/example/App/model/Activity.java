package com.example.App.model;

import java.util.Arrays;
import java.util.List;

import jakarta.persistence.Entity;

public enum Activity {
        GYM(50),
        LÖPNING(25),
        SIMNING(150),
        GOLF(3),
        FOTBOLL(30),
        ISHOCKEY(30),
        LÄNGDSKIDOR(400);

        public int value;
        
        public int getValue() {
            return value;
        }

        private Activity(int value) {
            this.value = value;
        }

        
/* 
        public static List<activityList> thing() {
            record Thing(String name, int value) {}
            return Arrays.stream(Activity.values())
            .map(activity -> {
                return new Thing(activity.name, activity.value);
            })
            .toList();
        }
            */


}
