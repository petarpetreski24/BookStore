package mk.finki.ukim.mk.lab.web.controller;

import lombok.AllArgsConstructor;
import mk.finki.ukim.mk.lab.model.Book;
import mk.finki.ukim.mk.lab.service.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import java.util.Optional;

@Controller
@RequestMapping("/bookDetails")
@AllArgsConstructor
public class BookDetailsController {
    private final BookService bookService;

    @PostMapping
    public String showDetails(@RequestParam long authorId, @RequestParam String book, Model model){
        Optional<Book> bookshow = this.bookService.findBookByIsbn(book);
        if (bookshow.isEmpty()){
            return "redirect:/books";
        }
        model.addAttribute("book",bookshow.get());

        return "bookDetails";
    }

}
