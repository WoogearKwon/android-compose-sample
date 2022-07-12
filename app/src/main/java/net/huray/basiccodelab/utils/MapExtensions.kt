package net.huray.basiccodelab.utils

internal fun <E> MutableSet<E>.addOrRemove(element: E) {
    if (add(element).not()) {
        remove(element)
    }
}