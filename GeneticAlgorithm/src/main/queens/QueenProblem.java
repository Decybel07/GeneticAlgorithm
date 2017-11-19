package main.queens;

import ga.algorithm.GeneticAlgorithm;
import ga.individual.Individual;
import ga.process.crossover.UniformCrossoverStrategy;
import ga.process.initialization.RandomInitializationStrategy;
import ga.process.mutation.*;
import ga.process.selection.RouletteSelectionStrategy;
import ga.process.succession.NoSuccessionStrategy;

public class QueenProblem {

    public static void main(String [] args) {
        new QueenProblem(8);
    }

    public QueenProblem(int size) {
        Integer[] possibleGens = new Integer[size + 1];
        for (int i = -1; i < size; ++i) {
            possibleGens[i + 1] = i;
        }

        GeneticAlgorithm geneticAlgorithm = new GeneticAlgorithm(
                new RandomInitializationStrategy(10, size, possibleGens),
                new QueenEvaluationStrategy(),
                new RouletteSelectionStrategy(),
                new UniformCrossoverStrategy(),
                new MultiMutationStrategy(
                        new SwapMutationStrategy(0.10),
                        new ScrambleMutationStrategy(0.05),
                        new InversionMutationStrategy(0.025),
                        new UniqueMutationStrategy(1.0, possibleGens)
                ),
                new NoSuccessionStrategy(),
                new QueenTerminationStrategy(1_000_000)
        );

        Individual result = geneticAlgorithm.start();
        System.out.println("======= Result");
        System.out.println("Best: Individual { score = " + result.getScore() + ", gens = " + chessboard(result));
    }

    private String chessboard(Individual individual) {
        StringBuilder stringBuilder = new StringBuilder("{\n");
        stringBuilder.append(chessboardLine(individual.size() - 1, '┌', '┐', '┬'));

        for (int i = 0; i < individual.size(); ++i) {
            if (i > 0) {
                stringBuilder.append(chessboardLine(individual.size() - 1, '├', '┤', '┼'));
            }
            stringBuilder.append("\t│");
            Integer gen = (Integer)individual.getGens()[i];
            for (int j = 0; j < individual.size(); ++j) {
                stringBuilder.append(gen.equals(j) ? " ♛ " : " - ");
                stringBuilder.append("│");
            }
            stringBuilder.append("\n");
        }
        stringBuilder.append(chessboardLine(individual.size() -1, '└', '┘', '┴'));
        stringBuilder.append("}");
        return stringBuilder.toString();
    }

    private String chessboardLine(int size, char begin, char end, char separator) {
        return String.format("\t" + begin + "───%0" + size + "d" + end + "\n", 0).replace("0",separator + "───");
    }
}
