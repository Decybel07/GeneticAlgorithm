package ga.algorithm;

import ga.individual.Individual;
import ga.population.Population;
import ga.process.crossover.CrossoverStrategy;
import ga.process.evaluation.EvaluationStrategy;
import ga.process.initialization.InitializationStrategy;
import ga.process.mutation.MutationStrategy;
import ga.process.selection.SelectionStrategy;
import ga.process.succession.SuccessionStrategy;
import ga.process.termination.TerminationStrategy;


public class GeneticAlgorithm {

    public GeneticAlgorithm(InitializationStrategy initializer, EvaluationStrategy evaluator, SelectionStrategy selector, CrossoverStrategy crossover, MutationStrategy mutator, SuccessionStrategy successor, TerminationStrategy terminator) {
        this(initializer.createPopulation(), evaluator, selector, crossover, mutator, successor, terminator);
    }

    public GeneticAlgorithm(Population population, EvaluationStrategy evaluator, SelectionStrategy selector, CrossoverStrategy crossover, MutationStrategy mutator, SuccessionStrategy successor, TerminationStrategy terminator) {
        this.population = population;
        this.evaluator = evaluator;
        this.selector = selector;
        this.crossover = crossover;
        this.mutator = mutator;
        this.successor = successor;
        this.terminator = terminator;
    }

    public final Individual start() {
        Individual[] individuals = population.getIndividuals();
        scoreIndividuals(individuals);

        Individual bestIndividual = population.getBestIndividual();
        Individual tmpIndividual;

        while (!terminator.terminate(population)) {
            individuals = selector.select(population);
            crossIndividuals(individuals);
            mutateIndividuals(individuals);
            scoreIndividuals(individuals);
            successionIndividuals(individuals);
            population.setIndividuals(individuals);

            tmpIndividual = population.getBestIndividual();
            if (tmpIndividual.getScore() > bestIndividual.getScore()) {
                bestIndividual = tmpIndividual;
            }
        }
        return bestIndividual;
    }

    private void scoreIndividuals(Individual[] individuals) {
        for (Individual individual : individuals) {
            individual.setScore(evaluator.calculateScore(individual));
        }
    }

    private void crossIndividuals(Individual[] individuals) {
        Individual[] crossedIndividuals;
        for (int i = 0; i < individuals.length - 1; i+= 2) {
            crossedIndividuals = crossover.cross(individuals[i],individuals[i+1]);
            individuals[i] = crossedIndividuals[0];
            individuals[i+1] = crossedIndividuals[1];
        }
    }

    private void mutateIndividuals(Individual[] individuals) {
        for (int i = 0; i < individuals.length; ++i) {
            individuals[i] = mutator.mutate(individuals[i]);
        }
    }

    private void successionIndividuals(Individual[] individuals) {
        successor.select(individuals);
    }

    private Population population;

    private EvaluationStrategy evaluator;
    private SelectionStrategy selector;
    private CrossoverStrategy crossover;
    private MutationStrategy mutator;
    private SuccessionStrategy successor;
    private TerminationStrategy terminator;
}
