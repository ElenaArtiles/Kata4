package software.ulpgc.view;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;
import software.ulpgc.model.Histogram;

import javax.swing.*;

public class JfreeBarChartHistogramDisplay extends JPanel implements HistogramDisplay {
    @Override
    public void display(Histogram histogram) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for(String key: histogram.labels())
            dataset.addValue(histogram.valueOf(key), "Frequency", key);
        JFreeChart barChart = ChartFactory.createBarChart(
                histogram.title(),
                "Categories",
                "Frequency",
                dataset);
        add(new ChartPanel(barChart));
    }
}
