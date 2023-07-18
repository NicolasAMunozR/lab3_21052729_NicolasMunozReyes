package Lab3;

import java.util.Date;

public class Folder_NicolasMunoz_21052729 extends Mkdir_NicolasMunoz_21052729 {
    private File_NicolasMunoz_21052729 nombre;
    private Date fechaCreacion;
    private Date fechamodificacion;
    private String prueba;
    /**
     * setNombre capa modificadora: void
     * @param nombre: String;
     */
    public void setNombre(File_NicolasMunoz_21052729 nombre) {
        this.nombre = nombre;
    }
    /**
     * Folder_NicolasMunoz_21052729 constructor
     * @param nombre: File_NicolasMunoz_21052729;
     * @param usuario_log: String;
     * @param direccion: String;
     */
    public Folder_NicolasMunoz_21052729(File_NicolasMunoz_21052729 nombre, String usuario_log, String direccion) {
        super(direccion, usuario_log);
        this.fechaCreacion = new Date();
        this.fechamodificacion = new Date();
        this.nombre = nombre;
    }
    /**
     * setFechaCreacion capa modificadora: void
     * @param fechaCreacion: Date;
     */
    @Override
    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }
    /**
     * setFechamodificadora capa modificadora: void
     * @param fechamodificacion: Date;
     */
    @Override
    public void setFechamodificacion(Date fechamodificacion) {
        this.fechamodificacion = fechamodificacion;
    }
    /**
     * getNombre capa selectora:
     * @return:
     */
    public File_NicolasMunoz_21052729 getNombre() {
        return nombre;
    }
    /**
     * toString: String
     * @return: String;
     */
    @Override
    public String toString() {
        prueba ="Carpeta = (" +
                getDireccion() + ", " + nombre +
                ", Fecha de creacion = " + fechaCreacion +
                ", Fecha de ultima modificacion = " + fechamodificacion +
                ", Usuario = " + getUsuario_log() +
                ")\n";
        return prueba;
    }
    /**
     * getPrueba capa selectora: String
     * @return: String;
     */
    public String getPrueba() {
        return prueba;
    }
}