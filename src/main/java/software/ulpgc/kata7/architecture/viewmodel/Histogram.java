package software.ulpgc.kata7.architecture.viewmodel;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class Histogram implements Iterable<Integer> {
    private final Map<Integer, Integer> map;
    private final Map<String, String> labels;

    public Histogram(Map<String, String> labels) {
        this.map = new HashMap<>();
        this.labels = labels;
    }

    public void addTo(int bin) {
        map.put(bin, count(bin) + 1);
    }

    public Integer count(int i) {
        return map.getOrDefault(i, 0);
    }

    @Override
    public Iterator<Integer> iterator() {
        return map.keySet().iterator();
    }

    public int size() {
        return map.size();
    }

    public String title() {
        return labels.getOrDefault("title", "");
    }

    public String x() {
        return labels.getOrDefault("x", "");
    }

    public String y() {
        return labels.getOrDefault("y", "");
    }

    public String legend() {
        return labels.getOrDefault("legend", "");
    }
}
