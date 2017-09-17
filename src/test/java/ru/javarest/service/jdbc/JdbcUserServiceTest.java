package ru.javarest.service.jdbc;

import org.springframework.test.context.ActiveProfiles;
import ru.javarest.Profiles;
import ru.javarest.service.AbstractUserServiceTest;

@ActiveProfiles(Profiles.JDBC)
public class JdbcUserServiceTest extends AbstractUserServiceTest {
}