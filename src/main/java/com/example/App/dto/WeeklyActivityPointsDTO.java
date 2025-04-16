package com.example.App.dto;

import com.example.App.model.Activity;

public class WeeklyActivityPointsDTO {
    private Integer id;
    private Activity activity;
    private int totalPoints;
    private int points;

    public WeeklyActivityPointsDTO(Integer id, Activity activity, Integer points) {
        this.id = id;
        this.activity = activity;
        this.points = points;
    }

    public Integer getPoints() {
        return points;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    public int getTotalPoints() {
        return totalPoints;
    }

    public void setTotalPoints(int totalPoints) {
        this.totalPoints = totalPoints;
    }

}
