import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.groupingBy;

public class Queries {

    //Q1: Hur många filmer gjordes 1975 (enligt vårt data). Returnera ett tal
    public final long noOfMoviesFrom1975 (List<Movie> movieList) {
        return movieList.size();
    }

    //Q2: Hitta längden på den film som var längst (högst runtime). Returnera ett tal.
    public final int longestRuntime (List<Movie> movieList) {
        OptionalInt optionalInt = movieList.stream()
                .mapToInt(Movie::getRuntime)
                .max();

        return optionalInt.orElse(0);
    }

    //Q3: Hur många UNIKA genrer hade filmerna från 1975. Returnera ett tal.
    public final long uniqueGenres (List<Movie> movieList) {
        return movieList.stream()
                .flatMap(movie -> movie.getGenres().stream())
                .distinct()
                .count();
    }

    //Q4: Vilka skådisar som spelade i den film som hade högst imdb-rating. Returnera en List<String> med deras namn.
    public final List<String> castOfBestMovie(List<Movie> movieList) {
        double highestRating = movieList.stream()
                .mapToDouble(Movie::getImdbRating)
                .max()
                .orElse(0.0);

        return movieList.stream()
                .filter(movie -> movie.getImdbRating() == highestRating)
                .flatMap(movie -> movie.getCast().stream())
                .toList();
    }

    //Q5: Vad är titeln på den film som hade minst antal skådisar listade? Returnera en String.
    public final String movieWithSmallestCast (List<Movie> movieList) {
        int smallestListSize = movieList.stream()
                .map(Movie::getCast)
                .toList()
                .stream()
                .mapToInt(List::size)
                .min()
                .orElse(0);

        return movieList.stream()
                .filter(movie -> movie.getCast().size() == smallestListSize)
                .map(Movie::getTitle)
                .reduce((a, b) -> a + ", " + b)
                .orElse("");
    }

    //Q6: Hur många skådisar var med i mer än 1 film? Returnera ett tal.
    public final long noOfActorsInMultipleMovies (List<Movie> movieList) {
        return movieList.stream()
                .flatMap(movie -> movie.getCast().stream())
                .collect(groupingBy((actor -> actor), Collectors.counting()))
                .values()
                .stream()
                .filter(occurrence -> occurrence > 1)
                .count();
    }

    //Q7: Vad hette den skådis som var med i flest filmer? Returnera en String
    public final String mostRecurringActor (List<Movie> movieList) {
        Map<String, Long> tempActors = movieList.stream()
                .flatMap(movie -> movie.getCast().stream())
                .collect(groupingBy((actor -> actor), Collectors.counting()));

        long maxOccurence = Collections.max(tempActors.values());

        return tempActors.entrySet().stream()
                .filter(entry -> entry.getValue() == maxOccurence)
                .map(Map.Entry::getKey)
                .collect(Collectors.joining(", "));
    }

    //Q8: Hur många UNIKA språk har filmerna? Returnera ett tal.
    public final long uniqueLanguages (List<Movie> movieList) {
        return movieList.stream()
                .flatMap(movie -> movie.getLanguages().stream())
                .distinct()
                .count();
    }

    //Q9: Finns det någon titel som mer än en film har? Returnera en bool.
    public final boolean duplicateTitle (List<Movie> movieList) {
        return movieList.stream()
                .map(Movie::getTitle)
                .collect(groupingBy((title -> title), Collectors.counting()))
                .values()
                .stream()
                .anyMatch(value -> value > 1);
    }


    ////Queries using generic Higher-order function

    //Higher order function to group and count occurrences of input element
    private <T> Map<T, Long> groupByOccurrences(Stream<T> stream) {
        return stream.collect(groupingBy((element -> element), Collectors.counting()));
    }

    //Higher order function to filter a map by input condition
    private <T> Map<T, Long> filterBy(Map<T, Long> inputMap, Predicate<Long> condition) {
        return inputMap.entrySet()
                .stream()
                .filter(map -> condition.test(map.getValue()))
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue
                ));
    }

    //Q6: Hur många skådisar var med i mer än 1 film? Returnera ett tal.
    public final long noOfActorsInMultipleMoviesCompact (List<Movie> movieList) {
        Map<String, Long> actorGroup = groupByOccurrences(movieList.stream().flatMap(movie -> movie.getCast().stream()));

        return filterBy(actorGroup, value -> value > 1).size();
    }

    //Q7: Vad hette den skådis som var med i flest filmer? Returnera en String
    public final String mostRecurringActorCompact (List<Movie> movieList) {
        Map<String, Long> actorCount = groupByOccurrences(
                movieList.stream().flatMap(movie -> movie.getCast().stream())
        );

        long maxOccurrence = Collections.max(actorCount.values());

        Map<String, Long> filteredMap = filterBy(actorCount, value -> value == maxOccurrence);

        return String.join(", ", filteredMap.keySet());
    }

    //Q9: Finns det någon titel som mer än en film har? Returnera en bool.
    public final boolean duplicateTitleCompact (List<Movie> movieList) {
        Map<String, Long> groupedMap = groupByOccurrences(movieList.stream().map(Movie::getTitle));

        return !filterBy(groupedMap, value -> value > 1).isEmpty();
    }
}
