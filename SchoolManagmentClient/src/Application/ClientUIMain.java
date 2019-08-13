
package Application;

import java.io.IOException;
import javafx.application.Application;
import javafx.stage.Stage;
import Boundery.GUIPlayer;

/**
 * The Class ClientUIMain to run the client application.
 */
public class ClientUIMain extends Application
{
	
	/**
	 * show the first screen of connection to server
	 */
	@Override
	public void start(Stage primaryStage) throws IOException
	{
		GUIPlayer screen = new GUIPlayer();
		screen.ClientUI();
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
