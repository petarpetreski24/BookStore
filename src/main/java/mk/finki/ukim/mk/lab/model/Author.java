package mk.finki.ukim.mk.lab.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@Table(name = "authors")
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Convert(converter = AuthorFullnameConverter.class)
    AuthorFullname authorFullname;
    @Column(length = 999)
    String biography;
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDate dateOfBirth;

    public Author() {
    }

    public Author(AuthorFullname authorFullname, String biography) {
        this.authorFullname = authorFullname;
        this.biography = biography;
        this.dateOfBirth = LocalDate.now();
    }
    public Author(String name,String surname, String biography) {
        this.authorFullname = new AuthorFullname(name,surname);
        this.biography = biography;
        this.dateOfBirth = LocalDate.now();
    }
}
