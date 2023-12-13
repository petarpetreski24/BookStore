package mk.finki.ukim.mk.lab.repository;

import mk.finki.ukim.mk.lab.model.BookSales;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookSalesRepository extends JpaRepository<BookSales,Long> {
}
