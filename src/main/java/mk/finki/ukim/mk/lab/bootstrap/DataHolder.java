package mk.finki.ukim.mk.lab.bootstrap;

import jakarta.annotation.PostConstruct;
import mk.finki.ukim.mk.lab.model.Movie;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataHolder {
    public static List<Movie> movies = new ArrayList<>();

    @PostConstruct
    public void init(){
        movies.add(new Movie(
                "The Godfather",
                "It revolves around the powerful Italian-American Mafia family, the Corleones, and their patriarch Vito Corleone (Marlon Brando) as he navigates a world of crime and power struggles",
                9.3));
        movies.add(new Movie(
                "The Dark Knight",
                "Batman (Christian Bale) facing off against the Joker (Heath Ledger) in a gritty battle of chaos and order in Gotham City",
                9.0
        ));
        movies.add(new Movie(
                "The Lord of the Rings: The Return of the King",
                "The final battle for Middle-earth as Frodo and Sam continue their quest to destroy the One Ring and Aragorn embraces his destiny as king",
                9.0
        ));
        movies.add(new Movie(
                "Pulp Fiction",
                "Intertwining multiple interconnected stories involving hitmen, a boxer, and a pair of small-time criminals in a non-linear narrative filled with dark humor and violence.",
                8.9
        ));
        movies.add(new Movie(
                "Forrest Gump",
                "Portraying the life journey of a kind-hearted man with a low IQ, Forrest Gump (Tom Hanks), as he unwittingly influences significant moments in American history.",
                8.8
        ));
    }
}
