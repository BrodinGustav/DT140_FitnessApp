package com.example.App.dto;

import org.springframework.validation.annotation.Validated;

import com.example.App.model.Activity;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

@Validated
public class CreateUserActivityDTO {

    //Properties
    @NotNull
    private Integer userId;
    @Valid
    private Activity activity;
    @NotNull
    private Integer seconds;

    public Integer getUserId() {
        return userId;
    }
    public Activity getActivity() {
        return activity;
    }
    public Integer getSeconds() {
        return seconds;
    }
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((userId == null) ? 0 : userId.hashCode());
        result = prime * result + ((activity == null) ? 0 : activity.hashCode());
        result = prime * result + ((seconds == null) ? 0 : seconds.hashCode());
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
        if (activity != other.activity)
            return false;
        if (seconds == null) {
            if (other.seconds != null)
                return false;
        } else if (!seconds.equals(other.seconds))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "PutUserActivityDTO [userId=" + userId + ", activityName=" + activity + ", duration=" + seconds + "]";
    }
    
}
