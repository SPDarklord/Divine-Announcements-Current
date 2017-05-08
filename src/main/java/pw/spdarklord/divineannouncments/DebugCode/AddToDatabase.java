package pw.spdarklord.divineannouncments.DebugCode;

import pw.spdarklord.divineannouncments.Database.MessageHandler;
import pw.spdarklord.divineannouncments.Database.SQLManager;

/**
 * Created by Thomas on 08/05/2017.
 */
public class AddToDatabase {

    public static void addToDatabase(){
        String message = "Test 3";
        MessageHandler.getInstance().insertMessage(message);
    }

}
