package Controller;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.Inet4Address;
import java.net.UnknownHostException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

import java.util.logging.Level;
import java.util.logging.Logger;

import Application.EchoServer;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import Boundery.GUIPlayer;
import OCSF.ConnectionToClient;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

/**
 * The Class Server2Controller is for create and export server logs.
 */
public class Server2Controller extends Server1Controller
{
	
	/** The last thread. */
	public static Object last;
	
	/** The Log. */
	public ArrayList<String> Log = new ArrayList<String>();
	
	/** The Connected devices. */
	public static ArrayList<String> ConDevices = new ArrayList<String>();
	
	/** The Connection time. */
	public static ArrayList<String> ConTime = new ArrayList<String>();
	
	/** The Log 2. */
	public static ArrayList<String> Log2 = new ArrayList<String>();
	
	/** The Log time. */
	public static ArrayList<String> LogTime = new ArrayList<String>();
	
	/** The Clear log button. */
    @FXML // fx:id="ClrLog"
    private Button ClrLog; // Value injected by FXMLLoader
    
	/** The exit button. */
	@FXML // fx:id="Btn_Exit"
    private Button Btn_Exit; // Value injected by FXMLLoader

    /** The IP address. */
    @FXML // fx:id="ipAdd"
    private Text ipAdd; // Value injected by FXMLLoader

    /** The export devices list button. */
    @FXML // fx:id="expDevices"
    private Button expDevices; // Value injected by FXMLLoader

    /** The port number. */
    @FXML // fx:id="portNum"
    private Text portNum; // Value injected by FXMLLoader

    /** The Connected devices. */
    @FXML // fx:id="ConnectedDevices"
    private TextArea ConnectedDevices; // Value injected by FXMLLoader

    /** The DB connection. */
    @FXML // fx:id="dbConnection"
    private Text dbConnection; // Value injected by FXMLLoader

    /** The Date. */
    @FXML // fx:id="Date"
    private Text Date; // Value injected by FXMLLoader

    /** The Log screen. */
    @FXML // fx:id="LogScreen"
    private TextArea LogScreen; // Value injected by FXMLLoader
    
    /** The export log button. */
    @FXML // fx:id="expLog"
    private Button expLog; // Value injected by FXMLLoader

	
	/**
	 * Instantiates a new server 2 controller.
	 */
	public Server2Controller()
	{
		last=this;
		Platform.runLater(new Runnable()
   		{
   			@Override
   			public void run()
   			{
   				try
   				{
   					portNum.setText(port1);
   					setTime();
   					ipAdd.setText(Inet4Address.getLocalHost().getHostAddress().toString());
				}catch (UnknownHostException e){
					e.printStackTrace();
					}
   			}
   		});	
	}
	
    
	/**
     * export all the connected devices log file.
     * @param event Gets the ActionEvent when the function called.
     */
    @FXML
    void ExportConnectedDevicesFile(ActionEvent event)
    {
    	String Dev = "ASIS - GROUP 1\nCONNECTED DEVICES REPORT\n" +
    			"GENERATION TIME : " + getTime() + "\n" +
    			"_____________________________________" + "\n\n\n\n" ;
    	FileChooser fileChooser = new FileChooser(); 
        //Set extension filter
    	FileChooser.ExtensionFilter DocExt = new FileChooser.ExtensionFilter("Word file (*.doc)", "*.doc");
        FileChooser.ExtensionFilter TxtExt = new FileChooser.ExtensionFilter("Text file (*.txt)", "*.txt");
        fileChooser.getExtensionFilters().add(DocExt); 
        fileChooser.getExtensionFilters().add(TxtExt);             
        //Show save file dialog
        File file = fileChooser.showSaveDialog(GUIPlayer.primaryStage);             
        if(file != null){
        	Dev = Dev.concat(CreateString(ConTime,ConDevices,"\n"));
        	SaveFile(Dev, file);
        }
    }
   
