package aoc2022.day07

object Day202207 {

    class EDir(
        private val name: String,
        private val parent: EDir? = null,
        private val subDirs: MutableList<EDir> = mutableListOf(),
        private val files: MutableList<EFile> = mutableListOf()
    ) {
        val size:Long
            get() = files.sumOf { it.size } + subDirs.sumOf { it.size }

        fun sum(block: (EDir) -> Boolean):Long =
            (if (block(this)) this.size else 0) + subDirs.sumOf { it.sum(block) }

        fun list():List<EDir> = subDirs + subDirs.map { it.list()}.flatten()

        // A chunk start with a dir listing and then some cd commands
        fun parseChunk(lines: List<String>): EDir {

            var currentDir = this
            lines.filter { it.isNotBlank() }.forEach { l ->
                when {

                    l.startsWith("$ cd ..") -> {
                        currentDir = currentDir.parent ?: currentDir
                    }

                    l.startsWith("$ cd") -> {
                        val name = l.substring(5,l.length)
                        currentDir = currentDir.subDirs.firstOrNull { it.name == name } ?: currentDir
                    }

                    l.startsWith("dir ") -> {
                        currentDir.subDirs.add(EDir(l.substring(4, l.length),currentDir))
                    }

                    !l.startsWith("$") -> {
                        val s = l.split(' ')
                        currentDir.files.add(EFile(s[1],s[0].toLong()))
                    }
                }
            }

            return currentDir
        }
    }

    class EFile(val name: String, val size: Long)

    fun part1(text: String): Long {
        val root = parseInput(text)
        return root.sum { it.size <= 100000L }
    }

    fun part2(text: String): Long {
        val root = parseInput(text)
        val free = 70000000 - root.size
        val needed = 30000000 - free

        return root.list().sortedBy { it.size }.firstOrNull { it.size >= needed }?.size ?: 0
    }

    fun parseInput(text: String): EDir {
        val root = EDir("root")
        var currentDir = root
        text.split("$ ls").drop(1).forEach { c ->
            currentDir = currentDir.parseChunk(c.split("\n"))
        }
        return root
    }


}