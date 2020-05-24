package justdemo.login.test;

import justdemo.login.domain.User;
import org.junit.Test;
import justdemo.servlet.LoginCheck;

public class LoginCheckTest {
    @Test
    public void testLogin() {
        User loginUser = new User();
        loginUser.setUsername("zhangsanfeng1");
        loginUser.setPassword("123456");

        LoginCheck loginCheck = new LoginCheck();
        User resultUser = loginCheck.login(loginUser);

        System.out.println(resultUser);
    }
}
