package lt.viko.eif.madamkevicius.webservices.service;

import lt.viko.eif.madamkevicius.webservices.controller.FileController;
import org.springframework.stereotype.Service;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

@Service
public class ServerClientService {

    private DataOutputStream dos;

    private DataInputStream dis;

    private FileController fileController;

    public ServerClientService() {
        fileController = new FileController();
    }

    public void connectToServer(String hostName, int port, String filePath) {
        try(Socket socket = new Socket(hostName, port)) {
            dis = new DataInputStream(socket.getInputStream());
            dos = new DataOutputStream(socket.getOutputStream());
            System.out.println("Sending file to server...");
            System.out.println("Connected to " + hostName + " on port " + port);
            System.out.println("Sending file...");

            fileController.sendFile(dos, filePath);


            dis.close();
            dos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
