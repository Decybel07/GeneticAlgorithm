package main;

import ga.algorithm.GeneticAlgorithm;
import ga.individual.Individual;
import ga.process.crossover.UniformCrossoverStrategy;
import ga.process.evaluation.BinaryEvaluationStrategy;
import ga.process.initialization.RandomInitializationStrategy;
import ga.process.mutation.*;
import ga.process.selection.RouletteSelectionStrategy;
import ga.process.succession.NoSuccessionStrategy;
import ga.process.termination.BinaryTerminationStrategy;

public class Main {

    public static void main(String [] args) {

        Object[] possibleGens = new Byte[]{0,1};

        GeneticAlgorithm geneticAlgorithm = new GeneticAlgorithm(
                new RandomInitializationStrategy(10,63, possibleGens),
                new BinaryEvaluationStrategy(),
                new RouletteSelectionStrategy(),
                new UniformCrossoverStrategy(),
                new MultiMutationStrategy(
                        new SwapMutationStrategy(0.15),
                        new InversionMutationStrategy(0.10),
                        new ScrambleMutationStrategy(0.10),
                        new GenValueMutationStrategy(0.05, possibleGens)
                ),
                new NoSuccessionStrategy(),
                new BinaryTerminationStrategy(5000)
        );

        Individual result = geneticAlgorithm.start();
        System.out.println("======= Result");
        System.out.println(result);
    }

}
