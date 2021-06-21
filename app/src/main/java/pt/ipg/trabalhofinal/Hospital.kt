package pt.ipg.trabalhofinal

import android.content.ContentValues
import android.database.Cursor
import android.provider.BaseColumns

data class Hospital(var id: Long = -1, var nome: String, var regiao: String, var capacidade: Int, var ocpacaoAtual: Int) {
    fun toContentValues(): ContentValues {
        val valores = ContentValues().apply{
            put(TabelaHospital.CAMPO_NOME, nome)
            put(TabelaHospital.CAMPO_REGIAO, regiao)
            put(TabelaHospital.CAMPO_CAPACIDADE, capacidade)
            put(TabelaHospital.CAMPO_OCUPACAOATUAL, ocpacaoAtual)
        }
        return valores
    }

    companion object {
        fun fromCursor(cursor: Cursor): Hospital {
            val colId = cursor.getColumnIndex(BaseColumns._ID)
            val colNome = cursor.getColumnIndex(TabelaHospital.CAMPO_NOME)
            val colRegiao = cursor.getColumnIndex(TabelaHospital.CAMPO_REGIAO)
            val colCapacidade = cursor.getColumnIndex(TabelaHospital.CAMPO_CAPACIDADE)
            val colOcupacaoAtual = cursor.getColumnIndex(TabelaHospital.CAMPO_OCUPACAOATUAL)

            val id = cursor.getLong(colId)
            val nome = cursor.getString(colNome)
            val regiao = cursor.getString(colRegiao)
            val capacidade = cursor.getInt(colCapacidade)
            val ocupacaoAtual = cursor.getInt(colOcupacaoAtual)

            return Hospital(id, nome, regiao, capacidade, ocupacaoAtual)
        }
    }
}