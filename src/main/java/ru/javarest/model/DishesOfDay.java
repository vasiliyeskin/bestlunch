package ru.javarest.model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "dishesofday", uniqueConstraints = {@UniqueConstraint(columnNames = {"datelunch", "dish_id"}, name = "dishesofday_unique_datelunch_dish_id_idx")})
public class DishesOfDay {

    @Column(name = "date", nullable = false)
    private Date date;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "restaurant_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @NotNull
    private Dish dish;

    public DishesOfDay(){}

    public DishesOfDay(Date date, Dish dish){
        this.date = date;
        this.dish = dish;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Dish getDish() {
        return dish;
    }

    public void setDish(Dish dish) {
        this.dish = dish;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DishesOfDay that = (DishesOfDay) o;

        return dish.equals(that.dish);
    }

    @Override
    public int hashCode() {
        return dish.hashCode();
    }

    @Override
    public String toString() {
        return "DishesOfDay{" +
                "date=" + date +
                ", dish=" + dish.getName() +
                '}';
    }
}
