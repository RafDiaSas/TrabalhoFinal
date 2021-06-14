package pt.ipg.trabalhofinal

import android.content.ContentProvider
import android.content.ContentValues
import android.content.UriMatcher
import android.database.Cursor
import android.net.Uri
import android.provider.BaseColumns

class ContentProviderCasos : ContentProvider(){
    private var bdCasosOpenHelper : BDCasosOpenHelper? = null

    override fun onCreate(): Boolean {
        bdCasosOpenHelper = BDCasosOpenHelper(context)


        return true
    }

    override fun query(
        uri: Uri,
        projection: Array<out String>?,
        selection: String?,
        selectionArgs: Array<out String>?,
        sortOrder: String?
    ): Cursor? {
        val bd = bdCasosOpenHelper!!.readableDatabase

        return when(getUriMatcher().match(uri)){
            URI_PESSOAS -> TabelaPessoas(bd).query(
                projection as Array<String>,
                selection,
                selectionArgs as Array<String>,
                null,
                null,
                sortOrder
            )
            URI_PESSOA_ESPECIFICA -> TabelaPessoas(bd).query(
                projection as Array<String>,
                "${BaseColumns._ID}=?",
                arrayOf(uri.lastPathSegment!!), // id
                null,
                null,
                null
            )
            URI_ATIVOS -> TabelaAtivos(bd).query(
                projection as Array<String>,
                selection,
                selectionArgs as Array<String>,
                null,
                null,
                sortOrder
            )
            URI_ATIVO_ESPECIFICO ->TabelaAtivos(bd).query(
                projection as Array<String>,
                "${BaseColumns._ID}=?",
                arrayOf(uri.lastPathSegment!!), // id
                null,
                null,
                null
            )
            URI_RECUPERADOS -> TabelaRecuperados(bd).query(
                projection as Array<String>,
                selection,
                selectionArgs as Array<String>,
                null,
                null,
                sortOrder
            )
            URI_RECUPERADO_ESPECIFICO -> TabelaRecuperados(bd).query(
                projection as Array<String>,
                "${BaseColumns._ID}=?",
                arrayOf(uri.lastPathSegment!!), // id
                null,
                null,
                null
            )
            URI_TOTAIS -> TabelaTotais(bd).query(
                projection as Array<String>,
                selection,
                selectionArgs as Array<String>,
                null,
                null,
                sortOrder
            )
            URI_TOTAL_ESPECIFICO ->TabelaTotais(bd).query(
                projection as Array<String>,
                "${BaseColumns._ID}=?",
                arrayOf(uri.lastPathSegment!!), // id
                null,
                null,
                null
            )
            else -> null
        }
    }
    override fun getType(uri: Uri): String? {
        return when(getUriMatcher().match(uri)){
            URI_PESSOAS -> "$MULTIPLOS_ITEMS/$PESSOAS"
            URI_PESSOA_ESPECIFICA -> "$UNICO_ITEM/$PESSOAS"
            URI_ATIVOS -> "$MULTIPLOS_ITEMS/$ATIVOS"
            URI_ATIVO_ESPECIFICO -> "$UNICO_ITEM/$ATIVOS"
            URI_RECUPERADOS -> "$MULTIPLOS_ITEMS/$RECUPERADOS"
            URI_RECUPERADO_ESPECIFICO -> "$UNICO_ITEM/$RECUPERADOS"
            URI_TOTAIS -> "$MULTIPLOS_ITEMS/$TOTAIS"
            URI_TOTAL_ESPECIFICO -> "$UNICO_ITEM/$TOTAIS"
            else -> null
        }
    }

