package mk.finki.ukim.mk.lab.repository;

import mk.finki.ukim.mk.lab.bootstrap.DataHolder;
import mk.finki.ukim.mk.lab.model.Author;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;

@Repository
public class AuthorRepository {

    public List<Author> findAll(){
        return DataHolder.authors;
    }
    public Author findById(Long id){
        return DataHolder.authors.stream().filter(x -> Objects.equals(x.getId(), id)).findFirst().orElse(null);
    }
}
