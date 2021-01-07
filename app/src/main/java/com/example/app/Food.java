package com.example.app;

public class Food  {
    private String Name;
    private Long Calories;

    public Food(){}

    public Food(String Name, Long Calories) {
        this.Name = Name;
        this.Calories = Calories;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public Long getCalories() {
        return Calories;
    }

    public void setCalories(Long Calories) {
        this.Calories = Calories;
    }
}


