package pt.ipg.trabalhofinal

class DadosApp {
    companion object{
        lateinit var activity: MainActivity
        lateinit var fragmentListaPessoas: ListaPessoasFragment
        var pessoaSelecionada: Pessoas? = null
        lateinit var fragmentListaHospital: ListaHospitalFragment
        var hospitalSelecionado: Hospital? = null
        lateinit var fragmentListaObitos: ListaObitosFragment
        var obitoSelecionado: Obitos? = null
    }
}