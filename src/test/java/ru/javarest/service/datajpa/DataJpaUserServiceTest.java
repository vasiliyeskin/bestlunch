package ru.javarest.service.datajpa;

import org.junit.Test;
import org.springframework.test.context.ActiveProfiles;
import ru.javarest.Profiles;
import ru.javarest.MealTestData;
import ru.javarest.model.User;
import ru.javarest.service.AbstractJpaUserServiceTest;
import ru.javarest.util.exception.NotFoundException;

import static ru.javarest.UserTestData.*;

@ActiveProfiles(Profiles.DATAJPA)
public class DataJpaUserServiceTest extends AbstractJpaUserServiceTest {
    @Test
    public void testGetWithMeals() throws Exception {
        User user = service.getWithMeals(USER_ID);
        MATCHER.assertEquals(USER, user);
        //MealTestData.MATCHER.assertListEquals(MealTestData.MEALS, user.getMeals());
    }

    @Test(expected = NotFoundException.class)
    public void testGetWithMealsNotFound() throws Exception {
        service.getWithMeals(1);
    }
}