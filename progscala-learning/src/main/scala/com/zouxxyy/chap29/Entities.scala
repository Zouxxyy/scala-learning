package com.zouxxyy.chap29

object Apple extends Food("Apple")
object Orange extends Food("Orange")
object Cream extends Food("Cream")
object Sugar extends Food("Sugar")
object FruitSalad extends Recipe("fruit salad", List(Apple, Orange, Cream, Sugar), "Stir it all together")

trait FoodCategories {
  case class FoodCategory(name: String, foods: List[Food])

  def allCategories: List[FoodCategory]
}

abstract class Database extends FoodCategories {
  def allFoods: List[Food]

  def allRecipes: List[Recipe]

  def fooNamed(name: String): Option[Food] = allFoods.find(_.name == name)
}

trait SimpleFoods {

  object Pear extends Food("Pear")

  def allFoods = List(Apple, Pear)

  def allCategories = Nil
}

trait SimpleRecipes {
  this: SimpleFoods =>

  object FruitSalad extends Recipe("fruit salad", List(Apple, Pear), "Stir it all together")

  def allRecipes: List[Recipe] = List(FruitSalad)
}

// 完全由混入组成的SimpleDatabase
object SimpleDatabase extends Database with SimpleFoods with SimpleRecipes

abstract class Browser {

  val database: Database

  def recipesUsing(food: Food) =
    SimpleDatabase.allRecipes.filter(require =>
    require.ingredients.contains(food))

  def displayCategory(category: SimpleDatabase.FoodCategory): Unit = {
    println(category)
  }
}
