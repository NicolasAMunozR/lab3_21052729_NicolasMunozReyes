package Lab3;

public class Drive_NicolasMunoz_21052729 implements Interface_Drive_NicolasMunoz_21052729{
    private final String letra;
    private String name;
    private final int capacidad;

    /**
     * Drive_NicolasMunoz_21052729 constructor
     * @param letra: String;
     * @param name: String;
     * @param capacidad: int;
     */
    public Drive_NicolasMunoz_21052729(String letra, String name, int capacidad){
        this.letra = letra;
        this.name = name;
        this.capacidad = capacidad;
    }

    /**
     * setName capa modificadora: void
     * @param name: String;
     */
    public void setName(String name) {
        this.name = name;
    }
    /**
     * toString: String
     * @return: String;
     */
    @Override
    public String toString() {
        return "Drive = (" +
                "Letra = " + letra +
                ", Nombre = " + name +
                " Capacidad = " + capacidad +
                ')';
    }

    /**
     * getletra capa selectora: String
     * @return: String;
     */
    public String getletra() {
        return letra;
    }
}