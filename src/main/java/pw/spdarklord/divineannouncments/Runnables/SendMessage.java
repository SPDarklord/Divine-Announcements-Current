package pw.spdarklord.divineannouncments.Runnables;

import pw.spdarklord.divineannouncments.Database.MessageHandler;
import pw.spdarklord.divineannouncments.DivineAnnouncments;
import pw.spdarklord.divineannouncments.Utils.BungeeUtils;

import java.util.ArrayList;

/**
 * Created by Thomas on 29/04/2017.
 */
public class SendMessage {

    private static SendMessage instance;

    public static SendMessage getInstance() {
        if (instance == null) {
            instance = new SendMessage();
        }

        return instance;
    }

    public void sendMessage(String string){
        BungeeUtils.getInstance().sendMessageToSpigot(string);
        DivineAnnouncments.instance.getProxy().getLogger().info("Message sent to servers");
    }

    public void sendMessageFromDatabaseWithID(int id, String server, String user){
        String message = MessageHandler.getInstance().getMessage(id);
        BungeeUtils.getInstance().sendMessageToServer(server, message, user, false);
    }

    public void sendMessageFromDatabase(String server, String user){
        ArrayList<String> messageArray = MessageHandler.getInstance().getAllMessages();
        for (String message : messageArray){
            BungeeUtils.getInstance().sendMessageToServer(server, message, user, true);
        }

    }

}