    /**
     * export events log file
     * @param event Gets the ActionEvent when the function called.
     */
    @FXML
    void ExportLogFile(ActionEvent event)
    {
    	String Log = "ASIS - GROUP 1\nLOG REPORT\n" +
    			"GENERATION TIME : " + getTime() + "\n" +
    			"_____________________________________" + "\n\n\n\n" ;
    	FileChooser fileChooser = new FileChooser(); 
        //Set extension filter
        FileChooser.ExtensionFilter DocExt = new FileChooser.ExtensionFilter("Word file (*.doc)", "*.doc");
        FileChooser.ExtensionFilter TxtExt = new FileChooser.ExtensionFilter("Text file (*.txt)", "*.txt");
        fileChooser.getExtensionFilters().add(DocExt); 
        fileChooser.getExtensionFilters().add(TxtExt); 
        //Show save file dialog
        File file = fileChooser.showSaveDialog(GUIPlayer.primaryStage);             
        if(file != null){
        	Log = Log.concat(CreateString(LogTime,Log2,"\n"));
        	SaveFile(Log, file);
        	LogTime.clear();
    		Log2.clear();
            ClearLogFile(event);
        }
    }

    /**
     * exit from server
     * @param event Gets the ActionEvent when the function called.
     */
    @FXML
    void Exit(ActionEvent event)
    {    	
    	EchoServer.updateAll();
    }
  
    
    /**
     * Sets the time.
     */
    public void setTime()
    {
    	Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
            	DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            	Calendar cal = Calendar.getInstance();
            	String time=dateFormat.format(cal.getTime()).toString();
            	Date.setText(time);
            }
        }, 0, 1000);
    }
    
    
    /**
     * Adds a connected device to the list.
     * @param client the current client
     */
    public void AddDisplayDevice (ConnectionToClient client)
    {
		Platform.runLater(new Runnable()
		{
			@Override
			public void run()
			{
				ConTime.add("["+getTime()+"]");
				ConDevices.add(client.toString());
				ConnectedDevices.setText(CreateString(ConTime,ConDevices,""));
			}
		});
	}
    
    
    /**
     * delete a connected device from the list.
     * @param client the current client
     */
    public void DelDisplayDevice (ConnectionToClient client)
    {
		Platform.runLater(new Runnable()
		{
			@Override
			public void run()
			{
				int index = ConDevices.indexOf(client.toString());
				ConDevices.remove(index);
				ConTime.remove(index);
				ConnectedDevices.setText(CreateString(ConTime,ConDevices,""));
			}
		});
	}
    
    
    /**
     * Prints the details to log.
     * @param msg the log details
     */
    public void PrintToLog (String msg)
    {
		Platform.runLater(new Runnable()
		{
			@Override
			public void run()
			{	
				LogTime.add("["+getTime()+"]");
				Log2.add(msg);
				LogScreen.setText(CreateString(LogTime,Log2,""));
			}
		});
	}
    
    
    /**
     * Creates the string for display in in log.
     * @param Time the list of times of every event
     * @param Array the list of connected devices or events
     * @param str String For possible addition
     * @return the string to display
     */
    public String CreateString (ArrayList<String> Time,ArrayList<String> Array, String str)
    {
		String DisplayStr = "";
		for (int i=0;i<Array.size();i++)
		{
				DisplayStr = DisplayStr.concat((i+1) + " : " + Time.get(i)+"  "+Array.get(i)+"\n"+str);
		}
		return DisplayStr;
    }
    
    
    /**
     * Gets the time.
     * @return the time
     */
    public String getTime ()
    {
    	DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
    	Calendar cal = Calendar.getInstance();
    	String time=dateFormat.format(cal.getTime()).toString();
    	return time;
    }
    
    /**
     * Save file.
     * @param String the log
     * @param File the file for save
     */
    private void SaveFile(String content, File file){
        try {
            FileWriter fileWriter = null;
             
            fileWriter = new FileWriter(file);
            fileWriter.write(content);
            fileWriter.close();
        } catch (IOException ex) {
            Logger.getLogger(Server2Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
         
    }
    
    
    /**
     * Clear log file.
     * @param event Gets the ActionEvent when the function called.
     */
    @FXML
    void ClearLogFile(ActionEvent event) {
    	LogTime.clear();
		Log2.clear();
    	LogScreen.setText("");
    }
    

}
