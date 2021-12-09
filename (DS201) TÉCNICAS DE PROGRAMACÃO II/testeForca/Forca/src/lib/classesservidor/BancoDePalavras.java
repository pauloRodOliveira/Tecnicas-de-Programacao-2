package lib.classesservidor;

public class BancoDePalavras
{
    private static String[] palavras =
            {
                    "JAVA",
            };

    public static Palavra getPalavraSorteada ()
    {
        Palavra palavra = null;

        try
        {
            palavra =
                    new Palavra (BancoDePalavras.palavras[
                            (int)(Math.random() * BancoDePalavras.palavras.length)]);
        }
        catch (Exception e)
        {}

        return palavra;
    }
}

