package software.ulpgc.control;

import software.ulpgc.model.Histogram;
import software.ulpgc.model.Title;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HistogramGenerator {
    private final Map<String, Integer> map = new HashMap<>();
    public void feed(Title title) {
        String type = title.type().name();
        map.putIfAbsent(type, 0);
        map.compute(type, (k, v) -> v + 1);
    }

    public Histogram get() {
        return new FromMapHistogram(map);
    }

    private static class FromMapHistogram implements Histogram {
        private final Map<String, Integer> histogram;

        public FromMapHistogram(Map<String, Integer> histogram) {
            this.histogram = histogram;
        }

        @Override
        public String title() {
            return "Title types Distribution";
        }

        @Override
        public List<String> labels() {
            return new ArrayList<>(histogram.keySet());
        }

        @Override
        public int valueOf(String label) {
            return histogram.get(label);
        }
    }
}
