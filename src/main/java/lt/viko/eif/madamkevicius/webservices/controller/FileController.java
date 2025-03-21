package lt.viko.eif.madamkevicius.webservices.controller;

import java.io.*;
import java.util.Scanner;

public class FileController {

    public String getFileName() {
        String fileName;
        Scanner reader  = new Scanner(System.in);
        System.out.println("Enter file name:");
        fileName = reader.nextLine();
        return fileName;
    }

    public void sendFile(DataOutputStream dos, String filePath) throws IOException {
        int bytes = 0;
        File file = new File(filePath);
        FileInputStream fis = new FileInputStream(file);

        dos.writeLong(file.length());

        byte[] buffer = new byte[4 * 1024];
        while((bytes = fis.read(buffer)) != -1) {
            dos.write(buffer, 0, bytes);
            dos.flush();
        }
        fis.close();
    }
}
