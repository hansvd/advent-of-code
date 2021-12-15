package nl.avwie.aoc.common.search

import java.util.*

interface Context<T> {
    fun found(item: T): Boolean
    fun children(item: T): Iterable<T>
}
interface WithCost<T, C>  {
    fun cost(item: T): C
    fun compare(a: C, b: C): Int
    fun sum(c1: C, c2: C): C
}

abstract class GraphSearch<T>(private val context: Context<T>) {

    abstract fun add(item: T)
    abstract fun remove(): T?
    abstract fun isEmpty(): Boolean
    abstract fun clear()

    open fun search(init: T): Sequence<T> = sequence {
        clear()
        add(init)

        while (!isEmpty()) {
            val next = remove()!!
            val (solutions, children) = context.children(next).partition { context.found(it) }
            solutions.forEach {
                yield(it)
            }
            children.forEach {
                add(it)
            }
        }
    }
}

class DijkstraSearch<T, C>(context: C) : GraphSearch<T>(context) where C : Context<T>, C : WithCost<T, Int> {

    private val comparator = Comparator { a: T, b: T ->
        (context.cost(a) to context.cost(b)).let { (ca, cb) -> ca.compareTo(cb) }
    }

    private val queue = PriorityQueue(comparator)
    private val visited = mutableSetOf<T>()

    override fun add(item: T) {
        if (visited.contains(item)) return
        queue.add(item)
        visited.add(item)
    }
    override fun remove(): T? = queue.remove()
    override fun isEmpty() = queue.isEmpty()
    override fun clear() {
        visited.clear()
        queue.clear()
    }

    companion object {
        operator fun <T> invoke(found: (T) -> Boolean, children: (T) -> Iterable<T>, cost: (T) -> Int): GraphSearch<T> {
            val context = object : Context<T>, WithCost<T, Int> {
                override fun found(item: T): Boolean = found(item)
                override fun children(item: T): Iterable<T> = children(item)
                override fun cost(item: T): Int = cost(item)
                override fun sum(c1: Int, c2: Int): Int = c1 + c2
                override fun compare(a: Int, b: Int): Int = a.compareTo(b)
            }
            return DijkstraSearch(context)
        }
    }
}

fun <T> dijkstraSearch(init: T, found: (T) -> Boolean, children: (T) -> Iterable<T>, cost: (T) -> Int): Sequence<T> {
    return DijkstraSearch(found, children, cost).search(init)
}