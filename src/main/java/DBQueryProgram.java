import com.mongodb.client.*;
import org.bson.Document;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class DBQueryProgram {

    DBQueryProgram() {

        String uri = "mongodb+srv://viktorjonasson:bkfrJnOkJvONbwWs@dbtek-01.syqnq.mongodb.net/?retryWrites=true&w=majority&appName=DBTEK-01";
        Queries queries = new Queries();

        try (MongoClient mongoClient = MongoClients.create(uri)) {
            MongoDatabase db = mongoClient.getDatabase("sample_mflix");
            MongoCollection<Document> moviesCollection = db.getCollection("movies");

            //Sparar ner alla filmer från 1975 till en lista med Movie-objekt
            List<Movie> movieList = new ArrayList<>();
            for (Document doc : moviesCollection.find(new Document("year", 1975))) {
                {
                    movieList.add(Movie.fromDocument(doc));
                }
            }
            /*// Skriver ut alla filmer
            for (Movie movie : movieList) {
                System.out.println(movie);
            }*/

            //Här gör du anrop till alla dina funktioner som ska skriva ut svaren på frågorna som efterfrågas i uppgiften
            System.out.println("No. of movies: " + queries.noOfMoviesFrom1975(movieList));
            System.out.println("Longest runtime: " + queries.longestRuntime(movieList));
            System.out.println("Unique genres: " + queries.uniqueGenres(movieList));
            System.out.println("Cast of best movie(s): " + queries.castOfBestMovie(movieList).stream().collect(Collectors.joining(", ")));
            System.out.println("Title of movie(s) with smallest cast: " + queries.movieWithSmallestCast(movieList));
            System.out.println("Number of actors starring in multiple movies: " + queries.noOfActorsInMultipleMovies(movieList));
            System.out.println("Name of most recurring actor: " + queries.mostRecurringActor(movieList));
            System.out.println("Unique languages: " + queries.uniqueLanguages(movieList));
            System.out.println("True or false, several movies have the same title: " + queries.duplicateTitle(movieList));
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        DBQueryProgram m = new DBQueryProgram();
    }
}

