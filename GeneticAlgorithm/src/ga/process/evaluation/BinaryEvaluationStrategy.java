package ga.process.evaluation;

import ga.individual.Individual;

public class BinaryEvaluationStrategy implements EvaluationStrategy {

    @Override
    public double calculateScore(final Individual individual) {
        double score = 0;

        for (int i = 0; i < individual.size(); ++i) {
            score += (Byte) individual.getGens()[i] * ((long)1 << (individual.size() - i - 1));
        }
        return score;
    }
}