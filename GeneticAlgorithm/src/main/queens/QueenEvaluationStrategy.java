package main.queens;

import ga.individual.Individual;
import ga.process.evaluation.EvaluationStrategy;

public class QueenEvaluationStrategy implements EvaluationStrategy {

    @Override
    public double calculateScore(Individual individual) {
        double score = 0;

        int[] genPositions = new int[individual.size()];
        for (int i = 0; i < individual.getGens().length; ++i) {
            Byte[] gens = (Byte[])individual.getGens()[i];
            genPositions[i] = -1;
            for (int j = 0; j < gens.length; ++j) {
                if (gens[j] == 1) {
                    genPositions[i] = j;
                    break;
                }
            }
        }

        for (int i = 0; i < genPositions.length; ++i) {
            int collisionCount = 0;

            for (int j = i + 1; j < genPositions.length; ++j) {
                int x = Math.abs(genPositions[i] - genPositions[j]);
                int y = Math.abs(i - j);

                if (x == 0 || x == y) {
                    collisionCount++;
                }
            }
            if (collisionCount > 0) {
                score -= collisionCount * collisionCount;
                score += 1.0 - (double)((individual.size() - 1) * 2
                        + Math.min(genPositions[i], i)
                        + Math.min(genPositions[i], genPositions.length - 1 - i)
                        + Math.min(genPositions.length - 1 - genPositions[i], genPositions.length - 1 - i)
                        + Math.min(genPositions.length - 1 - genPositions[i], i)
                ) / (individual.size() * individual.size());
            }
        }

        return score;
    }
}