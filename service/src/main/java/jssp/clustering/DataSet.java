package jssp.clustering;

import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import org.apache.commons.math3.distribution.NormalDistribution;
import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;
import org.apache.commons.math3.ml.clustering.DoublePoint;
import org.apache.commons.math3.random.RandomAdaptor;
import org.apache.commons.math3.random.RandomGenerator;
import org.apache.commons.math3.random.SobolSequenceGenerator;
import org.apache.commons.math3.util.FastMath;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static org.apache.commons.math3.util.FastMath.PI;

/**
 * Plots clustering results for various algorithms and datasets.
 * Based on
 * <a href="http://scikit-learn.org/stable/auto_examples/cluster/plot_cluster_comparison.html">scikit learn</a>.
 */
public class DataSet {
    private final int samples;
    private final RandomGenerator random;
    private final boolean shuffle;

    public DataSet(int samples, RandomGenerator random, boolean shuffle) {
        this.samples = samples;
        this.random = random;
        this.shuffle = shuffle;
    }

    public List<List<DoublePoint>> generate() {
        List<List<DoublePoint>> dataSets = Lists.newArrayList(
                normalize(makeCircles(0.04, 0.5), -1, 1, -1, 1),
                normalize(makeMoons(0.04), -1, 2, -1, 1),
                normalize(makeBlobs(3, 1.0, -10, 10), -12, 12, -12, 12),
                normalize(makeRandom(), -1, 1, -1, 1));

        if (shuffle) {
            dataSets.forEach(list -> Collections.shuffle(list, new RandomAdaptor(random)));
        }
        return dataSets;
    }

    private List<Vector2D> makeCircles(double noise, double factor) {
        Preconditions.checkArgument(factor >= 0 && factor <= 1);

        NormalDistribution dist = new NormalDistribution(random, 0.0, noise, 1e-9);

        List<Vector2D> points = new ArrayList<>();
        double step = 2.0 * PI / (samples / 2.0 + 1);
        for (double angle = 0; angle < 2.0 * FastMath.PI; angle += step) {
            points.add(new Vector2D(FastMath.cos(angle), FastMath.sin(angle)).add(generateNoiseVector(dist)));
            points.add(new Vector2D(FastMath.cos(angle), FastMath.sin(angle)).scalarMultiply(factor).add(generateNoiseVector(dist)));
        }
        return points;
    }

    private List<Vector2D> makeMoons(double noise) {
        NormalDistribution dist = new NormalDistribution(random, 0.0, noise, 1e-9);

        int nSamplesOut = samples / 2;
        int nSamplesIn = samples - nSamplesOut;

        List<Vector2D> points = new ArrayList<>();
        double step = PI / (nSamplesOut / 2.0);
        for (double angle = 0; angle < PI; angle += step) {
            points.add(new Vector2D(FastMath.cos(angle), FastMath.sin(angle)).add(generateNoiseVector(dist)));
        }

        step = PI / (nSamplesIn / 2.0);
        for (double angle = 0; angle < PI; angle += step) {
            points.add(new Vector2D(1 - FastMath.cos(angle), 1 - FastMath.sin(angle) - 0.5).add(generateNoiseVector(dist)));
        }
        return points;
    }

    private List<Vector2D> makeBlobs(int centers, double clusterStd, double min, double max) {

        NormalDistribution dist = new NormalDistribution(random, 0.0, clusterStd, 1e-9);

        double range = max - min;
        Vector2D[] centerPoints = new Vector2D[centers];
        for (int i = 0; i < centers; i++) {
            centerPoints[i] = new Vector2D(
                    random.nextDouble() * range + min,
                    random.nextDouble() * range + min);
        }

        int[] nSamplesPerCenter = new int[centers];
        int count = samples / centers;
        Arrays.fill(nSamplesPerCenter, count);

        for (int i = 0; i < samples % centers; i++) {
            nSamplesPerCenter[i]++;
        }

        List<Vector2D> points = new ArrayList<>();
        for (int i = 0; i < centers; i++) {
            for (int j = 0; j < nSamplesPerCenter[i]; j++) {
                points.add(new Vector2D(dist.sample(), dist.sample()).add(centerPoints[i]));
            }
        }
        return points;
    }

    private List<Vector2D> makeRandom() {
        SobolSequenceGenerator generator = new SobolSequenceGenerator(2);
        generator.skipTo(999999);
        List<Vector2D> points = new ArrayList<>();

        for (double i = 0; i < samples; i++) {
            double[] vector = generator.nextVector();
            points.add(new Vector2D(new double[] {
                    vector[0] * 2 - 1,
                    vector[1] * 2 - 1
            }));
        }

        return points;
    }

    private Vector2D generateNoiseVector(NormalDistribution distribution) {
        return new Vector2D(distribution.sample(), distribution.sample());
    }

    public List<DoublePoint> normalize(final List<Vector2D> input, double minX, double maxX, double minY, double maxY) {
        double rangeX = maxX - minX;
        double rangeY = maxY - minY;
        return input.stream().map(p -> new DoublePoint(new double[]{
                    (p.getX() - minX) / rangeX * 2 - 1,
                    (p.getY() - minY) / rangeY * 2 - 1
            })).collect(Collectors.toList());

    }
}