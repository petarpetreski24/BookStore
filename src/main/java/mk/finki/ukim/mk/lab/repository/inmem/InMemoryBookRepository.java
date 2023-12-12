//package mk.finki.ukim.mk.lab.repository.inmem;
//
//import mk.finki.ukim.mk.lab.bootstrap.DataHolder;
//import mk.finki.ukim.mk.lab.model.Author;
//import mk.finki.ukim.mk.lab.model.Book;
//import org.springframework.stereotype.Repository;
//
//import java.util.List;
//import java.util.Optional;
//import java.util.stream.Collectors;
//
//@Repository
//public class InMemoryBookRepository {
//    public List<Book> findAll(){
//        return DataHolder.books;
//    }
//    public Optional<Book> findByIsbn(String isbn){
//        return DataHolder.books.stream().filter(x -> x.getIsbn().equals(isbn)).findFirst();
//    }
//    public Optional<Book> findById(long id){
//        return DataHolder.books.stream().filter(x -> x.getId().equals(id)).findFirst();
//    }
//    public Author addAuthorToBook(Author author, Book book) throws IllegalArgumentException{
//        Optional<Book> bookToAdd = findByIsbn(book.getIsbn());
//        if (bookToAdd.isEmpty()){
//            throw new IllegalArgumentException();
//        }
//        bookToAdd.get().getAuthors().removeIf(x-> x.getId().equals(author.getId()));
//        bookToAdd.get().getAuthors().add(author);
//        return author;
//    }
//    public List<Book> findByYear(int year){
//        return DataHolder.books.stream().filter(x -> x.getYear() == year).collect(Collectors.toList());
//    }
//
//    public Book addBook(Book book) throws IllegalArgumentException{
//        if (book.getTitle().isEmpty() || book.getIsbn().isEmpty()){
//            throw new IllegalArgumentException();
//        }
//        DataHolder.books.add(book);
//        return book;
//    }
//
//    public Book editBook(Book book){
//        DataHolder.books.removeIf(x-> x.getId().equals(book.getId()));
//        DataHolder.books.add(book);
//        return book;
//    }
//
//    public void deleteBook(Book book){
//        DataHolder.books.removeIf(x -> x.getId().equals(book.getId()));
//    }
//}
