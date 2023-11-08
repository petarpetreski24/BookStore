package mk.finki.ukim.mk.lab.repository;

import mk.finki.ukim.mk.lab.bootstrap.DataHolder;
import mk.finki.ukim.mk.lab.model.Author;
import mk.finki.ukim.mk.lab.model.Book;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class BookRepository {
    public List<Book> findAll(){
        return DataHolder.books;
    }
    public Book findByIsbn(String isbn){
        return DataHolder.books.stream().filter(x -> x.getIsbn().equals(isbn)).findFirst().orElse(null);
    }
    public Author addAuthorToBook(Author author, Book book){
        this.findByIsbn(book.getIsbn()).getAuthors().removeIf(x-> x.getId().equals(author.getId()));
        this.findByIsbn(book.getIsbn()).getAuthors().add(author);
        return author;
    }
    public List<Book> findByYear(int year){
        return DataHolder.books.stream().filter(x -> x.getYear() == year).collect(Collectors.toList());
    }
}
