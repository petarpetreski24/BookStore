package mk.finki.ukim.mk.lab.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;

@Data
@AllArgsConstructor
public class Book {
    String isbn;
    String title;
    String genre;
    int year;
    ArrayList<Author> authors;
}
