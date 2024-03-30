import enums.DoctorSpecialityEnum;
import enums.UserOptionEnum;
import models.Booking;
import models.Doctor;
import models.Patient;
import models.Slot;
import services.BookingService;
import services.DoctorService;
import services.PatientService;

import java.util.List;
import java.util.Scanner;

public class HospitalManagementMain {
    public static void main(String[] args) {
        System.out.println(new String(new char[50]).replace("\0", "*"));
        System.out.println("Welcome to Hospital Management System!");
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
                            System.out.println("Doctor Name : Timing");
                            PatientService.getDoctorsBySpeciality(doctorSpeciality);
                            break;
                        case UserOptionEnum.registerPatient:
                            String patientName = userInputList.get(1).trim();
                            PatientService.register(patientName);
                            System.out.println(patientName + " registered successfully!");
                            break;
                        case UserOptionEnum.bookAppointment:
                            patientName = userInputList.get(1).trim();
                            doctorName = userInputList.get(2).trim();
                            slots = Slot.formatSlots(userInputList.get(3).trim().split(","));
                            Slot userSlot = slots.getFirst();
                            Booking newBooking = BookingService.createBooking(userSlot, patientName, doctorName);
                            System.out.println("Booking successful! Booking Id: " + newBooking.getBookingID());
                            break;
                        case UserOptionEnum.cancelBookingID:
                            String bookingID = userInputList.get(1).trim();
                            BookingService.cancelBooking(bookingID);
                            System.out.println("Booking cancelled!");
                            break;
                        case UserOptionEnum.showBookings:
                            patientName = userInputList.get(1).trim();
                            System.out.println("Booking ID -|- Doctor Name -|- Slot Timings");
                            BookingService.showPatientBooking(patientName);
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
