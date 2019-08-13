/**
 * Sample Skeleton for 'ClientUI.fxml' Controller Class
 */

package Controller;

import java.io.IOException;

import Application.ClientConsole;
import Boundery.GUIPlayer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

/**
 * The Class MainController.
 */
public class MainController
{
	
	/** The last thread. */
	public static Object last;
	
	/** The screen. */
	GUIPlayer screen = new GUIPlayer();
	
	/** The client. */
	static ClientConsole client;
	
    /** The button connect. */
    @FXML // fx:id="Btn_Connect"
    private Button Btn_Connect; // Value injected by FXMLLoader
    
    /** The connection text. */
    @FXML // fx:id="conText"
    private Text conText; // Value injected by FXMLLoader

    /** The Server host. */
    @FXML // fx:id="ServerHost"
    private TextField ServerHost; // Value injected by FXMLLoader

    /** The Port number. */
    @FXML // fx:id="PortNum"
    private TextField PortNum; // Value injected by FXMLLoader

    /**
     * Connect to the server.
     *
     * @param event the event
     * @throws IOException Signals that an I/O exception has occurred.
     */
    @FXML
    void Connect(ActionEvent event) throws IOException
    {
    	if(!ServerHost.getText().isEmpty() && !PortNum.getText().isEmpty())// Checking if the serverAdd and the portNum field are not empty.
    	{
    		try
        	{	
        		client = new ClientConsole(ServerHost.getText(), PortNum.getText());
        		
        	}catch(Exception ex){// If the connection failed the program shows a message to the client.
        		}
    		if(client.Flag)
    			screen.Login();
    		else
    			displayConn("       Server is offline");
    	}
    	else// If there are empty fields.
    		displayConn("You must Fill all the fields");
    }
    
    /**
     * Display message in connection field.
     *
     * @param s the s
     */
    public  void displayConn(String s)
	{
		conText.setText(s);	
	}
    
}