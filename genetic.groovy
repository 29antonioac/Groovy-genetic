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
  boolean[] mutated = chromosome.collect()
  int index = generator.nextInt(chromosome.length)
  mutated[index] = !mutated[index]
  return mutated
}

boolean[][] mutate_pool( pool)
{
  def new_pool = []
  pool.each
  {
    new_pool.add(mutate(it))
  }
  return new_pool
}

boolean[] chromosome = random_chromosome(4)
println chromosome
println mutate(chromosome)
println chromosome

def pool = [chromosome,mutate(chromosome)]
println ""
println pool

println mutate_pool(pool)
