package system.login;

import org.springframework.dao.DataAccessException;
import system.login.domain.User;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import system.login.util.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginCheck {
    //声明JDBCTemplate对象共用
    private static JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

    /**
     * 这个方法已过时
     * 验证用户登录，返回true或者false。
     * @param userName
     * @param password
     * @return
     */
    @Deprecated
    public static boolean loginCheck(String userName, String password) {
        boolean checkResult = false;
        if (null != userName && null != password) {
            Connection conn = null;
            PreparedStatement pstmt = null;
            ResultSet rs = null;

            try {
                conn = JDBCUtils.getConnection();
                String sql = "SELECT * FROM `user` WHERE username = ? AND password = ?";
                pstmt = conn.prepareStatement(sql);
                pstmt.setString(1, userName);
                pstmt.setString(2, password);
                rs = pstmt.executeQuery();

                //判断结果集是否存在
                if (rs.next()) {
                    checkResult = true;
                }

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } finally {
                JDBCUtils.close(rs, pstmt, conn);
            }
        }

        return checkResult;
    }

    /**
     * 验证用户登录，如果用户不存在，返回null
     * @param loginUser
     * @return
     */
    public User login(User loginUser) {
        try {
            String sql = "SELECT * FROM `user` WHERE username = ? AND password = ?";
            User user = template.queryForObject(sql,
                        new BeanPropertyRowMapper<User>(User.class),
                        loginUser.getUsername(), loginUser.getPassword());

            return user;
        } catch (DataAccessException e) {
            return null;
        }
    }
}
