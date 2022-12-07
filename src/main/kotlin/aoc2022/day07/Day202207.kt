package aoc2022.day07

object Day202207 {

    class EDir(
        val name: String,
        val parent: EDir? = null,
        val subDirs: MutableList<EDir> = mutableListOf(),
        val files: MutableList<EFile> = mutableListOf()
    ) {
        val size:Long
            get() = files.sumOf { it.size } + subDirs.sumOf { it.size }

        fun sum(block: (EDir) -> Boolean):Long =
            (if (block(this)) this.size else 0) + subDirs.sumOf { it.sum(block) }
    }

    class EFile(val name: String, val size: Long)

    fun part1(text: String): Long {
        val root = parseInput(text)
        return root.sum { it.size <= 100000L }
    }

    fun part2(text: String): Int {
        return 0
    }

    fun parseInput(text: String): EDir {
        val root = EDir("root")
        val chunks = text.split("$ ls")
        var currentDir = root
        for (c in chunks.drop(1)) {
            currentDir = parseChunk(c.split("\n"), currentDir)
        }
        return root
    }

    fun parseChunk(lines: List<String>, currentDirIn: EDir): EDir {

        var currentDir = currentDirIn
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