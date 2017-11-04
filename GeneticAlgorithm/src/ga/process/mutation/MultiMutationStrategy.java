package ga.process.mutation;

import ga.individual.Individual;

public class MultiMutationStrategy implements MutationStrategy {

    @Override
    public Individual mutate(final Individual individual) {
        Individual mutatedIndividual = individual;
        for (MutationStrategy mutation : mutationStrategies) {
            mutatedIndividual = mutation.mutate(mutatedIndividual);
        }
        return mutatedIndividual;
    }

    public MultiMutationStrategy(final MutationStrategy... mutationStrategies) {
        this.mutationStrategies = mutationStrategies;
    }

    private final MutationStrategy[] mutationStrategies;
}
