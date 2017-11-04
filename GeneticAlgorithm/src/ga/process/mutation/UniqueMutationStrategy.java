package ga.process.mutation;

import ga.individual.Individual;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class UniqueMutationStrategy implements MutationStrategy {

    @Override
    public Individual mutate(final Individual individual) {
        if (randomGenerator.nextDouble() >= probability) {
            return individual;
        }
        Object[] gens = individual.getGens().clone();
        List<Object> unusedGens = new ArrayList<>(Arrays.asList(this.possibleGens));

        for (int i = 0; i < gens.length; ++i) {
            Object gen = gens[i];
            List<Integer> duplicate = new ArrayList<>();
            duplicate.add(i);
            for (int j = i + 1; j < gens.length; ++j) {
                if(gen == gens[j]) {
                    duplicate.add(j);
                }
            }

            if (duplicate.size() > 1) {
                for (Integer index : duplicate) {
                    gens[index] = null;
                }
                gens[randomGenerator.nextInt(duplicate.size())] = gen;
            }
        }

        unusedGens.removeAll(Arrays.asList(gens));
        for (int i = 0; i < gens.length; ++i) {
            if (gens[i] == null) {
                gens[i] = unusedGens.remove(randomGenerator.nextInt(unusedGens.size()));
            }
        }
        return new Individual(gens);
    }

    public UniqueMutationStrategy(double probability, final Object[] possibleGens) {
        this.possibleGens = possibleGens;
        this.probability = probability;
    }

    private final double probability;
    private final Object[] possibleGens;

    private final Random randomGenerator = new Random();
}
