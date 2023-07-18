package Lab3;

public class Login_NicolasMunoz_21052729 implements Interface_Login_NicolasMunoz_21052729{
    private final String nombre;
    /**
     * Login_NicolasMunoz_21052729 constructor
     * @param user_login: String;
     */
    public Login_NicolasMunoz_21052729(String user_login) {
       this.nombre = user_login;
    }
    /**
     * toString: String
     * @return: String;
     */
    @Override
    public String toString() {
        return "Ususario logeado = (" +
                nombre + ')';
    }
    /**
     * getnombre capa selectora: String
     * @return: String;
     */
    public String getnombre() {
        return nombre;
    }
}