    override fun insert(uri: Uri, values: ContentValues?): Uri? {
        val bd = bdCasosOpenHelper!!.writableDatabase

        val id = when(getUriMatcher().match(uri)){
            URI_PESSOAS -> TabelaPessoas(bd).insert(values!!)
            URI_ATIVOS -> TabelaAtivos(bd).insert(values!!)
            URI_RECUPERADOS -> TabelaRecuperados(bd).insert(values!!)
            URI_TOTAIS -> TabelaTotais(bd).insert(values!!)
            else -> -1
        }

        if (id == -1L) return null
        return Uri.withAppendedPath(uri, id.toString())
    }

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<out String>?): Int {
        val bd = bdCasosOpenHelper!!.writableDatabase

        return when(getUriMatcher().match(uri)){
            URI_PESSOA_ESPECIFICA -> TabelaPessoas(bd).delete(
                "${BaseColumns._ID}=?",
                arrayOf(uri.lastPathSegment!!) // id
            )
            URI_ATIVO_ESPECIFICO ->TabelaAtivos(bd).delete(
                "${BaseColumns._ID}=?",
                arrayOf(uri.lastPathSegment!!) // id
            )
            URI_RECUPERADO_ESPECIFICO ->TabelaRecuperados(bd).delete(
                "${BaseColumns._ID}=?",
                arrayOf(uri.lastPathSegment!!) // id
            )
            URI_TOTAL_ESPECIFICO ->TabelaTotais(bd).delete(
                "${BaseColumns._ID}=?",
                arrayOf(uri.lastPathSegment!!) // id
            )
            else -> 0
        }
    }

    override fun update(
        uri: Uri,
        values: ContentValues?,
        selection: String?,
        selectionArgs: Array<out String>?
    ): Int {
        val bd = bdCasosOpenHelper!!.writableDatabase

        return when(getUriMatcher().match(uri)){
            URI_PESSOA_ESPECIFICA -> TabelaPessoas(bd).update(
                values!!,
                "${BaseColumns._ID}=?",
                arrayOf(uri.lastPathSegment!!) // id
            )
            URI_ATIVO_ESPECIFICO ->TabelaAtivos(bd).update(
                values!!,
                "${BaseColumns._ID}=?",
                arrayOf(uri.lastPathSegment!!) // id
            )
            URI_RECUPERADO_ESPECIFICO ->TabelaRecuperados(bd).update(
                values!!,
                "${BaseColumns._ID}=?",
                arrayOf(uri.lastPathSegment!!) // id
            )
            URI_TOTAL_ESPECIFICO ->TabelaTotais(bd).update(
                values!!,
                "${BaseColumns._ID}=?",
                arrayOf(uri.lastPathSegment!!) // id
            )
            else -> 0
        }
    }

    companion object{
        private const val AUTHORITY = "pt.ipg.trabalhofinal"
        private const val PESSOAS = "pessoas"
        private const val ATIVOS = "ativos"
        private const val RECUPERADOS = "recuperados"
        private const val TOTAIS = "totais"

        private const val URI_PESSOAS = 100
        private const val URI_PESSOA_ESPECIFICA = 101
        private const val URI_ATIVOS = 200
        private const val URI_ATIVO_ESPECIFICO = 201
        private const val URI_RECUPERADOS = 300
        private const val URI_RECUPERADO_ESPECIFICO = 301
        private const val URI_TOTAIS = 400
        private const val URI_TOTAL_ESPECIFICO = 401

        private const val MULTIPLOS_ITEMS = "vnd,android.cursor.dir"
        private const val UNICO_ITEM = "vnd,android.cursor.item"

        private val ENDERECO_BASE = Uri.parse("content://$AUTHORITY")
        public val ENDERECO_PESSOAS = Uri.withAppendedPath(ENDERECO_BASE, PESSOAS)
        public val ENDERECO_ATIVOS = Uri.withAppendedPath(ENDERECO_BASE, ATIVOS)
        public val ENDERECO_RECUPERADOS = Uri.withAppendedPath(ENDERECO_BASE, RECUPERADOS)
        public val ENDERECO_TOTAIS = Uri.withAppendedPath(ENDERECO_BASE, TOTAIS)

        private fun getUriMatcher() : UriMatcher {
            val uriMatcher = UriMatcher(UriMatcher.NO_MATCH)

            uriMatcher.addURI(AUTHORITY, PESSOAS, 100)
            uriMatcher.addURI(AUTHORITY, "$PESSOAS/#", 101)
            uriMatcher.addURI(AUTHORITY, ATIVOS, 200)
            uriMatcher.addURI(AUTHORITY, "$ATIVOS/#", 201)
            uriMatcher.addURI(AUTHORITY, RECUPERADOS, 300)
            uriMatcher.addURI(AUTHORITY, "$RECUPERADOS/#", 301)
            uriMatcher.addURI(AUTHORITY, TOTAIS, 400)
            uriMatcher.addURI(AUTHORITY, "$TOTAIS/#", 401)

            return uriMatcher
        }
    }

}