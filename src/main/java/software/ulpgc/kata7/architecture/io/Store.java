package software.ulpgc.kata7.architecture.io;

import software.ulpgc.kata7.architecture.model.Movie;
import java.io.IOException;
import java.util.stream.Stream;

public interface Store {
    Stream<Movie> loadAll() throws IOException;
}