package lt.viko.eif.madamkevicius.webservices;

import java.util.Scanner;

public class FileNameGetter  {

    public String getFileName() {
        String fileName;
        Scanner reader  = new Scanner(System.in);
        System.out.println("Enter file name:");
        fileName = reader.nextLine();
        return fileName;
    }
}
