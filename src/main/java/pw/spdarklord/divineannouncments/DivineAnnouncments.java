package pw.spdarklord.divineannouncments;

//Import all libaries and internal code needed for this class
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.api.plugin.Plugin;
import pw.spdarklord.divineannouncments.Database.SQLManager;
import pw.spdarklord.divineannouncments.DebugCode.AddToDatabase;
import pw.spdarklord.divineannouncments.DebugCode.Commands;
import pw.spdarklord.divineannouncments.DebugCode.TestMessageSend;
import pw.spdarklord.divineannouncments.Runnables.Timer;

/**
 * Created by torsb_000 on 13/01/2017.
 */

//Define what this class is called and what interfaces and hooks it needs.
public class DivineAnnouncments extends Plugin implements Listener {

    //Create a variable which will contain a "copy" of this class
    public static DivineAnnouncments instance;

    //Create a method to set this class to instance
    public DivineAnnouncments() {
        //set instance to this class
        instance = this;
    }
    //create a method to return the value of this instance
    public static DivineAnnouncments getInstance() {
        return instance;
    }

    //Create a variable for the SQLManager Class
    private SQLManager sql;

    @Override
    //When the plugin is enabled do the following
    public void onEnable() {
        //Register a debug command
        getProxy().getInstance().getPluginManager().registerCommand(this, new Commands());
        //Register user of the "BungeeCord" messaging channel for plugin communication
        getProxy().registerChannel("BungeeCord");
        //Log that the plugin is online
        getProxy().getLogger().info("DivineAnnouncments Online");
        //Run the method initDatabase
        initDatabase();
        //Register a listener for this class
        getProxy().getPluginManager().registerListener(this, this);
        //Test code for broadcasting a message and adding a message to database
        TestMessageSend.testBroadcast();
        AddToDatabase.addToDatabase();
        //Start the timer for messages
        Timer.runTimer();

    }

    @Override
    //When the plugin is disabled
    public void onDisable() {
        getProxy().getLogger().severe("DivineAnnouncments OFFLINE");
        //Close all HikariCP Connections
        sql.onDisable();
    }

    //Create an instance of the SQLManager Class, this will run the code contained
    //in the main method
    private void initDatabase(){
        sql = new SQLManager(this);
    }

    //Return value of the instance
    public SQLManager getSQLManager(){
        return sql;
    }

}
