package com.mlsoluciones.prenatal.model

import android.provider.BaseColumns

object DBContract {

    /* Clase interna que define el contenido de la tabla tusuario. */
    class UserEntry : BaseColumns {
        companion object {
            val TABLE_NAME = "tusuario"
            val COLUMN_ID_USER = "iduser"
            val COLUMN_NOMBRE = "nombre"
            val COLUMN_EMAIL = "email"
            val COLUMN_TELEFONO = "telefono"
            val COLUMN_CONTRASENA = "contrasena"
            val COLUMN_EDAD_GESTACIONAL = "edadGestacional"
            val COLUMN_SEMANA_GESTACION_ECOGRAFIA = "semanaGestacionEcografia"
            val COLUMN_FECHA_PROBABLE_PARTO = "fechaProbableParto"
            val COLUMN_MADRE_RH_NEGATIVO = "madreRhNegativo"
            val COLUMN_PADRE_RH_POSITIVO = "padreRhPositivo"
            val COLUMN_HIJO_ANT_RH_POSITIVO = "hijoAnteriorRhPositivo"

        }
    }

    /* Clase interna que define el contenido de la tabla texamen. */
    class ExamenEntry : BaseColumns {
        companion object {
            val TABLE_NAME = "texamen"
            val COLUMN_ID_EXAM = "idexam"
            val COLUMN_CODIGO = "codigo"
            val COLUMN_NOMBRE_EXAMEN = "nombreExamen"
            val COLUMN_NUM_TRIMESTRE = "numTrimestre"
            val COLUMN_TIPO_EXAMEN = "tipoExamen"
            val COLUMN_EXAMEN_OPCIONAL = "examenOpcional"
            val COLUMN_IMG_EXAMEN = "imgExamen"
        }
    }
}