package mk.finki.ukim.mk.lab.service.impl;

import lombok.AllArgsConstructor;
import mk.finki.ukim.mk.lab.model.Author;
import mk.finki.ukim.mk.lab.model.Book;
import mk.finki.ukim.mk.lab.model.BookStore;
import mk.finki.ukim.mk.lab.repository.AuthorRepository;
import mk.finki.ukim.mk.lab.repository.BookRepository;
import mk.finki.ukim.mk.lab.repository.BookStoreRepository;
import mk.finki.ukim.mk.lab.service.BookService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class BookServiceImpl implements BookService {
    private AuthorRepository authorRepository;
    private BookRepository bookRepository;
    private BookStoreRepository bookStoreRepository;
    @Override
    public List<Book> listBooks() {
        return bookRepository.findAll();
    }

    @Override
    public Author addAuthorToBook(Long authorId, String isbn) {
        Optional<Author> author = authorRepository.findById(authorId);
        Optional<Book> book = bookRepository.findByIsbn(isbn);
        if(author.isEmpty()){
            throw new IllegalArgumentException(); //TODO Nonexistent author
        }
        if(book.isEmpty()){
            throw new IllegalArgumentException(); //TODO Nonexistent book
        }
        List<Author> avtori = new ArrayList<>(book.get().getAuthors());
        if(avtori.contains(author.get()))
        {
            book.get().setAuthors(avtori);
            return author.get();
        }
        avtori.add(author.get());
//        bookRepo.delete(book);
        book.get().setAuthors(avtori);

        bookRepository.save(book.get());

        return author.get();
//        book.get().getAuthors().add(author.get());
//        return author.get();
    }

    @Override
    public Optional<Book> findBookByIsbn(String isbn) {
        return bookRepository.findByIsbn(isbn);
    }

    @Override
    public Optional<Book> findBookById(long id) {
        return bookRepository.findById(id);
    }

    @Override
    public List<Book> findBookByYear(int year) {
        return bookRepository.findAllByYear(year);
    }

    @Override
    public Optional<Book> addBook(String title, String isbn, String genre, int year, long bookStoreId) throws IllegalArgumentException{
        Optional<BookStore> foundBookstore = bookStoreRepository.findAll().stream().filter(x -> x.getId().equals(bookStoreId)).findFirst();
        if (title.isEmpty() || isbn.isEmpty() || genre.isEmpty() || foundBookstore.isEmpty()){
            throw new IllegalArgumentException();
        }
        Book newBook = new Book(String.valueOf((long) (Math.random()*1000)),title,genre,year,new ArrayList<>(),foundBookstore.get());
        bookRepository.save(newBook);
        return Optional.of(newBook);

//        try{
//            BookStore foundBookstore = bookStoreRepository.findAll().stream().filter(x -> x.getId().equals(bookStoreId)).findFirst().get();
//            Book newBook = new Book(isbn,(long) (Math.random() * 1000),title,genre,year,new ArrayList<>(),foundBookstore);
//            bookRepository.addBook(newBook);
//            return Optional.of(newBook);
//        }
//        catch (Exception ex){
//            return Optional.empty();
//        }
    }

    @Override //TODO Without removing
    public Optional<Book> editBook(int bookId, String title, String isbn, String genre, int year, long bookStoreId) throws IllegalArgumentException{
        Optional<BookStore> foundBookstore = bookStoreRepository.findAll().stream().filter(x -> x.getId().equals(bookStoreId)).findFirst();
        Optional<Book> foundBook = bookRepository.findById((long) bookId);
        if (foundBook.isEmpty() || foundBookstore.isEmpty() || title.isEmpty() || isbn.isEmpty() || genre.isEmpty()){
            throw new IllegalArgumentException();
        }
        foundBook.get().setTitle(title);
        foundBook.get().setIsbn(isbn);
        foundBook.get().setGenre(genre);
        foundBook.get().setYear(year);
        foundBook.get().setBookStore(foundBookstore.get());
        bookRepository.save(foundBook.get());
        return foundBook;
//        try{
//            BookStore foundBookstore = bookStoreRepository.findAll().stream().filter(x -> x.getId().equals(bookStoreId)).findFirst().get();
//            Book editBook = new Book(isbn,(long) bookId,title,genre,year,new ArrayList<>(),foundBookstore);
//            bookRepository.editBook(editBook);
//            return Optional.of(editBook);
//        }
//        catch (Exception ex){
//            return Optional.empty();
//        }
    }

    @Override
    public Optional<Book> deleteBook(int bookId) throws IllegalArgumentException{
        Optional<Book> deleteBook = bookRepository.findById((long)bookId);
        if (deleteBook.isEmpty()){
            throw new IllegalArgumentException();
        }
//            BookStore foundBookstore = bookStoreRepository.findAll().stream().filter(x -> x.getId().equals(bookStoreId)).findFirst().get();

        bookRepository.delete(deleteBook.get());
        return deleteBook;
    }
}
