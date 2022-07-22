package de.jakkoble

// An object is used if there is just one object inside, in my example the Fruit List.
// It's accessable to all classes.
object Fruits {
    val list: MutableList<Fruit> = mutableListOf(
        Fruit("Apple", false),
        Fruit("Banana", true),
        Fruit("Lemon", false)
    )
}