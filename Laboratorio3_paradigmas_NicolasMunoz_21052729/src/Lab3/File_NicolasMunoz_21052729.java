package Lab3;

public class File_NicolasMunoz_21052729 implements  Interface_File_NicolasMunoz_21052729{
    private String nombre;
    private String extension;
    private String contenido;
    /**
     * File_NicolasMunoz_21052729 constructor
     * @param nombre: String;
     * @param extension: String;
     * @param contenido: String;
     */
    public File_NicolasMunoz_21052729(String nombre, String extension, String contenido) {
        this.nombre = nombre;
        this.extension = extension;
        this.contenido = contenido;
    }
    /**
     * getContenido capa selectora: String
     * @return: String;
     */
    public String getContenido() {
        return contenido;
    }
    /**
     * getNombre capa selectora: String
     * @return: String;
     */
    public String getNombre() {
        return nombre;
    }
    /**
     * setNombre capa modificadora: void
     * @param nombre: String;
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    /**
     * setContenido capa modificadora: void
     * @param contenido: String;
     */
    public void setContenido(String contenido) {
        this.contenido = contenido;
    }
    /**
     * setExtension capa modificadora: void
     * @param extension: String;
     */
    public void setExtension(String extension) {
        this.extension = extension;
    }
    /**
     * getExtension capa selectora: String
     * @return: String;
     */
    public String getExtension() {
        return extension;
    }
    /**
     * toString: String
     * @return: String;
     */
    @Override
    public String toString() {
        return "Archivo = (" +
                "Nombre = " + nombre +
                ", extension = " + extension +
                ", contenido = " + contenido +
                ')';
    }
}