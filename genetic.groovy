#!/usr/bin/env groovy

import groovy.transform.Field

@Field Random generator = new Random(System.currentTimeMillis())

def random_chromosome(int length)
{
  def chromosome = []
  length.times
  {
    chromosome << generator.nextBoolean()
  }
  return chromosome
}

int compute_fitness(boolean[] chromosome)
{
  return chromosome.count(true)
}

boolean[] mutate(boolean[] chromosome)
{
  boolean[] mutated = chromosome
  int index = generator.nextInt(chromosome.length)
  mutated[index] = !mutated[index]
  return mutated
}

boolean[] chromosome = random_chromosome(4)
println chromosome
println mutate(chromosome)
