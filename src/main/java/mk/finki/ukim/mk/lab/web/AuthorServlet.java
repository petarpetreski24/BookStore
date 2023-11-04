package mk.finki.ukim.mk.lab.web;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import mk.finki.ukim.mk.lab.model.Book;
import mk.finki.ukim.mk.lab.service.AuthorService;
import mk.finki.ukim.mk.lab.service.BookService;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import java.io.IOException;

@WebServlet(urlPatterns = {"/author"})
@AllArgsConstructor
public class AuthorServlet extends HttpServlet {
    private SpringTemplateEngine springTemplateEngine;
    private AuthorService authorService;
    private BookService bookService;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("isbn") != null){
            Book book =  bookService.findBookByIsbn(req.getParameter("isbn"));
            IWebExchange exchange = JakartaServletWebApplication
                    .buildApplication(getServletContext())
                    .buildExchange(req,resp);

            WebContext context = new WebContext(exchange);
            context.setVariable("book",book);
            context.setVariable("authors",authorService.listAuthors());

            this.springTemplateEngine.process(
                    "authorList.html",
                    context,
                    resp.getWriter()
            );
        }
        else{
            resp.sendRedirect("/listBooks");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.bookService.addAuthorToBook(Long.parseLong(req.getParameter("authorId")),req.getParameter("book"));
        RequestDispatcher dispatcher = req.getRequestDispatcher("/bookDetails");
        req.setAttribute("book",this.bookService.findBookByIsbn(req.getParameter("book")));
        dispatcher.forward(req,resp);
    }
}
