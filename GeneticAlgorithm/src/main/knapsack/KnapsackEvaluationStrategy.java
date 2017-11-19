package main.knapsack;

import ga.individual.Individual;
import ga.process.evaluation.EvaluationStrategy;

public class KnapsackEvaluationStrategy implements EvaluationStrategy {

    @Override
    public double calculateScore(Individual individual) {
        double totalAmount = 0;
        double totalWeight = 0;

        for (int i = 0; i < individual.size(); i++) {
            final Byte gen = (Byte)individual.getGens()[i];
            totalAmount += gen * items[i].getAmount();
            totalWeight += gen * items[i].getWeight();
        }

        if (totalWeight > capacity) {
            return capacity - totalWeight;
        }
        return totalAmount;
    }

    public KnapsackEvaluationStrategy(Item[] items, double capacity) {
        this.items = items;
        this.capacity = capacity;
    }

    private final Item[] items;
    private final double capacity;

}
