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
                selectionArgs as Array<String>?,
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
            URI_HOSPITAL -> TabelaHospital(bd).query(
                projection as Array<String>,
                selection,
                selectionArgs as Array<String>?,
                null,
                null,
                sortOrder
            )
            URI_HOSPITAL_ESPECIFICO -> TabelaHospital(bd).query(
                projection as Array<String>,
                "${BaseColumns._ID}=?",
                arrayOf(uri.lastPathSegment!!), // id
                null,
                null,
                null
            )
            URI_OBITOS -> TabelaObitos(bd).query(
                projection as Array<String>,
                selection,
                selectionArgs as Array<String>?,
                null,
                null,
                sortOrder
            )
            URI_OBITO_ESPECIFICO -> TabelaObitos(bd).query(
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
            URI_HOSPITAL -> "$MULTIPLOS_ITEMS/$HOSPITAL"
            URI_HOSPITAL_ESPECIFICO -> "$UNICO_ITEM/$HOSPITAL"
            URI_OBITOS -> "$MULTIPLOS_ITEMS/$OBITOS"
            URI_OBITO_ESPECIFICO -> "$UNICO_ITEM/$OBITOS"
            else -> null
        }
    }

    override fun insert(uri: Uri, values: ContentValues?): Uri? {
        val bd = bdCasosOpenHelper!!.writableDatabase

        val id = when(getUriMatcher().match(uri)){
            URI_PESSOAS -> TabelaPessoas(bd).insert(values!!)
            URI_HOSPITAL -> TabelaHospital(bd).insert(values!!)
            URI_OBITOS -> TabelaObitos(bd).insert(values!!)
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
            URI_HOSPITAL_ESPECIFICO -> TabelaHospital(bd).delete(
                "${BaseColumns._ID}=?",
                arrayOf(uri.lastPathSegment!!) // id
            )
            URI_OBITO_ESPECIFICO -> TabelaObitos(bd).delete(
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
            URI_HOSPITAL_ESPECIFICO -> TabelaHospital(bd).update(
                values!!,
                "${BaseColumns._ID}=?",
                arrayOf(uri.lastPathSegment!!) // id
            )
            URI_OBITO_ESPECIFICO -> TabelaObitos(bd).update(
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
        private const val HOSPITAL = "hospital"
        private const val OBITOS = "obitos"

        private const val URI_PESSOAS = 100
        private const val URI_PESSOA_ESPECIFICA = 101
        private const val URI_HOSPITAL = 200
        private const val URI_HOSPITAL_ESPECIFICO = 201
        private const val URI_OBITOS = 300
        private const val URI_OBITO_ESPECIFICO = 301


        private const val MULTIPLOS_ITEMS = "vnd,android.cursor.dir"
        private const val UNICO_ITEM = "vnd,android.cursor.item"

        private val ENDERECO_BASE = Uri.parse("content://$AUTHORITY")
        public val ENDERECO_PESSOAS = Uri.withAppendedPath(ENDERECO_BASE, PESSOAS)
        public val ENDERECO_HOSPITAL = Uri.withAppendedPath(ENDERECO_BASE, HOSPITAL)
        public val ENDERECO_OBITOS = Uri.withAppendedPath(ENDERECO_BASE, OBITOS)

        private fun getUriMatcher() : UriMatcher {
            val uriMatcher = UriMatcher(UriMatcher.NO_MATCH)

            uriMatcher.addURI(AUTHORITY, PESSOAS, 100)
            uriMatcher.addURI(AUTHORITY, "$PESSOAS/#", 101)
            uriMatcher.addURI(AUTHORITY, HOSPITAL, 200)
            uriMatcher.addURI(AUTHORITY, "$HOSPITAL/#", 201)
            uriMatcher.addURI(AUTHORITY, OBITOS, 300)
            uriMatcher.addURI(AUTHORITY, "$OBITOS/#", 301)


            return uriMatcher
        }
    }

}