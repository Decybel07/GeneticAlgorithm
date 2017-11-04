package main.queens;

import ga.algorithm.GeneticAlgorithm;
import ga.individual.Individual;
import ga.process.crossover.UniformCrossoverStrategy;
import ga.process.initialization.UniqueRandomInitializationStrategy;
import ga.process.mutation.*;
import ga.process.selection.RouletteSelectionStrategy;
import ga.process.succession.NoSuccessionStrategy;

import java.util.Arrays;

public class QueenProblem {

    public static void main(String [] args) {
        new QueenProblem(10);
    }

    public QueenProblem(int size) {
        if(size < 4) {
            System.out.println("Size must be greater than or equal to 4");
            return;
        }

        Byte[][] possibleGens = new Byte[size][size];
        Byte fillValue = 0;

        for (int i = 0; i < size; ++i) {

            Arrays.fill(possibleGens[i], fillValue);
            possibleGens[i][i] = 1;
        }

        GeneticAlgorithm geneticAlgorithm = new GeneticAlgorithm(
                new UniqueRandomInitializationStrategy(10, size, possibleGens),
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

        for (int i = 0; i < individual.size(); i++) {
            if (i > 0) {
                stringBuilder.append(chessboardLine(individual.size() - 1, '├', '┤', '┼'));
            }
            stringBuilder.append("\t│");
            Byte[] gens = (Byte[])individual.getGens()[i];
            for (Byte gen : gens) {
                stringBuilder.append(gen == 1 ? " ♛ " : " - ");
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
