package ga.process.selection;

import ga.individual.Individual;
import ga.population.Population;

import java.util.Random;

public class RandomSelectionStrategy implements SelectionStrategy {

    @Override
    public Individual[] select(final Population population) {
        Individual[] individuals = new Individual[population.size()];

        for (int i = 0; i < population.size(); ++i) {
            individuals[i] = population.getIndividuals()[randomGenerator.nextInt(population.size())];
        }
        return individuals;
    }

    private final Random randomGenerator = new Random();
}
