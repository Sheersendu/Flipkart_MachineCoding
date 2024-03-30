package services;

import datastore.DataStore;
import models.Booking;
import models.Doctor;
import models.Patient;
import models.Slot;

import java.util.List;

public class BookingService {
    private static int BookingNumber = 0;

    public static Booking createBooking(Slot slot, String patientName, String doctorName)
    {
        Doctor doctor = DoctorService.getDoctor(doctorName);
        Patient patient = PatientService.getPatient(patientName);
        Booking newBooking = new Booking(generateBookingID(), slot, patient, doctor);
        try {
            // next 3 steps should be transactional but how?
            patient.addBooking(newBooking);
            doctor.addBooking(newBooking);
            DataStore.addBooking(newBooking);
        }
        catch (Exception e)
        {
            BookingNumber -= 1;
            throw e;
        }
        return newBooking;
    }

    public static void cancelBooking(String bookingID)
    {
        Booking booking = DataStore.getBooking(bookingID);
        if(booking == null)
        {
            throw new IllegalArgumentException("Invalid Booking ID!");
        }
        Doctor doctor = booking.getDoctor();
        Patient patient = booking.getPatient();
        doctor.cancelBooking(booking);
        patient.cancelBooking(booking);
        DataStore.cancelBooking(bookingID);
    }

    public static void showPatientBooking(String patientName)
    {
        Patient patient = PatientService.getPatient(patientName);
        List<Booking> patientBookings = patient.getBookings();
        if(patientBookings.isEmpty())
        {
            System.out.println(patientName + " has no bookings!");
        }
        else
        {
            for(Booking booking : patientBookings)
            {
                System.out.println(booking.getBookingID() + " -|- Dr. " + booking.getDoctor().getName() + " -|- " + booking.getBookingSlot().getDetails());
            }
        }
    }

    private static String generateBookingID()
    {
        BookingNumber += 1;
        return String.format("%6s", BookingNumber).replace(' ', '0');
    }
}
