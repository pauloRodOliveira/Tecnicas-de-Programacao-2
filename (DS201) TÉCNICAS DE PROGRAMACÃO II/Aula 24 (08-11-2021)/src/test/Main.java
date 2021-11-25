package test;

import java.util.ArrayList;

public class Main {
        /* ****** ETAPA 1 ****** */
        public static final String PORTA_PADRAO = "3000";
        public static void main (String[] args)
        {
            if (args.length>1) // caso a quantidades de elementos em args seja maior que um {}
            {
                System.err.println ("Uso esperado: java Servidor [PORTA]\n");
                return;
            }

            String porta = Servidor.PORTA_PADRAO; // já recebe o servidor padrão

            if (args.length==1)
                porta = args[0];
                //caso a quantidade de ele. em args
                // for == a 1, vai adotar o elemento da posição 0

            /* ****** ETAPA 2 ****** */
            ArrayList<Parceiro> usuarios =
                    new ArrayList<Parceiro> ();

            /* ****** ETAPA 3 ****** */
            AceitadoraDeConexao aceitadoraDeConexao=null;
            try
            {
                aceitadoraDeConexao =
                        new AceitadoraDeConexao(porta, usuarios);
                aceitadoraDeConexao.start();
            }
            catch (Exception erro)
            {
                System.err.println ("Escolha uma porta apropriada e liberada para uso!\n");
                return;
            }

            for(;;)
            {
                System.out.println ("O servidor esta ativo! Para desativa-lo,");
                System.out.println ("use o comando \"desativar\"\n");
                System.out.print   ("> ");

                String comando=null;
                try
                {
                    comando = Teclado.getUmString();
                }
                catch (Exception erro)
                {}

                if (comando.toLowerCase().equals("desativar"))
                {
                    synchronized (usuarios)
                    {
                        ComunicadoDeDesligamento comunicadoDeDesligamento =
                                new ComunicadoDeDesligamento();

                        for (Parceiro usuario:usuarios)
                        {
                            try
                            {
                                usuario.receba (comunicadoDeDesligamento);
                                usuario.adeus  ();
                            }
                            catch (Exception erro)
                            {}
                        }
                    }

                    System.out.println ("O servidor foi desativado!\n");
                    System.exit(0);
                }
                else
                    System.err.println ("Comando invalido!\n");
            }
        }
    }
