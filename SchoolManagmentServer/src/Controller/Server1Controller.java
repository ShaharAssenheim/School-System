package Controller;

import java.io.File;

import Application.EchoServer;
import Boundery.GUIPlayer;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.DirectoryChooser;

/**
 * The Class Server1Controller.
 * responsible for connecting to the DB
 */
public class Server1Controller
{
	
	/** The port. */
	public static String port1;
	
	/** The HW path. */
	public static String HWPath;
	
	/** The Submission path. */
	public static String SubPath;
	
	/** The Checked homework path. */
	public static String CHWPath;
	
	/** The Evaluation form path. */
	public static String EvPath;
	
	/** The screen. */
	public GUIPlayer screen = new GUIPlayer();
	
	/** The HW path. */
	@FXML // fx:id="HWP"
    private TextField HWP; // Value injected by FXMLLoader

    /** The Checked homework path. */
    @FXML // fx:id="CHWP"
    private TextField CHWP; // Value injected by FXMLLoader

    /** The connect button. */
    @FXML // fx:id="conn"
    private Button conn; // Value injected by FXMLLoader

    /** The user name. */
    @FXML // fx:id="uname"
    private TextField uname; // Value injected by FXMLLoader

    /** The DB label. */
    @FXML // fx:id="DBLbl"
    private Text DBLbl; // Value injected by FXMLLoader

    /** The password. */
    @FXML // fx:id="pass"
    private PasswordField pass; // Value injected by FXMLLoader

    /** The Password label. */
    @FXML // fx:id="PassLbl"
    private Text PassLbl; // Value injected by FXMLLoader

    /** The default button. */
    @FXML // fx:id="defaultbtn"
    private Button defaultbtn; // Value injected by FXMLLoader

    /** The cancel button. */
    @FXML // fx:id="cancelbtn"
    private Button cancelbtn; // Value injected by FXMLLoader

    /** The Port label. */
    @FXML // fx:id="PortLbl"
    private Text PortLbl; // Value injected by FXMLLoader

    /** The User Name label. */
    @FXML // fx:id="UNLbl"
    private Text UNLbl; // Value injected by FXMLLoader

    /** The Submissions Path label. */
    @FXML // fx:id="SubPLbl"
    private Text SubPLbl; // Value injected by FXMLLoader

    /** The DB name. */
    @FXML // fx:id="dbname"
    private TextField dbname; // Value injected by FXMLLoader

    /** The port. */
    @FXML // fx:id="port"
    private TextField port; // Value injected by FXMLLoader

    /** The Checked HWS Path label. */
    @FXML // fx:id="CHWPLbl"
    private Text CHWPLbl; // Value injected by FXMLLoader

    /** The Evaluation form Path label. */
    @FXML // fx:id="EvPLbl"
    private Text EvPLbl; // Value injected by FXMLLoader

    /** The configuration button. */
    @FXML // fx:id="configbtn"
    private Button configbtn; // Value injected by FXMLLoader

    /** The Sub P. */
    @FXML // fx:id="SubP"
    private TextField SubP; // Value injected by FXMLLoader

    /** The Checked HWS Path choose button. */
    @FXML // fx:id="GSchoosebtn"
    private Button GSchoosebtn; // Value injected by FXMLLoader
    
    /** The Submissions Path choose button. */
    @FXML // fx:id="Subchoosebtn"
    private Button Subchoosebtn; // Value injected by FXMLLoader
    
    /** The HW path choose button. */
    @FXML // fx:id="HWchoosebtn"
    private Button HWchoosebtn; // Value injected by FXMLLoader
    
    /** The Evaluation forms path choose button. */
    @FXML // fx:id="Evchoosebtn"
    private Button Evchoosebtn; // Value injected by FXMLLoader

    /** The HW path lbl. */
    @FXML // fx:id="HWPLbl"
    private Text HWPLbl; // Value injected by FXMLLoader

    /** The text. */
    @FXML // fx:id="text"
    private Text text; // Value injected by FXMLLoader

    /** The Evaluation forms Path. */
    @FXML // fx:id="EvP"
    private TextField EvP; // Value injected by FXMLLoader

    /** The save button. */
    @FXML // fx:id="savebtn"
    private Button savebtn; // Value injected by FXMLLoader

