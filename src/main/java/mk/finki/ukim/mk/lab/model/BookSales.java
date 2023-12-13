package mk.finki.ukim.mk.lab.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Data
@Entity
@Table(name = "sales")
public class BookSales {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer numberOfCopies;
    private LocalDateTime timeBought;
    @OneToMany(mappedBy = "sales")
    List<Book> books;


    public BookSales() {
        this.timeBought = LocalDateTime.now();
    }

    public BookSales(Integer numberOfCopies){
        long minDay = LocalDate.of(1970, 1, 1).toEpochDay();
        long maxDay = LocalDate.of(2015, 12, 31).toEpochDay();
        long randomDay = ThreadLocalRandom.current().nextLong(minDay, maxDay);
        LocalDate randomDate = LocalDate.ofEpochDay(randomDay);
        this.timeBought = randomDate.atStartOfDay();
        this.numberOfCopies = numberOfCopies;
    }
}
