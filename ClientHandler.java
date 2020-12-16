/*
Arham Arshad
CSCI 3171
B00768939
Assignment 4 - Question 2

This program implements a multi threaded chat with multiple clients

Note:
I finally got the clients to send each other messages but they were delayed for some reason, I don't know why :(s
 */
//This is a support class that extends Thread, runs the client thread
//and sends and receives messages
import java.io.*;
import java.net.*;
import java.util.*;
public class ClientHandler extends Thread{
    private Socket client;
    private BufferedReader in;
    private PrintWriter out;
    //Hash table for Client Names and corresponding PrintWriter objects
    private static Hashtable<String, PrintWriter> writers = new Hashtable<>();
    //Hash table for Client IDs and corresponding message received
    private static Hashtable<Integer,String> clientNames = new Hashtable<>();

    public ClientHandler(Socket socket){
        client = socket;
        try{
            in = new BufferedReader(new InputStreamReader(client.getInputStream()));
            out = new PrintWriter(client.getOutputStream(), true);
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }
    public void run(){
        try{
            String received;
            int counter = 0;//to check num of messages
            String clientName = in.readLine(); //read name of client
            System.out.println(clientName + " has joined");
            do{
                writers.put(clientName,out);// add to hashtable
                received = in.readLine();//read from client
                System.out.println("Message from " + clientName + ": " + received);//to server
                clientNames.put(counter,received);//add client messages to hastable
                Set <String> setOfClients = writers.keySet();//get all keys as strings
                //for loop broadcasts messages to all clients except the client the client that sent the message
                for (String key : setOfClients) {
                    if(!(key.equals(clientName))) {
                        writers.get(key).println("Message from " + clientName + " : " + clientNames.get(counter));//sends back to current client
                    }
                }
                counter++;
            } while (!received.equals("BYE"));
        }
        catch(IOException e){
            e.printStackTrace();
        }
        finally{
            try{
                if(client!=null){
                    System.out.println("Closing connection");
                    client.close();
                }
            }
            catch(IOException e){
                e.printStackTrace();
            }
        }
    }//end run
}//end ClientHandler class
