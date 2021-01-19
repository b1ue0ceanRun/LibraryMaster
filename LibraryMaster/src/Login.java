import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


//用于登录的类
public class Login {
    public boolean login(String id, String password) {
        Connection connection = null;
        PreparedStatement psst = null;
        ResultSet rs = null;
        if (id == null || password == null) {
            return false;
        }
        //连接数据库
        try {
            connection = JDBCUtils.getConnection();
            String sql = "select * from students where student_id = ? and password = ?";
            psst = connection.prepareStatement(sql);
            psst.setString(1,id);
            psst.setString(2,password);
            rs = psst.executeQuery();

            return rs.next();


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            JDBCUtils.close(psst,connection,rs);
        }


        return false;
    }

}
