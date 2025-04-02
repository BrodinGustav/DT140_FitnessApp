package com.example.App.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

import java.util.List;

@Entity
public class Activity {

    //Properties

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private int points;


    //Konstruktor

    protected Activity() {
        //Standard konstruktor enligt JPA
    }

    public Activity(String name, Integer points) {
        this.name = name;
        this.points = points;
        }



    //Relationer
    
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @OneToMany(mappedBy = "activity", cascade = CascadeType.ALL)
    private List<UserActivity> userActivities;


    // Getters and setters
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public List<UserActivity> getUserActivities() {
        return userActivities;
    }

    public void setUserActivities(List<UserActivity> userActivities) {
        this.userActivities = userActivities;
    }



    
    @Override
    public String toString() {
        return "Activity [id=" + id + ", name=" + name + ", points=" + points + ", category=" + category
                + ", userActivities=" + userActivities + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + points;
        result = prime * result + ((category == null) ? 0 : category.hashCode());
        result = prime * result + ((userActivities == null) ? 0 : userActivities.hashCode());
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
        if (category == null) {
            if (other.category != null)
                return false;
        } else if (!category.equals(other.category))
            return false;
        if (userActivities == null) {
            if (other.userActivities != null)
                return false;
        } else if (!userActivities.equals(other.userActivities))
            return false;
        return true;
    }












}
