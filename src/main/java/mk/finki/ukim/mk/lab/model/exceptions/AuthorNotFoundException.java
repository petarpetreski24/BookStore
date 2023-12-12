package mk.finki.ukim.mk.lab.model.exceptions;

public class AuthorNotFoundException extends RuntimeException{
    public AuthorNotFoundException(Long authorId) {
        super(String.format("Category with id %d does not exist.", authorId));
    }

}
