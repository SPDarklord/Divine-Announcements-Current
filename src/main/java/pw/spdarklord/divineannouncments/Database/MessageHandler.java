package pw.spdarklord.divineannouncments.Database;

import net.md_5.bungee.BungeeCord;
import pw.spdarklord.divineannouncments.DivineAnnouncments;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.GregorianCalendar;
import java.util.logging.Level;

/**
 * Created by Thomas on 04/05/2017.
 */
public class MessageHandler {

    private static MessageHandler instance;
    private ConnectionPoolManager pool;

    public static MessageHandler getInstance() {
        if (instance == null) {
            instance = new MessageHandler();
        }

        return instance;
    }

    public ResultSet getMessage(int MessageID) {
        pool = new ConnectionPoolManager(DivineAnnouncments.getInstance());
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet results = null;

        try {
            conn = pool.getConnection();
            ps = conn.prepareStatement("SELECT Message FROM Messages WHERE ID=" + MessageID + "");
            results = ps.executeQuery();
            BungeeCord.getInstance().getLogger().log(Level.FINE, results.getString("Message"));
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            pool.close(conn, ps, results);
        }

        return results;
    }

    public void insertMessage(String message){
        pool = new ConnectionPoolManager(DivineAnnouncments.getInstance());
        Connection conn = null;
        PreparedStatement ps = null;

    }
}

