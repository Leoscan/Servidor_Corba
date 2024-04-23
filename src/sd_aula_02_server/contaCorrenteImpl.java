package sd_aula_02_server;
import financeiro._contaCorrentePOA;
import org.omg.CORBA.ORB;

class contaCorrenteImpl extends _contaCorrentePOA {

    private ORB orb;
    private float saldo;

    void setORB(ORB orb_val) {
        orb = orb_val;
        registraOperacao("Inicializando ORB do Leonardo", 0);
    }

    private void registraOperacao(String operacao, float valor) {
        String txt = operacao + " -> " + String.valueOf(valor) + 
              ". Saldo: " + String.valueOf(saldo);
        
        System.out.println(txt);
    }

    @Override
    public boolean credita(float valor) {
        saldo = saldo + valor;
        registraOperacao("Creditou", valor);
        return true;
    }

    @Override
    public boolean debita(float valor) {
        saldo = saldo + valor;
        registraOperacao("Debitou", valor);
        return true;
    }

    @Override
    public String getSituacao() {
        return "Pendente";
    }

    @Override
    public boolean setSituacao(String msg) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
