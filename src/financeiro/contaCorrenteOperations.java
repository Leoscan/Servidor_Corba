package financeiro;


/**
* financeiro/contaCorrenteOperations.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from financeiro.idl
* Sexta-feira, 19 de Abril de 2024 20h30min59s BRT
*/

public interface contaCorrenteOperations 
{
  boolean credita (float valor);
  boolean debita (float valor);
  String getSituacao ();
  boolean setSituacao (String msg);
} // interface contaCorrenteOperations