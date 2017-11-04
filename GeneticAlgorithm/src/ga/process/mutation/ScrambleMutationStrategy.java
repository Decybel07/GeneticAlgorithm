package ga.process.mutation;

import ga.individual.Individual;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class ScrambleMutationStrategy implements MutationStrategy {

    @Override
    public Individual mutate(final Individual individual) {
        if (individual.size() < 2 && randomGenerator.nextDouble() >= probability) {
            return individual;
        }
        Object[] gens = individual.getGens().clone();
        final int start = randomGenerator.nextInt(gens.length - 2);
        final int end = start + randomGenerator.nextInt(gens.length - start - 2) + 2;

        ArrayList<Object> newGens = new ArrayList<>(Arrays.asList(gens).subList(start, end));
        for (int i = start; i < end; ++i) {
            gens[i] = newGens.remove(randomGenerator.nextInt(newGens.size()));
        }
        return new Individual(gens);
    }

    public ScrambleMutationStrategy(double probability) {
        this.probability = probability;
    }

    private final double probability;
    private final Random randomGenerator = new Random();
}
