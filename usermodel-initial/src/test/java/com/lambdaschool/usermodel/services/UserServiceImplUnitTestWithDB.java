package com.lambdaschool.usermodel.services;

import com.lambdaschool.usermodel.UserModelApplication;
import com.lambdaschool.usermodel.models.Role;
import com.lambdaschool.usermodel.models.User;
import com.lambdaschool.usermodel.models.UserRoles;
import com.lambdaschool.usermodel.models.Useremail;
import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = UserModelApplication.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UserServiceImplUnitTestWithDB
{

    @Autowired
    UserService userService;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        // mocks -> fake data
        // stubs -> fake methods
        // Java -> mocks
        //use this function to see your data in the System.out
        //Helps you identify names and ids
        //Also could be used to display any other data you are wanting to double check.
        //        List<User> myList = userService.findAll();
        //        for (User u : myList) {
        //            System.out.println(u.getUserid() + " " + u.getUsername());
        //        }
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void findUserById() {
        assertEquals("barnbarn", userService.findUserById(11).getUsername());
    }

    @Test
    public void findByNameContaining() {
        assertEquals(1, userService.findByNameContaining("ci").size());

    }

    @Test
    public void findAll() {
        assertEquals(4, userService.findAll().size());
    }

    @Test
    public void delete() {
        userService.delete(13);
        assertEquals(4, userService.findAll().size());
        //there were 5 users now there should be 4 as it affects test above
    }

    @Test
    public void findByName() {
        assertEquals(14, userService.findByName("misskitty").getUserid());
    }

    @Test
    public void save() {
        //test name is what we want to test, must be lowercase, see data in system.out
        String testName = "userbunny";
        User u3 = new User(testName,
            "ILuvM4th!",
            "test@lambdaschool.local");

        //dont have access to role so have to make new role here, setting id
        Role r2 = new Role("tester");
        r2.setRoleid(1);

        u3.getRoles()
            .add(new UserRoles(u3,
                r2));
        u3.getUseremails()
            .add(new Useremail(u3,
                "barnbarn@email.local"));

        //making sure user is saved, not null, and has name that we want to test
        User newUser = userService.save(u3);
        assertNotNull(newUser);
        assertEquals(testName, newUser.getUsername());
    }

    @Test
    public void update() {
    }

    @Test
    public void deleteAll() {
    }
}