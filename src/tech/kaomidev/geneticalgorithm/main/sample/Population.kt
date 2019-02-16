package tech.kaomidev.geneticalgorithm.main.sample

/**
 * @class Population
 * Holds a population of individuals
 */
class Population(populationSize: Int, initialize: Boolean) {

    private val individuals = if (initialize) {
        MutableList(populationSize) { Individual.createIndividual() }
    } else {
        MutableList(populationSize) { Individual() }
    }

    fun getIndividual(index: Int): Individual {
        return individuals[index]
    }

    fun saveIndividual(index: Int, individual: Individual) {
        individuals[index] = individual
    }

    fun size(): Int = individuals.size

    // Loop through individuals to find fittest
    fun getFittest(): Individual {
        var fittest = getIndividual(0)

        for (i in 1 until size()) {
            if (fittest.getFitness() <= getIndividual(i).getFitness()) {
                fittest = getIndividual(i)
            }
        }

        return fittest
    }
}