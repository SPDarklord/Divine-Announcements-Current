package pw.spdarklord.divineannouncments;

//Import all libaries and internal code needed for this class
import net.md_5.bungee.api.event.PluginMessageEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.api.plugin.Plugin;
import pw.spdarklord.divineannouncments.Database.MessageHandler;
import pw.spdarklord.divineannouncments.Database.SQLManager;
import pw.spdarklord.divineannouncments.DebugCode.AddToDatabase;
import pw.spdarklord.divineannouncments.DebugCode.Commands;
import pw.spdarklord.divineannouncments.DebugCode.TestMessageSend;
import pw.spdarklord.divineannouncments.Runnables.SendMessage;
import pw.spdarklord.divineannouncments.Runnables.Timer;
import pw.spdarklord.divineannouncments.Utils.PluginMessage;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;

/**
 * Created by torsb_000 on 13/01/2017.
 */

//Define what this class is called and what interfaces and hooks it needs.
public class DivineAnnouncments extends Plugin implements Listener{

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
        //Register user of the "BungeeCord" messaging channel for plugin communication
        getProxy().registerChannel("BungeeCord");
        //Register a a listener
        getProxy().getInstance().getPluginManager().registerListener(this, this);
        //Log that the plugin is online
        getProxy().getLogger().info("DivineAnnouncments Online");
        //Run the method initDatabase
        initDatabase();
        //Test code for broadcasting a message and adding a message to database
        //TestMessageSend.testBroadcast();
        //AddToDatabase.addToDatabase();
        //Start the timer for messages
        //Timer.runTimer();

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

    public void onPluginMessage(PluginMessageEvent e){
        DivineAnnouncments.getInstance().getLogger().info("Message get");
        DataInputStream in = new DataInputStream(new ByteArrayInputStream(e.getData()));
        try {
            String channelMaster = in.readUTF();
            if (channelMaster.equalsIgnoreCase("BungeeCord")) {


                String channel = in.readUTF();
                DivineAnnouncments.getInstance().getLogger().info("Channel " + channel);
                if (channel.equals("Request"))
                {
                    String request = in.readUTF();
                    if (request == "listall"){
                        String server = in.readUTF();
                        String user = in.readUTF();
                        SendMessage.getInstance().sendMessageFromDatabase(server, user);
                    }else if(request == "list"){
                        int id = Integer.parseInt(in.readUTF());
                        String server = in.readUTF();
                        String user = in.readUTF();
                        SendMessage.getInstance().sendMessageFromDatabaseWithID(id, server, user);
                    }
                }
                if (channel.equals("add")){
                    String message = in.readUTF();
                    MessageHandler.getInstance().insertMessage(message);
                }
                if (channel.equals("addID")){
                    int id = Integer.parseInt(in.readUTF());
                    String message = in.readUTF();
                    MessageHandler.getInstance().insertMessageID(message, id);

                }

            }
        }catch (Exception localException){
            localException.printStackTrace();
        }
    }

}
