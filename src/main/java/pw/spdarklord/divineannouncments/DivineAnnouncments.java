package pw.spdarklord.divineannouncments;


import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.api.plugin.Plugin;
import pw.spdarklord.divineannouncments.Database.SQLManager;
import pw.spdarklord.divineannouncments.DebugCode.AddToDatabase;
import pw.spdarklord.divineannouncments.DebugCode.Commands;
import pw.spdarklord.divineannouncments.DebugCode.TestMessageSend;

/**
 * Created by torsb_000 on 13/01/2017.
 */
public class DivineAnnouncments extends Plugin implements Listener {

    public static DivineAnnouncments instance;

    public DivineAnnouncments() {
        instance = this;
    }
    public static DivineAnnouncments getInstance() {
        return instance;
    }

    private SQLManager sql;

    @Override
    public void onEnable() {
        getProxy().getInstance().getPluginManager().registerCommand(this, new Commands());
        getProxy().registerChannel("Bungeecord");
        getProxy().getLogger().info("DivineAnnouncments Online");
        initDatabase();
        getProxy().getPluginManager().registerListener(this, this);
        TestMessageSend.testBroadcast();
        AddToDatabase.addToDatabase();

    }

    @Override
    public void onDisable() {
        getProxy().getLogger().severe("DivineAnnouncments OFFLINE");
        sql.onDisable();
    }

    private void initDatabase(){
        sql = new SQLManager(this);
    }

    public SQLManager getSQLManager(){
        return sql;
    }

}
