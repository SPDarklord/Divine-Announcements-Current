package pw.spdarklord.divineannouncments.DebugCode;

import pw.spdarklord.divineannouncments.Database.MessageHandler;
import pw.spdarklord.divineannouncments.Database.SQLManager;

/**
 * Created by Thomas on 08/05/2017.
 */
public class AddToDatabase {

    //Debug code to add test 3 to the database
    public static void addToDatabase(){
        String message = "Test 3";
        //Call method to add to database
        MessageHandler.getInstance().insertMessage(message);
    }

}
