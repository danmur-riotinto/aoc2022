//val inp =
//  """
//    |vJrwpWtwJgWrhcsFMMfFFhFp
//    |jqHRNqRjqzjGDLGLrsFMfFZSrLrFZsSL
//    |PmmdzqPrVvPwwTWBwg
//    |wMqvLMZHhHMvwLHjbvcjnnSBnvTQFn
//    |ttgJtRGJQctTZtZT
//    |CrZsJsPPZsGzwwsLwLmpwMDw
//    |""".stripMargin

// round 2 sample
//val inp =
//  """
//    |vJrwpWtwJgWrhcsFMMfFFhFp
//    |jqHRNqRjqzjGDLGLrsFMfFZSrLrFZsSL
//    |PmmdzqPrVvPwwTWBwg
//    |wMqvLMZHhHMvwLHjbvcjnnSBnvTQFn
//    |ttgJtRGJQctTZtZT
//    |CrZsJsPPZsGzwwsLwLmpwMDw
//    |""".stripMargin

val file = scala.io.Source.fromFile("Projects/aoc2022/day3.txt")
val inp = try file.mkString finally file.close()

case class Sack(c1: String, c2: String):
  def inCommon =
    val xsect = c1.toSet & c2.toSet
    assert(xsect.size == 1)
    xsect.toSeq // This should probably just return 1 but leaving it in case the challenge changes

  def cAll = c1 + c2

object Sack:
  def fromString(contents: String) =
    assert(contents.length % 2 == 0)
    val comp = contents.splitAt(contents.length / 2)
    Sack(comp(0), comp(1))


val sacks = inp.strip.split('\n').map(Sack.fromString)

def pri(char: Char) =
  char.isLower match
    case false => char.intValue - 38
    case true => char.intValue - 96

assert(pri('a') == 1)
assert(pri('z') == 26)
assert(pri('A') == 27)
assert(pri('Z') == 52)

// part 1
//sacks.map(s => s.inCommon.map(ch => pri(ch))).flatten.sum

// part 2
sacks.map(_.cAll.toSet).grouped(3).map(_.reduce((s1, s2) => s1 & s2)).flatten.map(pri(_)).sum