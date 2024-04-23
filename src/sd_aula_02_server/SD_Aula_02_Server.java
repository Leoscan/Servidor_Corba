/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package sd_aula_02_server;

import financeiro.contaCorrente;
import financeiro.contaCorrenteHelper;
import java.util.Properties;
import org.omg.CORBA.ORB;
import org.omg.CosNaming.NameComponent;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;
import org.omg.PortableServer.POA;
import org.omg.PortableServer.POAHelper;

public class SD_Aula_02_Server {

    public static void main(String[] args) {
        Properties props = System.getProperties();
        props.put("org.omg.CORBA.ORBInitialHost", "localhost");
        props.put("org.omg.CORBA.ORBInitialPort", "9876");
        try {
            //Criando o gerenciador ORB - Object Request Broker
            ORB orb = ORB.init(args, null); 

	    //Instanciando o servidor POA - Portable Object Adapter
            POA rootpoa = POAHelper.narrow(orb.resolve_initial_references("RootPOA"));  
	    rootpoa.the_POAManager().activate(); 

            // Instanciar o objeto Servente
            contaCorrenteImpl oContaCorrenteServant = new contaCorrenteImpl();
	    oContaCorrenteServant.setORB(orb);  
            
            //Apenas imprimir a refer�ncia do objeto
            System.out.println(oContaCorrenteServant.toString());

	    org.omg.CORBA.Object ref = rootpoa.servant_to_reference(oContaCorrenteServant);
	    contaCorrente contaCorrenteRef = contaCorrenteHelper.narrow(ref);
            
            //Registrando o servant criado anteriormente no servidor de objetos - CORBA
	    org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");
	    NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);

	    NameComponent path[] = ncRef.to_name("contaCorrente");
	    ncRef.rebind(path, contaCorrenteRef);
	    
	    orb.run();
	}
	catch(Exception e) {
	    System.out.println(e);
	    e.printStackTrace(System.out);
	} 
    }    
}
