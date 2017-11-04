package ga.process.mutation;

import ga.individual.Individual;

import java.util.Random;

public class GenValueMutationStrategy implements MutationStrategy {

    @Override
    public Individual mutate(final Individual individual) {
        if (randomGenerator.nextDouble() >= probability) {
            return individual;
        }
        Object[] gens = individual.getGens().clone();
        gens[randomGenerator.nextInt(gens.length)] = possibleGens[randomGenerator.nextInt(possibleGens.length)];
        return new Individual(gens);
    }

    public GenValueMutationStrategy(double probability, final Object[] possibleGens) {
        this.possibleGens = possibleGens;
        this.probability = probability;
    }

    private final double probability;
    private final Object[] possibleGens;

    private final Random randomGenerator = new Random();
}