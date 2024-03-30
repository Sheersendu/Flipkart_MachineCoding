package models;

import models.Booking;

import java.util.ArrayList;
import java.util.List;

public class BaseModel {

    private String name;


    BaseModel(String name)
    {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
