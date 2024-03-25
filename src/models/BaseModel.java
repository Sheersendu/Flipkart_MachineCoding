package models;

import models.Booking;

import java.util.ArrayList;
import java.util.List;

public class BaseModel {

    private String name;
    private List<Booking> bookings = new ArrayList<>();

    BaseModel(String name)
    {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public List<Booking> getBookings() {
        return bookings;
    }

    public void addBooking(Booking booking)
    {
        this.bookings.add(booking);
    }
}
