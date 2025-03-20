package lt.viko.eif.madamkevicius.webservices.menu;

import lt.viko.eif.madamkevicius.webservices.FileNameGetter;
import lt.viko.eif.madamkevicius.webservices.model.Appointment;
import lt.viko.eif.madamkevicius.webservices.model.Appointments;
import lt.viko.eif.madamkevicius.webservices.repo.AppointmentRepo;
import lt.viko.eif.madamkevicius.webservices.service.Transformable;
import lt.viko.eif.madamkevicius.webservices.service.TransformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class UserMenu {

    private AppointmentRepo appointmentRepo;
    private Appointments appointments;
    private TransformationService transformationService;
    private FileNameGetter get;

    @Autowired
    public UserMenu(AppointmentRepo appointmentRepo) {
        this.appointmentRepo = appointmentRepo;
        this.transformationService = new TransformationService();
        appointments = new Appointments();
        get = new FileNameGetter();
    }

    public int displayMenu(Scanner input) {
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
        System.out.println("|                                3.Transform from XML to JavaObject                     |");
        System.out.println("|                                6.Exit                                                 |");
        System.out.println("-----------------------------------------------------------------------------------------");
        return input.nextInt();
    }

    //TODO
    public void showMenu() {
        Scanner input = new Scanner(System.in);
        String file = "";
        int choice;
        do {
            choice = displayMenu(input);
            switch (choice) {
                case 1:
                    appointments.setAppointments(appointmentRepo.findAll());
                    for(Appointment appointment : appointments.getAppointments()) {
                        System.out.println(appointment);
                    }
                    break;
                case 2:
                    do {
                        file =  get.getFileName();
                    } while (file.equals(""));

                    if(appointments.getAppointments() == null || appointments.getAppointments().isEmpty()) {
                        System.out.println("Data is empty");
                    } else {
                        transformationService.transformToXml(appointments, file);
                    }
                    break;
            }
        } while (choice != 6);
    }

}
