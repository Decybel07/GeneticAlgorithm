package ga.process.termination;

import ga.population.Population;

public class BinaryTerminationStrategy implements TerminationStrategy {

    @Override
    public boolean terminate(Population population) {
        System.out.println("======= Generation " + generationCount);
        System.out.println("Best: " + population.getBestIndividual());

        return generationCount++ >= maxGenerationCount
                || population.getBestIndividual().getScore() == ((long)1 << population.getIndividuals()[0].size()) - 1;
    }

    public BinaryTerminationStrategy(final int maxGenerationCount) {
        this.maxGenerationCount = maxGenerationCount;
    }

    private int generationCount = 0;
    final private int maxGenerationCount;
}
