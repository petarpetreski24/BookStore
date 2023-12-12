package mk.finki.ukim.mk.lab.web.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import mk.finki.ukim.mk.lab.model.Book;
import mk.finki.ukim.mk.lab.service.BookService;
import mk.finki.ukim.mk.lab.service.BookStoreService;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.http.HttpRequest;
import java.util.Optional;

@Controller
@RequestMapping("/books")
@AllArgsConstructor
public class BookController {
    private final BookService bookService;
    private final BookStoreService bookStoreService;

    @GetMapping
    public String getBooksPage(@RequestParam(required = false) String error, Model model){
        model.addAttribute("books",bookService.listBooks());

        return "listBooks";
    }

    @PostMapping("/add")
    public String saveBook(@RequestParam String title, @RequestParam String isbn,
                           @RequestParam String genre, @RequestParam int year,
                           @RequestParam long bookStoreId){
        if(this.bookService.addBook(title,isbn,genre,year,bookStoreId).isPresent()){
            return "redirect:/books";
        }
        else {
            return "redirect:/books?error=addBook";
        }

    }

    @PostMapping("/edit/{bookId}")
    public String editBook(@RequestParam String title, @RequestParam String isbn,
                           @RequestParam String genre, @RequestParam int year,
                           @RequestParam long bookStoreId,
                           @PathVariable int bookId){
        if(this.bookService.editBook(bookId,title,isbn,genre,year,bookStoreId).isPresent()){
            return "redirect:/books";
        }
        else {
            return "redirect:/books?error=editBook";
        }

    }

    @GetMapping("/delete/{id}")
    public String deleteBook(@PathVariable int id) {
        if(this.bookService.deleteBook(id).isPresent()){
            return "redirect:/books";
        }
        else {
            return "redirect:/books?error=deleteBook";

        }

    }

    @GetMapping("/edit-form/{id}")
    public String getEditBookForm(@PathVariable int id, Model model){
        Optional<Book> editBook = this.bookService.findBookById(id);
        if (editBook.isPresent()){
            model.addAttribute("book",editBook.get());
            model.addAttribute("bookstores",this.bookStoreService.findAll());
            model.addAttribute("subject","Edit");
            return "add-book";
        }
        else {
            return "redirect:/books?error=editBookForm";
        }
    }

    @GetMapping("/add-form")
    public String getAddBookPage(Model model){
        model.addAttribute("subject","Add");
        model.addAttribute("bookstores",this.bookStoreService.findAll());

        return "add-book";
    }
}
