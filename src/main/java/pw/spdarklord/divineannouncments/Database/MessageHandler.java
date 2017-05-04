package pw.spdarklord.divineannouncments.Database;

import pw.spdarklord.divineannouncments.DivineAnnouncments;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return results;
    }
}

