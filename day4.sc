val sampleInput =
  """
    |2-4,6-8
    |2-3,4-5
    |5-7,7-9
    |2-8,3-7
    |6-6,4-6
    |2-6,4-8
    |""".stripMargin.strip

val file = scala.io.Source.fromFile("Projects/aoc2022/day4.txt")
val realInput = try file.mkString finally file.close()

val input = realInput

// pt1, subset
//input.split('\n').map(_.split(Array('-', ',')))
//  .flatten.map(_.toInt).grouped(4)
//  .map(g => ((g(0) to g(1)).toSet, (g(2) to g(3)).toSet))
//  .map((first, second) => (first subsetOf second) || (second subsetOf first))
//  .count(isSubset => isSubset)

// pt2, any overlap
input.split('\n').flatMap(_.split(Array('-', ',')))
  .map(_.toInt).grouped(4)
  .map(g => ((g(0) to g(1)).toSet, (g(2) to g(3)).toSet))
  .map((first, second) => (first & second).nonEmpty)
  .count(isSubset => isSubset)
