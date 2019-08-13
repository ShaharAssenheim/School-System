package Application;

import java.io.IOException;
import Boundery.GUIPlayer;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * The Class ServerUIMain to run the server application.
 */
public class ServerUIMain extends Application
{
	
	/**
	 * show the first screen of connection to DB
	 */
	@Override
	public void start(Stage primaryStage) throws IOException
	{
		GUIPlayer screen = new GUIPlayer();
		screen.Server1();
	}
	
	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args)
	{
		launch(args);
	}
}
