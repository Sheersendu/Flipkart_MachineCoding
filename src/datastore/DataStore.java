package datastore;

import enums.DoctorSpecialityEnum;
import models.Booking;
import models.Doctor;
import models.Patient;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DataStore {
    private static List<Doctor> doctorList = new ArrayList<>();
    private static List<Patient> patientList = new ArrayList<>();
    private static HashMap<DoctorSpecialityEnum, List<Doctor>> specialityToDoctorMap = new HashMap<>();

    private static HashMap<String, Booking> bookings = new HashMap<>();

    public static void addDoctor(Doctor doctor) {
        doctorList.add(doctor);
        List<Doctor> doctorSpecialityList = specialityToDoctorMap.get(doctor.getSpeciality());
        if(doctorSpecialityList == null)
        {
            List<Doctor> newDoctorList = new ArrayList<>();
            newDoctorList.add(doctor);
            specialityToDoctorMap.put(doctor.getSpeciality(), newDoctorList);
        }
        else{
            specialityToDoctorMap.get(doctor.getSpeciality()).add(doctor);
        }

    }

    public static void addPatient(Patient patient) {
        patientList.add(patient);
    }

    public static void addBooking(Booking booking) {
        String bookingID = booking.getBookingID();
        bookings.put(bookingID, booking);
    }

    public static void cancelBooking(String bookingID) {
        bookings.remove(bookingID);
    }

    public static List<Doctor> getDoctorList() {
        return doctorList;
    }

    public static List<Patient> getPatientList() {
        return patientList;
    }

    public static Booking getBooking(String bookingID) {
        return bookings.getOrDefault(bookingID, null);
    }

    public static List<Doctor> getSpecialityToDoctorMap(DoctorSpecialityEnum speciality) {
        return specialityToDoctorMap.getOrDefault(speciality, new ArrayList<>());
    }
}
