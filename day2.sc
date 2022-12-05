
val file = scala.io.Source.fromFile("Projects/aoc2022/day2.txt")
val guide = try file.mkString finally file.close()

enum Outcome:
  case Win, Lose, Draw

enum HandType:
  case Rock, Paper, Scissors

// enumeration is a stupid choice for this
enum Play(val oppChar: Char, val myChar: Char, val score: Int, val win: HandType, val lose: HandType) {
  case Rock extends Play('A', 'X', 1, HandType.Paper, HandType.Scissors)
  case Paper extends Play('B', 'Y', 2, HandType.Scissors, HandType.Rock)
  case Scissors extends Play('C', 'Z', 3, HandType.Rock, HandType.Paper)
}

def part2Move(oppPlay: Play, myPlay: Play) =
  myPlay.myChar match
    // X I lose, Y draw, Z I win
    case 'X' => Play.fromOrdinal(oppPlay.lose.ordinal)
    case 'Y' => oppPlay
    case 'Z' => Play.fromOrdinal(oppPlay.win.ordinal)

def playFromChar(c: Char) = c match {
  case Play.Rock.oppChar | Play.Rock.myChar => Play.Rock
  case Play.Paper.oppChar | Play.Paper.myChar => Play.Paper
  case Play.Scissors.oppChar | Play.Scissors.myChar => Play.Scissors
}

def outcome(oppPlay: Play, myPlay: Play) =
  (oppPlay, myPlay) match
    case (Play.Paper, Play.Rock) => Outcome.Lose
    case (Play.Rock, Play.Scissors) => Outcome.Lose
    case (Play.Scissors, Play.Paper) => Outcome.Lose
    case (a, b) if a == b => Outcome.Draw
    case _ => Outcome.Win

def score(play: Play, outcome: Outcome) =
  outcome match
    case Outcome.Win => 6 + play.score
    case Outcome.Draw => 3 + play.score
    case Outcome.Lose => play.score

val myScores =
  for round <- guide.split('\n')
      oppPlay <- Some(playFromChar(round(0)))
      myPlayr1 <- Some(playFromChar(round(2)))
      myPlay <- Some(part2Move(oppPlay, myPlayr1))
      outcome <- Some(outcome(oppPlay, myPlay))
    yield score(myPlay, outcome)

println(myScores.sum)
