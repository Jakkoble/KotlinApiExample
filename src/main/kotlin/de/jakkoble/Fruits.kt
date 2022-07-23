package de.jakkoble

// An Object is used instead of a class to hold just one object, in my example the Fruit List.
// It's accessable to all classes.
object Fruits {
    val list: MutableList<Fruit> = mutableListOf(
        Fruit("Apple", false),
        Fruit("Banana", true),
        Fruit("Lemon", false)
    )
}