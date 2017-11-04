package ga.process.selection;

import ga.individual.Individual;
import ga.population.Population;

import java.util.Random;

public class RouletteSelectionStrategy implements SelectionStrategy {

    @Override
    public Individual[] select(final Population population) {
        if (population.size() == 0) {
            return population.getIndividuals();
        }

        final double[] occurrenceFrequencies = calculateOccurrenceFrequencies(population);
        Individual[] individuals = new Individual[population.size()];

        for (int i = 0; i < population.size(); ++i) {
            final double value = randomGenerator.nextDouble();

            for (int j = 0; j < occurrenceFrequencies.length; ++j) {
                if (value <= occurrenceFrequencies[j]) {
                    individuals[i] = population.getIndividuals()[j];
                    break;
                }
            }

            if (individuals[i] == null) {
                individuals[i] = population.getIndividuals()[population.size()-1];
            }
        }
        return individuals;
    }

    private double[] calculateOccurrenceFrequencies(final Population population) {
        double[] occurrenceFrequencies = new double[population.size()];
        double[] scores = new double[population.size()];
        scores[0] = population.getIndividuals()[0].getScore();
        double minScore = scores[0];
        double maxScore = scores[0];

        for (int i = 1; i < occurrenceFrequencies.length; ++i) {
            scores[i] = population.getIndividuals()[i].getScore();

            if (scores[i] < minScore) {
                minScore = scores[i];
            } else if (scores[i] > maxScore) {
                maxScore = scores[i];
            }
        }
        maxScore -= minScore;

        if (maxScore > 0) {
            double totalScore = 0;
            double lastOccurrenceFrequency = 0;

            for (int i = 0; i < occurrenceFrequencies.length; ++i) {
                scores[i] = (scores[i] - minScore) / maxScore;
                totalScore += scores[i];
            }

            for (int i = 0; i < occurrenceFrequencies.length; ++i) {
                lastOccurrenceFrequency += scores[i] / totalScore;
                occurrenceFrequencies[i] = lastOccurrenceFrequency;
            }
        } else {
            double occurrenceFrequency = 1.0 / occurrenceFrequencies.length;
            for (int i = 0; i < occurrenceFrequencies.length; ++i) {
                occurrenceFrequencies[i] = occurrenceFrequency * (i+1);
            }
        }
        return occurrenceFrequencies;
    }

    private final Random randomGenerator = new Random();
}