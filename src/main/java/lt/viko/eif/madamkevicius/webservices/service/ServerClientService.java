package lt.viko.eif.madamkevicius.webservices.service;

import lt.viko.eif.madamkevicius.webservices.controller.FileController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

@Service
public class ServerClientService {

    private DataOutputStream dos;

    private DataInputStream dis;

    private FileController fileController;

    @Autowired
    public ServerClientService(FileController fileController) {
        this.fileController = fileController;
    }

    public void connectToServer(String hostName, int port, String filePath) {
        try(Socket socket = new Socket(hostName, port)) {
            dis = new DataInputStream(socket.getInputStream());
            dos = new DataOutputStream(socket.getOutputStream());
            System.out.println("Connected to " + hostName + " on port " + port);
            System.out.println("Sending file to server...");

            dos.writeUTF(filePath);
            fileController.sendFile(dos, filePath);


            dis.close();
            dos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
