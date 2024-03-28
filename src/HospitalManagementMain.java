import enums.DoctorSpecialityEnum;
import enums.UserOptionEnum;
import models.Slot;
import services.DoctorService;
import services.PatientService;

import java.util.List;
import java.util.Scanner;

public class HospitalManagementMain {
    public static void main(String[] args) {
        System.out.println(new String(new char[50]).replace("\0", "*"));
        System.out.println("Welcome to hospital management system!");
        System.out.println(new String(new char[50]).replace("\0", "*"));
        Scanner scannerObject = new Scanner(System.in);
        while (true)
        {
            System.out.print("i: ");
            String userInput = scannerObject.nextLine().trim();
            if(userInput.equals("quit"))
                break;
            else
            {
                try
                {
                    List<String> userInputList = List.of(userInput.split("->"));
                    String userOption = userInputList.getFirst().trim();
                    UserOptionEnum userOptionEnum = UserOptionEnum.valueOf(userOption);
                    System.out.print("o: ");
                    switch (userOptionEnum) {
                        case UserOptionEnum.registerDoc:
                            String doctorName = userInputList.get(1).trim();
                            DoctorSpecialityEnum doctorSpeciality = DoctorSpecialityEnum.valueOf(userInputList.get(2).trim());
                            DoctorService.register(doctorName, doctorSpeciality);
                            System.out.println("Welcome " + doctorName);
                            break;
                        case UserOptionEnum.markDocAvail:
                            doctorName = userInputList.get(1).trim();
                            List<Slot> slots = Slot.formatSlots(userInputList.get(2).trim().split(","));
                            DoctorService.addSlot(doctorName, slots);
                            System.out.println("Done Doc!");
                            break;
                        case UserOptionEnum.showAvailBySpeciality:
                            doctorSpeciality = DoctorSpecialityEnum.valueOf(userInputList.get(1).trim());
                            System.out.println("Name : Timing");
                            PatientService.getDoctorsBySpeciality(doctorSpeciality);
                            break;
                        default:
                            System.out.println("Invalid option!" + userOption + userOptionEnum);
                    }
                }
                catch (Exception e)
                {
                    System.out.println(e);
                }
            }
        }
    }
}
