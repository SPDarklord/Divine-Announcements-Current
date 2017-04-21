package pw.spdarklord.divineannouncments.Database;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import pw.spdarklord.divineannouncments.DivineAnnouncments;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Thomas on 20/04/2017.
 */
public class ConnectionPoolManager {

    private final DivineAnnouncments plugin;

    private HikariDataSource dataSource;

    private String hostname;
    private String port;
    private String database;
    private String username;
    private String password;

    private int miniumConnections;
    private int maxiumumConnections;
    private long conncetionTimeout;
    private String testQuery;

    public ConnectionPoolManager(DivineAnnouncments plugin){
        this.plugin = plugin;
        init();
        setupPool();
    }

    private void init(){
        hostname =  "localhost";
        port = "3306";
        database = "DBAnnouncments";
        username = "DBAnnouncments";
        password = "DivineAnnouncmentsControl123";

        miniumConnections = 1;
        maxiumumConnections = 10;
        conncetionTimeout = 5000;
    }

    private void setupPool(){
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:mysql://" + hostname + ":" + port + "/" + database);
        config.setDriverClassName("com.mysql.jdbc.Driver");
        config.setUsername(username);
        config.setPassword(password);
        dataSource = new HikariDataSource(config);
    }


    public Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

    public void close(Connection conn, PreparedStatement ps, ResultSet res){
        if (conn != null) try { conn.close();} catch (SQLException ignored){}
        if (ps != null) try {ps.close();}  catch (SQLException ignored){}
        if (res != null) try {res.close();}  catch (SQLException ignored){}
    }

    public void closePool() {
        if (dataSource != null && !dataSource.isClosed()) {
            dataSource.close();
        }
    }

}
