package com.mlsoluciones.prenatal.model

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteConstraintException
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException
import android.database.sqlite.SQLiteOpenHelper
import java.util.ArrayList

class UsersDBHelper (context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(SQL_CREATE_TUSUARIO)
        db.execSQL(SQL_CREATE_TEXAMEN)
        insertTexamen(db)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL(SQL_DELETE_TUSUARIO)
        db.execSQL(SQL_DELETE_TEXAMEN)
        //db.execSQL(SQL_DELETE_TIMAGEN)
        onCreate(db)
    }

    override fun onDowngrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        onUpgrade(db, oldVersion, newVersion)
    }

    fun insertTexamen(db: SQLiteDatabase) {
        // tipoExamen = 1:Ecografias 2:Paraclinicos 3:Vacunas
        // examenOpcional = 0: no 1: si

        db.execSQL("INSERT INTO texamen (codigo, nombreExamen, numTrimestre, tipoExamen, examenOpcional, " +
                "imgExamen, trimestreDesde, trimestreHasta) VALUES" +
                "('EXAMEN1', 'Translucencia nucal', 1, 1, 0, null, 1, 12.6), " +
                "('EXAMEN2', 'Doppler de arterias uterinas', 1, 1, 0, null, 1, 12.6)," +
                "('EXAMEN3', 'Ecoobtretica transvaginal', 1, 1, 0, null, 1, 12.6)," +
                "('EXAMEN4', 'Hemograma', 1, 2, 0, null, 1, 12.6)," +
                "('EXAMEN5', 'hemoclasificacion', 1, 2, 0, null, 1, 12.6)," +
                "('EXAMEN6', 'citologia', 1, 2, 0, null, 1, 12.6)," +
                "('EXAMEN7', 'frotis vaginal', 1, 2, 0, null, 1, 12.6)," +
                "('EXAMEN8', 'uorianalisis', 1, 2, 0, null, 1, 12.6)," +
                "('EXAMEN9', 'urocultivo', 1, 2, 0, null, 1, 12.6)," +
                "('EXAMEN10', 'vih', 1, 2, 0, null, 1, 12.6)," +
                "('EXAMEN11', 'toxoplasma igG igM', 1, 2, 0, null, 1, 12.6)," +
                "('EXAMEN12', 'hepatitis b', 1, 2, 0, null, 1, 12.6)," +
                "('EXAMEN13', 'citomegalovirus', 1, 2, 0, null, 1, 12.6)," +
                "('EXAMEN14', 'vdrl', 1, 2, 0, null, 1, 12.6)," +
                "('EXAMEN15', 'rubeola', 1, 2, 0, null, 1, 12.6)," +
                "('EXAMEN16', 'glicemia en ayunas', 1, 2, 0, null, 1, 12.6)," +
                "('EXAMEN17', 'influenza', 1, 3, 0, null, 1, 12.6)," +
                "('EXAMEN18', 'tetano', 1, 3, 0, null, 1, 12.6)," +
                "('EXAMEN19', 'ecografia obstetrica transabdominal de detalle anatomico', 2, 1, 0, null, 13, 24.6)," +
                "('EXAMEN20', 'vih', 2, 2, 0, null, 13, 24.6)," +
                "('EXAMEN21', 'toxoplasma igG igM', 2, 2, 0, null, 13, 24.6)," +
                "('EXAMEN22', 'hepatitis b', 2, 2, 0, null, 13, 24.6)," +
                "('EXAMEN23', 'citomegalovirus', 2, 2, 0, null, 13, 24.6)," +
                "('EXAMEN24', 'vdrl', 2, 2, 0, null, 13, 24.6)," +
                "('EXAMEN25', 'curva de tolerancia a la glucosa con carga de 75 mg entre las 24 a las 28 semanas', 2, 2, 0, null, 13, 24.6)," +
                "('EXAMEN26', 'coombs indirecto', 2, 2, 1, null, 13, 24.6)," +
                "('EXAMEN27', 'urocultivo', 2, 2, 1, null, 13, 24.6)," +
                "('EXAMEN28', 'uroanalisis', 2, 2, 1, null, 13, 24.6)," +
                "('EXAMEN29', 'hemograma', 2, 2, 0, null, 13, 24.6)," +
                "('EXAMEN30', 'tetano', 2, 3, 0, null, 13, 24.6)," +
                "('EXAMEN31', 'inmunoglobulina anti D', 2, 3, 1, null, 13, 24.6)," +
                "('EXAMEN32', 'ecografia obstetrica transabdominal', 3, 1, 0, null, 25, 40)," +
                "('EXAMEN33', 'perfil biofisico', 3, 1, 1, null, 25, 40)," +
                "('EXAMEN34', 'doppler de evaluacion de circulacion fetoplacentaria', 3, 1, 1, null, 25, 40)," +
                "('EXAMEN35', 'doppler de de circulación feto - placentaria', 3, 1, 1, null, 25, 40)," +
                "('EXAMEN36', 'hemograma', 3, 2, 0, null, 25, 40)," +
                "('EXAMEN37', 'vih', 3, 2, 0, null, 25, 40)," +
                "('EXAMEN38', 'toxoplasma igG igM', 3, 2, 0, null, 25, 40)," +
                "('EXAMEN39', 'hepatitis b', 3, 2, 0, null, 25, 40)," +
                "('EXAMEN40', 'citomegalovirus', 3, 2, 0, null, 25, 40)," +
                "('EXAMEN41', 'vdrl', 3, 2, 0, null, 25, 40)," +
                "('EXAMEN42', 'cultivo recto vaginal', 3, 2, 0, null, 25, 40)," +
                "('EXAMEN43', 'urocultivo', 3, 2, 1, null, 25, 40)," +
                "('EXAMEN44', 'uroanalisis', 3, 2, 1, null, 25, 40) ")
    }

    @Throws(SQLiteConstraintException::class)
    fun updateImgExamen(codigo: String, imgExamen: ByteArray): Boolean {
        // Obtiene el repositorio de datos en modo de escritura
        val db = writableDatabase
        // Define 'where' part of query.
        val selection = DBContract.ExamenEntry.COLUMN_CODIGO + "='" + codigo + "'"

        val values = ContentValues()
        values.put(DBContract.ExamenEntry.COLUMN_IMG_EXAMEN, imgExamen)
        // Issue SQL statement.
        db.update(DBContract.ExamenEntry.TABLE_NAME, values, selection, null)

        return true
    }

    @Throws(SQLiteConstraintException::class)
    fun updateTusuario(idUser: Int, edadGestacional: Float, fechaProbableParto: String, fechaUltimaMenstruacion: String): Boolean {
        // Obtiene el repositorio de datos en modo de escritura
        val db = writableDatabase
        // Define 'where' part of query.
        val selection = DBContract.UserEntry.COLUMN_ID_USER + "='" + idUser + "'"

        val values = ContentValues()
        values.put(DBContract.UserEntry.COLUMN_EDAD_GESTACIONAL, edadGestacional)
        values.put(DBContract.UserEntry.COLUMN_FECHA_PROBABLE_PARTO, fechaProbableParto)
        values.put(DBContract.UserEntry.COLUMN_FECHA_ULTIMA_MENSTRUACION, fechaUltimaMenstruacion)

        // Issue SQL statement.
        db.update(DBContract.UserEntry.TABLE_NAME, values, selection, null)

        return true
    }

    @Throws(SQLiteConstraintException::class)
    fun updateClaveUsuario(idUser: Int, clave: String): Boolean {
        // Obtiene el repositorio de datos en modo de escritura
        val db = writableDatabase
        // Define 'where' part of query.
        val selection = DBContract.UserEntry.COLUMN_ID_USER + "='" + idUser + "'"

        val values = ContentValues()
        values.put(DBContract.UserEntry.COLUMN_CONTRASENA, clave)

        // Issue SQL statement.
        db.update(DBContract.UserEntry.TABLE_NAME, values, selection, null)

        return true
    }

    fun selImgExamen(codigo: String): ByteArray? {
        val db = writableDatabase

        var cursor: Cursor? = null
        try {
            cursor = db.rawQuery("SELECT * FROM " + DBContract.ExamenEntry.TABLE_NAME + " WHERE " + DBContract.ExamenEntry.COLUMN_CODIGO + "='" + codigo + "'", null)

        } catch (e: SQLiteException) {
            db.execSQL(SQL_CREATE_TEXAMEN)
            return null
        }

        var imgExamen: ByteArray? = null

        if (cursor!!.moveToFirst()) {
            while (cursor.isAfterLast == false) {
                if(cursor.getBlob(cursor.getColumnIndex(DBContract.ExamenEntry.COLUMN_IMG_EXAMEN)) != null) {
                    imgExamen = cursor.getBlob(cursor.getColumnIndex(DBContract.ExamenEntry.COLUMN_IMG_EXAMEN))
                }
                cursor.moveToNext()
            }
        }
        return imgExamen
    }

    fun selTexamenById(codigo: String): ArrayList<ExamenModel> {
        val examens = ArrayList<ExamenModel>()
        val db = writableDatabase
        var cursor: Cursor? = null
        try {
            cursor = db.rawQuery("SELECT * FROM " + DBContract.ExamenEntry.TABLE_NAME , null)
        } catch (e: SQLiteException) {
            db.execSQL(SQL_CREATE_TEXAMEN)
            return ArrayList()
        }

        var idexam: Int
        var codigo: String
        var nombreExamen: String
        var numTrimestre: Int
        var tipoExamen: Int
        var examenOpcional: Int
        var imgExamen: ByteArray? = null
        var trimestreDesde: Float
        var trimestreHasta: Float

        if (cursor!!.moveToFirst()) {
            while (cursor.isAfterLast == false) {
                idexam = cursor.getInt(cursor.getColumnIndex(DBContract.ExamenEntry.COLUMN_ID_EXAM))
                codigo = cursor.getString(cursor.getColumnIndex(DBContract.ExamenEntry.COLUMN_CODIGO))
                nombreExamen = cursor.getString(cursor.getColumnIndex(DBContract.ExamenEntry.COLUMN_NOMBRE_EXAMEN))
                numTrimestre = cursor.getInt(cursor.getColumnIndex(DBContract.ExamenEntry.COLUMN_NUM_TRIMESTRE))
                tipoExamen = cursor.getInt(cursor.getColumnIndex(DBContract.ExamenEntry.COLUMN_TIPO_EXAMEN))
                examenOpcional = cursor.getInt(cursor.getColumnIndex(DBContract.ExamenEntry.COLUMN_EXAMEN_OPCIONAL))
                if(cursor.getColumnIndex(DBContract.ExamenEntry.COLUMN_IMG_EXAMEN) != null) {
                    imgExamen = cursor.getBlob(cursor.getColumnIndex(DBContract.ExamenEntry.COLUMN_IMG_EXAMEN))
                }
                trimestreDesde = cursor.getFloat(cursor.getColumnIndex(DBContract.ExamenEntry.COLUMN_TRIMESTRE_DESDE))
                trimestreHasta = cursor.getFloat(cursor.getColumnIndex(DBContract.ExamenEntry.COLUMN_TRIMESTRE_HASTA))

                examens.add(ExamenModel(idexam, codigo, nombreExamen, numTrimestre, tipoExamen,
                    examenOpcional, imgExamen, trimestreDesde, trimestreHasta))

                cursor.moveToNext()
            }
        }
        return examens
    }

    @Throws(SQLiteConstraintException::class)
    fun insertUser(user: UserModel): Long {
        // Gets the data repository in write mode
        val db = writableDatabase

        // Cree un nuevo mapa de valores, donde los nombres de columna son las claves
        val values = ContentValues()
        values.put(DBContract.UserEntry.COLUMN_NOMBRE, user.nombre)
        values.put(DBContract.UserEntry.COLUMN_EMAIL, user.email)
        values.put(DBContract.UserEntry.COLUMN_TELEFONO, user.telefono)
        values.put(DBContract.UserEntry.COLUMN_CONTRASENA, user.contrasena)
        values.put(DBContract.UserEntry.COLUMN_EDAD_GESTACIONAL, user.edadGestacional)
        values.put(DBContract.UserEntry.COLUMN_SEMANA_GESTACION_ECOGRAFIA, user.semanaGestacionEcografia)
        values.put(DBContract.UserEntry.COLUMN_FECHA_PROBABLE_PARTO, user.fechaProbableParto)
        values.put(DBContract.UserEntry.COLUMN_MADRE_RH_NEGATIVO, user.madreRhNegativo)
        values.put(DBContract.UserEntry.COLUMN_PADRE_RH_POSITIVO, user.padreRhPositivo)
        values.put(DBContract.UserEntry.COLUMN_HIJO_ANT_RH_POSITIVO, user.hijoAnteriorRhPositivo)

        // Inserte la nueva fila, devolviendo el valor de la clave principal de la nueva fila
        return db.insert(DBContract.UserEntry.TABLE_NAME, null, values)
    }

    fun readAllUsers(): ArrayList<UserModel> {
        val users = ArrayList<UserModel>()
        val db = writableDatabase
        var cursor: Cursor? = null
        try {
            cursor = db.rawQuery("SELECT * FROM " + DBContract.UserEntry.TABLE_NAME, null)
        } catch (e: SQLiteException) {
            db.execSQL(SQL_CREATE_TUSUARIO)
            return ArrayList()
        }

        var userid: Int
        var nombre: String
        var email: String
        var telefono: String
        var contrasena: String
        var edadGestacional: Float
        var semanaGestacionEcografia: Float
        var fechaProbableParto: String
        var madreRhNegativo: Int
        var padreRhPositivo: Int
        var hijoAnteriorRhPositivo: Int
        var fechaUltimaMenstruacion: String

        if (cursor!!.moveToFirst()) {
            while (cursor.isAfterLast == false) {
                userid = cursor.getInt(cursor.getColumnIndex(DBContract.UserEntry.COLUMN_ID_USER))
                nombre = cursor.getString(cursor.getColumnIndex(DBContract.UserEntry.COLUMN_NOMBRE))
                email = cursor.getString(cursor.getColumnIndex(DBContract.UserEntry.COLUMN_EMAIL))
                telefono = cursor.getString(cursor.getColumnIndex(DBContract.UserEntry.COLUMN_TELEFONO))
                contrasena = cursor.getString(cursor.getColumnIndex(DBContract.UserEntry.COLUMN_CONTRASENA))
                edadGestacional = cursor.getFloat(cursor.getColumnIndex(DBContract.UserEntry.COLUMN_EDAD_GESTACIONAL))
                semanaGestacionEcografia = cursor.getFloat(cursor.getColumnIndex(DBContract.UserEntry.COLUMN_SEMANA_GESTACION_ECOGRAFIA))
                fechaProbableParto = cursor.getString(cursor.getColumnIndex(DBContract.UserEntry.COLUMN_FECHA_PROBABLE_PARTO))
                madreRhNegativo = cursor.getInt(cursor.getColumnIndex(DBContract.UserEntry.COLUMN_MADRE_RH_NEGATIVO))
                padreRhPositivo = cursor.getInt(cursor.getColumnIndex(DBContract.UserEntry.COLUMN_PADRE_RH_POSITIVO))
                hijoAnteriorRhPositivo = cursor.getInt(cursor.getColumnIndex(DBContract.UserEntry.COLUMN_HIJO_ANT_RH_POSITIVO))
                fechaUltimaMenstruacion = cursor.getString(cursor.getColumnIndex(DBContract.UserEntry.COLUMN_FECHA_ULTIMA_MENSTRUACION))

                users.add(UserModel(userid, nombre, email, telefono, contrasena, edadGestacional, semanaGestacionEcografia, fechaProbableParto,
                                    madreRhNegativo, padreRhPositivo, hijoAnteriorRhPositivo, fechaUltimaMenstruacion))
                cursor.moveToNext()
            }
        }
        return users
    }

    fun loginUser(email: String, pass: String): Int {
        val users = ArrayList<UserModel>()
        val db = writableDatabase
        var cursor: Cursor? = null
        try {
            cursor = db.rawQuery("SELECT * FROM " + DBContract.UserEntry.TABLE_NAME + " WHERE " +
                    DBContract.UserEntry.COLUMN_EMAIL + "='" + email + "' AND " +
                    DBContract.UserEntry.COLUMN_CONTRASENA + "='" + pass + "'", null)
        } catch (e: SQLiteException) {
            db.execSQL(SQL_CREATE_TUSUARIO)
            return 0
        }

        var userid = 0

        if (cursor!!.moveToFirst()) {
            while (cursor.isAfterLast == false) {
                userid = cursor.getInt(cursor.getColumnIndex(DBContract.UserEntry.COLUMN_ID_USER))

                cursor.moveToNext()
            }
        }
        return userid
    }

    fun selNotificacion(EdadGestacional: Float): ArrayList<ExamenModel> {
        val examens = ArrayList<ExamenModel>()
        val db = writableDatabase

        var cursor: Cursor? = null
        try {
            cursor = db.rawQuery("SELECT * FROM " + DBContract.ExamenEntry.TABLE_NAME + " " +
             "WHERE " + DBContract.ExamenEntry.COLUMN_EXAMEN_OPCIONAL + "= 0" + " " +
             "AND " + DBContract.ExamenEntry.COLUMN_IMG_EXAMEN + " IS NULL" + " " +
             "AND " + DBContract.ExamenEntry.COLUMN_TRIMESTRE_HASTA + "<='" + EdadGestacional + "'" + " " +
             "UNION ALL" + " " +
             "SELECT * FROM " + DBContract.ExamenEntry.TABLE_NAME + " " +
             "WHERE " + DBContract.ExamenEntry.COLUMN_EXAMEN_OPCIONAL + "=1 AND " +
             DBContract.ExamenEntry.COLUMN_CODIGO + " " + "NOT IN ('EXAMEN27', 'EXAMEN28', 'EXAMEN33'," +
             " 'EXAMEN34', 'EXAMEN35', 'EXAMEN43', 'EXAMEN44', 'EXAMEN26', 'EXAMEN31')", null)
        } catch (e: SQLiteException) {
            db.execSQL(SQL_CREATE_TEXAMEN)
            return ArrayList()
        }

        var idexam: Int
        var codigo: String
        var nombreExamen: String
        var numTrimestre: Int
        var tipoExamen: Int
        var examenOpcional: Int
        var imgExamen: ByteArray? = null
        var trimestreDesde: Float
        var trimestreHasta: Float

        if (cursor!!.moveToFirst()) {
            while (cursor.isAfterLast == false) {
                idexam = cursor.getInt(cursor.getColumnIndex(DBContract.ExamenEntry.COLUMN_ID_EXAM))
                codigo = cursor.getString(cursor.getColumnIndex(DBContract.ExamenEntry.COLUMN_CODIGO))
                nombreExamen = cursor.getString(cursor.getColumnIndex(DBContract.ExamenEntry.COLUMN_NOMBRE_EXAMEN))
                numTrimestre = cursor.getInt(cursor.getColumnIndex(DBContract.ExamenEntry.COLUMN_NUM_TRIMESTRE))
                tipoExamen = cursor.getInt(cursor.getColumnIndex(DBContract.ExamenEntry.COLUMN_TIPO_EXAMEN))
                examenOpcional = cursor.getInt(cursor.getColumnIndex(DBContract.ExamenEntry.COLUMN_EXAMEN_OPCIONAL))
                if(cursor.getColumnIndex(DBContract.ExamenEntry.COLUMN_IMG_EXAMEN) != null) {
                    imgExamen = cursor.getBlob(cursor.getColumnIndex(DBContract.ExamenEntry.COLUMN_IMG_EXAMEN))
                }
                trimestreDesde = cursor.getFloat(cursor.getColumnIndex(DBContract.ExamenEntry.COLUMN_TRIMESTRE_DESDE))
                trimestreHasta = cursor.getFloat(cursor.getColumnIndex(DBContract.ExamenEntry.COLUMN_TRIMESTRE_HASTA))

                examens.add(ExamenModel(idexam, codigo, nombreExamen, numTrimestre, tipoExamen,
                    examenOpcional, imgExamen, trimestreDesde, trimestreHasta))

                cursor.moveToNext()
            }
        }
        return examens
    }

    companion object {
        // Si cambia el esquema de la base de datos, debe incrementar la versión de la base de datos.
        val DATABASE_VERSION = 9
        val DATABASE_NAME = "PrenatalApp.db"

        private val SQL_CREATE_TUSUARIO =
            "CREATE TABLE " + DBContract.UserEntry.TABLE_NAME + " (" +
                    DBContract.UserEntry.COLUMN_ID_USER + " INTEGER PRIMARY KEY autoincrement," +
                    DBContract.UserEntry.COLUMN_NOMBRE + " TEXT," +
                    DBContract.UserEntry.COLUMN_EMAIL + " TEXT," +
                    DBContract.UserEntry.COLUMN_TELEFONO + " TEXT," +
                    DBContract.UserEntry.COLUMN_CONTRASENA + " TEXT," +
                    DBContract.UserEntry.COLUMN_EDAD_GESTACIONAL + " REAL," +
                    DBContract.UserEntry.COLUMN_SEMANA_GESTACION_ECOGRAFIA + " REAL," +
                    DBContract.UserEntry.COLUMN_FECHA_PROBABLE_PARTO + " DATE," +
                    DBContract.UserEntry.COLUMN_MADRE_RH_NEGATIVO + " INTEGER," +
                    DBContract.UserEntry.COLUMN_PADRE_RH_POSITIVO + " INTEGER," +
                    DBContract.UserEntry.COLUMN_HIJO_ANT_RH_POSITIVO + " INTEGER," +
                    DBContract.UserEntry.COLUMN_FECHA_ULTIMA_MENSTRUACION + " DATE)"

        private val SQL_DELETE_TUSUARIO= "DROP TABLE IF EXISTS " + DBContract.UserEntry.TABLE_NAME

        private val SQL_CREATE_TEXAMEN=
            "CREATE TABLE " + DBContract.ExamenEntry.TABLE_NAME + " (" +
                    DBContract.ExamenEntry.COLUMN_ID_EXAM + " INTEGER PRIMARY KEY autoincrement," +
                    DBContract.ExamenEntry.COLUMN_CODIGO + " TEXT," +
                    DBContract.ExamenEntry.COLUMN_NOMBRE_EXAMEN + " TEXT," +
                    DBContract.ExamenEntry.COLUMN_NUM_TRIMESTRE + " INTEGER," +
                    DBContract.ExamenEntry.COLUMN_TIPO_EXAMEN + " INTEGER," +
                    DBContract.ExamenEntry.COLUMN_EXAMEN_OPCIONAL + " INTEGER, " +
                    DBContract.ExamenEntry.COLUMN_IMG_EXAMEN + " BLOB, " +
                    DBContract.ExamenEntry.COLUMN_TRIMESTRE_DESDE + " REAL, " +
                    DBContract.ExamenEntry.COLUMN_TRIMESTRE_HASTA + " REAL)"

        private val SQL_DELETE_TEXAMEN = "DROP TABLE IF EXISTS " + DBContract.ExamenEntry.TABLE_NAME

    }

}