package mk.finki.ukim.mk.lab.model;

import lombok.AllArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
public class AuthorFullname implements Serializable {

    private String name;
    private String surname;

    public AuthorFullname() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }
}
