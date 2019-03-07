package learning

object ParTest extends App {

  val list = Range(1,100000000)
  val t1 = System.currentTimeMillis()
  list.map(_+42)
  val t2 = System.currentTimeMillis()

  println(s" time: ${t2-t1}")

  val t3= System.currentTimeMillis()
  list.par.map(_+43)
  val t4 = System.currentTimeMillis()
  println(s" time: ${t4-t3}")
}
