package org.example;
import java.util.*;
public class FileSystem {
    private static String nombre;
    private Date fechaCreacion;
    private final List<Drives> drives;
    private final List<Register> registers;
    private final List<Login> logins;
    private final List<Switchdrive> switchdrives;
    private final List<Mkdir> mkdirs;
    private final List<Folder> folders;
    private final List<Papelera> papeleras;
    private final List<Papelera2> papelera2s;
    private Date fechaswish;
    public static String getNombre() {
        return nombre;
    }
    public Date getFechaCreacion() {
        return fechaCreacion;
    }
    public FileSystem(String nombre) {
        FileSystem.nombre = nombre;
        if(!Objects.equals(nombre, "")) {
            this.fechaCreacion = new Date();
        }
        this.drives = new ArrayList<>();
        this.registers = new ArrayList<>();
        this.logins = new ArrayList<>();
        this.switchdrives = new ArrayList<>();
        this.mkdirs = new ArrayList<>();
        this.folders = new ArrayList<>();
        this.papeleras = new ArrayList<>();
        this.papelera2s = new ArrayList<>();
    }
    public void addDrive(String letra, String name, int capacidad){
        var drive = new Drives(letra, name, capacidad);
        List<String> currentletra = new ArrayList<>();
        for (Drives disco : drives){
            currentletra.add(disco.getletra());
        }
        if (!currentletra.contains(letra)) {
            drives.add(drive);
        }
    }
    public void register(String usuario){
        var user = new Register(usuario);
        List<String> currentuser = new ArrayList<>();
        for (Register disco : registers){
            currentuser.add(disco.getUsuario());
        }
        if (drives.size() != 0) {
            if(!Objects.equals(FileSystem.getNombre(), "")) {
                if (!currentuser.contains(usuario)) {
                    registers.add(user);
                }
            }
        }
        else {
            System.out.println("Ingrese unidad drive \n");
        }
    }
    public void login(String user_login){
        var log = new Login(user_login);
        List<String> currentu = new ArrayList<>();
        for (Register disco : registers){
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
    public void logout(){
        if (logins.size() != 0){
            logins.clear();
            switchdrives.clear();
        }
        else {
            System.out.println("No existe usuario logeado \n");
        }
    }
    public void switchdrive(String letra_ruta){
        var a = "Dirección = " + letra_ruta + ":/";
        var ruta = new Switchdrive(a);
        List<String> currentruta = new ArrayList<>();
        for (Drives disco : drives){
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
    public void mkdir(String nombre_carpeta){
        List<String> currentrut = new ArrayList<>();
        for (Login dis : logins){
            currentrut.add(dis.getnombre());
        }
        List<String> currentruto = new ArrayList<>();
        for (Mkdir disc : mkdirs){
            currentruto.add(disc.getDireccion());
        }
        if(switchdrives.size() != 0) {
            String ruta = String.valueOf(switchdrives.get(0));
            var direccion = ruta + nombre_carpeta + "/";
            if (!currentruto.contains(direccion)) {
                var mk = new Mkdir(direccion, currentrut.get(0));
                mkdirs.add(mk);
            }
            else {
                System.out.println("Ya existe una carpeta con este noombre \n");
            }
        }
    }
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
                var ruta = new Switchdrive(subcadena1);
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
                var ruta = new Switchdrive(direccion);
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
                var ruta = new Switchdrive(direccion);
                switchdrives.clear();
                switchdrives.add(ruta);
            }
        }
        else {
            String directorio = String.valueOf(switchdrives.get(0));
            String direccion = parametro;
            direccion = direccion.replace("./", "");
            List<String> currens = new ArrayList<>();
            for (Mkdir disco : mkdirs){
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
                        Switchdrive ruta;
                        if(dire.equals(direccion)) {
                            ruta = new Switchdrive(listaFiltrada.get(1));
                        }
                        else {
                            ruta = new Switchdrive(listaFiltrada.get(0));
                        }
                        switchdrives.set(0, ruta);
                    }
                    else {
                        var ruta = new Switchdrive(listaFiltrada.get(0));
                        switchdrives.set(0, ruta);
                    }
                }
            }
            else {
                System.out.println("No existe ninguna carpeta con ese nombre \n");
            }
        }
    }
    public static List<String> filtrarPorCadena(List<String> listaOriginal, String cadenaFiltro) {
        List<String> listaFiltrada = new ArrayList<>();
        for (String direccion : listaOriginal) {
            if (direccion.contains(cadenaFiltro)) {
                listaFiltrada.add(direccion);
            }
        }
        return listaFiltrada;
    }
    public static int contarSlashes(String direccion) {
        String[] partes = direccion.split("/");
        return partes.length - 1;
    }
    public void addFile(File nombre) {
        String direccion = String.valueOf(switchdrives.get(0));
        List<String> currentrut = new ArrayList<>();
        for (Login dis : logins){
            currentrut.add(dis.getnombre());
        }
        List<File> currentruta = new ArrayList<>();
        for (Folder disc : folders){
            currentruta.add(disc.getNombre());
        }
        List<String> currentrutas = new ArrayList<>();
        for (File disc : currentruta){
            currentrutas.add(disc.getNombre());
        }
        var name = nombre.getNombre();
        if (!currentrutas.contains(name)) {
            if (direccion.length() == 15) {
                var contenido = new Folder(nombre, currentrut.get(0), direccion);
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
                        var contenido = new Folder(nombre, currentrut.get(0), direccion);
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
                        var contenido = new Folder(nombre, currentrut.get(0), direccion);
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
                                var contenido = new Folder(nombre, currentrut.get(0), direccion);
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
    public void del(String condicion) {
        int dotIndex = condicion.lastIndexOf(".");
        if (condicion.equals("*.*") || condicion.equals("*")) {
            String directorio = String.valueOf(switchdrives.get(0));
            List<String> s = new ArrayList<>();
            for (Folder disco : folders) {
                s.add(disco.getPrueba());
            }
            List<String> listaFiltrada = filtrarPorCadena(s, directorio);
            Iterator<Folder> iterator = folders.iterator();
            while (iterator.hasNext()) {
                Folder f = iterator.next();
                for (String direccion : listaFiltrada) {
                    if (f.getPrueba().equals(direccion)) {
                        var p = new Papelera(f);
                        f.setFechamodificacion(new Date());
                        papeleras.add(p);
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
                System.out.println(direccion);
                String directorio = String.valueOf(switchdrives.get(0));
                int i = 0;
                while (i < folders.size()) {
                    var carpeta = folders.get(i);
                    var ruta = carpeta.getDireccion();
                    System.out.println(ruta);
                    if (ruta.equals(directorio)) {
                        var extension = carpeta.getNombre().getExtension();
                        System.out.println(extension);
                        if (extension.equals(direccion)) {
                            carpeta.setFechamodificacion(new Date());
                            var p = new Papelera(carpeta);
                            papeleras.add(p);
                            folders.remove(folders.get(i));
                            i = i - 1;
                        }
                    }
                    i = i + 1;
                }
            }int dotIndexs = condicion.lastIndexOf("*");
            if (dotIndexs != -1) {
                System.out.println(folders);
                String resultad = condicion.substring(0, dotIndexs);
                String[] partes = condicion.split("\\.");
                List<String> lista = new ArrayList<>();
                lista.add(partes[0]);
                lista.add("." + partes[1]);
                var extensio = lista.get(1).toLowerCase();
                String directorio = String.valueOf(switchdrives.get(0));
                System.out.println(directorio);
                int i = 0;
                while (i < folders.size()) {
                    var carpeta = folders.get(i);
                    var ruta = carpeta.getDireccion();
                    System.out.println(ruta);
                    if (ruta.equals(directorio)) {
                        var extension = carpeta.getNombre().getExtension();
                        var name = carpeta.getNombre().getNombre();
                        System.out.println(name);
                        System.out.println(resultad);
                        System.out.println(extension);
                        System.out.println(extensio);
                        if (extension.equals(extensio) && name.startsWith(resultad.trim())) {
                            carpeta.setFechamodificacion(new Date());
                            var p = new Papelera(carpeta);
                            papeleras.add(p);
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
                         System.out.println(ruta);
                         if (ruta.equals(directorio)) {
                             var extension = carpeta.getNombre().getExtension();
                             var name = carpeta.getNombre().getNombre();
                             System.out.println(extension);
                             if (extension.equals(extensio) && name.equals(nombre)) {
                                 carpeta.setFechamodificacion(new Date());
                                 var p = new Papelera(carpeta);
                                 papeleras.add(p);
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
            for (Mkdir disco : mkdirs) {
                currens.add(disco.getPrueba());
            }
            List<String> listaFiltrada = filtrarPorCadena(currens, directorio);
            if (listaFiltrada.size() != 0) {
                List<String> listaFiltrada1 = filtrarPorCadena(listaFiltrada, condicion);
                Iterator<Mkdir> iterator = mkdirs.iterator();
                while (iterator.hasNext()) {
                    Mkdir f = iterator.next();
                    for (String direccion : listaFiltrada1) {
                        if (f.getPrueba().equals(direccion)) {
                            var p = new Papelera2(f);
                            f.setFechamodificacion(new Date());
                            papelera2s.add(p);
                            iterator.remove();
                            break;
                        }
                    }
                }
                List<String> s = new ArrayList<>();
                for (Folder disco : folders) {
                    s.add(disco.getPrueba());
                }
                List<String> listaFiltrada2 = filtrarPorCadena(s, directorio);
                if (listaFiltrada2.size() != 0) {
                    List<String> listaFiltrada3 = filtrarPorCadena(listaFiltrada2, condicion);
                    Iterator<Folder> iterator2 = folders.iterator();
                    while (iterator2.hasNext()) {
                        Folder f = iterator2.next();
                        for (String direccion : listaFiltrada3) {
                            if (f.getPrueba().equals(direccion)) {
                                f.setFechamodificacion(new Date());
                                var p = new Papelera(f);
                                papeleras.add(p);
                                iterator2.remove();
                                break;
                            }
                        }
                    }
                }
            }
        }
    }
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
                                var contenido = new Folder(archivo, user, rut);
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
                                var contenido = new Folder(archivo, user, rut);
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
                var nueva_dire = "Dirección = " + ruta;
                List<String> currens = new ArrayList<>();
                for (Mkdir disco : mkdirs) {
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
                            var fol = new Folder(archivo, use, nueva_dire);
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
            for (Mkdir disco : mkdirs){
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
                        for (Mkdir disco : mkdirs) {
                            currentletra.add(disco.getDireccion());
                        }
                        var ruta1 = "Dirección = " + ruta;
                        if (currentletra.contains(ruta1)) {
                            var dire_nueva = dire.replaceFirst(directorio, "");
                            dire_nueva = ruta1 + dire_nueva;
                            if (!currentletra.contains(dire_nueva)) {
                                var mk = new Mkdir(dire_nueva, user);
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
            for (Folder disco : folders){
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
                        for (Mkdir disco : mkdirs) {
                            currentletra.add(disco.getDireccion());
                        }
                        var ruta1 = "Dirección = " + ruta;
                        if (currentletra.contains(ruta1)) {
                            var dire_nueva = dire.replaceFirst(directorio, "");
                            dire_nueva = ruta1 + dire_nueva;
                            int j = 0;
                            while (j < valor) {
                                var carpeta1 = folders.get(j);
                                var archivo1 = carpeta1.getNombre();
                                var rut1 = carpeta1.getDireccion();
                                if (!archivo1.equals(archivos) && rut1.equals(dire_nueva)) {
                                    var fol = new Folder(archivos, user, dire_nueva);
                                    fol.setFechaCreacion(fecha);
                                    fol.setFechamodificacion(new Date());
                                    folders.add(fol);
                                }
                                else if (archivo1.equals(archivos) && (rut1.equals(dire_nueva) || rut1.equals(directorio1))){
                                    carpeta1.setFechamodificacion(new Date());
                                    carpeta1.setNombre(archivos);
                                }
                                else {
                                    var fol = new Folder(archivos, user, dire_nueva);
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
                        System.out.println(carpeta);
                        var archivo = carpeta.getNombre();
                        int j = 0;
                        while (j < mkdirs.size()) {
                            var carpeta2 = mkdirs.get(j);
                            var rut = carpeta2.getDireccion();
                            String[] partess = rut.split("=");
                            String resultado = partess[1].trim();
                            var user = carpeta2.getUsuario_log();
                            if (resultado.equals(ruta)) {
                                var contenido = new Folder(archivo, user, rut);
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
                                var contenido = new Folder(archivo, user, rut);
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
            for (Mkdir disco : mkdirs){
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
                        for (Mkdir disco : mkdirs) {
                            currentletra.add(disco.getDireccion());
                        }
                        var ruta1 = "Dirección = " + ruta;
                        if (currentletra.contains(ruta1)) {
                            var dire_nueva = dire.replaceFirst(directorio, "");
                            dire_nueva = ruta1 + dire_nueva;
                            if (!currentletra.contains(dire_nueva)) {
                                var mk = new Mkdir(dire_nueva, user);
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
            for (Folder disco : folders){
                currentletrass.add(disco.getDireccion());
            }
            System.out.println(currentletrass);
            System.out.println(directorio1);
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
                    System.out.println(carpeta);
                    var dire = carpeta.getDireccion();
                    System.out.println(dire);
                    if (dire.contains(directorio1)) {
                        Date fecha = carpeta.getFechaCreacion();
                        var user = carpeta.getUsuario_log();
                        var archivos = carpeta.getNombre();
                        List<String> currentletra = new ArrayList<>();
                        for (Mkdir disco : mkdirs) {
                            currentletra.add(disco.getDireccion());
                        }
                        var ruta1 = "Dirección = " + ruta;
                        System.out.println(ruta1);
                        if (currentletra.contains(ruta1)) {
                            var dire_nueva = dire.replaceFirst(directorio, "");
                            dire_nueva = ruta1 + dire_nueva;
                            int j = i;
                            while (-1 < j) {
                                var carpeta1 = folders.get(j);
                                System.out.println(carpeta1);
                                var archivo1 = carpeta1.getNombre();
                                var rut1 = carpeta1.getDireccion();
                                if (!archivo1.equals(archivos) && rut1.equals(dire_nueva)) {
                                    var fol = new Folder(archivos, user, dire_nueva);
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
                                    var fol = new Folder(archivos, user, dire_nueva);
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
                    System.out.println("Si no se ingresa nada solo se mostra el contenido del directorio actual, excluyendo subdirectorios y contenido oculto.\nSi ingresa '/s' se mostrara el contenido del directorio actual junto a subdirectorios, pero excluyendo el contenido oculto.\nSi ingresa '/a' se mostrara solo el contenido del directorio actual incluyendo el contenido oculto.\nSi ingresa '/s /a' se mostra el contenido del directorio actual y subdirectorios, incluyendo contenido oculto.\nSi ingresa '/o N' se mostrara el contenido del directorio actual en orden alfabético ascendente.\nSi ingresa '/o D' se mostrara el contenido del directorio actual según fecha de creación, en orden ascendente.\nSi ingresa '/o -D' se mostrara el contenido del directorio actual según fecha de creación, en orden descendente.\nSi ingresa cualquier otra cosa se mostrara 'Este comando no es valido'\n");
                }
            }
        }
    }
    public static void ordenarPorFecha(List<String> carpetas) {
        carpetas.sort((carpeta1, carpeta2) -> {
            String fecha1 = obtenerFechaCreacion(carpeta1);
            String fecha2 = obtenerFechaCreacion(carpeta2);
            return fecha1.compareTo(fecha2);
        });
    }
    public static String obtenerFechaCreacion(String carpeta) {
        int inicio = carpeta.indexOf("Fecha de creación = ");
        if (inicio != -1) {
            inicio += "Fecha de creación = ".length();
            int fin = carpeta.indexOf(",", inicio);
            if (fin != -1) {
                return carpeta.substring(inicio, fin).trim();
            }
        }
        return "";
    }
    public void format(String letra, String nuevo) {
        List<String> currentletra = new ArrayList<>();
        for (Drives disco : drives){
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
                    var letra1 = "Dirección = " + letra + ":/";
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
    public void encrypt(String password, String elemnto_encriptar){
    }
    public void decrypt(String password, String elemnto_encriptar){
    }
    public void grep(String palabra, String lugar){
        var directorio_actual = switchdrives.get(0).toString();
        int i = 0;
        while (i < folders.size()){
            var carpeta = folders.get(i);
            var ruta = carpeta.getDireccion();
            if (directorio_actual.equals(ruta)) {
                String[] partes = lugar.split("\\.");
                if (partes.length == 2) {
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
                            System.out.println("La palabra '" + palabra + "' se encuentra en la posición: " + pos);
                        }
                    }
                }
                else if (lugar.equals(".")) {
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
                        System.out.println("La palabra '" + palabra + "' se encuentra en la posición: " + pos);
                    }
                }
            }
            i = i + 1;
        }
    }
    public void viewtrash() {
        List<Object> unidas2 = new ArrayList<>();
        unidas2.addAll(papelera2s);
        unidas2.addAll(papeleras);
        System.out.println(unidas2);
    }
    public void restore(String condicion) {
        if (condicion.equals("*.*")){
            int i = papeleras.size() - 1;
            while (-1 < i){
                var carpeta = papeleras.get(i);
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
                    papeleras.remove(carpeta);
                }
                else {
                    System.out.println("Anadir " + ele + " rompe la restriccion de unicidad \n");
                }
                i = i - 1;
            }
        }
        else {
            int i = papelera2s.size() - 1;
            while (-1 < i){
                var carpe = papelera2s.get(i);
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
                        papelera2s.remove(carpe);
                    }
                    else {
                        System.out.println("Anadir " + le + " rompe la restriccion de unicidad \n");
                    }
                }
                i = i - 1;
            }
            int o = papeleras.size() - 1;
            while (-1 < o){
                var carpeta = papeleras.get(o);
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
                        papeleras.remove(carpeta);
                    } else {
                        System.out.println("Anadir " + ele + " rompe la restriccion de unicidad \n");
                    }
                }
                o = o - 1;
            }
        }
    }
    @Override
    public String toString() {
        List<Object> unidas = new ArrayList<>();
        unidas.addAll(mkdirs);
        unidas.addAll(folders);
        List<Object> unidas2 = new ArrayList<>();
        unidas2.addAll(papelera2s);
        unidas2.addAll(papeleras);
        return "FileSystem{\n" +
                "Nombre del Sistema = " + nombre + "\n" +
                "Fecha de creación = " + fechaCreacion + "\n" +
                "Drives = " + drives + "\n" +
                "Usuarios = " + registers + "\n" +
                "Login = " + logins + "\n" +
                "Ruta = " + switchdrives + "\n" +
                "Carpetas = " + unidas + "\n" +
                "Papelera = " + unidas2 + "\n" +
                '}';
    }
}