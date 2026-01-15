package software.ulpgc.kata7.application;

import software.ulpgc.kata7.architecture.model.Movie;

public class MovieDeserializer {

    public static Movie parse(String str) {
        return parse(str.split("\t"));
    }

    private static Movie parse(String[] split) {
        return new Movie(split[2], toInt(split[5]), toInt(split[7]));
    }

    private static int toInt(String s) {
        if (s.equals("\\N")) return -1;
        return Integer.parseInt(s);
    }
}