package models;

import enums.DoctorSpecialityEnum;

import java.util.ArrayList;
import java.util.List;

public class Doctor extends BaseModel{

    private List<Slot> slots = new ArrayList<>();

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
