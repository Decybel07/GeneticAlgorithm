package ga.process.selection;

import ga.individual.Individual;
import ga.population.Population;

public class RankSelectionStrategy implements SelectionStrategy  {

    @Override
    public Individual[] select(final Population population) {
        return population.getIndividuals();
    }
}
