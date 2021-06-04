package pt.ipg.trabalhofinal

import android.content.ContentValues
import android.database.Cursor
import android.provider.BaseColumns

data class Ativos(var id: Long = -1, var idPessoas: Long) {
    fun toContentValues(): ContentValues {
        val valores = ContentValues().apply{
            put(TabelaAtivos.CAMPO_ID_PESSOAS, idPessoas)
        }
        return valores
    }

    companion object {
        fun fromCursor(cursor: Cursor): Ativos {
            val colId = cursor.getColumnIndex(BaseColumns._ID)
            val colIdPessoas = cursor.getColumnIndex(TabelaAtivos.CAMPO_ID_PESSOAS)

            val id = cursor.getLong(colId)
            val idPessoas = cursor.getLong(colIdPessoas)

            return Ativos(id, idPessoas)
        }
    }
}