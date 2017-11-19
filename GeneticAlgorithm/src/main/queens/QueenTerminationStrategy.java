package main.queens;

import ga.individual.Individual;
import ga.population.Population;
import ga.process.termination.TerminationStrategy;

public class QueenTerminationStrategy implements TerminationStrategy {

    @Override
    public boolean terminate(Population population) {
        Individual bestIndividual = population.getBestIndividual();

        System.out.println("======= Generation " + generationCount);
        System.out.println(String.format("Best: Individual { score = %.1f }", bestIndividual.getScore()));

        return generationCount++ >= maxGenerationCount || bestIndividual.getScore() == 0;
    }

    public QueenTerminationStrategy(final int maxGenerationCount) {
        this.maxGenerationCount = maxGenerationCount;
    }

    private int generationCount = 0;
    final private int maxGenerationCount;
}
