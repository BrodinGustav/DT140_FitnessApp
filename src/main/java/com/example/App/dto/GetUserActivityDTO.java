package com.example.App.dto;

import java.time.Duration;

public class GetUserActivityDTO {
    
    //Properties
    private Integer userId;
     private Duration duration;

    

    public Integer getUserId() {
        return userId;
    }

     public void setUserId(Integer userId) {
         this.userId = userId;
     }

     public Duration getDuration() {
         return duration;
     }

     public void setDuration(Duration duration) {
         this.duration = duration;
     }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((userId == null) ? 0 : userId.hashCode());
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
        GetUserActivityDTO other = (GetUserActivityDTO) obj;
        if (userId == null) {
            if (other.userId != null)
                return false;
        } else if (!userId.equals(other.userId))
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
        return "GetUserActivityDTO [userId=" + userId + ", duration=" + duration + "]";
    }

}