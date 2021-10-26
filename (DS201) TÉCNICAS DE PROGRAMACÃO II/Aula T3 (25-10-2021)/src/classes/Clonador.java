package classes;

import java.lang.reflect.*;

public class Clonador<X> {
    public X clone(X x){
     /*
     * assim obtemos a classe da instancia
     * no objeto X, que, conforme sabemos, é a classe X,
     * e a armazenamos no objeto chamado classe*/
        Class<?> classe = x.getClass();

        // null porque chamaremos um metodo sem parametros
        Class<?>[] tpsPArmsForms = null;

        Method metodo = null;
        try{
            metodo = classe.getMethod("clone", tpsPArmsForms);
        }catch (NoSuchMethodException erro){}

        Object[] parmsReais = null;

        X ret = null;
        try{
            ret = (X)metodo.invoke(x, parmsReais);
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        // ret = (X)x.clone(); //não pode ser utilizada, por isso a substituímos pelas linhas de cima

        return ret;
    }
}
