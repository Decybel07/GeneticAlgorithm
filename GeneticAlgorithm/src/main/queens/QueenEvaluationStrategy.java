package main.queens;

import ga.individual.Individual;
import ga.process.evaluation.EvaluationStrategy;

import java.util.Arrays;

public class QueenEvaluationStrategy implements EvaluationStrategy {

    @Override
    public double calculateScore(Individual individual) {
        Integer[] gens = Arrays.copyOf(individual.getGens(), individual.size(), Integer[].class);
        int emptyCount = 0;
        int collisionCount = 0;

        for (int i = 0; i < gens.length; ++i) {
            if(gens[i].equals(-1)) {
                ++emptyCount;
                continue;
            }

            for (int j = 0; j < gens.length; ++j) {
                if (j == i || gens[j].equals(-1)) {
                    continue;
                }

                int x = Math.abs(gens[i] - gens[j]);
                int y = Math.abs(i - j);

                if (x == 0 || x == y) {
                    ++collisionCount;
                }
            }
        }

        return -collisionCount - ((double)emptyCount) / individual.size();
    }
}