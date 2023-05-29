import Diagnosis._
import Conflicts.tpf
import gapt.expr.formula.Formula
import gapt.expr.stringInterpolationForExpressions
import gapt.expr.formula.fol.FOLTerm


object Main extends App {
  // Try adding different elements of the set of components to this list for the problems in Diagnosis
  val test = List(
    fot"a1",
    fot"a2",
    fot"o1"
  )


  val all = test.toSet.subsets.map(_.toList).toList

  for (component <- all) {
    val result = tpf(problem3, component)

    result match {
      case Some(res) =>
        println(s"Conflict set for component $component:")
        res.foreach(println)
        println()

      case None =>
        println(s"No conflict set found for component $component")
        println()
    }
  }

  sealed abstract class Tree

  case object Leaf extends Tree

  case class Node(elem: Option[Set[FOLTerm]],
                  children: List[Tree])
    extends Tree

  def makeHittingTree(problem: () => (List[Formula], List[FOLTerm], List[Formula]), HS: List[FOLTerm] = List()): Tree = {
    val result = tpf(problem, HS)
    result match {
      case Some(conflictSet) =>
        val children: List[Tree] = conflictSet.toList.flatMap { element =>
          val newHS = HS :+ element
          Some(makeHittingTree(problem, newHS))
        }
        Node(result, children)

      case None => Leaf
    }
  }

  print(makeHittingTree(problem3, Nil))
}
  //val hs = List(fot"a1")
  // Try different problems and observe what the outcomes are, also try putting different combinations of components in hs
  //val Some(result) = tpf(problem1, hs)
  //val None = tpf(problem1, hs)
  //println("Conflict sets: ")
  //result.foreach(println)

  /*sealed abstract class Tree

  case object Leaf extends Tree

  case class Node(elem: Int,
                  children : List[Tree])
              extends Tree


  val b = 3
  val d = 0
  val dMax = 5

  def makeTree(b : Int, d : Int, dMax : Int) :  Tree = {
    if (d <= dMax){
      val children: List[Tree] = List.fill(b)(makeTree(b, d+1, dMax))
      Node(d, children)
    }else{
      Leaf
    }
  }

  val sum = 0
  def countNodes(tree : Tree) : Int = (tree) match {
    case (Leaf) => 0
    case (Node(_, list)) => 1 + list.map(countNodes).sum
  }

  print(makeTree(b, d, dMax))
  //print(countNodes(makeTree(b,d,dMax)))*/

