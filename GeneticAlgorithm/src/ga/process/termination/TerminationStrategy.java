package ga.process.termination;

import ga.population.Population;

public interface TerminationStrategy {

    boolean terminate(final Population population);
}
