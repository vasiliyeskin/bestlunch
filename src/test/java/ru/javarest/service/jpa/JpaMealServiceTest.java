package ru.javarest.service.jpa;

import org.springframework.test.context.ActiveProfiles;
import ru.javarest.Profiles;
import ru.javarest.service.AbstractMealServiceTest;

@ActiveProfiles(Profiles.JPA)
public class JpaMealServiceTest extends AbstractMealServiceTest {
}