package ga.process.succession;

import ga.individual.Individual;

public class NoSuccessionStrategy implements SuccessionStrategy {

    @Override
    public Individual[] select(Individual[] individuals) {
        return individuals;
    }
}
