package ru.javarest.service.jdbc;

import org.springframework.test.context.ActiveProfiles;
import ru.javarest.Profiles;
import ru.javarest.service.AbstractMealServiceTest;

@ActiveProfiles(Profiles.JDBC)
public class JdbcMealServiceTest extends AbstractMealServiceTest {
}