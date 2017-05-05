package pw.spdarklord.divineannouncments.Runnables;

import pw.spdarklord.divineannouncments.DivineAnnouncments;
import pw.spdarklord.divineannouncments.Utils.BungeeUtils;

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
        BungeeUtils.getInstance().sendToBungee(string);
        DivineAnnouncments.instance.getProxy().getLogger().info("Message sent to servers");
    }

}
