package tech.kaomidev.geneticalgorithm.main.calculation

import tech.kaomidev.geneticalgorithm.main.sample.Individual
import tech.kaomidev.geneticalgorithm.main.utils.parseByteArray

// Override this to change the solution
const val DEFAULT_SOLUTION = "0011000011010001010010100000000000111000100110110010100101100110"

/**
 * @object FitnessCalc
 * Calculates the fitness of individuals
 */
object FitnessCalc {

    // Set a candidate solution as a byte array
    private val solution: ByteArray = DEFAULT_SOLUTION.parseByteArray()

    // Calculate individuals fitness by comparing
    // it to our candidate solution
    fun getFitness(individual: Individual): Int {

        if (individual.size() != solution.size) {
            return 0
        }

        // Gets the count of genes from our solution
        // which are equal to the individual's genes
        return solution.filterIndexed { index, byte ->
            individual.getGene(index) == byte
        }.count()
    }

    // Get optimum fitness
    fun getMaxFitness(): Int {
        return solution.size
    }
}
