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
import java.util.List;

@WebServlet(urlPatterns = {"/servlet/listBooks"})
@AllArgsConstructor
public class BookListServlet extends HttpServlet {
    private SpringTemplateEngine springTemplateEngine;
    private BookService bookService;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        IWebExchange exchange = JakartaServletWebApplication
                .buildApplication(getServletContext())
                .buildExchange(req,resp);

        WebContext context = new WebContext(exchange);
        List<Book> books = req.getParameter("year") != null ? bookService.findBookByYear(Integer.parseInt(req.getParameter("year"))) : bookService.listBooks();
        context.setVariable("books",books);
        this.springTemplateEngine.process(
                "listBooks.html",
                context,
                resp.getWriter()
        );
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        req.setAttribute("book",bookService.findBookByIsbn(req.getParameter("isbn")));
//        RequestDispatcher dispatcher = req.getRequestDispatcher("/author");
//        dispatcher.forward(req,resp);
    }


}
