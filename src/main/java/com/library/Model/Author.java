package com.library.Model;

import org.hibernate.validator.constraints.Length;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

/**
 * Created by D on 16.1.2016 г..
 */
@Entity
public class Author {
    @Id
    private long id;

    @NotNull
    @Length(min = 3)
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
