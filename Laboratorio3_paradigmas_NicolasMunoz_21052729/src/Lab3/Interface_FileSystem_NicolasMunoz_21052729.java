package Lab3;

import java.util.*;

public interface Interface_FileSystem_NicolasMunoz_21052729 {
    Date getFechaCreacion();
    void addDrive(String letra, String name, int capacidad);
    void register(String usuario);
    void login(String user_login);
    void logout();
    void switchdrive(String letra_ruta);
    void mkdir(String nombre_carpeta);
    void cd(String parametro);
    List<String> filtrarPorCadena(List<String> listaOriginal, String cadenaFiltro);
    int contarSlashes(String direccion);
    void addFile(File_NicolasMunoz_21052729 nombre);
    void del(String condicion);
    void copy(String copiar, String ruta);
    void move(String mover, String ruta);
    void ren(String antiguo, String nuevo);
    void dir(List<String> condicion);
    void ordenarPorFecha(List<String> carpetas);
    String obtenerFechaCreacion(String carpeta);
    void format(String letra, String nuevo);
    void encrypt(String password, String elemnto_encriptar);
    void encry_carpeta(String directorio, String password, String elemnto_encriptar, Mkdir_NicolasMunoz_21052729 carpeta_trabajar);
    void encry_archivo(String directorio, String password, String elemnto_encriptar, Folder_NicolasMunoz_21052729 carpeta_trabajar);
    void decrypt(String password, String elemento_desencriptar);
    void decry_carpeta(String directorio, String password, String elemnto_desencriptar, Mkdir_NicolasMunoz_21052729 carpeta_trabajar);
    void decry_archivo(String directorio, String password, String elemnto_desencriptar, Folder_NicolasMunoz_21052729 carpeta_trabajar);
    void grep(String palabra, String lugar);
    void viewtrash();
    void restore(String condicion);
    String toString();
}
