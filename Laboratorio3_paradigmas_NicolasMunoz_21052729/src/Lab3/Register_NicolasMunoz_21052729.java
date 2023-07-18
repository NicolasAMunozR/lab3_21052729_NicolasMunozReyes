package Lab3;

public class Register_NicolasMunoz_21052729 implements Interface_Register_NicolasMunoz_21052729{
    private final String usuario;
    /**
     * Register_NicolasMunoz_21052729 constructor
     * @param usuario: String;
     */
    public Register_NicolasMunoz_21052729(String usuario) {
        this.usuario = usuario;
    }
    /**
     * toString: String
     * @return: String;
     */
    @Override
    public String toString() {
        return "Usuario = (" +
                "Nombre = " + usuario +
                ')';
    }
    /**
     * getUsuario capa selectora: String
     * @return: String;
     */
    public String getUsuario() {
        return usuario;
    }
}