package Lab3;

public class PapeleraFolder_NicolasMunoz_21052729 implements Interface_PapeleraFolder_NicolasMunoz_21052729{
    private final Folder_NicolasMunoz_21052729 elemento;
    /**
     * getElemento capa selectora: Folder_NicolasMunoz_21052729
     * @return: Folder_NicolasMunoz_21052729;
     */
    public Folder_NicolasMunoz_21052729 getElemento() {
        return elemento;
    }
    /**
     * PapeleraFolder_NicolasMunoz_21052729 constructor
     * @param elemento: Folder_NicolasMunoz_21052729;
     */
    public PapeleraFolder_NicolasMunoz_21052729(Folder_NicolasMunoz_21052729 elemento) {
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