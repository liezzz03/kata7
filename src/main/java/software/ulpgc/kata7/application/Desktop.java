package software.ulpgc.kata7.application;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import software.ulpgc.kata7.architecture.io.Store;
import software.ulpgc.kata7.architecture.model.Movie;
import software.ulpgc.kata7.architecture.viewmodel.Histogram;
import software.ulpgc.kata7.architecture.viewmodel.HistogramBuilder;

import javax.swing.*;
import java.io.IOException;
import java.util.stream.Stream;

public class Desktop extends JFrame {
    private final Store store;

    private Desktop(Store store) {
        this.store = store;
        this.setTitle("Histogram");
        this.setResizable(false);
        this.setSize(800, 600);
        this.setLocationRelativeTo(null);
    }

    public static Desktop create(Store store) {
        return new Desktop(store);
    }

    public Desktop display() throws IOException {
        this.getContentPane().add(chartPanelWith(histogram()));
        return this;
    }

    private ChartPanel chartPanelWith(Histogram histogram) {
        return new ChartPanel(chartWith(histogram));
    }

    private JFreeChart chartWith(Histogram histogram) {
        return ChartFactory.createHistogram(
                histogram.title(),
                histogram.x(),
                histogram.y(),
                datasetWith(histogram)
        );
    }

    private XYSeriesCollection datasetWith(Histogram histogram) {
        XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(series(histogram));
        return dataset;
    }

    private XYSeries series(Histogram histogram) {
        XYSeries xySeries = new XYSeries(histogram.legend());
        for (int bin : histogram) {
            xySeries.add(bin, histogram.count(bin));
        }
        return xySeries;
    }

    private Histogram histogram() throws IOException {
        return HistogramBuilder
                .with(movies(store))
                .title("Movies per year")
                .x("year")
                .y("count")
                .legend("Movies")
                .build(Movie::year);
    }

    static Stream<Movie> movies(Store store) throws IOException {
        return store.loadAll();
    }
}
