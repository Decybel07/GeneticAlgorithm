package ga.process.crossover;

import ga.individual.Individual;

public interface CrossoverStrategy {

    Individual[] cross(final Individual a, final Individual b);
}
