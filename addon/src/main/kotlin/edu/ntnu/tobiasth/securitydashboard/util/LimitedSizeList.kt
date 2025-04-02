package edu.ntnu.tobiasth.securitydashboard.util

class LimitedSizeList<E>(private val maxSize: Int) : ArrayList<E>() {
    override fun add(element: E): Boolean {
        if (super.size == maxSize) {
            super.removeAt(0)
        }

        return super.add(element)
    }
}