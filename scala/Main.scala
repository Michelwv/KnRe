import Diagnosis._
import Conflicts.tpf
import gapt.expr.stringInterpolationForExpressions


object Main extends App {
  println("Running diagnostics on problem 1 with an empty list of broken components..")
  // Try adding different elements of the set of components to this list for the problems in Diagnosis
  val test = List(
    fot"a1",
    fot"a2"
  )

  val all = test.toSet.subsets.map(_.toList).toList

  for (component <- all){
    val result = tpf(problem1, component)

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
  //val hs = List(fot"a1")
  // Try different problems and observe what the outcomes are, also try putting different combinations of components in hs
  //val Some(result) = tpf(problem1, hs)
  //val None = tpf(problem1, hs)
  //println("Conflict sets: ")
  //result.foreach(println)
}
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

