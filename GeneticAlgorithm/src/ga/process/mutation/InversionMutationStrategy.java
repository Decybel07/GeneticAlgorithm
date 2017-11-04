package ga.process.mutation;

import ga.individual.Individual;

import java.util.Arrays;
import java.util.Random;

public class InversionMutationStrategy implements MutationStrategy {

    @Override
    public Individual mutate(final Individual individual) {
        if (individual.size() < 2 && randomGenerator.nextDouble() >= probability) {
            return individual;
        }
        Object[] gens = individual.getGens().clone();
        final int start = randomGenerator.nextInt(gens.length - 2);
        final int end = start + randomGenerator.nextInt(gens.length - start - 2) + 2;

        Object[] newGens = Arrays.copyOfRange(gens, start, end);
        for (int i = 0; i < newGens.length; ++i) {
            gens[start+i] = newGens[newGens.length - i - 1];
        }
        return new Individual(gens);
    }

    public InversionMutationStrategy(double probability) {
        this.probability = probability;
    }

    private final double probability;
    private final Random randomGenerator = new Random();
}