package ga.process.initialization;

import ga.individual.Individual;
import ga.population.Population;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class UniqueRandomInitializationStrategy implements InitializationStrategy {

    @Override
    public Population createPopulation() {
        Individual[] individuals = new Individual[populationSize];

        for (int i = 0; i < populationSize; ++i) {
            List<Object> gens = new ArrayList<>();
            List<Object> possibleGens = new ArrayList<>(Arrays.asList(this.possibleGens));

            for (int j = 0; j < individualSize; ++j) {
                gens.add(possibleGens.remove(randomGenerator.nextInt(possibleGens.size())));
            }
            individuals[i] = new Individual(gens.toArray());
        }
        return new Population(individuals);
    }

    public UniqueRandomInitializationStrategy(int populationSize, int individualSize, Object[] possibleGens) {
        if(possibleGens.length != individualSize) {
            System.out.println("`possibleGens.length` must be greater than or equal to " + individualSize);
        }
        this.populationSize = populationSize;
        this.individualSize = individualSize;
        this.possibleGens = possibleGens;
    }

    private final int populationSize;
    private final int individualSize;
    private final Object[] possibleGens;

    private final Random randomGenerator = new Random();
}
