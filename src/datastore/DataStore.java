package datastore;

import models.Doctor;
import models.Patient;

import java.util.ArrayList;
import java.util.List;

public class DataStore {
    private List<Doctor> doctorList = new ArrayList<>();
    private List<Patient> patientList = new ArrayList<>();

    public void addDoctor(Doctor doctor) {
        this.doctorList.add(doctor);
    }

    public void addPatient(Patient patient) {
        this.patientList.add(patient);
    }

    public List<Doctor> getDoctorList() {
        return doctorList;
    }

    public List<Patient> getPatientList() {
        return patientList;
    }
}
