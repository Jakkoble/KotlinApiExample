package de.jakkoble

// A data class is used if the only reason of the class is to store data (In this Case the 'name', 'favorite' and 'path')
// For further Information about Data Classes visit: https://kotlinlang.org/docs/data-classes.html
data class Fruit(val name: String, val favorite: Boolean) {

   // Accessable with the class, not Instance of it (Fruit.path => "/fruits")
   companion object {
      const val path = "/fruits"
   }
}