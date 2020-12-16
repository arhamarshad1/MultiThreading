/*
Arham Arshad
CSCI 3171
B00768939
Assignment 4 - Question 2

This program implements a multi threaded chat with multiple clients

Note:
I finally got the clients to send each other messages but they were delayed for some reason, I don't know why.
 */
import java.io.*;
import java.net.*;
import java.util.*;

public class MultiEchoServer{
    //declare the ServerSocket variable and the port number for the server(constant)
    private static ServerSocket serverSock;
    private static final int PORT = 1234;

    //The main method will create the ServerSocket object and listens to inputs
    //from multiple clients

    public static void main(String[] args) throws IOException{
        try{
            serverSock = new ServerSocket(PORT);
        }
        catch(IOException e){
            System.out.println("Can't listen on " + PORT);
            System.exit(1);
        }
        do{
            Socket client = null;
            System.out.println("Listening for connection ...");

            try{
                client = serverSock.accept();
                System.out.println("New client accepted");
                ClientHandler handler = new ClientHandler(client);
                handler.start();
            }
            catch(IOException e){
                System.out.println("Accept failed");
                System.exit(1);
            }
            System.out.println("Connection successful");
            System.out.println("Listening for input ...");
        } while (true);
    }//end main
}//end class MultiEchoServer
