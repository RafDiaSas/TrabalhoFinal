package pt.ipg.trabalhofinal

import android.content.ContentValues
import android.database.Cursor
import android.provider.BaseColumns

data class Obitos(var id: Long = -1, var dataObito: Int, var idPessoa: Int, var idHospital: Int) {
    fun toContentValues(): ContentValues {
        val valores = ContentValues().apply{
            put(TabelaObitos.CAMPO_DATAOBITO, dataObito)
            put(TabelaObitos.CAMPO_ID_PESSOA, idPessoa)
            put(TabelaObitos.CAMPO_ID_HOSPITAL, idHospital)
        }
        return valores
    }

    companion object {
        fun fromCursor(cursor: Cursor): Obitos {
            val colId = cursor.getColumnIndex(BaseColumns._ID)
            val colDataObito = cursor.getColumnIndex(TabelaObitos.CAMPO_DATAOBITO)
            val colIDPessoa = cursor.getColumnIndex(TabelaObitos.CAMPO_ID_PESSOA)
            val colIDHospital = cursor.getColumnIndex(TabelaObitos.CAMPO_ID_HOSPITAL)

            val id = cursor.getLong(colId)
            val dataObito = cursor.getInt(colDataObito)
            val idPessoa = cursor.getInt(colIDPessoa)
            val idHospital = cursor.getInt(colIDHospital)

            return Obitos(id, dataObito, idPessoa, idHospital)
        }
    }
}