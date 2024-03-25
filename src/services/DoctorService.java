package services;

import datastore.DataStore;
import enums.DoctorSpecialityEnum;
import models.Doctor;
import models.Slot;

import java.util.List;


public class DoctorService {

    private static DataStore dataStore = new DataStore();

    public static Doctor register(String name, DoctorSpecialityEnum speciality)
    {
        Doctor newDoctor = new Doctor(name, speciality);
        dataStore.addDoctor(newDoctor);
        return newDoctor;
    }

    public static void addSlot(String name, List<Slot> slots)
    {
        Doctor requestedDoctor = getDoctor(name);
        for(Slot slot : slots)
        {
            requestedDoctor.addSlot(slot);
        }
    }
    private static Doctor getDoctor(String name)
    {
        for (Doctor doctor : dataStore.getDoctorList()) {
            if (doctor.getName().equals(name))
                return doctor;
        }
        throw new IllegalArgumentException("Doctor Doesn't exists");
    }
}
