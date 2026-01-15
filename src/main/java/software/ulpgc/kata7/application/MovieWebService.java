package software.ulpgc.kata7.application;

import io.javalin.Javalin;
import io.javalin.http.Context;
import org.jetbrains.annotations.NotNull;
import software.ulpgc.kata7.architecture.io.Store;
import software.ulpgc.kata7.architecture.model.Movie;
import software.ulpgc.kata7.architecture.viewmodel.Histogram;
import software.ulpgc.kata7.architecture.viewmodel.HistogramBuilder;

import java.io.IOException;
import java.util.function.Function;

public class MovieWebService {
    private final Store store;

    public MovieWebService(Store store) {
        this.store = store;
    }

    public void execute() {
        Javalin.create()
                .get("/histogram", this::handleHistogramRequest)
                .start(8080);
    }

    private void handleHistogramRequest(@NotNull Context context) throws IOException {
        String type = context.queryParam("type");
        if (type == null) type = "year";
        var histogram = HistogramBuilder.with(store.loadAll())
                .title("Movies by " + type)
                .x(type)
                .y("count")
                .build(selectExtract(type));

        context.result(HistogramSerializer.serialize(histogram));
    }

    private Function<Movie, Integer> selectExtract(String type) {
        return switch (type.toLowerCase()) {
            case "duration" -> Movie::duration;
            default -> Movie::year;
        };
    }

}
