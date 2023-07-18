package Lab3;

import java.util.Date;
public class Mkdir_NicolasMunoz_21052729 implements Interface_Mkdir_NicolasMunoz_21052729{
    private Date fechaCreacion;
    private Date fechamodificacion;
    private final String usuario_log;
    private String direccion;
    private String prueba;
    /**
     * getDireccion capa selectora: String
     * @return: String;
     */
    public String getDireccion() {
        return direccion;
    }
    /**
     * setDechaCreacion capa modificadora: void
     * @param fechaCreacion: Date;
     */
    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }
    /**
     * getFechaCreacion capa selectora: Date
     * @return: Date;
     */
    public Date getFechaCreacion() {
        return fechaCreacion;
    }
    /**
     * Mkdir_NicolasMunoz_21052729 constructor
     * @param direccion: String;
     * @param usuario_log: String;
     */
    public Mkdir_NicolasMunoz_21052729(String direccion, String usuario_log) {
        this.direccion = direccion;
        this.fechaCreacion = new Date();
        this.fechamodificacion = new Date();
        this.usuario_log = usuario_log;
    }
    /**
     * getPrueba capa selectora: String
     * @return: String;
     */
    public String getPrueba() {
        return prueba;
    }
    /**
     * getUsuario_log capa selectora: String
     * @return: String;
     */
    public String getUsuario_log() {
        return usuario_log;
    }
    /**
     * setDireccion capa modificadora: void
     * @param direccion: String;
     */
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
    /**
     * setFechamodificacion capa modificadora: void
     * @param fechamodificacion: Date;
     */
    public void setFechamodificacion(Date fechamodificacion) {
        this.fechamodificacion = fechamodificacion;
    }
    /**
     * toString: String
     * @return: String;
     */
    @Override
    public String toString() {
        prueba = "Carpeta = (" +
                direccion +
                ", Fecha de creacion = " + fechaCreacion +
                ", Fecha de ultima modificacion = " + fechamodificacion +
                ", Usuario = " + usuario_log +
                ")\n";
        return prueba;
    }
}