package services;

import datastore.DataStore;
import enums.DoctorSpecialityEnum;
import models.Doctor;
import models.Patient;
import models.Slot;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class PatientService {

    public static void getDoctorsBySpeciality(DoctorSpecialityEnum speciality)
    {
        HashMap<String, ArrayList<String>> doctorTimings = new HashMap<>();
        List<Doctor> doctorSpecialityEnumListHashMap = DataStore.getSpecialityToDoctorMap(speciality);
        doctorSpecialityEnumListHashMap.forEach((doctor) -> {
            List<Slot> doctorSlots = doctor.getSlots();
            doctorSlots.forEach((slot) -> {
                String formattedSlot = String.format("%11s", slot.getDetails()).replace(' ', '0');
                if(!doctorTimings.containsKey(formattedSlot))
                {
                    ArrayList<String> doctorList = new ArrayList<>();
                    doctorList.add(doctor.getName());
                    doctorTimings.put(formattedSlot, doctorList);
                }
                else
                {
                    doctorTimings.get(formattedSlot).add(doctor.getName());
                }
            });
        });
        ArrayList<String> sortedKeys = new ArrayList<>(doctorTimings.keySet());
        if(sortedKeys.isEmpty())
        {
            doctorSpecialityEnumListHashMap.forEach((doctor) -> {
                System.out.println("Dr. " + doctor.getName() + ": No slots available!");
            });
        }
        else
        {
            Collections.sort(sortedKeys);
            sortedKeys.forEach((key) -> {
                doctorTimings.get(key).forEach((doctorName) -> {
                    System.out.println("Dr. " + doctorName + ": (" + key + ")");
                });
            });
        }
    }

    public static Patient register(String name)
    {
        Patient newPatient = new Patient(name);
        DataStore.addPatient(newPatient);
        return newPatient;
    }

    public static Patient getPatient(String name)
    {
        List<Patient> patients = DataStore.getPatientList();
        for(Patient patient : patients)
        {
            if(patient.getName().equals(name))
                return patient;
        }
        throw new IllegalArgumentException("Patient does not exists!");
    }
}
