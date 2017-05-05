package pw.spdarklord.divineannouncments.DebugCode;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.plugin.Command;
import pw.spdarklord.divineannouncments.Database.MessageHandler;

/**
 * Created by Thomas on 04/05/2017.
 */
public class Commands extends Command{

    public Commands() {
        super("getmessage");
    }

    public void execute(CommandSender commandSender, String[] strings) {
        int MessageID;
        if (strings.length == 0){
            commandSender.sendMessage(new ComponentBuilder("You need a ID!").create());
        }else{
            try{
                MessageID = Integer.parseInt(strings[0]);
                MessageHandler.getInstance().getMessage(MessageID);
            }catch (NumberFormatException e){
                commandSender.sendMessage(new ComponentBuilder("Invalid Number").color(ChatColor.DARK_RED).create());
            }
        }

    }

}
