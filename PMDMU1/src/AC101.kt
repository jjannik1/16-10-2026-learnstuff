fun main() {
    val tecnico = "Jannik Jendryewski"

    var cont = 3

    cont++

    println("Tecnico: $tecnico | Revisiones hoy: $cont")

    val temperaturaTexto = "38.5"

    val temperaturaDouble = temperaturaTexto.toDouble()

    println("La temperatura es: $temperaturaDouble")


    if (temperaturaDouble > 35.0){
       val estadoInvernadero = "ALERTA: Activar ventilación lateral e irrigación."
    }
    else {
        val estadoInvernadero = "ESTADO NORMAL: Parámetros óptimos."
    }


    var observacionesTecnico:String? = null

    println(observacionesTecnico?.length)

    observacionesTecnico = "Sensor 04 con acumulación de humedad"

    println(observacionesTecnico)
    println(observacionesTecnico?.length)


}
