package tech.kaomidev.geneticalgorithm.main

import tech.kaomidev.geneticalgorithm.main.calculation.Algorithm
import tech.kaomidev.geneticalgorithm.main.calculation.FitnessCalc
import tech.kaomidev.geneticalgorithm.main.sample.Population

fun main() {
    // Creates an initial population
    var population = Population(50, true)

    var generationCount = 0

    // Evolves our population until we reach an optimum solution
    while (population.getFittest().getFitness() < FitnessCalc.getMaxFitness()) {
        generationCount++
        println("Generation: " + generationCount + " Fittest: " + population.getFittest().getFitness())
        population = Algorithm.evolvePopulation(population)
    }

    println("-".repeat(64))
    println("Solution found!")
    println("Generation: $generationCount")
    println("Genes: " + population.getFittest())
}

