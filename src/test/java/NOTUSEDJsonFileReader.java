import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class NOTUSEDJsonFileReader {
    private final ObjectMapper objectMapper;
    private final String filePath;
    List<Movie> testMovieList;

    public List<Movie> getTestData() {
        try {
            NOTUSEDJsonFileReader reader = new NOTUSEDJsonFileReader("src/test/testData_mflix.movies.json");
            testMovieList = reader.readJsonFileAsList(Movie.class);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return testMovieList;
    }

    public NOTUSEDJsonFileReader(String filePath) {
        this.objectMapper = new ObjectMapper();
        this.filePath = filePath;
    }

    // If your JSON represents a list of documents
    public <T> List<T> readJsonFileAsList(Class<T> valueType) throws IOException {
        return objectMapper.readValue(
                new File(filePath),
                objectMapper.getTypeFactory().constructCollectionType(List.class, valueType)
        );
    }
}
