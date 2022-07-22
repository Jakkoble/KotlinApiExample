package de.jakkoble

// A data class is used if the only reason of the class is to store data
data class Fruit(val name: String, val favorite: Boolean) {

    // Accessable with the class, not Instance of it
    companion object {
        const val path = "/fruits"
    }
}