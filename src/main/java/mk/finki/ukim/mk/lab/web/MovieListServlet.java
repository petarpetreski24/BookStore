package mk.finki.ukim.mk.lab.web;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mk.finki.ukim.mk.lab.model.TicketOrder;
import mk.finki.ukim.mk.lab.service.MovieService;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import java.io.IOException;

@WebServlet("/movies")
public class MovieListServlet extends HttpServlet {
    private final SpringTemplateEngine springTemplateEngine;
    private final MovieService movieService;

    public MovieListServlet(SpringTemplateEngine springTemplateEngine, MovieService movieService) {
        this.springTemplateEngine = springTemplateEngine;
        this.movieService = movieService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        IWebExchange exchange = JakartaServletWebApplication
                .buildApplication(getServletContext())
                .buildExchange(req,resp);

        WebContext context = new WebContext(exchange);
        context.setVariable("movies",movieService.listAll());

        this.springTemplateEngine.process(
                "listMovies.html",
                context,
                resp.getWriter()
        );

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(req.getParameter("buyTickets") != null){
            TicketOrder ticket = new TicketOrder(
                    req.getParameter("title"),
                    "Petko Petkov",
                    req.getRemoteAddr(),
                    Long.parseLong(req.getParameter("numTickets")));
            RequestDispatcher dispatcher = req.getRequestDispatcher("ticketOrder");
            req.setAttribute("ticket",ticket);
            dispatcher.forward(req,resp);
        }
        else if(req.getParameter("filterMovies") != null){
            IWebExchange exchange = JakartaServletWebApplication
                    .buildApplication(getServletContext())
                    .buildExchange(req,resp);

            WebContext context = new WebContext(exchange);
            context.setVariable("movies",
                    movieService.searchMovies(req.getParameter("filterText"),
                            Long.parseLong(req.getParameter("filterRating"))));

            this.springTemplateEngine.process(
                    "listMovies.html",
                    context,
                    resp.getWriter()
            );
        }
        else if(req.getParameter("allMovies") != null){
            resp.sendRedirect("/movies");
        }

    }
}
