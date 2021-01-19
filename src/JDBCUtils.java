import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.Properties;

//工具类

public class JDBCUtils {
    private static String url;
    private static String user;
    private static String password;
    private static String Driver;


    static {

        try {
            Properties pro = new Properties();

            ClassLoader classLoader = JDBCUtils.class.getClassLoader();
            URL url1 = classLoader.getResource("jdbc.properties");

            assert url1 != null;
            String path = url1.getPath();
            pro.load(new FileReader(path));

            url = pro.getProperty("url");
            user = pro.getProperty("user");
            password = pro.getProperty("password");
            Driver = pro.getProperty("Driver");

            Class.forName(Driver);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }

    public static void close(Statement st, Connection con) {
        if (st != null)
            try {
                st.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

        if (con != null)
            try {
                con.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }


    }

    public static void close(Statement st, Connection con, ResultSet result) {
        if (st != null)
            try {
                st.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

        if (con != null)
            try {
                con.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }


        if (result != null)
            try {
                result.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }


    }


}



