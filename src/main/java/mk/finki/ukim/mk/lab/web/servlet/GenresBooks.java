package mk.finki.ukim.mk.lab.web.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import mk.finki.ukim.mk.lab.model.Book;
import mk.finki.ukim.mk.lab.service.BookService;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import java.io.IOException;
import java.util.stream.Collectors;

@WebServlet(urlPatterns = {"/servlet/showGenres"})
@AllArgsConstructor
public class GenresBooks extends HttpServlet {
    private SpringTemplateEngine springTemplateEngine;
    private final BookService bookService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        IWebExchange exchange = JakartaServletWebApplication
                .buildApplication(getServletContext())
                .buildExchange(req,resp);

        WebContext context = new WebContext(exchange);
        context.setVariable("books",bookService.listBooks());
        context.setVariable("genres",bookService.listBooks().stream().map(Book::getGenre).collect(Collectors.toSet()).stream().toList());
        this.springTemplateEngine.process(
                "genreGroup.html",
                context,
                resp.getWriter()
        );
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    }
}
