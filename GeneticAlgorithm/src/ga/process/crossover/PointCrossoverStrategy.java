package ga.process.crossover;

import ga.individual.Individual;

import java.util.Random;

public class PointCrossoverStrategy implements CrossoverStrategy {

    @Override
    public Individual[] cross(final Individual a, final Individual b) {
        Object[] gensA = a.getGens().clone();
        Object[] gensB = b.getGens().clone();

        int index = randomGenerator.nextInt(gensA.length - 1);

        for (int i = 0; i < index; ++i) {
            final Object tmp = gensA[i];
            gensA[i] = gensB[i];
            gensB[i] = tmp;
        }

        return new Individual[]{
                new Individual(gensA),
                new Individual(gensB)
        };
    }

    private final Random randomGenerator = new Random();
}
