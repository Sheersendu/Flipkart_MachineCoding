package models;

import enums.DoctorSpecialityEnum;

import java.util.ArrayList;
import java.util.List;

public class Doctor extends BaseModel{

    private final List<Slot> slots = new ArrayList<>();
    private List<Booking> bookings = new ArrayList<>();

    private final DoctorSpecialityEnum speciality;


    public Doctor(String name, DoctorSpecialityEnum speciality)
    {
        super(name);
        this.speciality = speciality;
    }

    public void addSlot(Slot slot)
    {
        for(Booking existingBooking : bookings)
        {
            if(existingBooking.getBookingSlot().getDetails().equals(slot.getDetails()))
                throw new IllegalArgumentException("Slot: " + slot.getDetails() + " is already booked!");
        }
        this.slots.add(slot);
    }

    public List<Slot> getSlots()
    {
        return this.slots;
    }

    public DoctorSpecialityEnum getSpeciality()
    {
        return this.speciality;
    }

    public void addBooking(Booking booking)
    {
        Slot bookingSlot = booking.getBookingSlot();
        Slot matchingSlot = null;
        for(Booking existingBooking : bookings)
        {
            if(existingBooking.getBookingSlot().getDetails().equals(bookingSlot.getDetails()))
                throw new IllegalArgumentException("Slot is already booked!");
        }
        for(Slot slot : slots)
        {
            if(slot.getDetails().equals(bookingSlot.getDetails()))
            {
                matchingSlot = slot;
                break;
            }
        }
        if(matchingSlot != null)
        {
            this.bookings.add(booking);
            this.slots.remove(matchingSlot);
        }
        else {
            throw new IllegalArgumentException("Doctor has no such slot available!");
        }
    }

    public void cancelBooking(Booking booking)
    {
        Slot bookingSlot = booking.getBookingSlot();
        bookings.remove(booking);
        slots.add(bookingSlot);
    }

}
