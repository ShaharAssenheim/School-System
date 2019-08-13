package Boundery;

import java.io.IOException;
import java.net.URL;

import Application.EchoServer;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 * The Class GUIPlayer.
 */
public class GUIPlayer
{
	
	/** The url. */
	private URL url;
	
	/** The pane. */
	private AnchorPane pane;
	
	/** The scene. */
	private Scene scene;
	
	/** The primary stage. */
	public static Stage primaryStage = new Stage();
	
	/**
	 * Display the connection to DB screen.
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public void Server1() throws IOException
	{
		// Constructing our scene
		url = getClass().getResource("Server1.fxml");
		pane = FXMLLoader.load(url);
		scene = new Scene(pane);
		   
		// Setting the stage
		primaryStage.setScene(scene);
		primaryStage.setResizable(false);
		primaryStage.centerOnScreen();
		primaryStage.setTitle("SchoolSystem Server");
		primaryStage.show(); 
	}
	
	/**
	 * Display the main server screen.
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public void Server2() throws IOException
	{
		// Constructing our scene
		url = getClass().getResource("Server2.fxml");
		pane = FXMLLoader.load(url);
		scene = new Scene(pane);
		
		// Setting the stage
		primaryStage.setScene(scene);
		primaryStage.centerOnScreen();
		primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>(){
			@Override
			public void handle(WindowEvent event)
			{
				EchoServer.updateAll();
			}
		});
		primaryStage.setTitle("SchoolSystem Server");
		primaryStage.show();
	}
}
