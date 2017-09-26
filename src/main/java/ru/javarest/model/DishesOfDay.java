package ru.javarest.model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@NamedQueries({
        @NamedQuery(name = DishesOfDay.DELETE, query = "DELETE FROM DishesOfDay dd WHERE dd.id=:id"),
        @NamedQuery(name = DishesOfDay.BY_DATE, query = "SELECT dd FROM DishesOfDay dd WHERE dd.datelunch=:datelunch"),
        @NamedQuery(name = DishesOfDay.ALL_SORTED, query = "SELECT dd FROM DishesOfDay dd ORDER BY dd.datelunch")
})

@Entity
@Table(name = "dishesofday", uniqueConstraints = {@UniqueConstraint(columnNames = {"datelunch", "dish_id"}, name = "dishesofday_unique_datelunch_dish_id_idx")})
public class DishesOfDay extends AbstractBaseEntity {

    public static final String DELETE = "DishesOfDay.delete";
    public static final String BY_DATE = "DishesOfDay.getDishesOfDate";
    public static final String ALL_SORTED = "DishesOfDay.getAllSorted";

    @Column(name = "datelunch", nullable = false)
    private Date datelunch;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "dish_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @NotNull
    private Dish dish;

    public DishesOfDay(){}

    public DishesOfDay(Date date, Dish dish){
        this.datelunch = date;
        this.dish = dish;
    }

    public Date getDatelunch() {
        return datelunch;
    }

    public void setDatelunch(Date datelunch) {
        this.datelunch = datelunch;
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
                "id=" + getId() +
                ", datelunch=" + datelunch +
                ", dish=" + dish.getName() +
                '}';
    }
}
