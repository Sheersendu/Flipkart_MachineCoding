package models;

import java.util.List;
public class Patient extends BaseModel{
    private String name;
    private List<Booking> bookings;

    public Patient(String name) {
        super(name);
    }
}
