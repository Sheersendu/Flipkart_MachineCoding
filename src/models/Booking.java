package models;

public class Booking {
    private String bookingID;
    private Slot slot;
    private Patient patient;
    private Doctor doctor;


    public Booking(String booking_ID, Slot slot, Patient patient, Doctor doctor) {
        this.bookingID = booking_ID;
        this.slot = slot;
        this.patient = patient;
        this.doctor = doctor;
    }

    public Slot getBookingSlot()
    {
        return this.slot;
    }

    public String getBookingID()
    {
        return this.bookingID;
    }

    public Doctor getDoctor()
    {
        return this.doctor;
    }

    public Patient getPatient()
    {
        return this.patient;
    }
}
