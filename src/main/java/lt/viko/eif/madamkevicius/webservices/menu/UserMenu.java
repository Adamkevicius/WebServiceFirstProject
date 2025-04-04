package lt.viko.eif.madamkevicius.webservices.menu;

import lt.viko.eif.madamkevicius.webservices.controller.FileController;
import lt.viko.eif.madamkevicius.webservices.model.Appointment;
import lt.viko.eif.madamkevicius.webservices.model.Appointments;
import lt.viko.eif.madamkevicius.webservices.repo.AppointmentRepo;
import lt.viko.eif.madamkevicius.webservices.service.ServerClientService;
import lt.viko.eif.madamkevicius.webservices.service.TransformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import java.io.File;
import java.util.Scanner;

@Component
public class UserMenu {

    private AppointmentRepo appointmentRepo;
    private Appointments appointments;
    private TransformationService transformationService;
    private ServerClientService serverClientService;
    private FileController fileController;

    @Autowired
    public UserMenu(AppointmentRepo appointmentRepo, FileController fileController, ServerClientService serverClientService, TransformationService transformationService, Appointments appointments) {
        this.appointmentRepo = appointmentRepo;
        this.fileController = fileController;
        this.serverClientService = serverClientService;
        this.transformationService = transformationService;
        this.appointments = appointments;
    }

    public String displayMenu(Scanner input) {
        System.out.println(" __    __                                      __       __                               \n" +
                "/  |  /  |                                    /  \\     /  |                              \n" +
                "$$ |  $$ |  _______   ______    ______        $$  \\   /$$ |  ______   _______   __    __ \n" +
                "$$ |  $$ | /       | /      \\  /      \\       $$$  \\ /$$$ | /      \\ /       \\ /  |  /  |\n" +
                "$$ |  $$ |/$$$$$$$/ /$$$$$$  |/$$$$$$  |      $$$$  /$$$$ |/$$$$$$  |$$$$$$$  |$$ |  $$ |\n" +
                "$$ |  $$ |$$      \\ $$    $$ |$$ |  $$/       $$ $$ $$/$$ |$$    $$ |$$ |  $$ |$$ |  $$ |\n" +
                "$$ \\__$$ | $$$$$$  |$$$$$$$$/ $$ |            $$ |$$$/ $$ |$$$$$$$$/ $$ |  $$ |$$ \\__$$ |\n" +
                "$$    $$/ /     $$/ $$       |$$ |            $$ | $/  $$ |$$       |$$ |  $$ |$$    $$/ \n" +
                " $$$$$$/  $$$$$$$/   $$$$$$$/ $$/             $$/      $$/  $$$$$$$/ $$/   $$/  $$$$$$/  \n");
        System.out.println("-----------------------------------------------------------------------------------------");
        System.out.println("|                                1.Receive data                                         |");
        System.out.println("|                                2.Transform to XML and save to file                    |");
        System.out.println("|                                3.Transform to POJO                                    |");
        System.out.println("|                                4.Send Xml file to server                              |");
        System.out.println("|                                5.Exit                                                 |");
        System.out.println("-----------------------------------------------------------------------------------------");
        return input.nextLine();
    }

    public void showMenu() {
        Scanner input = new Scanner(System.in);
        String file = "";
        String choice;
        do {
            choice = displayMenu(input);
            switch (choice) {
                case "1":
                    appointments.setAppointments(appointmentRepo.findAll());
                    for(Appointment appointment : appointments.getAppointments()) {
                        System.out.println(appointment);
                    }
                    break;
                case "2":
                    do {
                        file =  fileController.getFileName();
                    } while (file.equals(""));

                    if(appointments.getAppointments() == null || appointments.getAppointments().isEmpty()) {
                        System.out.println("Data is empty");
                    } else {
                        transformationService.transformToXml(appointments, file);
                    }
                    break;
                case "3":
                    do {
                        file =  fileController.getFileName();
                    } while (file.equals(""));

                    File isFileExists = new File(file);

                    if(isFileExists.exists()) {
                        transformationService.transformToPOJO(file);
                    } else {
                        System.out.println("File does not exist");
                    }
                    break;
                case "4":
                    do {
                        file =  fileController.getFileName();
                    } while (file.equals(""));

                    serverClientService.connectToServer("localhost", 9090, file);
                    break;
                case "5":
                    System.out.println("\nGoodbye User!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice");
                    break;
            }
        } while (!choice.equals("5"));
    }

}
