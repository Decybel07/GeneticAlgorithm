package ga.process.succession;

import ga.individual.Individual;

public interface SuccessionStrategy {

    Individual[] select(final Individual[] individuals);
}
