import java.util.Arrays;
/**
 * La clase encapsula en un array
 * una lista de numeros
 * 
 * @author - Eunate Ibarrola Santesteban
 * 
 */

public class ListaNumeros 
{
    public static final int TAM_LISTA = 16;
    private int[] numeros;  
    private int pos;  

    /**
     * Constructor de la clase ListaNumeros 
     * Crea e inicializa adecuadamente los
     * atributos
     * 
     * @param n el tama�o m�ximo del array
     */
    public ListaNumeros(int n) 
    {
        if (n > TAM_LISTA) {
            throw new IllegalArgumentException("Valor no permitido para tama�o lista");
        }
        else {
            numeros = new int [n];
        }
        pos = 0;
    }

    /**
     * @param numero el valor que se a�ade al final de numeros. No se hace nada si
     *               el array est� completo o ya est� el elemento
     * @return true si se ha podido a�adir, false en otro caso
     * 
     * asumimos que numero es >= 0 (no hay que comprobarlo)
     */
    public boolean addElemento(int numero)
    {
        if (!estaCompleta() && !estaElemento(numero)) {
            numeros[pos] = numero;
            pos++;
            return true;
        }
        return false;
    }

    /**
     * devuelve true si numeros est� completo, false en otro caso Hazlo sin if
     */
    public boolean estaCompleta()
    {
        return pos == numeros.length;
    }

    /**
     * devuelve true si la lista est� vac�a, false en otro caso. Hazlo sin if
     */
    public boolean estaVacia() 
    {
        return pos == 0;
    }

    /**
     * devuelve el n� de elementos realmente guardados en la lista
     */
    public int getTotalNumeros()
    {
        return pos;
    }

    /**
     * Vac�a la lista
     */
    public void vaciarLista() 
    {
        for (int i = 0; i < pos; i++) {
            numeros[i] = 0;
            pos = 0;
        }
    }

    /**
     * @param numero el valor a buscar
     * @return true si se encuentra, false en otro caso
     */
    public boolean estaElemento(int numero) 
    {
        int aux = Arrays.binarySearch(numeros, numero);
        if (aux > 0 && aux < pos) {
            return true;
        }
        return false;
    }

    /**
     * Representaci�n textual de la lista de la forma indicada  (ver enunciado)
     * Si numeros = {14, 8, 13, 9, 11, 5, 3, 10, 7, 1}
     *  devuelve | 14 | 8 | 13 | 9 | 11 | 5 | 3 | 10 | 7 | 1 |
     * 
     * Si la lista est� vac�a devuelve | |
     */
    public String toString() 
    {
        String str = "";
        if (estaVacia()) {
            str += " | | ";
        } else {
            for (int i = 0; i < pos; i++) {
                    str += numeros[i] + " | ";
            }
        }
        return " | " + str;
    }

    /**
     * Mostrar en pantalla la lista
     */
    public void escribirLista() 
    {
        System.out.println(this.toString());
    }

    /**
     * a partir de un array de pares contador/valor crea y devuelve
     * un nuevo array resultado de expandir estos pares contador/valor
     *  
     *   Si numeros =  {3, 8, 4, 2, 0, 42, 5, 1}
     *                  |  |  |  |  |   |  |  | 
     *                  +--+  +--+  +---+  +--+ 
     *                  par    par    par   par 
     * 
     *  se devuelve: {8, 8, 8, 2, 2, 2, 2, 1, 1, 1, 1, 1}
     * (ver detalles en el enunciado)
     */
    public int[] expandir() {
        int [] resultado;
        if(esImpar(numeros.length)) {
            throw new RuntimeException("N� impar de elementos en el array, a�ada uno m�s");
        } else {
            int contadorDeNumeros = 0;
            for (int i = 0; i < pos; i+= 2) {
                contadorDeNumeros += numeros[i];
            }
            resultado = new int [contadorDeNumeros];
            int nuevaPos = 0;
            for (int j = 0; j < pos; j += 2) {
                for (int k = 0; k < numeros[j]; k++) {
                    resultado[nuevaPos] = numeros[j + 1];
                    nuevaPos++;
                }
            }
        }
        return resultado;
    }

    /**
     * @param valor el n� a analizar
     * @return true si valor es impar, false en otro caso
     */
    private static boolean esImpar(int valor) {
        return valor % 2 != 0;
    }

    /**
     *  Modifica la lista reorganizando los valores pares e impares, los pares se
     *  colocan al principio y los impares al final. Se mantiene el orden de ambos
     *  
     *  Se har� recorriendo una sola vez la lista y sin  usar ning�n otro array auxiliar
     * 
     *  Si numeros = {3, 7, 4, 9, 2, 5, 8, 11, 13} 
     *  despu�s de reorganizarParesImpares() quedar�a {4, 2, 8, 3, 7, 9, 5, 11, 13}
     */
    public void reorganizarParesImpares() {
        for (int i = pos; i <= 0; i--) {
            if (!esImpar(numeros[i]) && numeros[i] != pos - 1) {
                int aux = numeros[i];
                System.arraycopy(numeros, numeros[i + 1], numeros, i, pos - aux - 1);
            }
        }
    }

    /**
     *  Usando m�todos de la clase Arrays haz una copia 
     *  de numeros al tama�o indicado por su longitud l�gica
     *  Ordena esta copia
     *  Crea y devuelve un nuevo objeto ListaNumeros 
     *  que incluya los elementos del array ordenado
     */
    public ListaNumeros nuevaLista() {
        int [] array = Arrays.copyOf(numeros, pos);
        Arrays.sort(array);
        ListaNumeros lista = new ListaNumeros (array.length);
        for (int i = 0; i < pos; i++) {
            lista.addElemento(array[i]);
        }
        return lista;
    }

    /**
     * devuelve un array de 2 dimensiones de 4 filas y 4 columnas  
     * y guarda en este array los elementos de numeros tal como indica el enunciado
     * 
     *  Si numeros = {3, 7, 4, 9, 2, 5, 8, 11, 13}
     *  el nuevo array tendr� { {3, 7, 4, 9},
     *                          {2, 5, 8, 11} ,
     *                          {13, 0, 0, 0} ,
     *                          {0, 0, 0, 0} }
     * 
     */
    public int[][] toArray2D() 
    {
        int [][] array = new int [4][4];
        int aux = 0;
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length; j++) {
                array[i][j] = numeros[aux];
                aux++;
            }
        }
        return array;
    }

    /**
     * Punto de entrada a la aplicaci�n
     * Contiene c�digo para probar los m�todos de ListaNumeros
     */
    public static void main(String[] args) 
    {
        ListaNumeros numeros = new ListaNumeros(10);
        numeros.addElemento(3);
        numeros.addElemento(7);
        numeros.addElemento(4);
        numeros.addElemento(9);
        numeros.addElemento(2);
        numeros.addElemento(5);
        numeros.addElemento(8);
        numeros.addElemento(11);

        System.out.println("Original: " + numeros.toString());
        int[] expandido = numeros.expandir();
        System.out.println("Expandido: " + Arrays.toString(expandido));
        int[][] array2D = numeros.toArray2D();
        System.out.println("Array 2D: " + Arrays.toString(array2D));

    }
}