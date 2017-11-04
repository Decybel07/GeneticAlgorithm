package ga.process.initialization;

import ga.individual.Individual;
import ga.population.Population;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomInitializationStrategy implements InitializationStrategy {

    public Population createPopulation() {
        Individual[] individuals = new Individual[populationSize];

        for (int i = 0; i < populationSize; ++i) {
            List<Object> gens = new ArrayList<>();
            for (int j = 0; j < individualSize; ++j) {
                gens.add(possibleGens[randomGenerator.nextInt(possibleGens.length)]);
            }
            individuals[i] = new Individual(gens.toArray());
        }
        return new Population(individuals);
    }

    public RandomInitializationStrategy(int populationSize, int individualSize, Object[] possibleGens) {
        this.populationSize = populationSize;
        this.individualSize = individualSize;
        this.possibleGens = possibleGens;
    }

    private final int populationSize;
    private final int individualSize;
    private final Object[] possibleGens;

    private final Random randomGenerator = new Random();
}
