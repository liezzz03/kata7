package software.ulpgc.kata7.architecture.viewmodel;

import software.ulpgc.kata7.architecture.model.Movie;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Stream;

public class HistogramBuilder {
    private final Stream<Movie> movies;
    private final Map<String, String> labels;

    public static HistogramBuilder histogramCreation(Stream<Movie> movies) {
        return new HistogramBuilder(movies);
    }
    private HistogramBuilder(Stream<Movie> movies) {
        this.movies = movies
                .filter(Movie->Movie.year()>=1900)
                .filter(Movie->Movie.year()<=2025);
        this.labels = new HashMap<>();
    }

    public static HistogramBuilder with(Stream<Movie> movies) {
        if (movies == null) throw new IllegalArgumentException("Movies cannot be null"); {
            return new HistogramBuilder(movies);
        }
    }

    public Histogram build(Function<Movie, Integer> binarize) {
        Histogram histogram = new Histogram(labels);

        movies.map(binarize).forEach(histogram::addTo);
        return histogram;
    }


    public HistogramBuilder title(String label){
        labels.put("title", label);
        return this;
    }

    public HistogramBuilder x(String label){
        labels.put("x", label);
        return this;
    }

    public HistogramBuilder y(String label){
        labels.put("y", label);
        return this;
    }

    public HistogramBuilder legend(String label){
        labels.put("legend", label);
        return this;
    }

}
