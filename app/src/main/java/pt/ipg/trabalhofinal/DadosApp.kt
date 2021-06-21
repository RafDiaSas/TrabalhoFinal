package pt.ipg.trabalhofinal

class DadosApp {
    companion object{
        lateinit var activity: MainActivity
        lateinit var fragmentListaPessoas: ListaPessoasFragment
        var pessoaSelecionada: Pessoas? = null
    }
}