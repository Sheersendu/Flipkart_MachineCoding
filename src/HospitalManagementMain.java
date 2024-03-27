import enums.DoctorSpecialityEnum;
import enums.UserOptionEnum;
import models.Slot;
import services.DoctorService;
import java.util.List;
import java.util.Scanner;

public class HospitalManagementMain {
    public static void main(String[] args) {
        System.out.println("Hi from hospital management!");
        Scanner scannerObject = new Scanner(System.in);
        while (true)
        {
            String userInput = scannerObject.nextLine();
            if(userInput.equals("quit"))
                break;
            else
            {
                try
                {
                    List<String> userInputList = List.of(userInput.split("->"));
                    String userOption = userInputList.getFirst().trim();
                    UserOptionEnum userOptionEnum = UserOptionEnum.valueOf(userOption);
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
                        default:
                            System.out.println("Invalid option!");
                    }
                }
                catch (Exception e)
                {
                    System.out.println(e.getLocalizedMessage());
                }
            }
        }
    }
}
