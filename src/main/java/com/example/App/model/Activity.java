package com.example.App.model;

public enum Activity {
        GYM(50),
        LÖPNING(25),
        SIMNING(150),
        GOLF(3),
        FOTBOLL(30),
        ISHOCKEY(30),
        LÄNGDSKIDOR(400);

        private int value;

        public int value() {
            return this.value;
        }
        
        public int getValue() {
            return value;
        }

        private Activity(int value) {
            this.value = value;
        }


}
