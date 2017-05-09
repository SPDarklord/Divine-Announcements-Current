package pw.spdarklord.divineannouncments.Utils;

import net.md_5.bungee.api.event.PluginMessageEvent;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.util.EventListener;

import static com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type.Int;

/**
 * Created by Thomas on 09/05/2017.
 */
public class PluginMessage implements EventListener {

    public void onPluginMessage(PluginMessageEvent e){
        if (e.getTag().equalsIgnoreCase("BungeeCord")) {
            DataInputStream in = new DataInputStream(new ByteArrayInputStream(e.getData()));
            try {
                String channel = in.readUTF();
                if (channel.equals("Request"))
                {
                    String request = in.readUTF();
                    if (request == "listall"){

                    }else if(request == "list"){
                    }
                }
            }catch (Exception localException){
                localException.printStackTrace();
            }
        }
    }
}
