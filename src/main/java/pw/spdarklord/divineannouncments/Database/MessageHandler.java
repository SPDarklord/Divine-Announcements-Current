package pw.spdarklord.divineannouncments.Database;

//Import the main class

import pw.spdarklord.divineannouncments.DivineAnnouncments;

//Import required libaries and apis
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

/**
 * Created by Thomas on 04/05/2017.
 */
public class MessageHandler {

    //Create an instance of this
    private static MessageHandler instance;
    private ConnectionPoolManager pool;

    //Return this instance
    public static MessageHandler getInstance() {
        //If there is no defined instance of this class instance equals a new class.
        if (instance == null) {
            instance = new MessageHandler();
        }
        //Return this class
        return instance;
    }

    //Method to get a message with an id
    public String getMessage(int MessageID) {
        //Create a new connection pool, using an instance of the main class.
        pool = new ConnectionPoolManager(DivineAnnouncments.getInstance());
        //Create variables for later use
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet results = null;
        String message = "";

        try {
            //Create a connection
            conn = pool.getConnection();
            //Create a prepared statement
            ps = conn.prepareStatement("SELECT Message FROM Messages WHERE ID=" + MessageID + "");
            //Result set of the prepared statement query
            results = ps.executeQuery();
            //While there is still data in the result set
            while (results.next()) {
                //Get data from the column Message
                message = results.getString("Message");
                //Log the message - Debug code
                DivineAnnouncments.getInstance().getProxy().getLogger().log(Level.FINE, message);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            //Close connection, prepared statement and clear results
            pool.close(conn, ps, results);
        }
        //Return the data
        return message;
    }

    public ArrayList getAllMessages() {
        //Create a new connection pool, using an instance of the main class.
        pool = new ConnectionPoolManager(DivineAnnouncments.getInstance());
        //Create variables for later use
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet results = null;
        ArrayList<String> messageArray = new ArrayList<String>();
        try {
            //Create a connection
            conn = pool.getConnection();
            //Create a prepared statement
            ps = conn.prepareStatement("SELECT Message FROM Messages");
            //Result set of the prepared statement query
            results = ps.executeQuery();
            while (results.next()) {
                //Get data from the column Message
                String currentMessage = results.getString("Message");
                messageArray.add(currentMessage);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            //Close connection, prepared statement and clear results
            pool.close(conn, ps, results);
        }

        return messageArray;
    }

    //Method to insert messages that do not have a id
    public void insertMessage(String message) {
        //Create a new connection pool
        pool = new ConnectionPoolManager(DivineAnnouncments.getInstance());
        //Define variables to user later
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            //Create a connection
            conn = pool.getConnection();
            //Create a prepared statement insert null value for ID, this will force the
            // MySQL Table to add data
            ps = conn.prepareStatement("INSERT INTO Messages VALUES (null, ?)");
            //Set the ? string to message
            ps.setString(1, message);
            //Execture prepared statement
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            pool.close(conn, ps, null);
        }


    }

    //Same as above but using an id as well
    public void insertMessageID(String message, Integer id) {
        pool = new ConnectionPoolManager(DivineAnnouncments.getInstance());
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            //Insert values ID and Message using called data
            ps = conn.prepareStatement("INSERT INTO Message (ID, Message) VALUES (" + id + message + ")");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            pool.close(conn, ps, null);
        }
    }
}

