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
import java.util.Hashtable;

public class MultiEchoClient{
    private static final int PORT = 1234;
    private static Socket link;
    private static BufferedReader in;
    private static PrintWriter out;
    private static BufferedReader kbd;

    public static void main(String[] args) throws Exception{
        try{
            link = new Socket("127.0.0.1", PORT);
            in = new BufferedReader(new InputStreamReader(link.getInputStream()));
            out = new PrintWriter(link.getOutputStream(), true);
            kbd = new BufferedReader(new InputStreamReader(System.in));
            String message, response;
            System.out.println("Enter name: ");
            String clientName = kbd.readLine();
            out.println(clientName);//send clientName to server

            do{
                System.out.println("Enter message (BYE to quit)");//to client
                message = kbd.readLine();
                out.println(message);//goes to server
                System.out.println(in.readLine());//from server back to client

            }while (!message.equals("BYE"));
        }
        catch(UnknownHostException e){System.exit(1);}
        catch(IOException e){System.exit(1);}
        finally{
            try{
                if (link!=null){
                    System.out.println("Closing");
                    link.close();
                }
            }
            catch(IOException e){System.exit(1);}
        }
    }//end main
}//end class MultiEchoClient
	