    /**
     * Method to connect the server to the database.
     * @param event Gets the ActionEvent when the function called.
     * @throws NumberFormatException the number format exception
     * @throws Exception the exception
     */
    @FXML
    public void ConnectToDB(ActionEvent event) throws NumberFormatException, Exception 
    {
    	String dbname1 = dbname.getText();// Saving the database name from dbname field.
    	port1 = port.getText();// Saving the port number from the port field.
    	String uname1 = uname.getText();// Saving the MySQL username from the uname field.
    	String pass1 = pass.getText();// Saving the MySQL password from the pass field.
    	HWPath = HWP.getText();
		SubPath = SubP.getText();
		CHWPath = CHWP.getText();
		EvPath = EvP.getText();
    	 if(!(dbname1.isEmpty() || port1.isEmpty() || uname1.isEmpty() || pass1.isEmpty()))// Checking if there are empty fields or not.
         {
    		 EchoServer server = new EchoServer(Integer.valueOf(port1));// Defining a Server variable with the entered port number.
         	if(server.initDBConnection(dbname1,uname1, pass1))// Checking if the server succeed to connect.
         	{
         		server.setPort(Integer.valueOf(port.getText()));// Setting the server port.
         		display("SQL connection succeed");// Showing a successful message.		
         		try{// Trying to listen the the port (checking if the port is free or in use).
         			server.listen(); //Start listening for connections
         			screen.Server2();
         			}catch(Exception ex){// If the port is in use.
         				display("ERROR - Could not listen for clients!");// Showing an error message.
         				}
         	}
     		else // If the connection failed the program shows a message.
     			display("SQL connection failed.");
         }
         else// If there are empty fields.
         	display("You must Fill all the fields");
	}
    
    /**
     *  Method to print a message in the text field.
     * @param s the message
     */
	public void display(String s)
	{
		text.setText(s + "\n");
		System.out.println(s);
	}
	
	/**
	 * Method to print a message in the text field.
	 * @param s the message
	 */
    public void display2 (String s)
    {
		Platform.runLater(new Runnable()
		{
			@Override
			public void run()
			{
				text.setText(s);
			}
		});
	}
    
       

    /**
     * Sets the configuration.
     * @param event Gets the ActionEvent when the function called.
     */
    @FXML
    void SetConfig(ActionEvent event) {
    	//text.setVisible(false);
    	HWPath = HWP.getText();
		SubPath = SubP.getText();
		CHWPath = CHWP.getText();
		EvPath = EvP.getText();
		port.setVisible(false);
		PortLbl.setVisible(false); //label
		dbname.setVisible(false);
		DBLbl.setVisible(false); //label
		uname.setVisible(false);
		UNLbl.setVisible(false); //label
		pass.setVisible(false);
		PassLbl.setVisible(false); //label
		conn.setVisible(false);
		configbtn.setVisible(false);
		HWP.setVisible(true);
		HWPLbl.setVisible(true);//label
		SubP.setVisible(true);
		SubPLbl.setVisible(true);//label
		CHWP.setVisible(true);
		CHWPLbl.setVisible(true);//label
		EvP.setVisible(true);
		EvPLbl.setVisible(true);//label
		savebtn.setVisible(true);
		cancelbtn.setVisible(true);
		defaultbtn.setVisible(true);
		HWchoosebtn.setVisible(true);
		Subchoosebtn.setVisible(true);
		GSchoosebtn.setVisible(true);
		Evchoosebtn.setVisible(true);
    }

    /**
     * Save configuration.
     * @param event Gets the ActionEvent when the function called.
     */
    @FXML
    void SaveConfig(ActionEvent event) {
    	if(HWP.getText().isEmpty() || SubP.getText().isEmpty() || CHWP.getText().isEmpty() || EvP.getText().isEmpty())
		{
    		Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("");
			alert.setHeaderText(null);
			alert.setContentText("Please fill all the fields");
		}
		else
		{
			port.setVisible(true);
			PortLbl.setVisible(true); //label
			dbname.setVisible(true);
			DBLbl.setVisible(true); //label
			uname.setVisible(true);
			UNLbl.setVisible(true); //label
			pass.setVisible(true);
			PassLbl.setVisible(true); //label
			conn.setVisible(true);
			configbtn.setVisible(true);
			HWP.setVisible(false);
			HWPLbl.setVisible(false);//label
			SubP.setVisible(false);
			SubPLbl.setVisible(false);//label
			CHWP.setVisible(false);
			CHWPLbl.setVisible(false);//label
			EvP.setVisible(false);
			EvPLbl.setVisible(false);//label
			savebtn.setVisible(false);
			cancelbtn.setVisible(false);
			defaultbtn.setVisible(false);
			HWchoosebtn.setVisible(false);
			Subchoosebtn.setVisible(false);
			GSchoosebtn.setVisible(false);
			Evchoosebtn.setVisible(false);			
		}
    }

