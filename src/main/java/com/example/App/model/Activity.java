package com.example.App.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Activity {

    
    public enum ActivityName {
        GYM,
        LÖPNING,
        SIMNING,
        GOLF,
        FOTBOLL,
        ISHOCKEY,
        LÄNGDSKIDOR
    }

    //Properties

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private ActivityName name;
    private int points;


    //Konstruktor

    protected Activity() {
        //Standard konstruktor enligt JPA
    }

    public Activity(ActivityName name, Integer points) {
        this.name = name;
        this.points = points;
        }



      // Getters and setters
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ActivityName getName() {
        return name;
    }

    public void setName(ActivityName name) {
        this.name = name;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    
  
    
    @Override
    public String toString() {
        return "Activity [id=" + id + ", name=" + name + ", points=" + points + ", ]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + points;
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
        Activity other = (Activity) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (points != other.points)
            return false;
        return true;
    }












}
