package com.zouxxyy.chap23

/**
 * 第23章：重访for表达式
 */

object example01 {

  def main(args: Array[String]): Unit = {

    println("******找出列表中孩子和母亲的组合******")

    case class Person(name: String, isMale: Boolean, children: Person*) {
      override def toString: String = name
    }

    val lara = Person("Bob", isMale = true)
    val bob = Person("Bob", isMale = true)
    val julie = Person("Julie", false, lara, bob)
    val persons = List(lara, bob, julie)

    // 使用map、flatMap、withFilter
    val mapResult = persons.withFilter(!_.isMale)
        .flatMap(p => p.children.map(c => (p.name, c.name)))

    // 使用for
    val forResult = for (
      p <- persons if !p.isMale;
      c <- p.children) yield (p.name, c.name)

    if (mapResult == forResult)
      println(forResult)


    println("\n******用for推导式进行翻译******")

    case class Book(title: String, authors: String*)

    val books: List[Book] =
      List(
        Book(
          "Structure and Interpretation of Computer Programs",
          "Abelson, Harold", "Sussman, Gerald J."
        ),
        Book(
          "Principles of Compiler Design",
          "Aho, Alfred", "Ullman, Jeffrey"
        ),
        Book(
          "Programming in Modula-2",
          "Wirth, Niklaus"
        ),
        Book(
          "Elements of ML Programming",
          "Ullman, Jeffrey"
        ),
        Book(
          "The Java Language Specification", "Gosling, James",
          "Joy, Bill", "Steele, Guy", "Bracha, Gilad"
        )
      )

    // 找出所有至少编写了两本书以上作者的名字

    // 使用for
    val forAuthors = for (b1 <- books;                  // 连续的生成器，被翻译成 expr1.flatMap(x => (...))
         b2 <- books if b1 != b2;                       // books.withFilter(b2 => b1 != b2)
         a1 <- b1.authors;
         a2 <- b2.authors if a1 == a2)
      yield a1

    // 使用map、flatMap、withFilter
    val mapAuthors = books.flatMap(b1 =>
      books.withFilter(b2 => b1 != b2).flatMap(b2 =>
        b1.authors.flatMap(a1 =>
          b2.authors.withFilter(a2 => a1 == a2).map(a2 => a1))))

    if (forAuthors == mapAuthors) // List(Ullman, Jeffrey, Ullman, Jeffrey)
      println(mapAuthors.distinct)

    // 因为在编译时 for 其实就是使用 map、flatMap、withFilter，因此只要实现了它们就可以使用 for
    // 而且它们是可组合的～

  }
}

/*
******找出列表中孩子和母亲的组合******
List((Julie,Bob), (Julie,Bob))

******用for推导式进行翻译******
List(Ullman, Jeffrey)
 */