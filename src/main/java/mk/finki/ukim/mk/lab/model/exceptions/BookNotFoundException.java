package mk.finki.ukim.mk.lab.model.exceptions;

public class BookNotFoundException extends RuntimeException{
    public BookNotFoundException(Long bookId) {
        super(String.format("Category with id %d does not exist.", bookId));
    }
}
