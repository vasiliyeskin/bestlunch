package ru.javarest.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.javarest.model.Dish;
import ru.javarest.model.Restaurant;
import ru.javarest.model.User;
import ru.javarest.service.DishService;
import ru.javarest.service.RestaurantService;
import ru.javarest.service.UserService;
import ru.javarest.util.ValidationUtil;

import java.net.URI;
import java.util.List;

@RestController
public class AdminRestController {
    public static final String REST_USERS  = "/rest/admin/users";
    public static final String REST_RESTS  = "/rest/admin/restaurants";
    public static final String REST_DISHES = "/rest/admin/dishes";

    protected final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private UserService userService;

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private DishService dishService;

    @GetMapping(value = REST_USERS, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<User> getAllUsers() {
        log.info("getAll");
        return userService.getAll();
    }

    @GetMapping(value = REST_RESTS, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Restaurant> getAllRestaurants()
    {
        log.info("getAll restaurants");
        return restaurantService.getAll();
    }

    @GetMapping(value = REST_DISHES, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Dish> getAllDishes()
    {
        log.info("getAll dishes");
        return dishService.getAll();
    }

    @GetMapping(value = REST_USERS + "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public User getUser(@PathVariable("id") int id) {
        log.info("get user {}", id);
        return userService.get(id);
    }

    @GetMapping(value = REST_RESTS + "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Restaurant getRestaurant(@PathVariable("id") int id) {
        log.info("get restaurant {}", id);
        return restaurantService.get(id);
    }

    @GetMapping(value = REST_DISHES + "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Dish getDish(@PathVariable("id") int id) {
        log.info("get dish {}", id);
        return dishService.get(id);
    }

    @PostMapping(value = REST_USERS, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> createWithLocationUser(@RequestBody User user) {
        log.info("create user {}", user);
        ValidationUtil.checkNew(user);
        User created = userService.create(user);

        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_USERS + "/{id}")
                .buildAndExpand(created.getId()).toUri();

        return ResponseEntity.created(uriOfNewResource).body(created);
    }


    @PostMapping(value = REST_RESTS, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Restaurant> createWithLocationRestaurant(@RequestBody Restaurant restaurant) {
        log.info("create restaurant {}", restaurant);
        ValidationUtil.checkNew(restaurant);
        Restaurant created = restaurantService.create(restaurant);

        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_RESTS + "/{id}")
                .buildAndExpand(created.getId()).toUri();

        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @PostMapping(value = REST_DISHES, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Dish> createWithLocationDish(@RequestBody Dish dish) {
        log.info("create dish {}", dish);
        ValidationUtil.checkNew(dish);
        Dish created = dishService.create(dish);

        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_DISHES + "/{id}")
                .buildAndExpand(created.getId()).toUri();

        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @DeleteMapping(value = REST_USERS + "/{id}")
    public void deleteUser(@PathVariable("id") int id) {
        log.info("delete user {}", id);
        userService.delete(id);
    }

    @DeleteMapping(value = REST_RESTS + "/{id}")
    public void deleteRestaurant(@PathVariable("id") int id) {
        log.info("delete restaurant {}", id);
        restaurantService.delete(id);
    }


    @DeleteMapping(value = REST_DISHES + "/{id}")
    public void deleteDish(@PathVariable("id") int id) {
        log.info("delete dish {}", id);
        dishService.delete(id);
    }

    @PutMapping(value = REST_USERS + "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void updateUser(@RequestBody User user, @PathVariable("id") int id) {
        log.info("update user {} with id={}", user, id);
        ValidationUtil.assureIdConsistent(user, id);
        userService.update(user);
    }

    @PutMapping(value = REST_RESTS + "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void updateRestaurant(@RequestBody Restaurant restaurant, @PathVariable("id") int id) {
        log.info("update restaurant {} with id={}", restaurant, id);
        ValidationUtil.assureIdConsistent(restaurant, id);
        restaurantService.update(restaurant);
    }

    @PutMapping(value = REST_DISHES + "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void updateDish(@RequestBody Dish dish, @PathVariable("id") int id) {
        log.info("update dish {} with id={}", dish, id);
        ValidationUtil.assureIdConsistent(dish, id);
        dishService.update(dish);
    }

    @GetMapping(value = REST_USERS + "/by", produces = MediaType.APPLICATION_JSON_VALUE)
    public User getUserByMail(@RequestParam("email") String email) {
        log.info("getByEmail {}", email);
        return userService.getByEmail(email);
    }

    @GetMapping(value = REST_RESTS + "/by", produces = MediaType.APPLICATION_JSON_VALUE)
    public Restaurant getRestaurantByMail(@RequestParam("email") String email) {
        log.info("getByEmail {}", email);
        return restaurantService.getByEmail(email);
    }

}