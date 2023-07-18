package Lab3;

public class Switchdrive_NicolasMunoz_21052729 implements Interface_Switchdrive_NicolasMunoz_21052729{
    private final String letra;
    /**
     * Switchdrive_NicolasMunoz_21052729 constructor
     * @param letra: String;
     */
    public Switchdrive_NicolasMunoz_21052729(String letra) {
        this.letra = letra;
    }
    /**
     * toString: String
     * @return: String;
     */
    @Override
    public String toString() {
        return letra;
    }
}