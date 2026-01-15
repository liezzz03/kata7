package software.ulpgc.kata7.application.database;

import software.ulpgc.kata7.application.*;
import software.ulpgc.kata7.architecture.model.Movie;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) throws SQLException, IOException {
        Connection connection = DriverManager.getConnection("jdbc:sqlite:movies.db");
        checkDatabase(connection);
        System.out.println("Starting Web Service on http://localhost:8080/histogram");
        new MovieWebService(new DatabaseStore(connection)).execute();
    }

    private static void checkDatabase(Connection connection) throws SQLException, IOException {
        boolean exists = connection.getMetaData()
                .getTables(null, null, "movies", null)
                .next();
        if (!exists) {
            System.out.println("Database empty. Importing from remote source...");
            importIfNeededInto(connection);
        }
    }

    private static void importIfNeededInto(Connection connection) throws IOException, SQLException {
        Stream<Movie> movies = new RemoteStore(MovieDeserializer::parse).loadAll();
        connection.setAutoCommit(false);
        new DatabaseRecorder(connection).record(movies);
        connection.commit();
        System.out.println("Import finished successfully");
    }
}
