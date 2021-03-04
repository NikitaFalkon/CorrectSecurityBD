package com.Model;

import com.Repository.UserRepository;
import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class UserServiceTest extends TestCase {
    @Autowired
    UserService userService;
    @MockBean
    UserRepository userRepository;
    @Test
    public void testNewUser() {
        User user = new User();
        user.setUsername("name");
        boolean Created = userService.NewUser(user);
        Assert.assertTrue(Created);
        Assert.assertNotNull(user.getUsername());
        Mockito.verify(userRepository, Mockito.times(1)).save(user);
    }
    @Test
    public void addUserFailTest()
    {
        User user = new User();
        user.setUsername("u");
        Mockito.doReturn(new User())
                .when(userRepository)
                .findByUsername("u");
        boolean Created = userService.NewUser(user);
        Assert.assertFalse(Created);
    }
    @Test
    public void testFindUser()
    {
        User user = new User();
        user.setUsername("u");
        Mockito.doReturn(new User())
                .when(userRepository)
                .findByUsername("u");
        boolean Exist = userService.FindUser(user);
        Assert.assertTrue(Exist);
    }
    @Test
    public void testFindUserFail()
    {
        User user = new User();
        boolean Exist = userService.FindUser(user);
        Assert.assertFalse(Exist);
    }
/*    @Test
    public void testRedactUser()
    {
        User user = new User();
        user.setPassword("password");
        user.setUsername("user");
        user.setId((long) 1);
        Mockito.doReturn(new User())
                .when(userRepository)
                .findById((long)2).orElseThrow();
        boolean Redact = userService.redactUser("u", "p", 1);
        Assert.assertTrue(Redact);
        Assert.assertEquals(user.getPassword(), "p");
    }*/
}