    /**
     * Cancel configuration.
     * @param event Gets the ActionEvent when the function called.
     */
    @FXML
    void CancelConfig(ActionEvent event) {
    	//text.setVisible(true);
    	HWP.setText(HWPath);
		SubP.setText(SubPath);
		CHWP.setText(CHWPath);
		EvP.setText(EvPath);
		port.setVisible(true);
		PortLbl.setVisible(true); //label
		dbname.setVisible(true);
		DBLbl.setVisible(true); //label
		uname.setVisible(true);
		UNLbl.setVisible(true); //label
		pass.setVisible(true);
		PassLbl.setVisible(true); //label
		conn.setVisible(true);
		configbtn.setVisible(true);
		HWP.setVisible(false);
		HWPLbl.setVisible(false);//label
		SubP.setVisible(false);
		SubPLbl.setVisible(false);//label
		CHWP.setVisible(false);
		CHWPLbl.setVisible(false);//label
		EvP.setVisible(false);
		EvPLbl.setVisible(false);//label
		savebtn.setVisible(false);
		cancelbtn.setVisible(false);
		defaultbtn.setVisible(false);
		HWchoosebtn.setVisible(false);
		Subchoosebtn.setVisible(false);
		GSchoosebtn.setVisible(false);
		Evchoosebtn.setVisible(false);
    }

    /**
     * Sets the default configuration.
     * @param event Gets the ActionEvent when the function called.
     */
    @FXML
    void SetDefault(ActionEvent event) {
    	HWP.setText("C:\\Homeworks");
		SubP.setText("C:\\Submissions");
		CHWP.setText("C:\\GradeSheets");
		EvP.setText("C:\\Evaluations");
    }

    
    /**
     * Select HWS folder.
     * @param event Gets the ActionEvent when the function called.
     */
    @FXML
    void SelectHWFolder(ActionEvent event) {
    	final DirectoryChooser directoryChooser = new DirectoryChooser();
        final File selectedDirectory = directoryChooser.showDialog(GUIPlayer.primaryStage);
        if (selectedDirectory != null) {
        	HWP.setText(selectedDirectory.getAbsolutePath());
        }
    }

    /**
     * Select submission folder.
     * @param event Gets the ActionEvent when the function called.
     */
    @FXML
    void SelectSubFolder(ActionEvent event) {
    	final DirectoryChooser directoryChooser = new DirectoryChooser();
        final File selectedDirectory = directoryChooser.showDialog(GUIPlayer.primaryStage);
        if (selectedDirectory != null) {
        	SubP.setText(selectedDirectory.getAbsolutePath());
        }
    }

    /**
     * Select Grade Sheets (checked HW forms) folder.
     * @param event Gets the ActionEvent when the function called.
     */
    @FXML
    void SelectGSFolder(ActionEvent event) {
    	final DirectoryChooser directoryChooser = new DirectoryChooser();
        final File selectedDirectory = directoryChooser.showDialog(GUIPlayer.primaryStage);
        if (selectedDirectory != null) {
        	CHWP.setText(selectedDirectory.getAbsolutePath());
        }
    }

    /**
     * Select evaluation forms folder.
     * @param event Gets the ActionEvent when the function called.
     */
    @FXML
    void SelectEvFolder(ActionEvent event) {
    	final DirectoryChooser directoryChooser = new DirectoryChooser();
        final File selectedDirectory = directoryChooser.showDialog(GUIPlayer.primaryStage);
        if (selectedDirectory != null) {
        	EvP.setText(selectedDirectory.getAbsolutePath());
        }
    }

}
