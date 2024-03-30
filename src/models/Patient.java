package models;

import java.util.ArrayList;
import java.util.List;
public class Patient extends BaseModel{
    private List<Booking> bookings = new ArrayList<>();

    public Patient(String name) {
        super(name);
    }

    public void addBooking(Booking booking)
    {
        Slot bookingSlot = booking.getBookingSlot();
        for(Booking existingBooking : bookings)
        {
            if(existingBooking.getBookingSlot().getDetails().equals(bookingSlot.getDetails()))
                throw new IllegalArgumentException("Slot is already booked!");
        }
        bookings.add(booking);
    }

    public void cancelBooking(Booking booking)
    {
        bookings.remove(booking);
    }

    public List<Booking> getBookings()
    {
        return this.bookings;
    }
}
