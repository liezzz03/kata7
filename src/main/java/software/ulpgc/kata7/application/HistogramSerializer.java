package software.ulpgc.kata7.application;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import software.ulpgc.kata7.architecture.viewmodel.Histogram;

public class HistogramSerializer {
    private static final Gson gson = new Gson();

    public static String serialize(Histogram histogram) {
        JsonObject json = new JsonObject();
        json.addProperty("title", histogram.title());
        json.addProperty("x", histogram.x());
        json.addProperty("y", histogram.y());
        JsonObject dataNote = new JsonObject();
        for (int key : histogram) {
            dataNote.addProperty(String.valueOf(key), histogram.count(key));
        }
        json.add("values", dataNote);
        return gson.toJson(json);
    }

}
