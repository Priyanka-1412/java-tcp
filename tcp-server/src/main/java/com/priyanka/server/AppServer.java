package com.priyanka.server;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class AppServer
{
    private ServerSocket serverSocket;
    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;

    public static void main( String[] args ) throws Exception
    {
        AppServer server = new AppServer();

        server.start(6666);
        server.stop();
    }

    // Start server and open input output stream and socket
    public void start(int port) throws Exception {

        System.out.println("Server listening on port: "+ port);
        serverSocket = new ServerSocket(port);

        clientSocket = serverSocket.accept();

        out = new PrintWriter(clientSocket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

        String receivedData = in.readLine();

        out.println(reverse(receivedData.toUpperCase()));
    }

    // Stop all input output stream and socket connection
    public void stop() throws Exception{
        in.close();
        out.close();
        clientSocket.close();
        serverSocket.close();
    }

    /**
     * Reverse a String
     * @param str String
     * @return String
     */
    public static String reverse(String str) {
        return new StringBuilder(str).reverse().toString();
    }
}
