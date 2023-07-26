package Login_Register;
/*
处理用户登录
*/

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Login {

    Admin admin;

    void setAdmin(Admin admin) {
        this.admin = admin;
        // System.out.println(this.admin.getPassword()+" " + this.admin.getID());
    }

    /*
     * JudgeAdmin()方法
     * 判断Admin的ID和密码是否正确，如果正确，显示登录成功
     * 如果错误，弹出一个窗口，显示账号或密码错误
     */
    private String driver = "com.mysql.cj.jdbc.Driver";
    private String url = "jdbc:mysql://127.0.0.1:3306/plane";
    private String user = "root";
    private String password = "123456";

    public boolean login(Admin admin) throws SQLException, ClassNotFoundException {
        String sql = "select * from admin where id=? and password=?";

        Class.forName(driver);
        Connection conn = DriverManager.getConnection(url, user, password);
//        System.out.println(conn+"111111");
        PreparedStatement ps = conn.prepareStatement(sql);

        ps.setString(1, admin.getID());
        ps.setString(2, admin.getPassword());
        ResultSet rs = ps.executeQuery();

        int ans = 0;
        if (rs.next()) {
            ans = 1;
        }
        rs.close();
        ps.close();
        conn.close();
        if (ans == 1) {
            return true;
        } else
            return false;
    }

    int JudgeAdmin() {

        try {
            if (login(this.admin)) {
                System.out.println("登录成功");
                return 1;
            } else {
                return 0;
            }
        } catch (Exception e) {
            // e.printStackTrace();
            // System.out.println("!!!!!!!!!");
        }
        return 0;

    }
}
