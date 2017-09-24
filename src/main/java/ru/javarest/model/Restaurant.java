package ru.javarest.model;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.*;

@Entity
@Table(name="restaurants", uniqueConstraints = {@UniqueConstraint(columnNames = "email", name = "restaurants_unique_email_idx")})
public class Restaurant extends AbstractBaseEntity {

    @NotBlank
    @Column(name = "title", nullable = false)
    protected String title;

    @NotBlank
    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "email", nullable = false, unique = true)
    @Email
    @NotBlank
    private String email;

    @NotBlank
    @Column(name = "site", nullable = false)
    private String site;

    @Column(name = "registered", columnDefinition = "timestamp default now()")
    @NotNull
    private Date registered = new Date();

    public Restaurant(){}

    public Restaurant(Restaurant restaurant) {
        super(restaurant.getId());
        this.title = restaurant.title;
        this.address = restaurant.address;
        this.email = restaurant.email;
        this.site = restaurant.site;
        this.registered = restaurant.registered;
    }

    public Restaurant(Integer id, String title, String address, String email, String site, Date registered) {
        super(id);
        this.title = title;
        this.address = address;
        this.email = email;
        this.site = site;
        this.registered = registered;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public Date getRegistered() {
        return registered;
    }

    public void setRegistered(Date registered) {
        this.registered = registered;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Restaurant that = (Restaurant) o;

        if (!title.equals(that.title)) return false;
        return email.equals(that.email);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + title.hashCode();
        result = 31 * result + email.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Restaurant{" +
                "id=" + getId() + '\'' +
                ", title='" + title + '\'' +
                ", address='" + address + '\'' +
                ", email='" + email + '\'' +
                ", site='" + site + '\'' +
                ", registered=" + registered +
                '}';
    }
}
