package models;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class Slot {
    private String startTime;
    private String endTime;

    public Slot(String startTime, String endTime) {
        this.startTime = startTime;
        this.endTime = endTime;
    }

    private boolean validSlot()
    {
        String[] slotStartTime = this.startTime.split(":");
        String[] endStartTime = this.endTime.split(":");
        int startHour = Integer.parseInt(slotStartTime[0]), startMinute = Integer.parseInt(slotStartTime[1]);
        int endHour = Integer.parseInt(endStartTime[0]), endMinute = Integer.parseInt(endStartTime[1]);
        if (((endHour - startHour) > 1) || ((endMinute - startMinute) >= 1))
            throw new IllegalArgumentException("Slots are only 60 minutes");
        if ((endHour <= startHour) || (startHour < 9) || (startHour >= 21) || (endHour < 10) || (endHour >= 21))
            throw new IllegalArgumentException("Invalid slot timings");
        return true;
    }

    public static List<Slot> formatSlots(String[] slots)
    {
        List<Slot> slotList = new ArrayList<>();
        for(String slot : slots)
        {
            String[] slotTimings = slot.split("-");
            String startTime = slotTimings[0].trim(), endTime = slotTimings[1].trim();
            Slot newSlot = new Slot(startTime,endTime);
            if (newSlot.validSlot())
            {
                slotList.add(newSlot);
            }
        }
        return slotList;
    }

}
