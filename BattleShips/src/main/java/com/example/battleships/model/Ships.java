package com.example.battleships.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Min;

import com.example.battleships.model.enums.CategoryNames;
import org.hibernate.validator.constraints.Length;

@Entity
@Table(name = "ships")
public class Ships {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(unique = true, nullable = false)
    @Length(min = 2, max = 10)
    private String name;

    @Min(0)
    private long health;

    @Min(0)
    private long power;

    private LocalDate created;

    @ManyToOne(targetEntity = Categories.class)
    private CategoryNames type;

    @ManyToOne
    private Users user;

    public Ships(String name, long health, long power, LocalDate created, CategoryNames type, Users user) {
        this.name = name;
        this.health = health;
        this.power = power;
        this.created = created;
        this.type = type;
        this.user = user;
    }

    public Ships() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public Ships setName(String name) {
        this.name = name;
        return null;
    }

    public long getHealth() {
        return health;
    }

    public void setHealth(long health) {
        this.health = health;
    }

    public long getPower() {
        return power;
    }

    public void setPower(long power) {
        this.power = power;
    }


    public LocalDate getCreated() {
        return created;
    }

    public void setCreated(LocalDate created) {
        this.created = created;
    }

    public CategoryNames getType() {
        return type;
    }

    public void setType(CategoryNames type) {
        this.type = type;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }
   

    
}
