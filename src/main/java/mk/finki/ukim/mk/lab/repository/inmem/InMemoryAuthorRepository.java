//package mk.finki.ukim.mk.lab.repository.inmem;
//
//import mk.finki.ukim.mk.lab.bootstrap.DataHolder;
//import mk.finki.ukim.mk.lab.model.Author;
//import org.springframework.stereotype.Repository;
//
//import java.util.List;
//import java.util.Objects;
//import java.util.Optional;
//
//@Repository
//public class InMemoryAuthorRepository {
//
//    public List<Author> findAll(){
//        return DataHolder.authors;
//    }
//    public Optional<Author> findById(Long id){
//        return DataHolder.authors.stream().filter(x -> Objects.equals(x.getId(), id)).findFirst();
//    }
//    public Author addAuthor(Author author) throws IllegalArgumentException{
//        if (author.getName().isEmpty() || author.getSurname().isEmpty()){
//            throw new IllegalArgumentException();
//        }
//        DataHolder.authors.add(author);
//        return author;
//    }
//
//    public Author editAuthor(Author author){
//        DataHolder.authors.removeIf(x-> x.getId().equals(author.getId()));
//        DataHolder.authors.add(author);
//        return author;
//    }
//
//    public Optional<Author> deleteAuthor(Author author){
//        DataHolder.authors.removeIf(x -> x.getId().equals(author.getId()));
//        return Optional.of(author);
//    }
//}
