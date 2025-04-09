package com.example.App.dto;

import java.time.Duration;

import com.example.App.model.Activity.ActivityName;

public class CreateUserActivityDTO {

    //Properties
    private Integer Id;
    private Integer userId;
    private ActivityName activityName;
    private Duration duration;


    public Integer getId() {
        return Id;
    }

    public Integer getUserId() {
        return userId;
    }
    public ActivityName getActivityName() {
        return activityName;
    }
    public Duration getDuration() {
        return duration;
    }
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((userId == null) ? 0 : userId.hashCode());
        result = prime * result + ((activityName == null) ? 0 : activityName.hashCode());
        result = prime * result + ((duration == null) ? 0 : duration.hashCode());
        return result;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        CreateUserActivityDTO other = (CreateUserActivityDTO) obj;
        if (userId == null) {
            if (other.userId != null)
                return false;
        } else if (!userId.equals(other.userId))
            return false;
        if (activityName != other.activityName)
            return false;
        if (duration == null) {
            if (other.duration != null)
                return false;
        } else if (!duration.equals(other.duration))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "PutUserActivityDTO [userId=" + userId + ", activityName=" + activityName + ", duration=" + duration + "]";
    }
    
}
