import kotlin.random.Random

fun main(){
    val DAUS: String = "⚀ ⚁ ⚂ ⚃ ⚄ ⚅ ⚀ ⚁ ⚂ ⚃ ⚄ ⚅ ⚀ ⚁ ⚂ ⚃ ⚄ ⚅ "
    val CARES_DAU: Array<String> = arrayOf("⚀", "⚁", "⚂", "⚃", "⚄", "⚅")

    var partides: Int?
    var tiradesPerPartida: Int?
    var partidasGnadas: Int = 0

    println("=====================================")
    println("       JOC DELS DAUS – VS CPU        ")
    println("=====================================")
    println(DAUS)
    println("Benvingut/da al joc dels daus.")
    println("Per guanyar cada partida, la suma dels teus daus")
    println("ha de ser superior a la de la CPU.")
    println(DAUS)

    // Llegim el número de partides que volem jugar
    do {
        println("Quantes partides vols jugar? (de 1 a 3) ")
        partides = readLine()?.toIntOrNull()

        if (partides != null && (partides < 1 || partides > 3)){
            partides = null
            println("ERROR: Valor no acceptat!")
        }
    }while(partides == null)

    // Llegim el número de quantes tirades volem fer per cada partida
    do {
        println("Quantes tirades vols fer per cada partida? (de 1 a 6)")
        tiradesPerPartida = readLine()?.toIntOrNull()

        if (tiradesPerPartida != null && (tiradesPerPartida < 1 || tiradesPerPartida > 6)){
            tiradesPerPartida = null
            println("ERROR: Valor no acceptat!")
        }
    }while(tiradesPerPartida == null)

    // Declarem la matriu
    var tiradesGuardades: Array<IntArray>

    // Inicialitzem la matriu de partides files i (tiradesPerPartida + 1) columnes
    tiradesGuardades = Array(partides){IntArray((tiradesPerPartida + 1)) }

    // Repetim tantes vegades com partides
    for(partida in 0 until partides) {
        var acumuladorCPU: Int = 0
        var tiradaActual: Int = 0

        println("\n=== PARTIDA ${partida + 1} / $partides ===")

        for (tirada in 0 until tiradesGuardades[partida].size - 1) {
            /** Tirades persona **/
            println("Tira el dau! (Intent ${tirada + 1})")
            tiradaActual = Random.nextInt(1, 6 + 1)
            println("Has tret un ${CARES_DAU[tiradaActual-1]} !")

            // Guardem la tirada
            tiradesGuardades[partida][tirada] = tiradaActual

            println("=========== VS ===========")

            // Acumulem el sumatori a l'última columna de la fila
            tiradesGuardades[partida][tiradesPerPartida] += tiradaActual

            /** Tirades CPU **/
            val tiradaCPU = Random.nextInt(1, 6 + 1)
            println("La CPU ha tret un ${CARES_DAU[tiradaCPU - 1]} !")
            acumuladorCPU += tiradaCPU
            println("_______________________________________")
        }

        println("Partida acabada!")
        println("Tu has aconseguit ${tiradesGuardades[partida][tiradesPerPartida]} punts")
        println("La CPU ha aconseguit $acumuladorCPU punts")

        if (tiradesGuardades[partida][tiradesPerPartida] > acumuladorCPU){
            println("Has guanyat!")
            partidasGnadas++
        }else if (tiradesGuardades[partida][tiradesPerPartida] < acumuladorCPU){
            println("Has perdut!")
        }else{
            println("Heu empatat!")
        }
        println()
    }

    // Aqui calculamos el pocertange de cada partida ganada, perdida o empatada
    val victoriasUsuario = (partidasGnadas.toDouble()/partides)*100

    // Todo esto es el historial
    println("=====================================")
    println("     RESUM FINAL DEL JOC     ")
    println("=====================================")
    println("Total de partides jugades: $partides")
    println("Partides guanyades: $partidasGnadas (${"%.1f".format(victoriasUsuario)}%)")
    println("=====================================")
}