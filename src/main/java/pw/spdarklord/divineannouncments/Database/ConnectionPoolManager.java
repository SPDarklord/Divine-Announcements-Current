package pw.spdarklord.divineannouncments.Database;

//Import apis and libaries

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

    //Create an instance of DivineAnnouncments
    private final DivineAnnouncments plugin;

    //Create a new datasource
    private HikariDataSource dataSource;

    //Create new variables for core database data
    private String hostname;
    private String port;
    private String database;
    private String username;
    private String password;

    //Create new variables for HikariCOnfig data
    private int miniumConnections;
    private int maxiumumConnections;
    private long conncetionTimeout;
    private String testQuery;

    //Initialise class
    public ConnectionPoolManager(DivineAnnouncments plugin) {
        //Set plugin to be an instance of this class
        this.plugin = plugin;
        //RUn the init method
        init();
        //Run the setupPool Method
        setupPool();
    }

    //Init method
    private void init() {
        //Define database and HikariCP Data
        hostname = "localhost";
        port = "3306";
        database = "DBAnnouncments";
        username = "DBAnnouncments";
        password = "DivineAnnouncmentsControl123";

        miniumConnections = 1;
        maxiumumConnections = 10;
        conncetionTimeout = 5000;
    }

    //Setup a HikariCP
    private void setupPool() {
        //Create new HikariConfig under variable config.
        HikariConfig config = new HikariConfig();
        //Define database details
        config.setJdbcUrl("jdbc:mysql://" + hostname + ":" + port + "/" + database);
        //Define the class for MySQL Driver, this case the default Java driver
        config.setDriverClassName("com.mysql.jdbc.Driver");
        //Set final data
        config.setUsername(username);
        config.setPassword(password);
        //Create a datasource with the config defined above
        dataSource = new HikariDataSource(config);
    }

    //Create a method for getting current connection
    public Connection getConnection() throws SQLException {
        //Return the current connection
        return dataSource.getConnection();
    }

    //Create a method for closing connections
    public void close(Connection conn, PreparedStatement ps, ResultSet res) {
        //Check if a connection is open, ignore errors
        if (conn != null) try {
            conn.close();
        } catch (SQLException ignored) {
        }
        //Check if there is a prepared statement, ignore errors
        if (ps != null) try {
            ps.close();
        } catch (SQLException ignored) {
        }
        //Check if a resultset is open, ignore erros
        if (res != null) try {
            res.close();
        } catch (SQLException ignored) {
        }
    }

    //Close the entire connection pool
    public void closePool() {
        if (dataSource != null && !dataSource.isClosed()) {
            dataSource.close();
        }
    }

}
