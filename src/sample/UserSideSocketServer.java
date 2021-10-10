package sample;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class UserSideSocketServer {
    public static void main(String[] args) {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(6600);
            Socket sc = serverSocket.accept();
            OutputStreamWriter o = new OutputStreamWriter(sc.getOutputStream());
            BufferedWriter sWriter = new BufferedWriter(o);
            InputStreamReader isr = new InputStreamReader(sc.getInputStream());
            BufferedReader sReader = new BufferedReader(isr);
            Scanner scn =new Scanner(new File("src/sample/Place_population"));
            while (scn.hasNext()){
                String placeName = scn.nextLine();
                String totalPeople = scn.nextLine();
                sWriter.write(placeName+"\n");
                sWriter.write(totalPeople+"\n");
                sWriter.flush();
            }sWriter.write("Null"+"\n");
            sWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
