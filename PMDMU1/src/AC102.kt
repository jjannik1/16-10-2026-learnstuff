fun evaluarTemperatura(temperatura:Double, umbralAlerta:Double = 35.0): String{

    return if(temperatura >= umbralAlerta) {
         "ALERTA ($temperatura ºC)"
    }
    else {
         "Normal ($temperatura ºC)"
    }

}

fun imprimirInformeSector(lista: List<Double>, nombre: String) {
    println("Para el sector: $nombre, los sensores reportan esto:")

    for (valor in lista) {
        val resultado = evaluarTemperatura(valor)
        println("- $resultado")
    }
}


fun main(){

    val lista = listOf(32.0,34.5,36.9,28.9)
    val sector = "Sector B - Invernadero Norte"

    imprimirInformeSector(lista, sector)


}