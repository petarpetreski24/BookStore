package mk.finki.ukim.mk.lab.bootstrap;

import jakarta.annotation.PostConstruct;
import mk.finki.ukim.mk.lab.model.Author;
import mk.finki.ukim.mk.lab.model.Book;
import mk.finki.ukim.mk.lab.model.BookStore;
import mk.finki.ukim.mk.lab.repository.AuthorRepository;
import mk.finki.ukim.mk.lab.repository.BookRepository;
import mk.finki.ukim.mk.lab.repository.BookStoreRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Component
public class DataHolder {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final BookStoreRepository bookStoreRepository;

    public DataHolder(BookRepository bookRepository, AuthorRepository authorRepository, BookStoreRepository bookStoreRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.bookStoreRepository = bookStoreRepository;
    }

    @PostConstruct
    public void init(){
        List<Author> authors = new ArrayList<>();
        List<Book> books = new ArrayList<>();
        List<BookStore> bookStores = new ArrayList<>();

        if (authorRepository.count() == 0){
            authors.add(new Author(
                    "William",
                    "Shakespeare",
                    "William Shakespeare is one of the most celebrated playwrights and poets in English literature. He was born in Stratford-upon-Avon, England. His works, including plays like \"Hamlet,\" \"Romeo and Juliet,\" and \"Macbeth,\" have had a profound impact on the world of literature and drama"
            ));
            authors.add(new Author(
                    "Jane",
                    "Austen",
                    ""
            ));
            authors.add(new Author(
                    "Charles",
                    "Dickens",
                    "Charles Dickens, born in Portsmouth, England, is a prominent Victorian novelist. He wrote numerous novels that vividly depicted the social and economic issues of his time. Some of his well-known works are \"Oliver Twist,\" \"Great Expectations,\" and \"A Tale of Two Cities.\""
            ));
            authors.add(new Author(
                    "Agatha",
                    "Christie",
                    "Agatha Christie, the \"Queen of Mystery,\" was an English author of detective fiction. Born in Torquay, England, she created iconic characters such as Hercule Poirot and Miss Marple. Her best-selling novels, including \"Murder on the Orient Express\" and \"The Murder of Roger Ackroyd,\" continue to captivate readers."
            ));
            authors.add(new Author(
                    "Gabriela Garcia",
                    "Marquez",
                    "Gabriel García Márquez, a Colombian novelist and Nobel laureate, is known for his magical realism and evocative storytelling. Born in Aracataca, Colombia, his works, such as \"One Hundred Years of Solitude\" and \"Love in the Time of Cholera,\" have made a lasting impact on world literature, particularly in Latin America."
            ));
            authorRepository.saveAll(authors);
        }

        if (bookStoreRepository.count() == 0){
            bookStores.add(new BookStore(
                    "Prosvetno Delo",
                    "Prilep",
                    "Address 1"
            ));
            bookStores.add(new BookStore(
                    "Office Plus",
                    "Skopje",
                    "Address 2"
            ));
            bookStores.add(new BookStore(
                    "Staedtler",
                    "Bitola",
                    "Address 3"
            ));
            bookStores.add(new BookStore(
                    "Literatura",
                    "Veles",
                    "Address 4"
            ));
            bookStores.add(new BookStore(
                    "Akademska Kniga",
                    "Skopje",
                    "Address 5"
            ));
            bookStoreRepository.saveAll(bookStores);
        }

        if (bookRepository.count() == 0){
            authors = authorRepository.findAll();
            books.add(new Book(
                    "978-0-316-03859-9",
                    "Hamlet",
                    "Tragedy",
                    1600,
                    new ArrayList<Author>(Arrays.asList(authors.get(0),authors.get(1))),
                    bookStores.get(0)
            ));
            books.add(new Book(
                    "978-1-250-04657-8",
                    "Pride and Prejudice",
                    "Romance",
                    1816,
                    new ArrayList<Author>(Arrays.asList(authors.get(1),authors.get(2))),
                    bookStores.get(1)
            ));
            books.add(new Book(
                    "978-0-553-21361-4",
                    "Great Expectations",
                    "Bildungsroman",
                    1861,
                    new ArrayList<Author>(Arrays.asList(authors.get(2),authors.get(3))),
                    bookStores.get(2)
            ));
            books.add(new Book(
                    "978-0-06-112008-4",
                    "Murder on the Orient Express",
                    "Detective Fiction",
                    1934,
                    new ArrayList<Author>(Arrays.asList(authors.get(3),authors.get(4))),
                    bookStores.get(3)
            ));
            books.add(new Book(
                    "978-0-451-52478-1",
                    "One Hundred Years of Solitude",
                    "Magical Realism",
                    1967,
                    new ArrayList<Author>(Collections.singletonList(authors.get(4))),
                    bookStores.get(4)
            ));
            books.add(new Book(
                    "978-0-451-52478-2",
                    "One Hundred Years of Solitude2",
                    "Magical Realism",
                    1967,
                    new ArrayList<Author>(Collections.singletonList(authors.get(1))),
                    bookStores.get(0)
            ));
            books.add(new Book(
                    "978-0-451-52478-3",
                    "One Hundred Years of Solitude3",
                    "Magical Realism",
                    1600,
                    new ArrayList<Author>(Collections.singletonList(authors.get(2))),
                    bookStores.get(3)
            ));
            bookRepository.saveAll(books);
        }
    }
}
