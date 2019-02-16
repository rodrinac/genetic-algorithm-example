package tech.kaomidev.geneticalgorithm.main.sample

import tech.kaomidev.geneticalgorithm.main.calculation.FitnessCalc

/**
 * @class Individual
 * Represents a individual in the population
 */
class Individual(private val genes: ByteArray = ByteArray(64)) {
    // Cache
    private var fitness = 0

    fun size() = genes.size

    fun getGene(index: Int): Byte = genes[index]

    fun setGene(index: Int, value: Byte) {
        genes[index] = value
        fitness = 0
    }

    fun getFitness(): Int {
        if (fitness == 0) {
            fitness = FitnessCalc.getFitness(this)
        }

        return fitness
    }

    override fun toString(): String {
        return genes.map { it.toString() }
            .reduce { acc, gene -> acc + gene }
    }

    companion object {
        // Creates a random individual
        fun createIndividual(): Individual {
            val genes = List(64) { Math.round(Math.random()).toByte() }

            return Individual(genes.toByteArray())
        }
    }
}