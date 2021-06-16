package com.priyanka.client;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class AppClient
{
    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;

    public static void main(String[] args) throws Exception {
        AppClient client = new AppClient();

        // Establish connection
        client.startConnection("127.0.0.1", 6666);

        // Input data
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();

        String response = client.sendMessage(input);

        System.out.println(response);

        client.stopConnection();
    }

    public void startConnection(String ip, int port) throws Exception{
        clientSocket = new Socket(ip, port);
        out = new PrintWriter(clientSocket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
    }

    public String sendMessage(String msg) throws Exception{
        out.println(msg);
        String response = in.readLine();
        return response;
    }

    public void stopConnection() throws Exception{
        in.close();
        out.close();
        clientSocket.close();
    }
}
