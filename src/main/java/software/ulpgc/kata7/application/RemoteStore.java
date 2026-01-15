package software.ulpgc.kata7.application;

import software.ulpgc.kata7.architecture.io.Store;
import software.ulpgc.kata7.architecture.model.Movie;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Stream;
import java.util.zip.GZIPInputStream;

public class RemoteStore implements Store {
    private final Function<String, Movie> deserialize;

    public RemoteStore(Function<String, Movie> deserialize) {
        this.deserialize = deserialize;
    }

    @Override
    public Stream<Movie> loadAll() throws IOException {
        return LoadFrom(new URL("https://datasets.imdbws.com/title.basics.tsv.gz"));
    }

    private Stream<Movie> LoadFrom(URL url) throws IOException {
        return LoadFrom(url.openConnection());
    }

    private Stream<Movie> LoadFrom(URLConnection urlConnection) throws IOException {
        return LoadFrom(unzip(urlConnection.getInputStream()));
    }

    private Stream<Movie> LoadFrom(InputStream is) throws IOException {
        return LoadFrom(new BufferedReader(new InputStreamReader(is)));
    }

    private Stream<Movie> LoadFrom(BufferedReader bufferedReader) throws IOException {
        return bufferedReader.lines().skip(1).map(deserialize);
    }

    private InputStream unzip(InputStream inputStream) throws IOException {
        return new GZIPInputStream(new BufferedInputStream(inputStream, 4096));
    }
}