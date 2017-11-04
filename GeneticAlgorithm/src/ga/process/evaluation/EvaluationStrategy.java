package ga.process.evaluation;

import ga.individual.Individual;

public interface EvaluationStrategy {

    double calculateScore(final Individual individual);
}
