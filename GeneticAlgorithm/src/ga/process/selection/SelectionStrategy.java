package ga.process.selection;

import ga.individual.Individual;
import ga.population.Population;

public interface SelectionStrategy {

    Individual[] select(final Population population);
}
