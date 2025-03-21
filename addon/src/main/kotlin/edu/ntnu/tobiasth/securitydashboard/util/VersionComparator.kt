package edu.ntnu.tobiasth.securitydashboard.util

import jakarta.enterprise.context.ApplicationScoped

@ApplicationScoped
class VersionComparator : Comparator<String> {
    fun isOlderThan(o1: String?, o2: String?): Boolean {
        return compare(o1, o2) == -1
    }

    override fun compare(o1: String?, o2: String?): Int {
        if (o1 == null || o2 == null) return 0

        for ((versionA, versionB) in (o1.split(".") zip o2.split("."))) {
            if (versionA.toInt() > versionB.toInt()) return 1
            if (versionA.toInt() < versionB.toInt()) return -1
        }

        return 0
    }
}