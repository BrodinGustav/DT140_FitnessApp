package com.example.App.model;


public enum Activity {
        GYM(30),
        LÖPNING(70),
        SIMNING(60),
        GOLF(27),
        FOTBOLL(60),
        ISHOCKEY(65),
        LÄNGDSKIDOR(90);

        public int value;
        
        public int getValue() {
            return value;
        }

        private Activity(int value) {
            this.value = value;
        }

        
}
