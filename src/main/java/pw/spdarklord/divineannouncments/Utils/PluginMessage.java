package pw.spdarklord.divineannouncments.Utils;

import net.md_5.bungee.api.event.PluginMessageEvent;
import net.md_5.bungee.api.plugin.Listener;
import pw.spdarklord.divineannouncments.Database.MessageHandler;
import pw.spdarklord.divineannouncments.DebugCode.AddToDatabase;
import pw.spdarklord.divineannouncments.DivineAnnouncments;
import pw.spdarklord.divineannouncments.Runnables.SendMessage;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.util.EventListener;

import static com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type.Int;

/**
 * Created by Thomas on 09/05/2017.
 */
public class PluginMessage implements Listener {

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
