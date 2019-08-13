package Application;

import OCSF.AbstractServer;
import OCSF.ConnectionToClient;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * The Class EchoServer  to initialize the connection with the database,get the message from the client,
 * Disconnect all clients when server close and print a message when the server start listening to a port.
 */
public class EchoServer extends AbstractServer 
{
	
	/** The connection to the DB. */
	private static Connection conn;// Connection.
	
	/**
	 * Instantiates a new echo server.
	 *
	 * @param port the port
	 */
	// Constructor.
	public EchoServer(int port) 
	{
		super(port);
	}
	
	/**
	 * Method to initialize the connection with the database.
	 *
	 * @param dbName the DB name
	 * @param User the user name for DB access
	 * @param Pass the password for DB access
	 * @return true, if successful
	 * @throws Exception the exception
	 */
	public boolean initDBConnection(String dbName, String User, String Pass) throws Exception
	{
		try// Trying to connect with the database.
		{
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection("jdbc:mysql://localhost/" + dbName, User, Pass);
			System.out.println("SQL connection succeed");
			return true;
		}catch (SQLException ex){
			return false;
			}
	}
	
	/**
	 * Method to get the message from the client.
	 * @param arr the message from the client
	 * @param client the current client
	 */
	public void handleMessageFromClient(Object arr, ConnectionToClient client)
	{
		MessageFromClient.MFC(arr, client, conn);
	}
		
	/**
	 * Disconnect all clients when server close.
	 */
	public static void updateAll()
	{
		try
		{
			PreparedStatement updateUnit = conn.prepareStatement("UPDATE users SET Connected = 0");				
			updateUnit.executeUpdate();
			updateUnit.close();
			System.exit(1);
		}catch(SQLException e){
			e.printStackTrace();
			}
	}
	
	/**
	 * Method to print a message when the server start listening to a port.
	 */
	protected void serverStarted()
	{
		System.out.println("Server listening for connections on port " + getPort());
	}
}
