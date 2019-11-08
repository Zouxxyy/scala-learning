package com.zouxxyy.chap29

object GotApples {

  def main(args: Array[String]): Unit = {

    val db: Database = SimpleDatabase

    object browser extends Browser {
      val database = db
    }

    val apple = SimpleDatabase.fooNamed("Apple").get

    for (recipe <- browser.recipesUsing(apple))
      println(recipe.name)
  }
}

/*
fruit salad
 */
