import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class QueriesTest {

    Queries queries = new Queries();

    List<Movie> testDataMovies = List.of(
        new Movie("573a1396f29313caabce5419", "Kenji Mizoguchi: The Life of a Film Director", 1975, 132, List.of("Documentary", "Biography"), "Director", List.of("Takako Irie", "Daisuke Itè", "Kyèko Kagawa", "Matsutarè Kawaguchi", "Vladimir Khrulev"), 7.3, List.of("Japanese")),
        new Movie("573a1396f29313caabce54ea", "Dersu Uzala", 1975, 144, List.of("Adventure", "Biography", "Drama"), "Director", List.of("Yuriy Solomin", "Maksim Munzuk", "Mikhail Bychkov", "Vladimir Khrulev"), 8.3, List.of("Russian", "Chinese")),
        new Movie("573a1396f29313caabce5522", "The Common Man", 1975, 100, List.of("Drama"), "Director", List.of("Jean Carmet", "Pierre Tornade", "Jean Bouise", "Michel Peyrelon"), 7.4, List.of("French")),
        new Movie("573a1396f29313caabce553b", "Wrong Move", 1975, 103, List.of("Drama"), "Director", List.of("Rèdiger Vogler", "Hans Christian Blech", "Hanna Schygulla", "Nastassja Kinski", "Giuseppe Anatrelli"), 7.1, List.of("German")),
        new Movie("573a1396f29313caabce5548", "Jacob the Liar", 1975, 108, List.of("Comedy"), "Director", List.of("Paolo Villaggio", "Anna Mazzamauro", "Gigi Reder", "Giuseppe Anatrelli"), 8.0, List.of("Italian", "Japanese", "French", "German")),
        new Movie("573a1396f29313caabce55f7", "Jacob the Liar", 1975, 100, List.of("Comedy", "Crime", "Drama"), "Director", List.of("Erwin Geschonneck", "Henry Hèbchen", "Giuseppe Anatrelli"), 7.3, List.of("German"))
    );

    List<Movie> emptyTestDataMovies = new ArrayList<>();

    @Test
    public final void testQuestion1() {
        assert(queries.noOfMoviesFrom1975(testDataMovies) == 6);
        assert(queries.noOfMoviesFrom1975(testDataMovies) != 7);
        assert(queries.noOfMoviesFrom1975(emptyTestDataMovies) == 0);
    }

    @Test
    public final void testQuestion2() {
        assert(queries.longestRuntime(testDataMovies) == 144);
        assert(queries.longestRuntime(testDataMovies) != 108);
        assert(queries.longestRuntime(emptyTestDataMovies) == 0);
    }

    @Test
    public final void testQuestion3() {
        assert(queries.uniqueGenres(testDataMovies) == 6);
        assert(queries.uniqueGenres(testDataMovies) != 3);
    }

    @Test
    public final void testQuestion4() {
        assert(queries.castOfBestMovie(testDataMovies).equals(List.of("Yuriy Solomin", "Maksim Munzuk", "Mikhail Bychkov", "Vladimir Khrulev")));
        assert!(queries.castOfBestMovie(testDataMovies).equals(List.of("Rèdiger Vogler", "Hans Christian Blech", "Hanna Schygulla", "Nastassja Kinski")));
    }

    @Test
    public final void testQuestion5() {
        assert(queries.movieWithSmallestCast(testDataMovies).equals("Jacob the Liar"));
        assert!(queries.movieWithSmallestCast(testDataMovies).equals("White Collar Blues"));
    }

    @Test
    public final void testQuestion6() {
        assert(queries.noOfActorsInMultipleMovies(testDataMovies) == 2);
        assert(queries.noOfActorsInMultipleMovies(testDataMovies) != 3);
    }

    @Test
    public final void testQuestion7() {
        assert(queries.mostRecurringActor(testDataMovies).equals("Giuseppe Anatrelli"));
        assert!(queries.mostRecurringActor(testDataMovies).equals("Yuriy Solomin"));
    }

    @Test
    public final void testQuestion8() {
        assert(queries.uniqueLanguages(testDataMovies) == 6);
        assert(queries.uniqueLanguages(testDataMovies) != 7);
    }

    @Test
    public final void testQuestion9() {
        assert(queries.duplicateTitle(testDataMovies));
        assert(!queries.duplicateTitle(emptyTestDataMovies));
    }

    //Tests for queries using Higher order function
    @Test
    public final void testQuestion6Compact() {
        assert(queries.noOfActorsInMultipleMoviesCompact(testDataMovies) == 2);
        assert(queries.noOfActorsInMultipleMoviesCompact(testDataMovies) != 3);
    }

    @Test
    public final void testQuestion7Compact() {
        assert(queries.mostRecurringActorCompact(testDataMovies).equals("Giuseppe Anatrelli"));
        assert!(queries.mostRecurringActorCompact(testDataMovies).equals("Yuriy Solomin"));
    }

    @Test
    public final void testQuestion9Compact() {
        assert(queries.duplicateTitleCompact(testDataMovies));
        assert(!queries.duplicateTitleCompact(emptyTestDataMovies));
    }
}