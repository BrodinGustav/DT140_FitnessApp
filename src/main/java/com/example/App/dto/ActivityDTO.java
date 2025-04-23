package com.example.App.dto;

public class ActivityDTO {

    private String activity;
    private Integer value;

    public ActivityDTO(String activity, Integer value) {
        this.activity = activity;
        this.value = value;

    }

     // getters
     public String getActivity() {
        return activity;
    }

    public Integer getValue() {
        return value;
    }

    
}
