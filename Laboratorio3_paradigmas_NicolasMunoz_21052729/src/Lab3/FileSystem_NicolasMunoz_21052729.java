package Lab3;
import java.util.*;
public class FileSystem_NicolasMunoz_21052729 implements Interface_FileSystem_NicolasMunoz_21052729{
    private static String nombre;
    private Date fechaCreacion;
    private final List<Drive_NicolasMunoz_21052729> drives;
    private final List<Register_NicolasMunoz_21052729> registers;
    private final List<Login_NicolasMunoz_21052729> logins;
    private final List<Switchdrive_NicolasMunoz_21052729> switchdrives;
    private final List<Mkdir_NicolasMunoz_21052729> mkdirs;
    private final List<Folder_NicolasMunoz_21052729> folders;
    private final List<PapeleraFolder_NicolasMunoz_21052729> papelerasfolders;
    private final List<PapeleraMkdir_NicolasMunoz_21052729> papelerasmkdirs;
    private Date fechaswish;
    /**
     * getNombre capa selectora: String
     * @return: String;
     */
    public static String getNombre() {
        return nombre;
    }
    /**
     * getFechaCreacion capa selectora: Date
     * @return: Date;
     */
    public Date getFechaCreacion() {
        return fechaCreacion;
    }
    /**
     * FileSystem_NicolasMunoz_21052729 constructor
     * @param nombre: String;
     */
    public FileSystem_NicolasMunoz_21052729(String nombre) {
        FileSystem_NicolasMunoz_21052729.nombre = nombre;
        if(!Objects.equals(nombre, "")) {
            this.fechaCreacion = new Date();
        }
        this.drives = new ArrayList<>();
        this.registers = new ArrayList<>();
        this.logins = new ArrayList<>();
        this.switchdrives = new ArrayList<>();
        this.mkdirs = new ArrayList<>();
        this.folders = new ArrayList<>();
        this.papelerasfolders = new ArrayList<>();
        this.papelerasmkdirs = new ArrayList<>();
    }
    /**
     * addDrive: void
     * @param letra: String;
     * @param name: String;
     * @param capacidad: int;
     */
    public void addDrive(String letra, String name, int capacidad){
        var drive = new Drive_NicolasMunoz_21052729(letra, name, capacidad);
        List<String> currentletra = new ArrayList<>();
        for (Drive_NicolasMunoz_21052729 disco : drives){
            currentletra.add(disco.getletra());
        }
        if (!currentletra.contains(letra)) {
            drives.add(drive);
        }
    }
    /**
     * register: void
     * @param usuario: String;
     */
    public void register(String usuario){
        var user = new Register_NicolasMunoz_21052729(usuario);
        List<String> currentuser = new ArrayList<>();
        for (Register_NicolasMunoz_21052729 disco : registers){
            currentuser.add(disco.getUsuario());
        }
        if (drives.size() != 0) {
            if(!Objects.equals(FileSystem_NicolasMunoz_21052729.getNombre(), "")) {
                if (!currentuser.contains(usuario)) {
                    registers.add(user);
                }
            }
        }
        else {
            System.out.println("Ingrese unidad drive \n");
        }
    }
    /**
     * login: void
     * @param user_login: String;
     */
    public void login(String user_login){
        var log = new Login_NicolasMunoz_21052729(user_login);
        List<String> currentu = new ArrayList<>();
        for (Register_NicolasMunoz_21052729 disco : registers){
            currentu.add(disco.getUsuario());
        }
        if (currentu.contains(user_login)) {
            if (logins.size() == 0){
                logins.add(log);
            }
        }
        else {
            System.out.println("Usuario no encontrado \n");
        }
    }
    /**
     * logout: void
     */
    public void logout(){
        if (logins.size() != 0){
            logins.clear();
            switchdrives.clear();
        }
        else {
            System.out.println("No existe usuario logeado \n");
        }
    }
    /**
     * switchdrive: void
     * @param letra_ruta: String;
     */
    public void switchdrive(String letra_ruta){
        var a = "Direccion = " + letra_ruta + ":/";
        var ruta = new Switchdrive_NicolasMunoz_21052729(a);
        List<String> currentruta = new ArrayList<>();
        for (Drive_NicolasMunoz_21052729 disco : drives){
            currentruta.add(disco.getletra());
        }
        if (currentruta.contains(letra_ruta)) {
            if (logins.size() != 0){
                if (switchdrives.size() == 0) {
                    switchdrives.add(ruta);
                    fechaswish = new Date();
                }
                switchdrives.set(0,ruta);
                fechaswish = new Date();
            }
        }
    }
    /**
     * mkdir: void
     * @param nombre_carpeta: String;
     */
    public void mkdir(String nombre_carpeta){
        List<String> currentrut = new ArrayList<>();
        for (Login_NicolasMunoz_21052729 dis : logins){
            currentrut.add(dis.getnombre());
        }
        List<String> currentruto = new ArrayList<>();
        for (Mkdir_NicolasMunoz_21052729 disc : mkdirs){
            currentruto.add(disc.getDireccion());
        }
        if(switchdrives.size() != 0) {
            String ruta = String.valueOf(switchdrives.get(0));
            var direccion = ruta + nombre_carpeta + "/";
            if (!currentruto.contains(direccion)) {
                var mk = new Mkdir_NicolasMunoz_21052729(direccion, currentrut.get(0));
                mkdirs.add(mk);
            }
            else {
                System.out.println("Ya existe una carpeta con este noombre \n");
            }
        }
    }
    /**
     * cd: void
     * @param parametro: String;
     */
    public void cd(String parametro){
        String direcc = String.valueOf(switchdrives.get(0));
        String[] partes = direcc.split("=");
        String resultado = partes[1].trim();
        if(Objects.equals(parametro, "..")){
            String dire = String.valueOf(switchdrives.get(0));
            int indiceUltimo = dire.lastIndexOf("/");
            String sub = dire.substring(0, indiceUltimo-14);
            if(Objects.equals(sub, "")) {
                System.out.println("No es posible hacer eso \n");
            }
            else{
                String direccion = String.valueOf(switchdrives.get(0));
                int indiceUltimoSlash = direccion.lastIndexOf("/");
                String subcadena = direccion.substring(0, indiceUltimoSlash);
                int indiceUltimoSlas = subcadena.lastIndexOf("/");
                String subcadena1 = direccion.substring(0, indiceUltimoSlas + 1);
                var ruta = new Switchdrive_NicolasMunoz_21052729(subcadena1);
                switchdrives.clear();
                switchdrives.add(ruta);
            }
        }
        else if (Objects.equals(parametro, ".") || Objects.equals(parametro, "./") || Objects.equals(parametro, "././././") || Objects.equals(parametro, "../"+resultado)){
            String direccion = String.valueOf(switchdrives.get(0));
            System.out.println("Usted esta en: " + direccion);
        }
        else if (Objects.equals(parametro, "/")) {
            String direccio = String.valueOf(switchdrives.get(0));
            String direccion = direccio;
            int numero = 0;
            while (direccio.lastIndexOf("/") >= 0) {
                int indiceUltimoSlash = direccio.lastIndexOf("/");
                direccio = direccio.substring(0, indiceUltimoSlash);
                numero = numero + 1;
            }
            numero = numero - 1;
            if(Objects.equals(numero, 0)){
                var ruta = new Switchdrive_NicolasMunoz_21052729(direccion);
                switchdrives.set(0, ruta);
            }
            else {
                do {
                    int indiceUltimoSlash = direccion.lastIndexOf("/");
                    direccion = direccion.substring(0, indiceUltimoSlash);
                    numero = numero - 1;
                } while (!Objects.equals(numero, 0));
                int indiceUltimoSlash = direccion.lastIndexOf("/");
                direccion = direccion.substring(0, indiceUltimoSlash + 1);
                var ruta = new Switchdrive_NicolasMunoz_21052729(direccion);
                switchdrives.clear();
                switchdrives.add(ruta);
            }
        }
        else {
            String directorio = String.valueOf(switchdrives.get(0));
            String direccion = parametro;
            direccion = direccion.replace("./", "");
            List<String> currens = new ArrayList<>();
            for (Mkdir_NicolasMunoz_21052729 disco : mkdirs){
                currens.add(disco.getDireccion());
            }
            List<String> listaFiltrada1 = filtrarPorCadena(currens, directorio);
            if (listaFiltrada1.size() != 0 ) {
                List<String> listaFiltrada = filtrarPorCadena(listaFiltrada1, direccion);
                int cantidadSlashes = contarSlashes(directorio);
                if (listaFiltrada.size() != 0){
                    if(cantidadSlashes >= 1){
                        int indiceUltimoSlash = directorio.lastIndexOf("/");
                        String subcadena = directorio.substring(0, indiceUltimoSlash);
                        int ultimoSlash = subcadena.lastIndexOf("/");
                        var dire = subcadena.substring(ultimoSlash + 1);
                        Switchdrive_NicolasMunoz_21052729 ruta;
                        if(dire.equals(direccion)) {
                            ruta = new Switchdrive_NicolasMunoz_21052729(listaFiltrada.get(1));
                        }
                        else {
                            ruta = new Switchdrive_NicolasMunoz_21052729(listaFiltrada.get(0));
                        }
                        switchdrives.set(0, ruta);
                    }
                    else {
                        var ruta = new Switchdrive_NicolasMunoz_21052729(listaFiltrada.get(0));
                        switchdrives.set(0, ruta);
                    }
                }
            }
            else {
                System.out.println("No existe ninguna carpeta con ese nombre \n");
            }
        }
    }
    /**
     * filtrarPorCadena: List<String>
     * @param listaOriginal: List<String>
     * @param cadenaFiltro: String;
     * @return: List<String>;
     */
    public List<String> filtrarPorCadena(List<String> listaOriginal, String cadenaFiltro) {
        List<String> listaFiltrada = new ArrayList<>();
        for (String direccion : listaOriginal) {
            if (direccion.contains(cadenaFiltro)) {
                listaFiltrada.add(direccion);
            }
        }
        return listaFiltrada;
    }
    /**
     * contarSlashes: int
     * @param direccion: String;
     * @return: List<String>
     */
    public int contarSlashes(String direccion) {
        String[] partes = direccion.split("/");
        return partes.length - 1;
    }
    /**
     * addFile: void
     * @param nombre: File_NicolasMunoz_21052729;
     */
    public void addFile(File_NicolasMunoz_21052729 nombre) {
        String direccion = String.valueOf(switchdrives.get(0));
        List<String> currentrut = new ArrayList<>();
        for (Login_NicolasMunoz_21052729 dis : logins){
            currentrut.add(dis.getnombre());
        }
        List<File_NicolasMunoz_21052729> currentruta = new ArrayList<>();
        for (Folder_NicolasMunoz_21052729 disc : folders){
            currentruta.add(disc.getNombre());
        }
        List<String> currentrutas = new ArrayList<>();
        for (File_NicolasMunoz_21052729 disc : currentruta){
            currentrutas.add(disc.getNombre());
        }
        var name = nombre.getNombre();
        if (!currentrutas.contains(name)) {
            if (direccion.length() == 15) {
                var contenido = new Folder_NicolasMunoz_21052729(nombre, currentrut.get(0), direccion);
                folders.add(contenido);
                contenido.setFechamodificacion(new Date());
                Date fecha = fechaswish;
                contenido.setFechaCreacion(fecha);
            }
            else {
                int i = 0;
                while (i < mkdirs.size()) {
                    var carpeta = mkdirs.get(i);
                    if (Objects.equals(carpeta.getDireccion(), direccion)) {
                        var contenido = new Folder_NicolasMunoz_21052729(nombre, currentrut.get(0), direccion);
                        folders.add(contenido);
                        contenido.setFechamodificacion(new Date());
                        var fecha = carpeta.getFechaCreacion();
                        contenido.setFechaCreacion(fecha);
                    }
                    i = i + 1;
                }
            }
        }else {
            int i = 0;
            while (i < folders.size()) {
                var carpeta = folders.get(i);
                var archivo = carpeta.getNombre();
                if (Objects.equals(carpeta.getDireccion(), direccion)) {
                    if (Objects.equals(archivo.getNombre(), nombre.getNombre())) {
                        archivo.setExtension(nombre.getExtension());
                        carpeta.setFechamodificacion(new Date());
                        archivo.setContenido(nombre.getContenido());
                    }
                }
                else {
                    if (direccion.length() == 15) {
                        var contenido = new Folder_NicolasMunoz_21052729(nombre, currentrut.get(0), direccion);
                        folders.add(contenido);
                        contenido.setFechamodificacion(new Date());
                        Date fecha = getFechaCreacion();
                        contenido.setFechaCreacion(fecha);
                    }
                    else {
                        int j = 0;
                        while (j < mkdirs.size()) {
                            var carpetas = mkdirs.get(j);
                            if (Objects.equals(carpetas.getDireccion(), direccion)) {
                                var contenido = new Folder_NicolasMunoz_21052729(nombre, currentrut.get(0), direccion);
                                folders.add(contenido);
                                contenido.setFechamodificacion(new Date());
                                var fecha = carpetas.getFechaCreacion();
                                contenido.setFechaCreacion(fecha);
                            }
                            j = j + 1;
                        }
                    }
                }
                i = i + 1;
            }
        }
    }
    /**
     * del: void
     * @param condicion: String;
     */
    public void del(String condicion) {
        int dotIndex = condicion.lastIndexOf(".");
        if (condicion.equals("*.*") || condicion.equals("*")) {
            String directorio = String.valueOf(switchdrives.get(0));
            List<String> s = new ArrayList<>();
            for (Folder_NicolasMunoz_21052729 disco : folders) {
                s.add(disco.getPrueba());
            }
            List<String> listaFiltrada = filtrarPorCadena(s, directorio);
            Iterator<Folder_NicolasMunoz_21052729> iterator = folders.iterator();
            while (iterator.hasNext()) {
                Folder_NicolasMunoz_21052729 f = iterator.next();
                for (String direccion : listaFiltrada) {
                    if (f.getPrueba().equals(direccion)) {
                        var p = new PapeleraFolder_NicolasMunoz_21052729(f);
                        f.setFechamodificacion(new Date());
                        papelerasfolders.add(p);
                        iterator.remove();
                        break;
                    }
                }
            }
        } else if (dotIndex != -1) {
            String result = condicion.substring(0, dotIndex);
             if (result.equals("*")) {
                String direccion = condicion;
                direccion = direccion.replace("*", "");
                direccion = direccion.toLowerCase();
                String directorio = String.valueOf(switchdrives.get(0));
                int i = 0;
                while (i < folders.size()) {
                    var carpeta = folders.get(i);
                    var ruta = carpeta.getDireccion();
                    if (ruta.equals(directorio)) {
                        var extension = carpeta.getNombre().getExtension();
                        if (extension.equals(direccion)) {
                            carpeta.setFechamodificacion(new Date());
                            var p = new PapeleraFolder_NicolasMunoz_21052729(carpeta);
                            papelerasfolders.add(p);
                            folders.remove(folders.get(i));
                            i = i - 1;
                        }
                    }
                    i = i + 1;
                }
            }int dotIndexs = condicion.lastIndexOf("*");
            if (dotIndexs != -1) {
                String resultad = condicion.substring(0, dotIndexs);
                String[] partes = condicion.split("\\.");
                List<String> lista = new ArrayList<>();
                lista.add(partes[0]);
                lista.add("." + partes[1]);
                var extensio = lista.get(1).toLowerCase();
                String directorio = String.valueOf(switchdrives.get(0));
                int i = 0;
                while (i < folders.size()) {
                    var carpeta = folders.get(i);
                    var ruta = carpeta.getDireccion();
                    if (ruta.equals(directorio)) {
                        var extension = carpeta.getNombre().getExtension();
                        var name = carpeta.getNombre().getNombre();
                        if (extension.equals(extensio) && name.startsWith(resultad.trim())) {
                            carpeta.setFechamodificacion(new Date());
                            var p = new PapeleraFolder_NicolasMunoz_21052729(carpeta);
                            papelerasfolders.add(p);
                            folders.remove(folders.get(i));
                            i = i - 1;
                        }
                    }
                    i = i + 1;
                }
             }
            else {
                 String[] partes = condicion.split("\\.");
                 if (partes.length == 2) {
                     List<String> lista = new ArrayList<>();
                     lista.add(partes[0]);
                     lista.add("." + partes[1]);
                     var nombre = lista.get(0);
                     var extensio = lista.get(1).toLowerCase();
                     String directorio = String.valueOf(switchdrives.get(0));
                     int i = 0;
                     while (i < folders.size()) {
                         var carpeta = folders.get(i);
                         var ruta = carpeta.getDireccion();
                         if (ruta.equals(directorio)) {
                             var extension = carpeta.getNombre().getExtension();
                             var name = carpeta.getNombre().getNombre();
                             if (extension.equals(extensio) && name.equals(nombre)) {
                                 carpeta.setFechamodificacion(new Date());
                                 var p = new PapeleraFolder_NicolasMunoz_21052729(carpeta);
                                 papelerasfolders.add(p);
                                 folders.remove(folders.get(i));
                                 i = i - 1;
                             }
                         }
                         i = i + 1;
                     }
                 }
             }
        }
        else {
            String directorio = String.valueOf(switchdrives.get(0));
            List<String> currens = new ArrayList<>();
            for (Mkdir_NicolasMunoz_21052729 disco : mkdirs) {
                currens.add(disco.getPrueba());
            }
            List<String> listaFiltrada = filtrarPorCadena(currens, directorio);
            if (listaFiltrada.size() != 0) {
                List<String> listaFiltrada1 = filtrarPorCadena(listaFiltrada, condicion);
                Iterator<Mkdir_NicolasMunoz_21052729> iterator = mkdirs.iterator();
                while (iterator.hasNext()) {
                    Mkdir_NicolasMunoz_21052729 f = iterator.next();
                    for (String direccion : listaFiltrada1) {
                        if (f.getPrueba().equals(direccion)) {
                            var p = new PapeleraMkdir_NicolasMunoz_21052729(f);
                            f.setFechamodificacion(new Date());
                            papelerasmkdirs.add(p);
                            iterator.remove();
                            break;
                        }
                    }
                }
                List<String> s = new ArrayList<>();
                for (Folder_NicolasMunoz_21052729 disco : folders) {
                    s.add(disco.getPrueba());
                }
                List<String> listaFiltrada2 = filtrarPorCadena(s, directorio);
                if (listaFiltrada2.size() != 0) {
                    List<String> listaFiltrada3 = filtrarPorCadena(listaFiltrada2, condicion);
                    Iterator<Folder_NicolasMunoz_21052729> iterator2 = folders.iterator();
                    while (iterator2.hasNext()) {
                        Folder_NicolasMunoz_21052729 f = iterator2.next();
                        for (String direccion : listaFiltrada3) {
                            if (f.getPrueba().equals(direccion)) {
                                f.setFechamodificacion(new Date());
                                var p = new PapeleraFolder_NicolasMunoz_21052729(f);
                                papelerasfolders.add(p);
                                iterator2.remove();
                                break;
                            }
                        }
                    }
                }
            }
        }
    }
    /**
     * copy: void
     * @param copiar: String;
     * @param ruta: String;
     */
    public void copy(String copiar, String ruta) {
        int dotIndex = copiar.lastIndexOf(".");
        if (dotIndex != -1) {
            String[] partes = copiar.split("\\.");
            List<String> lista = new ArrayList<>();
            lista.add(partes[0]);
            lista.add("." + partes[1]);
            var nombre = lista.get(0);
            var extension = lista.get(1).toLowerCase();
            int i = 0;
            int val = folders.size();
            if(nombre.equals("*")){
                while (i < val) {
                    var carpeta = folders.get(i);
                    var exten = carpeta.getNombre().getExtension();
                    var resultados = carpeta.getDireccion();
                    String[] partessss = resultados.split("=");
                    String resultadosss = partessss[1].trim();
                    String directorio = String.valueOf(switchdrives.get(0));
                    String[] partesss = directorio.split("=");
                    String resultadoss = partesss[1].trim();
                    if (exten.equals(extension) && resultadosss.equals(resultadoss)) {
                        var archivo = carpeta.getNombre();
                        int j = 0;
                        while (j < mkdirs.size()) {
                            var carpeta2 = mkdirs.get(j);
                            var rut = carpeta2.getDireccion();
                            String[] partess = rut.split("=");
                            String resultado = partess[1].trim();
                            var user = carpeta2.getUsuario_log();
                            if (resultado.equals(ruta)) {
                                var contenido = new Folder_NicolasMunoz_21052729(archivo, user, rut);
                                contenido.setFechamodificacion(new Date());
                                var fecha = carpeta2.getFechaCreacion();
                                contenido.setFechaCreacion(fecha);
                                folders.add(contenido);
                            }
                            j = j + 1;
                        }
                    }
                    i = i + 1;
                }
            } else {
                while (i < val) {
                    var carpeta = folders.get(i);
                    var name = carpeta.getNombre().getNombre();
                    var exten = carpeta.getNombre().getExtension();
                    if (name.equals(nombre) && exten.equals(extension)) {
                        var archivo = carpeta.getNombre();
                        int j = 0;
                        while (j < mkdirs.size()) {
                            var carpeta2 = mkdirs.get(j);
                            var rut = carpeta2.getDireccion();
                            String[] partess = rut.split("=");
                            String resultado = partess[1].trim();
                            var user = carpeta2.getUsuario_log();
                            if (resultado.equals(ruta)) {
                                var contenido = new Folder_NicolasMunoz_21052729(archivo, user, rut);
                                contenido.setFechamodificacion(new Date());
                                var fecha = carpeta2.getFechaCreacion();
                                contenido.setFechaCreacion(fecha);
                                folders.add(contenido);
                            }
                            j = j + 1;
                        }
                    }
                    i = i + 1;
                }
            }
        }else if (copiar.endsWith("*")) {
            int indiceSlash = copiar.lastIndexOf("/");
            int indiceAsterisco = copiar.indexOf("*");
            if ((indiceSlash != -1 && indiceAsterisco != -1) && indiceSlash < indiceAsterisco) {
                String subcadena = copiar.substring(0, indiceSlash + 1);
                String resultado = copiar.substring(indiceSlash + 1, indiceAsterisco);
                var nueva_dire = "Direccion = " + ruta;
                List<String> currens = new ArrayList<>();
                for (Mkdir_NicolasMunoz_21052729 disco : mkdirs) {
                    currens.add(disco.getPrueba());
                }
                List<String> listaFiltrada = filtrarPorCadena(currens, nueva_dire);
                if (listaFiltrada.size() != 0) {
                    int i = 0;
                    int val = folders.size();
                    while (i < val) {
                        var carpeta = folders.get(i);
                        var rut = carpeta.getDireccion();
                        var nombre = carpeta.getNombre().getNombre();
                        if (rut.endsWith(subcadena) && nombre.startsWith(resultado)) {
                            var archivo = carpeta.getNombre();
                            var fecha = carpeta.getFechaCreacion();
                            var use = carpeta.getUsuario_log();
                            var fol = new Folder_NicolasMunoz_21052729(archivo, use, nueva_dire);
                            fol.setFechaCreacion(fecha);
                            fol.setFechamodificacion(new Date());
                            folders.add(fol);
                        }
                        i = i + 1;
                    }
                }
            }
        }
        else {
            String directorio = String.valueOf(switchdrives.get(0));
            var directorio1 = directorio + copiar + "/";
            List<String> currentletras = new ArrayList<>();
            for (Mkdir_NicolasMunoz_21052729 disco : mkdirs){
                currentletras.add(disco.getDireccion());
            }
            if (currentletras.contains(directorio1)) {
                int valor = mkdirs.size();
                int i = 0;
                while (i < valor) {
                    var carpeta = mkdirs.get(i);
                    var dire = carpeta.getDireccion();
                    if (dire.contains(directorio1)) {
                        Date fecha = carpeta.getFechaCreacion();
                        var user = carpeta.getUsuario_log();
                        List<String> currentletra = new ArrayList<>();
                        for (Mkdir_NicolasMunoz_21052729 disco : mkdirs) {
                            currentletra.add(disco.getDireccion());
                        }
                        var ruta1 = "Direccion = " + ruta;
                        if (currentletra.contains(ruta1)) {
                            var dire_nueva = dire.replaceFirst(directorio, "");
                            dire_nueva = ruta1 + dire_nueva;
                            if (!currentletra.contains(dire_nueva)) {
                                var mk = new Mkdir_NicolasMunoz_21052729(dire_nueva, user);
                                mk.setFechaCreacion(fecha);
                                mk.setFechamodificacion(new Date());
                                mkdirs.add(mk);
                            }
                        }
                    }
                    i = i + 1;
                }
            }
            List<String> currentletrass = new ArrayList<>();
            for (Folder_NicolasMunoz_21052729 disco : folders){
                currentletrass.add(disco.getDireccion());
            }
            if (currentletrass.contains(directorio1)) {
                int valor = folders.size();
                int i = 0;
                while (i < valor) {
                    var carpeta = folders.get(i);
                    var dire = carpeta.getDireccion();
                    if (dire.contains(directorio1)) {
                        Date fecha = carpeta.getFechaCreacion();
                        var user = carpeta.getUsuario_log();
                        var archivos = carpeta.getNombre();
                        List<String> currentletra = new ArrayList<>();
                        for (Mkdir_NicolasMunoz_21052729 disco : mkdirs) {
                            currentletra.add(disco.getDireccion());
                        }
                        var ruta1 = "Direccion = " + ruta;
                        if (currentletra.contains(ruta1)) {
                            var dire_nueva = dire.replaceFirst(directorio, "");
                            dire_nueva = ruta1 + dire_nueva;
                            int j = 0;
                            while (j < valor) {
                                var carpeta1 = folders.get(j);
                                var archivo1 = carpeta1.getNombre();
                                var rut1 = carpeta1.getDireccion();
                                if (!archivo1.equals(archivos) && rut1.equals(dire_nueva)) {
                                    var fol = new Folder_NicolasMunoz_21052729(archivos, user, dire_nueva);
                                    fol.setFechaCreacion(fecha);
                                    fol.setFechamodificacion(new Date());
                                    folders.add(fol);
                                }
                                else if (archivo1.equals(archivos) && (rut1.equals(dire_nueva) || rut1.equals(directorio1))){
                                    carpeta1.setFechamodificacion(new Date());
                                    carpeta1.setNombre(archivos);
                                }
                                else {
                                    var fol = new Folder_NicolasMunoz_21052729(archivos, user, dire_nueva);
                                    fol.setFechaCreacion(fecha);
                                    fol.setFechamodificacion(new Date());
                                    folders.add(fol);
                                }
                                j = j + 1;
                            }
                        }
                    }
                    i = i + 1;
                }
            }
        }
    }
    /**
     * move: void
     * @param mover: String;
     * @param ruta: String;
     */
    public void move(String mover, String ruta) {
        int dotIndex = mover.lastIndexOf(".");
        if (dotIndex != -1) {
            String[] partes = mover.split("\\.");
            List<String> lista = new ArrayList<>();
            lista.add(partes[0]);
            lista.add("." + partes[1]);
            var nombre = lista.get(0);
            var extension = lista.get(1).toLowerCase();
            if (nombre.equals("*")) {
                int i = folders.size() - 1;
                while (-1 < i) {
                    var carpeta = folders.get(i);
                    var exten = carpeta.getNombre().getExtension();
                    var resultados = carpeta.getDireccion();
                    String[] partessss = resultados.split("=");
                    String resultadosss = partessss[1].trim();
                    String directorio = String.valueOf(switchdrives.get(0));
                    String[] partesss = directorio.split("=");
                    String resultadoss = partesss[1].trim();
                    if (exten.equals(extension) && resultadosss.equals(resultadoss)) {
                        var archivo = carpeta.getNombre();
                        int j = 0;
                        while (j < mkdirs.size()) {
                            var carpeta2 = mkdirs.get(j);
                            var rut = carpeta2.getDireccion();
                            String[] partess = rut.split("=");
                            String resultado = partess[1].trim();
                            var user = carpeta2.getUsuario_log();
                            if (resultado.equals(ruta)) {
                                var contenido = new Folder_NicolasMunoz_21052729(archivo, user, rut);
                                contenido.setFechamodificacion(new Date());
                                var fecha = carpeta2.getFechaCreacion();
                                contenido.setFechaCreacion(fecha);
                                folders.add(contenido);
                                folders.remove(folders.get(i));
                            }
                            j = j + 1;
                        }
                    }
                    i = i - 1;
                }
            } else {
                int i = 0;
                while (i < folders.size()) {
                    var carpeta = folders.get(i);
                    var name = carpeta.getNombre().getNombre();
                    var exten = carpeta.getNombre().getExtension();
                    if (name.equals(nombre) && exten.equals(extension)) {
                        var archivo = carpeta.getNombre();
                        int j = 0;
                        while (j < mkdirs.size()) {
                            var carpeta2 = mkdirs.get(j);
                            var rut = carpeta2.getDireccion();
                            String[] partess = rut.split("=");
                            String resultado = partess[1].trim();
                            var user = carpeta2.getUsuario_log();
                            if (resultado.equals(ruta)) {
                                var contenido = new Folder_NicolasMunoz_21052729(archivo, user, rut);
                                contenido.setFechamodificacion(new Date());
                                var fecha = carpeta2.getFechaCreacion();
                                contenido.setFechaCreacion(fecha);
                                folders.add(contenido);
                                folders.remove(folders.get(i));
                            }
                            j = j + 1;
                        }
                    }
                    i = i + 1;
                }
            }
        }
        else {
            String directorio = String.valueOf(switchdrives.get(0));
            var directorio1 = directorio + mover + "/";
            List<String> currentletras = new ArrayList<>();
            for (Mkdir_NicolasMunoz_21052729 disco : mkdirs){
                currentletras.add(disco.getDireccion());
            }
            if (currentletras.contains(directorio1)) {
                int i = mkdirs.size() - 1;
                while (-1 < i) {
                    var carpeta = mkdirs.get(i);
                    var dire = carpeta.getDireccion();
                    if (dire.contains(directorio1)) {
                        Date fecha = carpeta.getFechaCreacion();
                        var user = carpeta.getUsuario_log();
                        List<String> currentletra = new ArrayList<>();
                        for (Mkdir_NicolasMunoz_21052729 disco : mkdirs) {
                            currentletra.add(disco.getDireccion());
                        }
                        var ruta1 = "Direccion = " + ruta;
                        if (currentletra.contains(ruta1)) {
                            var dire_nueva = dire.replaceFirst(directorio, "");
                            dire_nueva = ruta1 + dire_nueva;
                            if (!currentletra.contains(dire_nueva)) {
                                var mk = new Mkdir_NicolasMunoz_21052729(dire_nueva, user);
                                mk.setFechaCreacion(fecha);
                                mk.setFechamodificacion(new Date());
                                mkdirs.add(mk);
                                mkdirs.remove(mkdirs.get(i));
                            }
                        }
                    }
                    i = i - 1;
                }
            }
            List<String> currentletrass = new ArrayList<>();
            for (Folder_NicolasMunoz_21052729 disco : folders){
                currentletrass.add(disco.getDireccion());
            }
            int Si = 0;
            for (String elemento : currentletrass) {
                if (elemento.contains(directorio1)) {
                    Si = 1;
                    break;
                }
            }
            if (Si == 1) {
                int i = folders.size() - 1;
                while (-1 < i) {
                    var carpeta = folders.get(i);
                    var dire = carpeta.getDireccion();
                    if (dire.contains(directorio1)) {
                        Date fecha = carpeta.getFechaCreacion();
                        var user = carpeta.getUsuario_log();
                        var archivos = carpeta.getNombre();
                        List<String> currentletra = new ArrayList<>();
                        for (Mkdir_NicolasMunoz_21052729 disco : mkdirs) {
                            currentletra.add(disco.getDireccion());
                        }
                        var ruta1 = "Direccion = " + ruta;
                        if (currentletra.contains(ruta1)) {
                            var dire_nueva = dire.replaceFirst(directorio, "");
                            dire_nueva = ruta1 + dire_nueva;
                            int j = i;
                            while (-1 < j) {
                                var carpeta1 = folders.get(j);
                                var archivo1 = carpeta1.getNombre();
                                var rut1 = carpeta1.getDireccion();
                                if (!archivo1.equals(archivos) && rut1.equals(dire_nueva)) {
                                    var fol = new Folder_NicolasMunoz_21052729(archivos, user, dire_nueva);
                                    fol.setFechaCreacion(fecha);
                                    fol.setFechamodificacion(new Date());
                                    folders.add(fol);
                                    folders.remove(folders.get(i));
                                }
                                else if (archivo1.equals(archivos) && (rut1.equals(dire_nueva) || rut1.equals(directorio1))){
                                    carpeta1.setFechamodificacion(new Date());
                                    carpeta1.setNombre(archivos);
                                    carpeta1.setDireccion(dire_nueva);
                                }
                                else {
                                    var fol = new Folder_NicolasMunoz_21052729(archivos, user, dire_nueva);
                                    fol.setFechaCreacion(fecha);
                                    fol.setFechamodificacion(new Date());
                                    folders.add(fol);
                                    folders.remove(folders.get(i));
                                }
                                j = j - 1;
                            }
                        }
                    }
                    i = i - 1;
                }
            }
        }
    }
    /**
     * ren: void
     * @param antiguo: String;
     * @param nuevo: String;
     */
    public void ren(String antiguo, String nuevo) {
        int dotIndex = antiguo.lastIndexOf(".");
        if (dotIndex != -1) {
            String[] partes = antiguo.split("\\.");
            List<String> lista = new ArrayList<>();
            lista.add(partes[0]);
            lista.add("." + partes[1]);
            String[] partess = nuevo.split("\\.");
            List<String> listas = new ArrayList<>();
            listas.add(partess[0]);
            listas.add("." + partess[1]);
            var nombre = lista.get(0);
            var extension = lista.get(1).toLowerCase();
            var nombres = listas.get(0);
            var extensions = listas.get(1).toLowerCase();
            int i = 0;
            while (i < folders.size()){
                var carpeta = folders.get(i);
                var nomb = carpeta.getNombre().getNombre();
                var exten = carpeta.getNombre().getExtension();
                var dir = carpeta.getDireccion();
                if (exten.equals(extension) && nomb.equals(nombre)){
                    int h = 0;
                    int j = 0;
                    while (j < folders.size()){
                        var carpetas = folders.get(j);
                        var nombs = carpetas.getNombre().getNombre();
                        var extens = carpetas.getNombre().getExtension();
                        var dirs = carpetas.getDireccion();
                        if (extens.equals(extensions) && nombs.equals(nombres) && dir.equals(dirs)){
                            h = 1;
                            break;
                        }
                        j = j + 1;
                    }
                    if (h == 0){
                        carpeta.getNombre().setNombre(nombres);
                        carpeta.getNombre().setExtension(extensions);
                        carpeta.setFechamodificacion(new Date());
                    }
                    else {
                        System.out.println("Esto rompe la restriccion de unicidad \n");
                    }
                }
                i = i + 1;
            }
        }
        else{
            int e = 0;
            while (e < folders.size()){
                var carpeta = folders.get(e);
                var dire = carpeta.getDireccion();
                if (dire.contains(antiguo)){
                    dire = dire.replace(antiguo, nuevo);
                    int h = 0;
                    int j = 0;
                    while (j < folders.size()){
                        var carpeta1 = folders.get(j);
                        var dire1 = carpeta1.getDireccion();
                        if (dire1.equals(dire)){
                            h = 1;
                            break;
                        }
                        j = j + 1;
                    }
                    int o = 0;
                    while (o < mkdirs.size()){
                        var carpeta1 = mkdirs.get(o);
                        var dire1 = carpeta1.getDireccion();
                        if (dire1.equals(dire)){
                            h = 1;
                            break;
                        }
                        o = o + 1;
                    }
                    if (h == 0){
                        carpeta.setDireccion(dire);
                        carpeta.setFechamodificacion(new Date());
                    }
                }
                e = e + 1;
            }
            int i = 0;
            while (i < mkdirs.size()){
                var carpeta = mkdirs.get(i);
                var dire = carpeta.getDireccion();
                if (dire.contains(antiguo)){
                    dire = dire.replace(antiguo, nuevo);
                    int h = 0;
                    int j = 0;
                    while (j < mkdirs.size()){
                        var carpeta1 = mkdirs.get(j);
                        var dire1 = carpeta1.getDireccion();
                        if (dire1.equals(dire)){
                            h = 1;
                            break;
                        }
                        j = j + 1;
                    }
                    if (h == 0){
                        carpeta.setDireccion(dire);
                        carpeta.setFechamodificacion(new Date());
                    }
                    else {
                        System.out.println("Esto rompe la restriccion de unicidad \n");
                    }
                }
                i = i + 1;
            }
        }
    }
    /**
     * dir: void
     * @param condicion: List<String>;
     */
    public void dir(List<String> condicion) {
        if (condicion.size() == 2) {
            var con = condicion.get(0);
            var con1 = condicion.get(1);
            if (con.equals("/S") && con1.equals("/A")){
                var directorio_actual = switchdrives.get(0).toString();
                int h = 0;
                while (h < mkdirs.size()) {
                    var carpeta = mkdirs.get(h);
                    var direccion = carpeta.getDireccion();
                    if (direccion.contains(directorio_actual) && !direccion.equals(directorio_actual)) {
                        System.out.println(carpeta);
                    }
                    h = h + 1;
                }
                int e = 0;
                while (e < folders.size()) {
                    var carpeta = folders.get(e);
                    var direccion = carpeta.getDireccion();
                    if (direccion.contains(directorio_actual)) {
                        System.out.println(carpeta);
                    }
                    e = e + 1;
                }
            }
            else if (!con.equals("/S") && con1.equals("/A")) {
                System.out.println("El comando 1 no es valido \n");
            }
            else if (con.equals("/S")) {
                System.out.println("El comando 2 no es valido \n");
            }
            else {
                System.out.println("Ambos comandos no son validos \n");
            }
        }
        else {
            List<String> Lista = new ArrayList<>();
            var directorio_actual = switchdrives.get(0).toString();
            int contador = 0;
            char caracterBuscado = '/';
            for (int i = 0; i < directorio_actual.length(); i++) {
                if (directorio_actual.charAt(i) == caracterBuscado) {
                    contador++;
                }
            }
            int i = 0;
            while (i < mkdirs.size()){
                var carpeta = mkdirs.get(i);
                var direccion = carpeta.getDireccion();
                int contador1 = 0;
                for (int j = 0; j < direccion.length(); j++) {
                    if (direccion.charAt(j) == caracterBuscado) {
                        contador1++;
                    }
                }
                if (direccion.contains(directorio_actual) && contador1 == (contador + 1)){
                    Lista.add(carpeta.toString());
                }
                i = i + 1;
            }
            int j = 0;
            while (j < folders.size()){
                var carpeta = folders.get(j);
                var direccion = carpeta.getDireccion();
                if (direccion.equals(directorio_actual)){
                    Lista.add(carpeta.toString());
                }
                j = j + 1;
            }
            if (condicion.size() == 0){
                for (String carpeta : Lista) {
                    System.out.println(carpeta);
                }
                Lista.clear();
            }
            else {
                var con = condicion.get(0);
                if (con.equals("/S")) {
                    int h = 0;
                    while (h < mkdirs.size()) {
                        var carpeta = mkdirs.get(h);
                        var direccion = carpeta.getDireccion();
                        if (direccion.contains(directorio_actual) && !direccion.equals(directorio_actual)) {
                            System.out.println(carpeta);
                        }
                        h = h + 1;
                    }
                    int e = 0;
                    while (e < folders.size()) {
                        var carpeta = folders.get(e);
                        var direccion = carpeta.getDireccion();
                        if (direccion.contains(directorio_actual)) {
                            System.out.println(carpeta);
                        }
                        e = e + 1;
                    }
                }
                if (con.equals("/A")) {
                    for (String carpeta : Lista) {
                        System.out.println(carpeta);
                    }
                    Lista.clear();
                }
                if (con.equals("/O N")) {
                    Collections.sort(Lista);
                    for (String carpeta : Lista) {
                        System.out.println(carpeta);
                    }
                    Lista.clear();
                }
                if (con.equals("/O -D")) {
                    ordenarPorFecha(Lista);
                    Collections.reverse(Lista);
                    for (String carpeta : Lista) {
                        System.out.println(carpeta);
                    }
                    Lista.clear();
                }
                if (con.equals("/O D")) {
                    ordenarPorFecha(Lista);
                    for (String carpeta : Lista) {
                        System.out.println(carpeta);
                    }
                    Lista.clear();
                }
                if (con.equals("/?")) {
                    System.out.println("Si no se ingresa nada solo se mostra el contenido del directorio actual, excluyendo subdirectorios y contenido oculto.\nSi ingresa '/s' se mostrara el contenido del directorio actual junto a subdirectorios, pero excluyendo el contenido oculto.\nSi ingresa '/a' se mostrara solo el contenido del directorio actual incluyendo el contenido oculto.\nSi ingresa '/s /a' se mostra el contenido del directorio actual y subdirectorios, incluyendo contenido oculto.\nSi ingresa '/o N' se mostrara el contenido del directorio actual en orden alfabético ascendente.\nSi ingresa '/o D' se mostrara el contenido del directorio actual según fecha de creacion, en orden ascendente.\nSi ingresa '/o -D' se mostrara el contenido del directorio actual según fecha de creacion, en orden descendente.\nSi ingresa cualquier otra cosa se mostrara 'Este comando no es valido'\n");
                }
            }
        }
    }
    /**
     * ordenarPorFecha: void
     * @param carpetas: List<String>;
     */
    public void ordenarPorFecha(List<String> carpetas) {
        carpetas.sort((carpeta1, carpeta2) -> {
            String fecha1 = obtenerFechaCreacion(carpeta1);
            String fecha2 = obtenerFechaCreacion(carpeta2);
            return fecha1.compareTo(fecha2);
        });
    }
    /**
     * obtenerfechaCreacion: String
     * @param carpeta: String;
     * @return: String;
     */
    public String obtenerFechaCreacion(String carpeta) {
        int inicio = carpeta.indexOf("Fecha de creacion = ");
        if (inicio != -1) {
            inicio += "Fecha de creacion = ".length();
            int fin = carpeta.indexOf(",", inicio);
            if (fin != -1) {
                return carpeta.substring(inicio, fin).trim();
            }
        }
        return "";
    }
    /**
     * format: void
     * @param letra: String;
     * @param nuevo: String;
     */
    public void format(String letra, String nuevo) {
        List<String> currentletra = new ArrayList<>();
        for (Drive_NicolasMunoz_21052729 disco : drives){
            currentletra.add(disco.getletra());
        }
        if (currentletra.contains(letra)) {
            int i = 0;
            while (i < drives.size()){
                var drive = drives.get(i);
                var letr = drive.getletra();
                if (letra.equals(letr)){
                    switchdrives.clear();
                    drive.setName(nuevo);
                    var letra1 = "Direccion = " + letra + ":/";
                    int j = 0;
                    while (j < mkdirs.size()){
                        var carpeta = mkdirs.get(j);
                        var dir = carpeta.getDireccion();
                        if (dir.startsWith(letra1)){
                            mkdirs.remove(mkdirs.get(j));
                            j = j - 1;
                        }
                        j = j + 1;
                    }
                    int o = 0;
                    while (o < folders.size()){
                        var carpeta = folders.get(o);
                        var dir = carpeta.getDireccion();
                        if (dir.startsWith(letra1)){
                            folders.remove(folders.get(o));
                            o = o - 1;
                        }
                        o = o + 1;
                    }
                }
                i = i + 1;
            }
        }
        else {
            System.out.println("No existe esta unidad \n");
        }
    }
    /**
     * encrypt: void
     * @param password: String;
     * @param elemnto_encriptar: String;
     */
    public void encrypt(String password, String elemnto_encriptar){
        int i = 0;
        int contador = 0;
        while (i < password.length()){
            char caracter = password.charAt(i);
            contador = contador + (int) caracter;
            i = i + 1;
        }
        int mod5 = contador % 5;
        var directorio_actual = switchdrives.get(0).toString();
        var comp = elemnto_encriptar.split("\\.");
        var comp1 = comp[0];
        if (comp.length == 2){
            int l = 0;
            while(l < folders.size()){
                var carpeta = folders.get(l);
                var ruta = carpeta.getDireccion();
                var archivo_nombre = carpeta.getNombre().getNombre();
                var archivo_extension = carpeta.getNombre().getExtension();
                var archivo_contenido = carpeta.getNombre().getContenido();
                if (ruta.equals(directorio_actual) && archivo_nombre.equals(comp1) && archivo_extension.equals("." + comp[1].toLowerCase())){
                    int o = 0;
                    StringBuilder nombre_encry = new StringBuilder();
                    while (o < archivo_nombre.length()){
                        char caracter = archivo_nombre.charAt(o);
                        int nuevoValorAscii = (int) caracter + mod5;
                        char nuevoCaracter = (char) nuevoValorAscii;
                        nombre_encry.append(nuevoCaracter);
                        o = o + 1;
                    }
                    var nuevo_nombre = nombre_encry.toString();
                    carpeta.getNombre().setNombre(nuevo_nombre);
                    carpeta.setFechamodificacion(new Date());
                    int p = 0;
                    StringBuilder contenido_encry = new StringBuilder();
                    while (p < archivo_contenido.length()){
                        char caracter = archivo_contenido.charAt(p);
                        int nuevoValorAscii = (int) caracter + mod5;
                        char nuevoCaracter = (char) nuevoValorAscii;
                        contenido_encry.append(nuevoCaracter);
                        p = p + 1;
                    }
                    var nuevo_contenido = contenido_encry.toString();
                    carpeta.getNombre().setContenido(nuevo_contenido);
                }
                l = l + 1;
            }
        }
        else if (comp.length == 1) {
            int h = 0 ;
            StringBuilder encriptar_encriptado = new StringBuilder();
            while(h < elemnto_encriptar.length()){
                char caracter = elemnto_encriptar.charAt(h);
                int nuevoValorAscii = (int) caracter + mod5;
                char nuevoCaracter = (char) nuevoValorAscii;
                encriptar_encriptado.append(nuevoCaracter);
                h = h + 1;
            }
            var carpeta_encry = encriptar_encriptado.toString();
            int n = 0;
            while (n < mkdirs.size()){
                var carpeta = mkdirs.get(n);
                var ruta = carpeta.getDireccion();
                if (ruta.contains(directorio_actual + elemnto_encriptar)){
                    var ruta_mantener = ruta.replace((directorio_actual + elemnto_encriptar), "");
                    var nueva_ruta = directorio_actual + carpeta_encry + ruta_mantener;
                    carpeta.setDireccion(nueva_ruta);
                    carpeta.setFechamodificacion(new Date());
                    String nuevaCadena = ruta_mantener.substring(1);
                    if (!nuevaCadena.equals("")) {
                        String[] partes = nuevaCadena.split("/");
                        if (partes.length > 0) {
                            String primeraPalabra = partes[0];
                            encry_carpeta((directorio_actual + carpeta_encry + "/"), password, primeraPalabra, carpeta);
                        }
                    }
                }
                n = n + 1;
            }
            int a = 0;
            while (a < folders.size()){
                var carpeta = folders.get(a);
                var ruta = carpeta.getDireccion();
                if (ruta.contains(directorio_actual + elemnto_encriptar)){
                    var ruta_mantener = ruta.replace((directorio_actual + elemnto_encriptar), "");
                    var nueva_ruta = directorio_actual + carpeta_encry + ruta_mantener;
                    carpeta.setDireccion(nueva_ruta);
                    carpeta.setFechamodificacion(new Date());
                    var archivo_nombre = carpeta.getNombre().getNombre();
                    var archivo_contenido = carpeta.getNombre().getContenido();
                    String nuevaCadena = ruta_mantener.substring(1);
                    if (!nuevaCadena.equals("")) {
                        String[] partes = nuevaCadena.split("/");
                        if (partes.length > 0) {
                            String primeraPalabra = partes[0];
                            encry_archivo((directorio_actual + carpeta_encry + "/"), password, primeraPalabra, carpeta);
                        }
                    }
                    int o = 0;
                    StringBuilder nombre_encry = new StringBuilder();
                    while (o < archivo_nombre.length()){
                        char caracter = archivo_nombre.charAt(o);
                        int nuevoValorAscii = (int) caracter + mod5;
                        char nuevoCaracter = (char) nuevoValorAscii;
                        nombre_encry.append(nuevoCaracter);
                        o = o + 1;
                    }
                    var nuevo_nombre = nombre_encry.toString();
                    carpeta.getNombre().setNombre(nuevo_nombre);
                    carpeta.setFechamodificacion(new Date());
                    int p = 0;
                    StringBuilder contenido_encry = new StringBuilder();
                    while (p < archivo_contenido.length()){
                        char caracter = archivo_contenido.charAt(p);
                        int nuevoValorAscii = (int) caracter + mod5;
                        char nuevoCaracter = (char) nuevoValorAscii;
                        contenido_encry.append(nuevoCaracter);
                        p = p + 1;
                    }
                    var nuevo_contenido = contenido_encry.toString();
                    carpeta.getNombre().setContenido(nuevo_contenido);
                }
                a = a + 1;
            }
        }
    }
    /**
     * encry_carpeta: void
     * @param directorio: String;
     * @param password: String;
     * @param elemnto_encriptar: String;
     * @param carpeta_trabajar: Mkdir_NicolasMunoz_21052729;
     */
    public void encry_carpeta(String directorio, String password, String elemnto_encriptar, Mkdir_NicolasMunoz_21052729 carpeta_trabajar){
        int i = 0;
        int contador = 0;
        while (i < password.length()){
            char caracter = password.charAt(i);
            contador = contador + (int) caracter;
            i = i + 1;
        }
        int mod5 = contador % 5;
        int h = 0 ;
        StringBuilder encriptar_encriptado = new StringBuilder();
        while(h < elemnto_encriptar.length()){
            char caracter = elemnto_encriptar.charAt(h);
            int nuevoValorAscii = (int) caracter + mod5;
            char nuevoCaracter = (char) nuevoValorAscii;
            encriptar_encriptado.append(nuevoCaracter);
            h = h + 1;
        }
        var carpeta_encry = encriptar_encriptado.toString();
        var ruta = carpeta_trabajar.getDireccion();
        var ruta_mantener = ruta.replace((directorio + elemnto_encriptar), "");
        var nueva_ruta = directorio + carpeta_encry + ruta_mantener;
        carpeta_trabajar.setDireccion(nueva_ruta);
        carpeta_trabajar.setFechamodificacion(new Date());
        String nuevaCadena = ruta_mantener.substring(1);
        if (!nuevaCadena.equals("")) {
            String[] partes = nuevaCadena.split("/");
            if (partes.length > 0) {
                String primeraPalabra = partes[0];
                encry_carpeta((directorio + carpeta_encry + "/"), password, primeraPalabra, carpeta_trabajar);
            }
        }
    }
    /**
     * encry_archivo: void
     * @param directorio: String;
     * @param password: String;
     * @param elemnto_encriptar: String;
     * @param carpeta_trabajar: Folder_NicolasMunoz_21052729;
     */
    public void encry_archivo(String directorio, String password, String elemnto_encriptar, Folder_NicolasMunoz_21052729 carpeta_trabajar){
        int i = 0;
        int contador = 0;
        while (i < password.length()){
            char caracter = password.charAt(i);
            contador = contador + (int) caracter;
            i = i + 1;
        }
        int mod5 = contador % 5;
        int h = 0 ;
        StringBuilder encriptar_encriptado = new StringBuilder();
        while(h < elemnto_encriptar.length()){
            char caracter = elemnto_encriptar.charAt(h);
            int nuevoValorAscii = (int) caracter + mod5;
            char nuevoCaracter = (char) nuevoValorAscii;
            encriptar_encriptado.append(nuevoCaracter);
            h = h + 1;
        }
        var carpeta_encry = encriptar_encriptado.toString();
        var ruta = carpeta_trabajar.getDireccion();
        var ruta_mantener = ruta.replace((directorio + elemnto_encriptar), "");
        var nueva_ruta = directorio + carpeta_encry + ruta_mantener;
        carpeta_trabajar.setDireccion(nueva_ruta);
        String nuevaCadena = ruta_mantener.substring(1);
        if (!nuevaCadena.equals("")) {
            String[] partes = nuevaCadena.split("/");
            if (partes.length > 0) {
                String primeraPalabra = partes[0];
                encry_carpeta((directorio + carpeta_encry + "/"), password, primeraPalabra, carpeta_trabajar);
            }
        }
    }
    /**
     * decrypt: void
     * @param password: String;
     * @param elemento_desencriptar: String;
     */
    public void decrypt(String password, String elemento_desencriptar){
        int i = 0;
        int contador = 0;
        while (i < password.length()){
            char caracter = password.charAt(i);
            contador = contador + (int) caracter;
            i++;
        }
        int mod5 = contador % 5;
        var directorio_actual = switchdrives.get(0).toString();
        var comp = elemento_desencriptar.split("\\.");
        var comp1 = comp[0];
        if (comp.length == 2){
            int l = 0;
            while(l < folders.size()){
                var carpeta = folders.get(l);
                var ruta = carpeta.getDireccion();
                var archivo_nombre = carpeta.getNombre().getNombre();
                var archivo_extension = carpeta.getNombre().getExtension();
                var archivo_contenido = carpeta.getNombre().getContenido();
                if (ruta.equals(directorio_actual)){
                    int o = 0;
                    StringBuilder nombre_encry = new StringBuilder();
                    while (o < archivo_nombre.length()){
                        char caracter = archivo_nombre.charAt(o);
                        int nuevoValorAscii = (int) caracter - mod5;
                        char nuevoCaracter = (char) nuevoValorAscii;
                        nombre_encry.append(nuevoCaracter);
                        o++;
                    }
                    var nuevo_nombre = nombre_encry.toString();
                    int p = 0;
                    StringBuilder contenido_encry = new StringBuilder();
                    while (p < archivo_contenido.length()){
                        char caracter = archivo_contenido.charAt(p);
                        int nuevoValorAscii = (int) caracter - mod5;
                        char nuevoCaracter = (char) nuevoValorAscii;
                        contenido_encry.append(nuevoCaracter);
                        p++;
                    }
                    var nuevo_contenido = contenido_encry.toString();
                    if (comp1.equals(nuevo_nombre) && archivo_extension.equals("." + comp[1].toLowerCase())){
                        carpeta.getNombre().setNombre(nuevo_nombre);
                        carpeta.setFechamodificacion(new Date());
                        carpeta.getNombre().setContenido(nuevo_contenido);
                    }
                }
                l = l + 1;
            }
        }
        else if (comp.length == 1) {
            int n = 0;
            while (n < mkdirs.size()){
                var carpeta = mkdirs.get(n);
                var ruta = carpeta.getDireccion();
                if (ruta.contains(directorio_actual)){
                    var ruta_mantener = ruta.replace((directorio_actual), "");
                    if (!ruta_mantener.equals("")) {
                        String[] partes = ruta_mantener.split("/");
                        String primeraPalabra = partes[0];
                        int h = 0 ;
                        StringBuilder encriptar_encriptado = new StringBuilder();
                        while(h < primeraPalabra.length()){
                            char caracter = primeraPalabra.charAt(h);
                            int nuevoValorAscii = (int) caracter - mod5;
                            char nuevoCaracter = (char) nuevoValorAscii;
                            encriptar_encriptado.append(nuevoCaracter);
                            h = h + 1;
                        }
                        var carpeta_decry = encriptar_encriptado.toString();
                        if (carpeta_decry.equals(elemento_desencriptar)){
                            var mantener = ruta.replace((directorio_actual + primeraPalabra), "");
                            var nueva_ruta = directorio_actual + carpeta_decry + mantener;
                            carpeta.setDireccion(nueva_ruta);
                            carpeta.setFechamodificacion(new Date());
                            String nuevaCadena1 = mantener.substring(1);
                            if (!nuevaCadena1.equals("")) {
                                String[] partes1 = nuevaCadena1.split("/");
                                if (partes1.length > 0) {
                                    String primeraPalabra1 = partes1[0];
                                    decry_carpeta((directorio_actual + carpeta_decry + "/"), password, primeraPalabra1, carpeta);
                                }
                            }
                        }
                    }
                }
                n = n + 1;
            }
            int a = 0;
            while (a < folders.size()){
                var carpeta = folders.get(a);
                var ruta = carpeta.getDireccion();
                if (ruta.contains(directorio_actual)){
                    var ruta_mantener = ruta.replace((directorio_actual), "");
                    if (!ruta_mantener.equals("")) {
                        String[] partes = ruta_mantener.split("/");
                        String primeraPalabra = partes[0];
                        int h = 0 ;
                        StringBuilder encriptar_encriptado = new StringBuilder();
                        while(h < primeraPalabra.length()){
                            char caracter = primeraPalabra.charAt(h);
                            int nuevoValorAscii = (int) caracter - mod5;
                            char nuevoCaracter = (char) nuevoValorAscii;
                            encriptar_encriptado.append(nuevoCaracter);
                            h = h + 1;
                        }
                        var carpeta_decry = encriptar_encriptado.toString();
                        if (carpeta_decry.equals(elemento_desencriptar)){
                            var archivo_nombre = carpeta.getNombre().getNombre();
                            var archivo_contenido = carpeta.getNombre().getContenido();
                            var mantener = ruta.replace((directorio_actual + primeraPalabra), "");
                            var nueva_ruta = directorio_actual + carpeta_decry + mantener;
                            carpeta.setDireccion(nueva_ruta);
                            carpeta.setFechamodificacion(new Date());
                            String nuevaCadena1 = mantener.substring(1);
                            if (!nuevaCadena1.equals("")) {
                                String[] partes1 = nuevaCadena1.split("/");
                                if (partes1.length > 0) {
                                    String primeraPalabra1 = partes1[0];
                                    decry_archivo((directorio_actual + carpeta_decry + "/"), password, primeraPalabra1, carpeta);
                                }
                            }
                            int o = 0;
                            StringBuilder nombre_encry = new StringBuilder();
                            while (o < archivo_nombre.length()){
                                char caracter = archivo_nombre.charAt(o);
                                int nuevoValorAscii = (int) caracter - mod5;
                                char nuevoCaracter = (char) nuevoValorAscii;
                                nombre_encry.append(nuevoCaracter);
                                o = o + 1;
                            }
                            var nuevo_nombre = nombre_encry.toString();
                            carpeta.getNombre().setNombre(nuevo_nombre);
                            carpeta.setFechamodificacion(new Date());
                            int p = 0;
                            StringBuilder contenido_encry = new StringBuilder();
                            while (p < archivo_contenido.length()){
                                char caracter = archivo_contenido.charAt(p);
                                int nuevoValorAscii = (int) caracter - mod5;
                                char nuevoCaracter = (char) nuevoValorAscii;
                                contenido_encry.append(nuevoCaracter);
                                p = p + 1;
                            }
                            var nuevo_contenido = contenido_encry.toString();
                            carpeta.getNombre().setContenido(nuevo_contenido);
                        }
                    }
                }
                a = a + 1;
            }
        }
    }
    /**
     * decry_carpeta: void
     * @param directorio: String;
     * @param password: String;
     * @param elemnto_desencriptar: String;
     * @param carpeta_trabajar: Mkdir_NicolasMunoz_21052729;
     */
    public void decry_carpeta(String directorio, String password, String elemnto_desencriptar, Mkdir_NicolasMunoz_21052729 carpeta_trabajar){
        int i = 0;
        int contador = 0;
        while (i < password.length()){
            char caracter = password.charAt(i);
            contador = contador + (int) caracter;
            i = i + 1;
        }
        int mod5 = contador % 5;
        int h = 0 ;
        StringBuilder encriptar_encriptado = new StringBuilder();
        while(h < elemnto_desencriptar.length()){
            char caracter = elemnto_desencriptar.charAt(h);
            int nuevoValorAscii = (int) caracter - mod5;
            char nuevoCaracter = (char) nuevoValorAscii;
            encriptar_encriptado.append(nuevoCaracter);
            h = h + 1;
        }
        var carpeta_encry = encriptar_encriptado.toString();
        var ruta = carpeta_trabajar.getDireccion();
        var ruta_mantener = ruta.replace((directorio + elemnto_desencriptar), "");
        var nueva_ruta = directorio + carpeta_encry + ruta_mantener;
        carpeta_trabajar.setDireccion(nueva_ruta);
        carpeta_trabajar.setFechamodificacion(new Date());
        String nuevaCadena = ruta_mantener.substring(1);
        if (!nuevaCadena.equals("")) {
            String[] partes = nuevaCadena.split("/");
            if (partes.length > 0) {
                String primeraPalabra = partes[0];
                encry_carpeta((directorio + carpeta_encry + "/"), password, primeraPalabra, carpeta_trabajar);
            }
        }
    }
    /**
     * decry_archivo: void
     * @param directorio: String;
     * @param password: String;
     * @param elemnto_desencriptar: String;
     * @param carpeta_trabajar: Folder_NicolasMunoz_21052729;
     */
    public void decry_archivo(String directorio, String password, String elemnto_desencriptar, Folder_NicolasMunoz_21052729 carpeta_trabajar){
        int i = 0;
        int contador = 0;
        while (i < password.length()){
            char caracter = password.charAt(i);
            contador = contador + (int) caracter;
            i = i + 1;
        }
        int mod5 = contador % 5;
        int h = 0 ;
        StringBuilder encriptar_encriptado = new StringBuilder();
        while(h < elemnto_desencriptar.length()){
            char caracter = elemnto_desencriptar.charAt(h);
            int nuevoValorAscii = (int) caracter - mod5;
            char nuevoCaracter = (char) nuevoValorAscii;
            encriptar_encriptado.append(nuevoCaracter);
            h = h + 1;
        }
        var carpeta_encry = encriptar_encriptado.toString();
        var ruta = carpeta_trabajar.getDireccion();
        var ruta_mantener = ruta.replace((directorio + elemnto_desencriptar), "");
        var nueva_ruta = directorio + carpeta_encry + ruta_mantener;
        carpeta_trabajar.setDireccion(nueva_ruta);
        String nuevaCadena = ruta_mantener.substring(1);
        if (!nuevaCadena.equals("")) {
            String[] partes = nuevaCadena.split("/");
            if (partes.length > 0) {
                String primeraPalabra = partes[0];
                encry_carpeta((directorio + carpeta_encry + "/"), password, primeraPalabra, carpeta_trabajar);
            }
        }
    }
    /**
     * grep: void
     * @param palabra: String;
     * @param lugar: String;
     */
    public void grep(String palabra, String lugar){
        var directorio_actual = switchdrives.get(0).toString();
        int i = 0;
        while (i < folders.size()){
            var carpeta = folders.get(i);
            var ruta = carpeta.getDireccion();
            if (lugar.equals("..")){
                String dire = String.valueOf(switchdrives.get(0));
                int indiceUltimo = dire.lastIndexOf("/");
                String sub = dire.substring(0, indiceUltimo-14);
                if(Objects.equals(sub, "")) {
                    System.out.println("No es posible hacer eso \n");
                }
                else{
                    String direccion = String.valueOf(switchdrives.get(0));
                    int indiceUltimoSlash = direccion.lastIndexOf("/");
                    String subcadena = direccion.substring(0, indiceUltimoSlash);
                    int indiceUltimoSlas = subcadena.lastIndexOf("/");
                    String subcadena1 = direccion.substring(0, indiceUltimoSlas + 1);
                    var ruta_revisar = new Switchdrive_NicolasMunoz_21052729(subcadena1).toString();
                    if (ruta.equals(ruta_revisar)){
                        var contenido_archivo = carpeta.getNombre().getContenido();
                        List<Integer> posiciones = new ArrayList<>();
                        String[] palabras = contenido_archivo.split("\\s+");
                        int posicion = 1;
                        for (String s : palabras) {
                            if (s.equalsIgnoreCase(palabra)) {
                                posiciones.add(posicion);
                            }
                            posicion++;
                        }
                        System.out.println("La palabra '" + palabra + "' aparece " + posiciones.size() + " veces en el archivo " + carpeta.getNombre().getNombre().toUpperCase() + carpeta.getNombre().getExtension().toLowerCase() + ".");
                        for (int pos : posiciones) {
                            System.out.println("La palabra '" + palabra + "' se encuentra en la posicion: " + pos);
                        }
                    }
                }
            }
            else if (lugar.equals("/")) {
                String direccio = String.valueOf(switchdrives.get(0));
                String direccion = direccio;
                int numero = 0;
                while (direccio.lastIndexOf("/") >= 0) {
                    int indiceUltimoSlash = direccio.lastIndexOf("/");
                    direccio = direccio.substring(0, indiceUltimoSlash);
                    numero = numero + 1;
                }
                numero = numero - 1;
                if(Objects.equals(numero, 0)){
                    var ruta_revisar = new Switchdrive_NicolasMunoz_21052729(direccion).toString();
                    if (ruta.equals(ruta_revisar)){
                        var contenido_archivo = carpeta.getNombre().getContenido();
                        List<Integer> posiciones = new ArrayList<>();
                        String[] palabras = contenido_archivo.split("\\s+");
                        int posicion = 1;
                        for (String s : palabras) {
                            if (s.equalsIgnoreCase(palabra)) {
                                posiciones.add(posicion);
                            }
                            posicion++;
                        }
                        System.out.println("La palabra '" + palabra + "' aparece " + posiciones.size() + " veces en el archivo " + carpeta.getNombre().getNombre().toUpperCase() + carpeta.getNombre().getExtension().toLowerCase() + ".");
                        for (int pos : posiciones) {
                            System.out.println("La palabra '" + palabra + "' se encuentra en la posicion: " + pos);
                        }
                    }
                }
            }
            else if (ruta.contains("Direccion = " + lugar)){
                var contenido_archivo = carpeta.getNombre().getContenido();
                List<Integer> posiciones = new ArrayList<>();
                String[] palabras = contenido_archivo.split("\\s+");
                int posicion = 1;
                for (String s : palabras) {
                    if (s.equalsIgnoreCase(palabra)) {
                        posiciones.add(posicion);
                    }
                    posicion++;
                }
                System.out.println("La palabra '" + palabra + "' aparece " + posiciones.size() + " veces en el archivo " + carpeta.getNombre().getNombre().toUpperCase() + carpeta.getNombre().getExtension().toLowerCase() + ".");
                for (int pos : posiciones) {
                    System.out.println("La palabra '" + palabra + "' se encuentra en la posicion: " + pos);
                }
                break;
            }
            else if (directorio_actual.equals(ruta)) {
                String[] partes = lugar.split("\\.");
                if (lugar.equals(".")) {
                    var contenido_archivo = carpeta.getNombre().getContenido();
                    List<Integer> posiciones = new ArrayList<>();
                    String[] palabras = contenido_archivo.split("\\s+");
                    int posicion = 1;
                    for (String s : palabras) {
                        if (s.equalsIgnoreCase(palabra)) {
                            posiciones.add(posicion);
                        }
                        posicion++;
                    }
                    System.out.println("La palabra '" + palabra + "' aparece " + posiciones.size() + " veces en el archivo " + carpeta.getNombre().getNombre().toUpperCase() + carpeta.getNombre().getExtension().toLowerCase() + ".");
                    for (int pos : posiciones) {
                        System.out.println("La palabra '" + palabra + "' se encuentra en la posicion: " + pos);
                    }
                }
                else if (partes[0].equals("*")) {
                    var extension = carpeta.getNombre().getExtension();
                    if (extension.equals("." + partes[1].toLowerCase())) {
                        var contenido_archivo = carpeta.getNombre().getContenido();
                        List<Integer> posiciones = new ArrayList<>();
                        String[] palabras = contenido_archivo.split("\\s+");
                        int posicion = 1;
                        for (String s : palabras) {
                            if (s.equalsIgnoreCase(palabra)) {
                                posiciones.add(posicion);
                            }
                            posicion++;
                        }
                        System.out.println("La palabra '" + palabra + "' aparece " + posiciones.size() + " veces en el archivo " + carpeta.getNombre().getNombre().toUpperCase() + carpeta.getNombre().getExtension().toLowerCase() + ".");
                        for (int pos : posiciones) {
                            System.out.println("La palabra '" + palabra + "' se encuentra en la posicion: " + pos);
                        }
                    }
                }
                else if (partes.length == 2) {
                    var nombre_archivo = carpeta.getNombre().getNombre();
                    var extension_archivo = carpeta.getNombre().getExtension();
                    List<String> lista = new ArrayList<>();
                    lista.add(partes[0]);
                    lista.add("." + partes[1]);
                    if (nombre_archivo.equals(lista.get(0).toUpperCase()) && extension_archivo.equals(lista.get(1).toLowerCase())) {
                        var contenido_archivo = carpeta.getNombre().getContenido();
                        List<Integer> posiciones = new ArrayList<>();
                        String[] palabras = contenido_archivo.split("\\s+");
                        int posicion = 1;
                        for (String s : palabras) {
                            if (s.equalsIgnoreCase(palabra)) {
                                posiciones.add(posicion);
                            }
                            posicion++;
                        }
                        System.out.println("La palabra '" + palabra + "' aparece " + posiciones.size() + " veces.");
                        for (int pos : posiciones) {
                            System.out.println("La palabra '" + palabra + "' se encuentra en la posicion: " + pos);
                        }
                    }
                }
            }
            i = i + 1;
        }
    }
    /**
     * viewtrash: void
     */
    public void viewtrash() {
        List<Object> papelera = new ArrayList<>();
        papelera.addAll(papelerasmkdirs);
        papelera.addAll(papelerasfolders);
        for (Object carpeta : papelera) {
            System.out.println(carpeta);
        }
    }
    /**
     * restore: void
     * @param condicion: String;
     */
    public void restore(String condicion) {
        if (condicion.equals("*.*")){
            int i = papelerasfolders.size() - 1;
            while (-1 < i){
                var carpeta = papelerasfolders.get(i);
                var ele = carpeta.getElemento();
                var dire = ele.getDireccion();
                var archivo = ele.getNombre().getNombre();
                var archiv = ele.getNombre().getExtension();
                int h = 0;
                int j = 0;
                while (j < folders.size()){
                    var car = folders.get(j);
                    var dire2 = car.getDireccion();
                    var archivo2 = car.getNombre().getNombre();
                    var archiv2 = car.getNombre().getExtension();
                    if (archivo.equals(archivo2) && dire2.equals(dire) && archiv2.equals(archiv)){
                        h = 1;
                        break;
                    }
                    j = j + 1;
                }
                if (h == 0){
                    ele.setFechamodificacion(new Date());
                    folders.add(ele);
                    papelerasfolders.remove(carpeta);
                }
                else {
                    System.out.println("Anadir " + ele + " rompe la restriccion de unicidad \n");
                }
                i = i - 1;
            }
        }
        else {
            int i = papelerasmkdirs.size() - 1;
            while (-1 < i){
                var carpe = papelerasmkdirs.get(i);
                var le = carpe.getElemento();
                var dire = le.getDireccion();
                if (dire.contains(condicion)){
                    int h = 0;
                    int j = 0;
                    while (j < mkdirs.size()){
                        var carpe1 = mkdirs.get(j);
                        var dire1 = carpe1.getDireccion();
                        if (dire.equals(dire1)){
                            h = 1;
                            break;
                        }
                        j = j + 1;
                    }
                    if (h == 0){
                        le.setFechamodificacion(new Date());
                        mkdirs.add(le);
                        papelerasmkdirs.remove(carpe);
                    }
                    else {
                        System.out.println("Anadir " + le + " rompe la restriccion de unicidad \n");
                    }
                }
                i = i - 1;
            }
            int o = papelerasfolders.size() - 1;
            while (-1 < o){
                var carpeta = papelerasfolders.get(o);
                var ele = carpeta.getElemento();
                var dire = ele.getDireccion();
                var archivo = ele.getNombre().getNombre();
                var archiv = ele.getNombre().getExtension();
                if (dire.contains(condicion)) {
                    int h = 0;
                    int j = 0;
                    while (j < folders.size()) {
                        var car = folders.get(j);
                        var dire2 = car.getDireccion();
                        var archivo2 = car.getNombre().getNombre();
                        var archiv2 = car.getNombre().getExtension();
                        if (archivo.equals(archivo2) && dire2.equals(dire) && archiv2.equals(archiv)) {
                            h = 1;
                            break;
                        }
                        j = j + 1;
                    }
                    if (h == 0) {
                        ele.setFechamodificacion(new Date());
                        folders.add(ele);
                        papelerasfolders.remove(carpeta);
                    } else {
                        System.out.println("Anadir " + ele + " rompe la restriccion de unicidad \n");
                    }
                }
                o = o - 1;
            }
        }
    }
    /**
     * toString: String
     * @return: String;
     */
    @Override
    public String toString() {
        List<Object> carpetas = new ArrayList<>();
        carpetas.addAll(mkdirs);
        carpetas.addAll(folders);
        List<Object> papelera = new ArrayList<>();
        papelera.addAll(papelerasmkdirs);
        papelera.addAll(papelerasfolders);
        return "\nFileSystem = (\n" +
                "Nombre del Sistema = " + nombre + "\n" +
                "Fecha de creacion = " + fechaCreacion + "\n" +
                "Drives = " + drives + "\n" +
                "Usuarios = " + registers + "\n" +
                "Login = " + logins + "\n" +
                "Ruta = " + switchdrives + "\n" +
                "Carpetas = " + carpetas + "\n" +
                "Papelera = " + papelera + "\n" +
                ')';
    }
}