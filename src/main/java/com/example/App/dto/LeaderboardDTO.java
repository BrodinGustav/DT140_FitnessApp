package com.example.App.dto;

public class LeaderboardDTO {
    
    private String name;
    private String activity;
    private int points;
    private int duration;

    public LeaderboardDTO(String name, String activity, int points, int duration) {
        this.name = name;
        this.activity = activity;
        this.points = points;
        this.duration = duration;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getActivity() {
        return activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((activity == null) ? 0 : activity.hashCode());
        result = prime * result + points;
        result = prime * result + duration;
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
        LeaderboardDTO other = (LeaderboardDTO) obj;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (activity == null) {
            if (other.activity != null)
                return false;
        } else if (!activity.equals(other.activity))
            return false;
        if (points != other.points)
            return false;
        if (duration != other.duration)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "LeaderboardDTO [name=" + name + ", activity=" + activity + ", points=" + points + ", duration="
                + duration + "]";
    }
    
    
}
