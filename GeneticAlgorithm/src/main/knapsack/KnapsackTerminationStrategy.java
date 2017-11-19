package main.knapsack;

import ga.individual.Individual;
import ga.population.Population;
import ga.process.termination.TerminationStrategy;

public class KnapsackTerminationStrategy implements TerminationStrategy {

    @Override
    public boolean terminate(Population population) {
        final Individual bestIndividual = population.getBestIndividual();
        System.out.println("======= Generation " + generationCount);
        System.out.println("Best: " + bestIndividual);

        if(generationCount++ >= maxGenerationCount || (minAmount > 0.0 && bestIndividual.getScore() >= minAmount)) {
            return true;
        }
        for (Object gen : bestIndividual.getGens()) {
            if((Byte)gen == 0) {
                return false;
            }
        }
        return true;
    }

    public KnapsackTerminationStrategy(final int maxGenerationCount) {
        this(maxGenerationCount, -1.0);
    }

    public KnapsackTerminationStrategy(final int maxGenerationCount, final double minAmount) {
        this.maxGenerationCount = maxGenerationCount;
        this.minAmount = minAmount;
    }

    private int generationCount = 0;
    private final int maxGenerationCount;
    private final double minAmount;
}