package tech.kaomidev.geneticalgorithm.main.calculation

import tech.kaomidev.geneticalgorithm.main.sample.Individual
import tech.kaomidev.geneticalgorithm.main.sample.Population

/**
 * @object Algorithm
 * Responsible to evolve a population
 */
object Algorithm {
    // Override these properties
    // to get different results
    private const val uniformRate = 0.5
    private const val mutationRate = 0.015
    private const val tournamentSize = 5
    private const val elitism = true

    // Evolve a population
    @Suppress("ConstantConditionIf")
    fun evolvePopulation(population: Population): Population {
        val newPopulation = Population(population.size(), false)

        // Keep our best individual
        if (elitism) {
            newPopulation.saveIndividual(0, population.getFittest())
        }

        // Crossover population
        val elitismOffset = if (elitism) 1 else 0


        // Loop over the population size and create new individuals with
        // crossover
        for (i in elitismOffset until population.size()) {
            val individual1 = tournamentSelection(population)
            val individual2 = tournamentSelection(population)
            val newIndividual = crossover(individual1, individual2)

            newPopulation.saveIndividual(i, newIndividual)
        }

        // Mutate population
        for (i in elitismOffset until newPopulation.size()) {
            mutate(newPopulation.getIndividual(i))
        }

        return newPopulation
    }

    // Select individuals for crossover
    private fun tournamentSelection(population: Population): Individual {
        // Create a tournament population
        val tournament = Population(tournamentSize, false)

        // For each place in the tournament get a random individual
        for (i in 0 until tournamentSize) {
            val randomIndex = (Math.random() * population.size()).toInt()

            tournament.saveIndividual(i, population.getIndividual(randomIndex))
        }

        // Get the fittest
        return tournament.getFittest()
    }

    // Crossover individuals
    private fun crossover(left: Individual, right: Individual): Individual {
        val newSolution = Individual()

        // Loop through genes
        for (i in 0 until left.size()) {
            if (Math.random() <= uniformRate) {
                newSolution.setGene(i, left.getGene(i))
            } else {
                newSolution.setGene(i, right.getGene(i))
            }
        }

        return newSolution
    }

    // Mutate an individual
    private fun mutate(individual: Individual) {
        // Loop through genes
        for (i in 0 until individual.size()) {
            // Create random gene
            if (Math.random() <= mutationRate) {
                val gene = Math.round(Math.random()).toByte()

                individual.setGene(i, gene)
            }
        }
    }


}



