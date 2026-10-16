//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
fun control() {
    val tecnico = "Jannik Jendryewski"
    //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
    // to see how IntelliJ IDEA suggests fixing it.
    var cont = 3

    val temperaturaTexto = "38.5"

    val temperarutaDouble: Double = temperaturaTexto.toDouble()

    val estadoInvernadero = "Default"

    if (temperarutaDouble > 35.0) {
        estadoInvernadero = "ALERTA: Activar ventilación lateral e irrigación."

    }
    else {
        estadoInvernadero = "ESTADO NORMAL: Parámetros óptimos."
    }



}