package pw.spdarklord.divineannouncments.Database;

import pw.spdarklord.divineannouncments.DivineAnnouncments;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by Thomas on 20/04/2017.
 */
public class SQLManager {

    private final DivineAnnouncments plugin;

    private final ConnectionPoolManager pool;

    public SQLManager(DivineAnnouncments plugin){
        this.plugin = plugin;
        pool = new ConnectionPoolManager(plugin);
        makeTable();
    }

    private void makeTable(){
        Connection conn = null;
        PreparedStatement ps = null;
        try{
            conn = pool.getConnection();
            ps = conn.prepareStatement("CREATE TABLE IF NOT EXISTS Messages (ID int(25) NOT NULL AUTO_INCREMENT PRIMARY KEY, Message VARCHAR (255))");
            ps.executeUpdate();
            DivineAnnouncments.getInstance().getLogger().info("Database Table has been created!");
        }catch (SQLException e){
            e.printStackTrace();
            DivineAnnouncments.instance.getLogger().severe("Failed to create table");
        } finally {
            pool.close(conn, ps, null);
        }
    }

    public void onDisable() {
        pool.closePool();
    }

}
