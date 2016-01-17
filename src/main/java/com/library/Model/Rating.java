package com.library.Model;


import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
public class Rating {
    @Id
    @GeneratedValue
    private long id;

    @NotNull
    @Length(max = 50)
    private String ip;

    @NotNull
    @Max(10)
    private byte value;

    @ManyToOne
    @JoinColumn(insertable=false, updatable=false,nullable=false)
    private PublicationWork publicationWork;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public byte getValue() {
        return value;
    }

    public void setValue(byte value) {
        this.value = value;
    }

    public PublicationWork getPublicationWork() {
        return publicationWork;
    }

    public void setPublicationWork(PublicationWork publicationWork) {
        this.publicationWork = publicationWork;
    }
}
