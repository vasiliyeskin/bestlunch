package ru.javarest.web;

import org.junit.*;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.javarest.UserTestData;
import ru.javarest.model.User;
import ru.javarest.repository.UserRepository;
import ru.javarest.util.exception.NotFoundException;

import java.util.Arrays;
import java.util.Collection;

import static ru.javarest.UserTestData.ADMIN;
import static ru.javarest.UserTestData.USER;

public class InMemoryAdminUsersRestControllerTest {
    private static ConfigurableApplicationContext appCtx;
    private static AdminUsersRestController controller;

    @BeforeClass
    public static void beforeClass() {
        appCtx = new ClassPathXmlApplicationContext("spring/spring-app.xml", "spring/mock.xml");
        System.out.println("\n" + Arrays.toString(appCtx.getBeanDefinitionNames()) + "\n");
        controller = appCtx.getBean(AdminUsersRestController.class);
    }

    @AfterClass
    public static void afterClass() {
//        May cause during JUnit "Cache is not alive (STATUS_SHUTDOWN)" as JUnit share Spring context for speed
//        http://stackoverflow.com/questions/16281802/ehcache-shutdown-causing-an-exception-while-running-test-suite
//        appCtx.close();
    }

    @Before
    public void setUp() throws Exception {
        // re-initialize
        UserRepository repository = appCtx.getBean(UserRepository.class);
        repository.getAll().forEach(u -> repository.delete(u.getId()));
        repository.save(USER);
        repository.save(ADMIN);
    }

    @Test
    public void testDelete() throws Exception {
        controller.deleteUser(UserTestData.USER_ID);
        Collection<User> users = controller.getAllUsers();
        Assert.assertEquals(users.size(), 1);
        Assert.assertEquals(users.iterator().next(), ADMIN);
    }

    @Test(expected = NotFoundException.class)
    public void testDeleteNotFound() throws Exception {
        controller.deleteUser(10);
    }
}