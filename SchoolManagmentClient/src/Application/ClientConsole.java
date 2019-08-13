package Application;

import java.io.*;
import Common.ChatClient;
import Common.ChatIF;
import Controller.MainController;


/**
 * The Class ClientConsole to create new client and transfer messages to the server.
 */
public class ClientConsole implements ChatIF 
{
	
	/** The client. */
	public ChatClient client;
	
	/** The Flag. */
	public boolean Flag = false;
	
	/**
	 * Instantiates a new client console.
	 *
	 * @param host the host
	 * @param port the port
	 */
	@SuppressWarnings("unused")
	public ClientConsole(String host, String port) 
	{
		MainController m = (MainController)MainController.last;
		try// Try to connect to the Host server using specific port.
		{
			int Port = Integer.valueOf(port);
			client = new ChatClient(host, Port, this);
			Flag = true;
		} 
		catch(IOException exception)// If the connection didn't success the program will be terminated.
		{
			System.out.println("Error: Can't setup connection!");
		    Flag = false;
		}
	}
	
	/**
	 * Method to send the message to the server.
	 *
	 * @param arr the message for the server
	 */
	public void Accept(Object arr)
	{
		try
		{
			client.handleMessageFromClientUI(arr);// Sending the message from the client to the server.
		}catch (Exception ex){ // In case the message didn't arrive the program will show a message to the client.
	    	System.out.println("Unexpected error while reading from console!");
	    	}
	}

	/**
	 * print the message that arrives from the server in the console
	 */
	public void display(String message) 
	{
		System.out.println("> " + message);
	}
	
	/**
	 * Gets the client.
	 *
	 * @return the client in string
	 */
	public String GetClient()
	{
		return client.toString();
	}
}
