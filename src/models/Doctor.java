package models;

import enums.DoctorSpecialityEnum;

import java.util.HashSet;
import java.util.Set;

public class Doctor extends BaseModel{

    private Set<Slot> slots = new HashSet<>();

    private DoctorSpecialityEnum speciality;


    public Doctor(String name, DoctorSpecialityEnum speciality)
    {
        super(name);
        this.speciality = speciality;
    }

    public void addSlot(Slot slot)
    {
        this.slots.add(slot);
    }

}
