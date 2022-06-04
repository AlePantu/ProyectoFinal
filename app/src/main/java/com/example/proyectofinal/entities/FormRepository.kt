package com.example.proyectofinal.entities

object FormRepository {

    var condicion : MutableList<String> = mutableListOf()
    var transporte : MutableList<String> = mutableListOf()
    var familia : MutableList<String> = mutableListOf()
    var provincia : MutableList<String> = mutableListOf()
    var dias : MutableList<String> = mutableListOf()

    init {

        condicion.add("Turista")
        condicion.add("Residente")
        condicion.add("Otro")

        transporte.add("Auto")
        transporte.add("Moto")
        transporte.add("Avion")
        transporte.add("Omnibus")
        transporte.add("Otro")

        familia.add("Solo")
        familia.add("En Pareja")
        familia.add("En Familia")
        familia.add("Con Amigos")
        familia.add("Otro")

        provincia.add("Buenos Aires")
        provincia.add("CABA")
        provincia.add("Catamarca")
        provincia.add("Chaco")
        provincia.add("Chubut")
        provincia.add("Córdoba")
        provincia.add("Corrientes")
        provincia.add("Entre Ríos")
        provincia.add("Formosa")
        provincia.add("Jujuy")
        provincia.add("La Pampa")
        provincia.add("La Rioja")
        provincia.add("Mendoza")
        provincia.add("Misiones")
        provincia.add("Neuquén")
        provincia.add("Río Negro")
        provincia.add("Salta")
        provincia.add("San Luis")
        provincia.add("Santa Cruz")
        provincia.add("Santa Fe")
        provincia.add("Santiago del Estero")
        provincia.add("Tierra del Fuego, Antártida e Islas del Atlántico Sur")
        provincia.add("Tucumán")
        provincia.add("Otro Pais")

        dias.add("1")
        dias.add("5 ")
        dias.add("10")
        dias.add("15")
        dias.add("Mas de 15")
        dias.add("Otro")

    }

}