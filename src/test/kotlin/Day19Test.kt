import org.junit.Ignore
import org.junit.Test
import kotlin.test.assertEquals

class Day19TestKotlin {
    @Test
    fun `real part 2`() {
        assertEquals(13083798, Day19(lines).part2())
    }

    @Test
    @Ignore
    fun `day 21 examples`() {
        var result = -1
        var n = 0
        do {
            try {
                result = Day19(lines21).part2(n++)
            } catch (e:IllegalStateException) {
                if(n%10000 == 0) {
                    println(n)
                }
            }
        } while(result < 0)
        assertEquals(15864120, result)
    }

    companion object {
        private val lines = """
#ip 5
addi 5 16 5
seti 1 3 1
seti 1 1 2
mulr 1 2 4
eqrr 4 3 4
addr 4 5 5
addi 5 1 5
addr 1 0 0
addi 2 1 2
gtrr 2 3 4
addr 5 4 5
seti 2 4 5
addi 1 1 1
gtrr 1 3 4
addr 4 5 5
seti 1 5 5
mulr 5 5 5
addi 3 2 3
mulr 3 3 3
mulr 5 3 3
muli 3 11 3
addi 4 8 4
mulr 4 5 4
addi 4 13 4
addr 3 4 3
addr 5 0 5
seti 0 8 5
setr 5 3 4
mulr 4 5 4
addr 5 4 4
mulr 5 4 4
muli 4 14 4
mulr 4 5 4
addr 3 4 3
seti 0 8 0
seti 0 4 5
            """.trimIndent().lines()

        private val lines21 = """
#ip 4
seti 123 0 3
bani 3 456 3
eqri 3 72 3
addr 3 4 4
seti 0 0 4
seti 0 5 3
bori 3 65536 2
seti 7637914 8 3
bani 2 255 1
addr 3 1 3
bani 3 16777215 3
muli 3 65899 3
bani 3 16777215 3
gtir 256 2 1
addr 1 4 4
addi 4 1 4
seti 27 1 4
seti 0 7 1
addi 1 1 5
muli 5 256 5
gtrr 5 2 5
addr 5 4 4
addi 4 1 4
seti 25 3 4
addi 1 1 1
seti 17 0 4
setr 1 8 2
seti 7 7 4
eqrr 3 0 1
addr 1 4 4
seti 5 5 4
            """.trimIndent().lines()
    }


}
