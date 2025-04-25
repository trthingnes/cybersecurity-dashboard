package edu.ntnu.tobiasth.securitydashboard.check

enum class Risk {
    DISABLED,
    UNKNOWN,
    NONE,
    LOW,
    MODERATE,
    HIGH;

    fun isHigh() = this == HIGH
    fun isSignificant() = listOf(HIGH, MODERATE, LOW).contains(this)
}