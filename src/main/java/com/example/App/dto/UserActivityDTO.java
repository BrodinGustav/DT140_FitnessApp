package com.example.App.dto;

import java.util.List;

public class UserActivityDTO {

    //Properties
    
    private Integer id;
    private Integer userId;
    private Integer activityId;
    private int points;
    private String userName;  
    private String activityName;  

    private List<UserActivityDTO> userActivities;


    // Getters and setters
    
    public List<UserActivityDTO> getActivities() {
        return userActivities;
    }

    public void setActivities(List<UserActivityDTO> userActivities) {
        this.userActivities = userActivities;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getActivityId() {
        return activityId;
    }

    public void setActivityId(Integer activityId) {
        this.activityId = activityId;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }
}
