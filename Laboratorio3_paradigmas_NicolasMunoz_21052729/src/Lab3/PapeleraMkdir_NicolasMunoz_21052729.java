package Lab3;

public class PapeleraMkdir_NicolasMunoz_21052729 implements Interface_PapeleraMkdir_NicolasMunoz_21052729{
    private final Mkdir_NicolasMunoz_21052729 elemento;
    /**
     * getElemento capa selectora: Mkdir_NicolasMunoz_21052729
     * @return: Mkdir_NicolasMunoz_21052729;
     */
    public Mkdir_NicolasMunoz_21052729 getElemento() {
        return elemento;
    }
    /**
     * PapeleraMkdir_NicolasMunoz_21052729 constructor: Mkdir_NicolasMunoz_21052729
     * @param elemento: Mkdir_NicolasMunoz_21052729;
     */
    public PapeleraMkdir_NicolasMunoz_21052729(Mkdir_NicolasMunoz_21052729 elemento) {
        this.elemento = elemento;
    }
    /**
     * toString: String
     * @return: String;
     */
    @Override
    public String toString() {
        return "papelera = (" +
                elemento +
                ")";
    }
}