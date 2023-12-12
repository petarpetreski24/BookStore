package mk.finki.ukim.mk.lab.service.impl;

import lombok.AllArgsConstructor;
import mk.finki.ukim.mk.lab.model.Author;
import mk.finki.ukim.mk.lab.model.AuthorFullname;
import mk.finki.ukim.mk.lab.repository.AuthorRepository;
import mk.finki.ukim.mk.lab.service.AuthorService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AuthorServiceImpl implements AuthorService {
    private AuthorRepository authorRepository;
    @Override
    public List<Author> listAuthors() {
        return authorRepository.findAll();
    }

    @Override
    public Optional<Author> findById(Long id) {
        return authorRepository.findById(id);
    }

    @Override
    public Optional<Author> addAuthor(String name, String surname, String biography) {
        Author newAuthor = new Author(new AuthorFullname(name,surname),biography);
        return Optional.of(authorRepository.save(newAuthor));
    }

    @Override
    public Optional<Author> editAuthor(long authorId, String name, String surname, String biography) {
        Optional<Author> foundAuthor = authorRepository.findById(authorId);
        if (foundAuthor.isEmpty()){
            throw new RuntimeException();
        }
        foundAuthor.get().setAuthorFullname(new AuthorFullname(name,surname));
        foundAuthor.get().setBiography(biography);
        return Optional.of(this.authorRepository.save(foundAuthor.get()));
    }

    @Override
    public void deleteAuthor(long authorId) {
        Optional<Author> found = this.authorRepository.findById(authorId);
        if (found.isEmpty()){
            throw new IllegalArgumentException();
        }
        this.authorRepository.delete(found.get());
    }
}
