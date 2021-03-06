package com.mlsoluciones.prenatal.model

import java.util.*

class UserModel(val iduser: Int, val nombre: String, val email: String, val telefono: String,
                val contrasena: String, val edadGestacional: Float,
                val semanaGestacionEcografia: Float, val fechaProbableParto: String,
                val madreRhNegativo: Int, val padreRhPositivo: Int, val hijoAnteriorRhPositivo: Int,
                val fechaUltimaMenstruacion: String)