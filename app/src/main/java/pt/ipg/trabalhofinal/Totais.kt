package pt.ipg.trabalhofinal

import android.content.ContentValues
import android.database.Cursor
import android.provider.BaseColumns

data class Totais(var id: Long = -1, var totais: Int, var ativos: Int, var recuperados: Int) {
    fun toContentValues(): ContentValues {
        val valores = ContentValues().apply{
            put(TabelaTotais.CAMPO_TOTAIS, totais)
            put(TabelaTotais.CAMPO_ATIVOS, ativos)
            put(TabelaTotais.CAMPO_RECUPERADOS, recuperados)
        }
        return valores
    }

    companion object {
        fun fromCursor(cursor: Cursor): Totais {
            val colId = cursor.getColumnIndex(BaseColumns._ID)
            val colTotais = cursor.getColumnIndex(TabelaTotais.CAMPO_TOTAIS)
            val colAtivos = cursor.getColumnIndex(TabelaTotais.CAMPO_ATIVOS)
            val colRecuperados = cursor.getColumnIndex(TabelaTotais.CAMPO_RECUPERADOS)

            val id = cursor.getLong(colId)
            val totais = cursor.getInt(colTotais)
            val ativos = cursor.getInt(colAtivos)
            val recuperados = cursor.getInt(colRecuperados)

            return Totais(id, totais, ativos, recuperados)
        }
    }
}