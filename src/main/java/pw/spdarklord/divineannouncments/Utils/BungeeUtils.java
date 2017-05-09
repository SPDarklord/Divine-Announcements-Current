package pw.spdarklord.divineannouncments.Utils;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import net.md_5.bungee.api.config.ServerInfo;
import pw.spdarklord.divineannouncments.DivineAnnouncments;

import java.io.ByteArrayOutputStream;
import java.util.Map;

/**
 * Created by torsb_000 on 15/01/2017.
 */
public class BungeeUtils {


    private static BungeeUtils instance;
    private ByteArrayDataOutput bado;

    public static BungeeUtils getInstance() {
        if (instance == null) {
            instance = new BungeeUtils();
        }

        return instance;
    }

    public void sendMessageToSpigot(String string) {
        try {
            ByteArrayDataOutput out = ByteStreams.newDataOutput();
            out.writeUTF("MessageToBroadcast");
            out.writeUTF(string);
            broadcastToBukkit(out);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void broadcastToBukkit(ByteArrayDataOutput out) {
        byte[] Dataout = out.toByteArray();
        for (Map.Entry<String, ServerInfo> e : DivineAnnouncments.getInstance().getProxy().getServers().entrySet()) {
            ((ServerInfo) e.getValue()).sendData("BungeeCord", Dataout);
        }
    }

}


