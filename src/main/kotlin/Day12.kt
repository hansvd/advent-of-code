import Day12.Companion.part1
import Day12.Companion.part2
import java.io.File
import java.util.*

fun main() {
    File("input/input12.txt").useLines {
        println(part1(it))
        println(part2(it))
    }
}

class Cave(val name:String) {
    val isBig = name == name.uppercase(Locale.getDefault())
    val connections = mutableSetOf<String>()

    fun addConnection(name:String) {
        connections.add(name)
    }

    override fun toString():String = name
}

typealias Route = List<Cave>
fun Route.isValid(part2:Boolean):Boolean {
    for (c in this) {
        if (!c.isBig && (this.count { it.name == c.name } > 1)) return false
    }
    return true
}

class Day12(val part2:Boolean = false) {


    private val caves = hashMapOf<String,Cave>()
    init {
        getOrCreate("start")
        getOrCreate("end")
    }

    fun getOrCreate(name:String):Cave = caves[name] ?: let {
        val n = Cave(name)
        caves[name] = n
        n
    }

    fun parseInput(line:String) {
        val names = line.split('-')
        if (names.size != 2) return
        val c1 = getOrCreate(names[0])
        val c2 = getOrCreate(names[1])
        c1.addConnection(c2.name)
        c2.addConnection(c1.name)
    }


    fun findRoutes(s: String, e: String): List<Route> {
        val start = caves[s] ?: return listOf()
        val end = caves[e] ?: return listOf()
        return findRoutes(listOf(),start, end)
    }
    private fun findRoutes(routeBase:Route, next:Cave, end: Cave): List<Route> {

        if (next.name == end.name) return listOf(routeBase+end)
        val route = routeBase+next
        if (!(route).isValid(part2)) return listOf()
        return next.connections.mapNotNull { caves[it] }
            .fold(listOf()) { result, connection -> result + findRoutes(route, connection, end) }
    }

    companion object {
        fun part1(lines: Sequence<String>): Int {
            val day12 = Day12()
            lines.forEach { day12.parseInput(it) }
            val routes = day12.findRoutes("start","end")
            return routes.size
        }

        fun part2(lines: Sequence<String>): Int {
            val day12 = Day12(true)
            lines.forEach { day12.parseInput(it) }
            val routes = day12.findRoutes("start","end")
            return routes.size
        }
    }

}