package main.knapsack;


import ga.algorithm.GeneticAlgorithm;
import ga.individual.Individual;
import ga.process.crossover.UniformCrossoverStrategy;
import ga.process.initialization.RandomInitializationStrategy;
import ga.process.mutation.*;
import ga.process.selection.RouletteSelectionStrategy;
import ga.process.succession.NoSuccessionStrategy;

import java.util.Arrays;
import java.util.Random;

public class KnapsackProblem {

    public static void main(String [] args) {
        new KnapsackProblem(createItems(50, 1.0,250),5.0);
    }

    private static Item[] createItems(int size, double maxWeight, double maxAmount) {
        Random random = new Random();
        Item[] items = new Item[size];
        for (int i = 0; i < items.length; ++i) {
            items[i] = new Item(random.nextDouble() * maxWeight, random.nextDouble() * maxAmount);
        }
        return items;
    }

    public KnapsackProblem(Item[] items, double capacity) {
        double totalAmount = 0;
        double totalWeight = 0;

        for (Item item : items) {
            totalAmount += item.getAmount();
            totalWeight += item.getWeight();
        }

        Object[] possibleGens = new Byte[]{0,1};

        GeneticAlgorithm geneticAlgorithm = new GeneticAlgorithm(
                new RandomInitializationStrategy(100, items.length, possibleGens),
                new KnapsackEvaluationStrategy(items, capacity),
                new RouletteSelectionStrategy(),
                new UniformCrossoverStrategy(),
                new MultiMutationStrategy(
                        new SwapMutationStrategy(0.05),
                        new GenValueMutationStrategy(0.025, possibleGens)
                ),
                new NoSuccessionStrategy(),
                new KnapsackTerminationStrategy(1_000)
        );

        Individual result = geneticAlgorithm.start();
        System.out.println("======= Items");
        System.out.println(Arrays.toString(items));
        System.out.println(String.format("Total: { weight = %.3f, amount = %.3f }", totalWeight, totalAmount));
        System.out.println("======= Result");
        System.out.println("Best: Individual { score = " + result.getScore() + ", gens = " + result);
    }
}
