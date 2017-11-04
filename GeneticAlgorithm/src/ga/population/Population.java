package ga.population;

import ga.individual.Individual;

import java.util.Arrays;

public class Population {

    public final Individual[] getIndividuals() {
        return individuals;
    }

    public void setIndividuals(final Individual[] individuals) {
        this.individuals = individuals;
    }

    public final Individual getBestIndividual() {
        Individual bestIndividual = individuals[0];
        for (Individual individual : individuals) {
            if (individual.getScore() > bestIndividual.getScore()) {
                bestIndividual.getScore();
            }
        }
        return bestIndividual;
    }

    public final int size() {
        return individuals.length;
    }

    public Population(final Individual[] individuals) {
        this.individuals = individuals;
    }

    @Override
    public String toString() {
        return "Population { " +
                "individuals = " + Arrays.toString(individuals) +
                "}";
    }

    private Individual[] individuals;
}
