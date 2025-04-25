package com.example.App.dto;

import java.util.List;

import com.example.App.model.Activity;
import com.example.App.model.UserActivity;

public class WeeklyActivityPointsDTO {
    private int userId;
    private Activity activity;
    private int points;
    private List<UserActivity> activities;

    public WeeklyActivityPointsDTO(int userId, Activity activity, int points, List<UserActivity> activities) {
        this.userId = userId;
        this.activity = activity;
        this.points = points;
        this.activities = activities;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public List<UserActivity> getActivities() {
        return activities;
    }

    public void setActivities(List<UserActivity> activities) {
        this.activities = activities;
    }

    

}
