package shared

import shared.Turn.Clockwise

enum class Turn {
    None,
    Clockwise,
    Counterclockwise
}
enum class Direction(val id:Int) {
    Right(0),
    Down(1),
    Left(2),
    Up(3);

    fun turn(turn: Turn) = if (turn == Turn.None) this else when (this) {
        Right -> if (turn == Clockwise) Down else Up
        Left -> if (turn == Clockwise) Up else Down
        Down -> if (turn == Clockwise) Left else Right
        Up -> if (turn == Clockwise) Right else Left
    }
}
