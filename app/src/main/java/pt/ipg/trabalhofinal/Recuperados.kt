package pt.ipg.trabalhofinal

import android.content.ContentValues
import android.database.Cursor
import android.provider.BaseColumns

data class Recuperados(var id: Long = -1, var idPessoas: Long) {
    fun toContentValues(): ContentValues {
        val valores = ContentValues().apply{
            put(TabelaRecuperados.CAMPO_ID_PESSOAS, idPessoas)
        }
        return valores
    }

    companion object {
        fun fromCursor(cursor: Cursor): Recuperados {
            val colId = cursor.getColumnIndex(BaseColumns._ID)
            val colIdPessoas = cursor.getColumnIndex(TabelaRecuperados.CAMPO_ID_PESSOAS)

            val id = cursor.getLong(colId)
            val idPessoas = cursor.getLong(colIdPessoas)

            return Recuperados(id, idPessoas)
        }
    }
}