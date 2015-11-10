#!/usr/bin/env

import groovy.time.*
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

def time_mutations(int number, boolean[] individual)
{
  def timeStart = new Date()

  number.times
  {
    mutate(individual)
  }

  def timeStop = new Date()
  TimeDuration duration = TimeCategory.minus(timeStop, timeStart)
  return duration
}

def main()
{
  def length = 16
  def iterations = 100000
  def top_length = 32768

  while (length <= top_length)
  {
    boolean[] individual = random_chromosome(length)
    def elapsed = time_mutations(iterations, individual).toString()
    println "Groovy-BitString " + length.toString() + ", " + elapsed
    length *= 2
  }
}

def tests()
{
  def pool = []
  def chromosome_length = 16
  def pool_size = 32

  pool_size.times
  { i ->
    println i
    boolean[] actual_chromosome = random_chromosome(chromosome_length)
    pool << actual_chromosome
    // assert pool[i].length > 0
    assert actual_chromosome.length == chromosome_length
    def mutated = mutate(actual_chromosome)
    assert actual_chromosome.length == mutated.length
    assert actual_chromosome != mutated
  }
}
tests()
main()
