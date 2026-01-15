package software.ulpgc.kata7.application.remote;

import software.ulpgc.kata7.application.Desktop;
import software.ulpgc.kata7.application.MovieDeserializer;
import software.ulpgc.kata7.application.RemoteStore;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        Desktop.
                create(new RemoteStore(MovieDeserializer::parse)).
                display().
                setVisible(true);
    }
}
