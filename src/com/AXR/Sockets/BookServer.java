package com.AXR.Sockets;

import com.AXR.HTTP.GoogleAPI;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

import static java.net.InetAddress.*;

public class BookServer {

    public static void Initialize(){

        int portNumber = 1111;

        try {
            InetAddress inetAddress = InetAddress.getLocalHost();
            System.out.print("Server initialized on port " + portNumber + " On IP " + inetAddress);

        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

        try (
                ServerSocket serverSocket = new ServerSocket(portNumber);
                Socket clientSocket = serverSocket.accept();
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))

        ) {

            String inputLine, outputLine;

            //9789043026970
            // Initiate conversation with client
            out.println("Welcome to Bookserver v1.0. Enter an ISBN number to find out more about the book.");

            while ((inputLine = in.readLine()) != null) {

                GoogleAPI GApi = new GoogleAPI("/books/v1/volumes?q=isbn:");
                out.println(GApi.executeQuery(inputLine));
            }
        } catch (IOException e) {
            System.out.println("\nException caught when trying to listen on port "
                    + portNumber + " or listening for a connection");
            System.out.println(e.getMessage());
        }
    }
}
