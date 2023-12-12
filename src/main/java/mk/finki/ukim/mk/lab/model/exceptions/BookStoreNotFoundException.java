package mk.finki.ukim.mk.lab.model.exceptions;

public class BookStoreNotFoundException extends RuntimeException{

    public BookStoreNotFoundException(Long bookStoreId) {
        super(String.format("Category with id %d does not exist.", bookStoreId));
    }
}
