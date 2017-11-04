package ga.process.mutation;

import ga.individual.Individual;

public interface MutationStrategy {

    Individual mutate(final Individual individual);
}
