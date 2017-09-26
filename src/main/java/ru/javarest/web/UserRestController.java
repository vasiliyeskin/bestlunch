package ru.javarest.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.javarest.model.Dish;
import ru.javarest.model.Restaurant;
import ru.javarest.model.Vote;
import ru.javarest.service.RestaurantService;
import ru.javarest.service.VoteService;
import ru.javarest.util.ValidationUtil;

import java.net.URI;
import java.util.*;

@RestController
public class UserRestController {
    public static final String REST_VOTES  = "/rest/vote";

    protected final Logger log = LoggerFactory.getLogger(getClass());
    @Autowired
    private VoteService voteService;
    @Autowired
    private RestaurantService restaurantService;

    @GetMapping(value = REST_VOTES, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Vote> getAlltoday() {
        log.info("getAllbyDate");
        return voteService.getByDate(new Date());
    }

    @PostMapping(value = REST_VOTES, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Vote> createWithLocationUser(@RequestBody Vote vote) {
        log.info("create vote {}", vote);
        ValidationUtil.checkNew(vote);
        Vote created = voteService.create(vote);

        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_VOTES + "/today")
                .buildAndExpand(created.getId()).toUri();

        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @GetMapping(value = REST_VOTES + "/today", produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Integer> getResultToday() {
        log.info("get Result today");
        List<Vote> votes = voteService.getByDate(new Date());
        List<Restaurant> restaurants = restaurantService.getAll();
        List<Integer> restr_ids = new ArrayList<>();

        for (Restaurant r: restaurants) {
            restr_ids.add(r.getId());
        }

        Map<Integer, Integer> mapRest = new HashMap<>();
        Map<String, Integer> mapRestName = new HashMap<>();
        for (Vote v: votes) {
            if(mapRest.containsKey(v.getRestaurant_id()))
            {
                mapRest.put(v.getRestaurant_id(), 1 + mapRest.get(v.getRestaurant_id()));
                mapRestName.put(restaurants.get(restr_ids.indexOf((Integer)v.getRestaurant_id())).getTitle(), mapRest.get(v.getRestaurant_id()));
            }
            else
            {
                mapRest.put(v.getRestaurant_id(),1);
                mapRestName.put(restaurants.get(restr_ids.indexOf((Integer)v.getRestaurant_id())).getTitle(), 1);
            }
        }

        return mapRestName;
    }

}
