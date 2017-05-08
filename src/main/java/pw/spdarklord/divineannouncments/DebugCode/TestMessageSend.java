package pw.spdarklord.divineannouncments.DebugCode;

import pw.spdarklord.divineannouncments.Runnables.SendMessage;

/**
 * Created by Thomas on 07/05/2017.
 */
public class TestMessageSend {


    public static void testBroadcast(){
        String testString = "TestingBroadcastToSPigot";
        SendMessage.getInstance().sendMessage(testString);
    }


}
