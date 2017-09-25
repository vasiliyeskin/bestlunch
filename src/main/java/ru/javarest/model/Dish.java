package ru.javarest.model;

import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;


@SuppressWarnings("JpaQlInspection")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@NamedQueries({
        @NamedQuery(name = Dish.ALL_RESTAUR_SORTED, query = "SELECT d FROM Dish d WHERE d.restaurant.id=:restaurant_id ORDER BY d.name DESC"),
        @NamedQuery(name = Dish.ALL_SORTED, query = "SELECT d FROM Dish d ORDER BY d.name DESC"),
        @NamedQuery(name = Dish.DELETE, query = "DELETE FROM Dish d WHERE d.id=:id")})

@Entity
@Table(name="dishes", uniqueConstraints = {@UniqueConstraint(columnNames = {"restaurant_id", "name"}, name = "dishes_unique_restaurant_name_idx")})
public class Dish extends AbstractBaseEntity {
    public static final String ALL_RESTAUR_SORTED = "Dish.getAllRestaurant";
    public static final String ALL_SORTED = "Dish.getAll";
    public static final String DELETE = "Dish.delete";

    @NotBlank
    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "price", nullable = false)
    private Integer price;

    @Column(name = "registered", columnDefinition = "timestamp default now()")
    @NotNull
    private Date registered = new Date();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @NotNull
    private Restaurant restaurant;

    public Dish(){}

    public Dish(Dish dish)
    {
        super(dish.getId());
        this.name = dish.name;
        this.price = dish.price;
        this.registered = dish.registered;
        this.restaurant = dish.restaurant;
    }

    public Dish(Integer id, String name, Integer price, Date registered, Restaurant restaurant) {
        super(id);
        this.name = name;
        this.price = price;
        this.registered = registered;
        this.restaurant = restaurant;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Date getRegistered() {
        return registered;
    }

    public void setRegistered(Date registered) {
        this.registered = registered;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Dish dish = (Dish) o;

        if (!name.equals(dish.name)) return false;
        return price.equals(dish.price);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + price.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Dish{" +
                "id=" + getId() + '\'' +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", registered=" + registered +
                ", restaurant=" + restaurant +
                '}';
    }
}
