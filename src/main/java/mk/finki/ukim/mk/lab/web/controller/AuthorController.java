package mk.finki.ukim.mk.lab.web.controller;

import jakarta.servlet.RequestDispatcher;
import lombok.AllArgsConstructor;
import mk.finki.ukim.mk.lab.model.Author;
import mk.finki.ukim.mk.lab.model.Book;
import mk.finki.ukim.mk.lab.service.AuthorService;
import mk.finki.ukim.mk.lab.service.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/authors")
@AllArgsConstructor
public class AuthorController {
    private AuthorService authorService;
    private BookService bookService;

    @GetMapping("show-authors")
    public String showAuthors(@RequestParam(required = false) String isbn, Model model){
        if(isbn != null){
            model.addAttribute("bookIsbn",isbn);
            model.addAttribute("authors",authorService.listAuthors());

            return "authorList";
        }
        else {
            return "redirect:/books";
        }
    }

    @PostMapping("/add-author-book")
    public String editAuthors(@RequestParam long authorId,@RequestParam String book, Model model){
        this.bookService.addAuthorToBook(authorId,book);

        return "forward:/bookDetails";
    }

    @PostMapping("/add")
    public String saveBook(@RequestParam String name, @RequestParam String surname,
                           @RequestParam String biography){
        if(this.authorService.addAuthor(name,surname,biography).isPresent()){
            return "redirect:/books";
        }
        else {
            return "redirect:/books?error=addAuthor";
        }

    }

    @PostMapping("/edit/{authorId}")
    public String editBook(@RequestParam String name, @RequestParam String surname,
                           @RequestParam String biography,
                           @PathVariable int authorId){
        if(this.authorService.editAuthor(authorId,name,surname,biography).isPresent()){
            return "redirect:/books";
        }
        else {
            return "redirect:/books?error=editAuthor";
        }

    }

    @GetMapping("/delete/{id}")
    public String deleteBook(@PathVariable int id) {
        try {
            this.authorService.deleteAuthor(id);
            return "redirect:/books";
        }
        catch (Exception ex){
            return "redirect:/books?error=deleteAuthor";
        }

    }

    @GetMapping("/edit-form/{id}")
    public String getEditBookForm(@PathVariable int id, Model model){
        Optional<Author> editAuthor = this.authorService.findById((long)id);
        if (editAuthor.isPresent()){
            model.addAttribute("author",editAuthor.get());
            model.addAttribute("subject","Edit");
            return "add-author";
        }
        else {
            return "redirect:/books?error=editAuthorForm";
        }
    }

    @GetMapping("/add-form")
    public String getAddBookPage(Model model){
        model.addAttribute("subject","Add");

        return "add-author";
    }
}
