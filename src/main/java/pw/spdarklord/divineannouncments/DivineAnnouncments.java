package pw.spdarklord.divineannouncments;


import net.md_5.bungee.api.plugin.Plugin;
import pw.spdarklord.divineannouncments.Database.SQLManager;

/**
 * Created by torsb_000 on 13/01/2017.
 */
public class DivineAnnouncments extends Plugin {

    public static DivineAnnouncments instance = null;
    private SQLManager sql;

    @Override
    public void onEnable() {
        instance = this;
        getProxy().getLogger().info("DivineAnnouncments Online");
        initDatabase();

    }

    @Override
    public void onDisable() {
        getProxy().getLogger().severe("DivineAnnouncments OFFLINE");
        sql.onDisable();
    }

    private  void initDatabase(){
        sql = new SQLManager(this);
    }

    public SQLManager getSQLManager(){
        return sql;
    }

}
