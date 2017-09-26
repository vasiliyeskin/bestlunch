package ru.javarest.service;

import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ru.javarest.model.Vote;
import ru.javarest.repository.jpa.JpaVoteRepository;
import ru.javarest.util.exception.NotFoundException;

import java.util.Date;
import java.util.List;
import static ru.javarest.util.ValidationUtil.checkNotFoundWithId;

@Service
public class VoteService {

    private final JpaVoteRepository repository;

    public VoteService(JpaVoteRepository repository)
    {
        this.repository = repository;
    }

    public Vote create(Vote vote) {
        Assert.notNull(vote, "vote must not be null");
        return repository.save(vote);
    }

    public void delete(int id) throws NotFoundException {
        checkNotFoundWithId(repository.delete(id), id);
    }

    public Vote get(int id) throws NotFoundException {
        return checkNotFoundWithId(repository.get(id), id);
    }

    public Vote get(int userId, Date date) throws NotFoundException {
        return repository.get(userId, date);
    }

    public List<Vote> getByDate(Date date) throws NotFoundException {
        Assert.notNull(date, "date must not be null");
        return repository.getAllByDate(date);
    }


    public void update(Vote vote) {
        Assert.notNull(vote, "vote must not be null");
        repository.save(vote);
    }

    public void evictCache() {
        // only for evict cache
    }

}
