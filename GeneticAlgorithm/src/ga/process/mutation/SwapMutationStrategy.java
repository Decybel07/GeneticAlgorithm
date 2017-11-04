package ga.process.mutation;

import ga.individual.Individual;

import java.util.Random;

public class SwapMutationStrategy implements MutationStrategy {

    @Override
    public Individual mutate(final Individual individual) {
        if (individual.size() < 2 && randomGenerator.nextDouble() >= probability) {
            return individual;
        }
        Object[] gens = individual.getGens().clone();
        final int indexA = randomGenerator.nextInt(gens.length);
        final int indexB = randomGenerator.nextInt(gens.length);
        final Object tmp = gens[indexA];
        gens[indexA] = gens[indexB];
        gens[indexB] = tmp;

        return new Individual(gens);
    }

    public SwapMutationStrategy(double probability) {
        this.probability = probability;
    }

    private final double probability;
    private final Random randomGenerator = new Random();
}
