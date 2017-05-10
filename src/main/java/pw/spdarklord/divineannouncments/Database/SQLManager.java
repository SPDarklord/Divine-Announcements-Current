package pw.spdarklord.divineannouncments.Database;

import pw.spdarklord.divineannouncments.DivineAnnouncments;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;

/**
 * Created by Thomas on 20/04/2017.
 */
public class SQLManager {

    //Create an instance of DivineAnnouncments
    private final DivineAnnouncments plugin;

    //Create a variable for the instance of ConnectionPoolManager
    private final ConnectionPoolManager pool;

    //Constructor
    public SQLManager(DivineAnnouncments plugin){
        this.plugin = plugin;
        //Create an instance of ConnectionPoolManger
        pool = new ConnectionPoolManager(plugin);
        //Run Make table
        makeTable();
        //Debug Code - Get data from tables
        MessageHandler.getInstance().getMessage(1);
        MessageHandler.getInstance().getMessage(2);
    }

    //Method to make table
    private void makeTable(){
        //Define two variables for later use.
        Connection conn = null;
        PreparedStatement ps = null;
        //Try to run below code, if it fails print stack trace
        try{
            //Create a connection
            conn = pool.getConnection();
            //Create a prepared statement in that connection.
            ps = conn.prepareStatement("CREATE TABLE IF NOT EXISTS Messages (ID int(25) NOT NULL AUTO_INCREMENT PRIMARY KEY, Message VARCHAR (255))");
            //Run the prepared statment
            ps.executeUpdate();
            //Log in console the table has been created
            DivineAnnouncments.getInstance().getProxy().getLogger().log(Level.FINEST, "Table created");
        }catch (SQLException e){
            e.printStackTrace();
            DivineAnnouncments.instance.getLogger().severe("Failed to create table");
        } finally {
            //Close the connection
            pool.close(conn, ps, null);
        }
    }

    public void onDisable() {
        pool.closePool();
    }

}
