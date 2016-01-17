package com.library.Model;

import javax.persistence.Entity;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * Created by D on 16.1.2016 г..
 */
@Entity
public class Magazine extends PublicationWork {

    @NotNull
    @Min(1)
    private int issue;

    public int getIssue() {
        return issue;
    }

    public void setIssue(int issue) {
        this.issue = issue;
    }
}
