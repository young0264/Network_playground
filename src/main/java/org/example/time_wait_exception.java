package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class time_wait_exception {

    public static void main(String[] args) {
        ServerExam();
        ClientExam();
    }

    public static void ServerExam() {
        int port = 8080;

        try {
            ServerSocket serverSocket = new ServerSocket(port);
            System.out.println("Server started on port " + port);

            Socket clientSocket = serverSocket.accept();
            System.out.println("Client connected");

            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

            String receivedMessage = in.readLine();
            System.out.println("Received message from client: " + receivedMessage);

            out.println("Hello from server!");

            clientSocket.close();
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void ClientExam() {
        String serverAddress = "localhost";
        int serverPort = 8080;

        try {
            Socket clientSocket = new Socket(serverAddress, serverPort);
            System.out.println("Connected to server");

            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

            out.println("Hello from client!");

            String receivedMessage = in.readLine();
            System.out.println("Received message from server: " + receivedMessage);

            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
