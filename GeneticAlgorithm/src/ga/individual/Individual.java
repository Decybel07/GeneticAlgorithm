package ga.individual;

import java.util.Arrays;

public class Individual {

    public final Object[] getGens() {
        return gens;
    }

    public final double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public final int size() {
        return gens.length;
    }

    public Individual(final Object[] gens) {
        this.gens = gens;
        this.score = 0;
    }

    @Override
    public String toString() {
        return "Individual {" +
                " score = " + score +
                ", gens = " + Arrays.toString(gens) +
                " }";
    }

    final private Object[] gens;
    private double score;
}
