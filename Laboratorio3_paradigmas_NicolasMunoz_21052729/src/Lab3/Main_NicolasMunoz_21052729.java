package Lab3;
import java.util.*;
import java.util.Objects;
import java.util.Scanner;

public class Main_NicolasMunoz_21052729{
    /**
     * main: void
     * @param args: String[];
     */
    public static void main(String[] args) {
        var filesystem = new FileSystem_NicolasMunoz_21052729("");
        Scanner s = new Scanner(System.in);

        String eleccion;
        do {
            Menu();
            eleccion = s.nextLine();
            if (esEntero(eleccion)) {
                int numero1 = Integer.parseInt(eleccion);
                switch (numero1) {

                    case 0:
                        System.out.println("\nUsted a finalizado la prueba del codigo.");
                        System.exit(0);
                        break;

                    case 1:
                            System.out.print("ingrese nombre del sistema: ");
                            var nombre = s.nextLine();
                            if (!nombre.trim().isEmpty()) {
                                if (!Objects.equals(nombre, "")) {
                                    nombre = nombre.toUpperCase();
                                    filesystem = new FileSystem_NicolasMunoz_21052729(nombre);
                                    System.out.println(filesystem);
                                    System.out.println();
                                    break;
                                }
                                System.out.println("\nNombre de sistema no valido. \n");
                                break;
                            }
                            System.out.println("\nNo se ingreso ninguna cadena de texto. \n");
                            break;

                    case 2:
                        if(!Objects.equals(FileSystem_NicolasMunoz_21052729.getNombre(), "")) {
                            System.out.print("Ingrese letra de la unidad: ");
                            var letra = s.nextLine();
                            if (letra.length() == 1) {
                                if (esLetra(letra)) {
                                    letra = letra.toUpperCase();
                                    System.out.print("Ingrese nombre de la unidad: ");
                                    var name = s.nextLine();
                                    name = name.toUpperCase();
                                    System.out.print("Ingrese capacidad de la unidad: ");
                                    var capacidad = s.nextLine();
                                    if (esEntero(capacidad)) {
                                        int numero = Integer.parseInt(capacidad);
                                        filesystem.addDrive(letra.trim(), name.trim(), numero);
                                    }
                                    else {
                                        System.out.println("\nEl valor ingresado en la capacidad no corresponde a un valor int. \n");
                                        break;
                                    }
                                }
                                else {
                                    System.out.println("\nEl valor ingresado para la letra de la unidad no corresponde a una letra. \n");
                                    break;
                                }
                            }
                            else {
                                System.out.println("\nIngrese exactamente una letra. \n");
                                break;
                            }
                            System.out.println(filesystem);
                            System.out.println();
                            break;
                        }
                        System.out.println("\nIngrese nombre del sistema. \n");
                        break;

                    case 3:
                        if(!Objects.equals(FileSystem_NicolasMunoz_21052729.getNombre(), "")) {
                            System.out.print("Ingrese nombre del usuario: ");
                            var user_name = s.nextLine();
                            user_name = user_name.toUpperCase();
                            filesystem.register(user_name.trim());
                            System.out.println(filesystem);
                            System.out.println();
                            break;
                        }
                        System.out.println("\nIngrese nombre del sistema. \n");
                        break;

                    case 4:
                        if(!Objects.equals(FileSystem_NicolasMunoz_21052729.getNombre(), "")) {
                            System.out.print("Ingrese nombre del usuario con el que quiere iniciar sesion: ");
                            var user_log = s.nextLine();
                            user_log = user_log.toUpperCase();
                            filesystem.login(user_log.trim());
                            System.out.println(filesystem);
                            System.out.println();
                            break;
                        }
                        System.out.println("\nIngrese nombre del sistema. \n");
                        break;

                    case 5:
                        if(!Objects.equals(FileSystem_NicolasMunoz_21052729.getNombre(), "")) {
                            filesystem.logout();
                            System.out.println(filesystem);
                            System.out.println();
                            break;
                        }
                        System.out.println("\nIngrese nombre del sistema. \n");
                        break;

                    case 6:
                        if(!Objects.equals(FileSystem_NicolasMunoz_21052729.getNombre(), "")) {
                            System.out.print("Ingrese letra de la unidad a iniciar: ");
                            var letra = s.nextLine();
                            if (esLetra(letra)) {
                                letra = letra.toUpperCase();
                                filesystem.switchdrive(letra.trim());
                            } else {
                                System.out.println("\nEl valor ingresado para la letra a iniciar no corresponde a una letra. \n");
                            }
                            System.out.println(filesystem);
                            System.out.println();
                            break;
                        }
                        System.out.println("\nIngrese nombre del sistema. \n");
                        break;

                    case 7:
                        if(!Objects.equals(FileSystem_NicolasMunoz_21052729.getNombre(), "")) {
                            System.out.print("Ingrese nombre de la carpeta: ");
                            var carpeta = s.nextLine();
                            carpeta = carpeta.toUpperCase();
                            filesystem.mkdir(carpeta.trim());
                            System.out.println(filesystem);
                            System.out.println();
                            break;
                        }
                        System.out.println("\nIngrese nombre del sistema. \n");
                        break;

                    case 8:
                        if(!Objects.equals(FileSystem_NicolasMunoz_21052729.getNombre(), "")) {
                            System.out.print("Ingrese parametro: ");
                            var carpeta = s.nextLine();
                            carpeta = carpeta.toUpperCase();
                            filesystem.cd(carpeta.trim());
                            System.out.println(filesystem);
                            System.out.println();
                            break;
                        }
                        System.out.println("\nIngrese nombre del sistema. \n");
                        break;

                    case 9:
                        if(!Objects.equals(FileSystem_NicolasMunoz_21052729.getNombre(), "")) {
                            System.out.print("Ingrese nombre del archivo: ");
                            var name = s.nextLine();
                            name = name.toUpperCase();
                            System.out.print("Ingrese la extension del archivo: ");
                            var extension = s.nextLine();
                            System.out.print("Ingrese el contenido del archivo: ");
                            var contenido = s.nextLine();
                            extension = "." + extension;
                            extension = extension.toLowerCase();
                            if(extension.equals(".txt")) {
                                File_NicolasMunoz_21052729 MyFile = new Texto_NicolasMunoz_21052729(name.trim(), extension.trim(), contenido.trim());
                                filesystem.addFile(MyFile);
                                System.out.println(filesystem);
                                System.out.println();
                                break;
                            }
                            if (extension.equals(".py") || extension.equals(".rkt") || extension.equals(".java")) {
                                File_NicolasMunoz_21052729 MyFile = new CodigoFuente_NicolasMunoz_21052729(name.trim(), extension.trim(), contenido.trim());
                                filesystem.addFile(MyFile);
                                System.out.println(filesystem);
                                System.out.println();
                                break;
                            }
                            if (extension.equals(".docx") || extension.equals(".pdf") || extension.equals(".word")) {
                                File_NicolasMunoz_21052729 MyFile = new Documento_NicolasMunoz_21052729(name.trim(), extension.trim(), contenido.trim());
                                filesystem.addFile(MyFile);
                                System.out.println(filesystem);
                                System.out.println();
                                break;
                            }
                            else {
                                System.out.println("\nNo existe este tipo de extension. \n");
                            }
                        }
                        System.out.println("\nIngrese nombre del sistema. \n");
                        break;

                    case 10:
                        if(!Objects.equals(FileSystem_NicolasMunoz_21052729.getNombre(), "")) {
                            System.out.print("Ingrese condicion: ");
                            var condicion = s.nextLine();
                            condicion = condicion.toUpperCase();
                            filesystem.del(condicion.trim());
                            System.out.println(filesystem);
                            System.out.println();
                            break;
                        }
                        System.out.println("\nIngrese nombre del sistema. \n");
                        break;

                    case 11:
                        if(!Objects.equals(FileSystem_NicolasMunoz_21052729.getNombre(), "")) {
                            System.out.print("Ingrese archivo a copiar: ");
                            var name = s.nextLine();
                            System.out.print("Ingrese ruta donde copiar: ");
                            var ruta = s.nextLine();
                            name = name.toUpperCase();
                            ruta = ruta.toUpperCase();
                            filesystem.copy(name.trim(), ruta.trim());
                            System.out.println(filesystem);
                            System.out.println();
                            break;
                        }
                        System.out.println("\nIngrese nombre del sistema. \n");
                        break;

                    case 12:
                        if(!Objects.equals(FileSystem_NicolasMunoz_21052729.getNombre(), "")) {
                            System.out.print("Ingrese archivo a mover: ");
                            var name = s.nextLine();
                            System.out.print("Ingrese ruta donde mover: ");
                            var ruta = s.nextLine();
                            name = name.toUpperCase();
                            ruta = ruta.toUpperCase();
                            filesystem.move(name.trim(), ruta.trim());
                            System.out.println(filesystem);
                            System.out.println();
                            break;
                        }
                        System.out.println("\nIngrese nombre del sistema. \n");
                        break;

                    case 13:
                        if(!Objects.equals(FileSystem_NicolasMunoz_21052729.getNombre(), "")) {
                            System.out.print("Ingrese nombre del archvio: ");
                            var name = s.nextLine();
                            System.out.print("Ingrese nuevo nombre: ");
                            var nuevo_name = s.nextLine();
                            name = name.toUpperCase();
                            nuevo_name = nuevo_name.toUpperCase();
                            filesystem.ren(name.trim(), nuevo_name.trim());
                            System.out.println(filesystem);
                            System.out.println();
                            break;
                        }
                        System.out.println("\nIngrese nombre del sistema. \n");
                        break;

                    case 14:
                        if(!Objects.equals(FileSystem_NicolasMunoz_21052729.getNombre(), "")) {
                            List<String> listaCadenas = new ArrayList<>();
                            System.out.print("Ingrese paramtro 1: ");
                            var condicion = s.nextLine();
                            condicion = condicion.toUpperCase().trim();
                            if (condicion.equals("")){
                                filesystem.dir(listaCadenas);
                                System.out.println();
                            }
                            else if ("/s".equals(condicion)){
                                listaCadenas.add(condicion);
                                System.out.print("Desea ingrese paramtro 2 (si la respuesta es no deje la vacio): ");
                                var condicion2 = s.nextLine();
                                condicion2 = condicion2.toUpperCase().trim();
                                if (!condicion2.equals("")){
                                    listaCadenas.add(condicion2);
                                }
                                filesystem.dir(listaCadenas);
                                System.out.println();
                            }
                            else {
                                listaCadenas.add(condicion);
                                filesystem.dir(listaCadenas);
                                System.out.println();
                            }
                            break;
                        }
                        System.out.println("Ingrese nombre del sistema. \n");
                        break;

                    case 15:
                        if(!Objects.equals(FileSystem_NicolasMunoz_21052729.getNombre(), "")) {
                            System.out.print("Ingrese letra: ");
                            var letra = s.nextLine();
                            System.out.print("Ingrese nuevo nombre del sistema: ");
                            var nuevo_nombre = s.nextLine();
                            letra = letra.toUpperCase();
                            nuevo_nombre = nuevo_nombre.toUpperCase();
                            filesystem.format(letra.trim(), nuevo_nombre.trim());
                            System.out.println(filesystem);
                            System.out.println();
                            break;
                        }
                        System.out.println("\nIngrese nombre del sistema. \n");
                        break;

                    case 16:
                        if(!Objects.equals(FileSystem_NicolasMunoz_21052729.getNombre(), "")) {
                            System.out.print("Ingrese contrasena: ");
                            var password = s.nextLine();  
                            if (password.length() >= 6){
                                if (password.matches(".*[a-zA-Z].*[0-9].*")) {
                                    System.out.print("Ingrese carpeta o archivo a encriptar: ");
                                    var elemento = s.nextLine();
                                    elemento = elemento.toUpperCase();
                                    filesystem.encrypt(password.trim(), elemento.trim()); 
                                    System.out.println(filesystem);
                                    System.out.println();
                                }
                                else {
                                    System.out.println("\nLa contrasena debe contener tanto letras como numeros. \n");
                                }
                            }
                            else {
                                System.out.println("\nEl largo no es el valido, la clave debe ser de largo 6. \n");
                            }
                            break;
                        }
                        System.out.println("\nIngrese nombre del sistema. \n");
                        break;

                    case 17:
                        if(!Objects.equals(FileSystem_NicolasMunoz_21052729.getNombre(), "")) {
                            System.out.print("Ingrese contrasena: ");
                            var password = s.nextLine();
                            System.out.print("Ingrese carpeta o archivo a desencriptar: ");
                            var elemento = s.nextLine();
                            elemento = elemento.toUpperCase();
                            filesystem.decrypt(password.trim(), elemento.trim());
                            System.out.println(filesystem);
                            System.out.println();
                            break;
                        }
                        System.out.println("\nIngrese nombre del sistema. \n");
                        break;

                    case 18:
                        if(!Objects.equals(FileSystem_NicolasMunoz_21052729.getNombre(), "")) {
                            System.out.print("Ingrese palabra: ");
                            var palabra = s.nextLine();
                            System.out.print("Ingrese ruta o archivo: ");
                            var ruta = s.nextLine();
                            ruta = ruta.toUpperCase();
                            filesystem.grep(palabra.trim(), ruta.trim());
                            System.out.println();
                            break;
                        }
                        System.out.println("\nIngrese nombre del sistema. \n");
                        break;

                    case 19:
                        if(!Objects.equals(FileSystem_NicolasMunoz_21052729.getNombre(), "")) {
                            filesystem.viewtrash();
                            System.out.println();
                            break;
                        }
                        System.out.println("\nIngrese nombre del sistema. \n");
                        break;

                    case 20:
                        if(!Objects.equals(FileSystem_NicolasMunoz_21052729.getNombre(), "")) {
                            System.out.print("Ingrese condicion: ");
                            var condicion = s.nextLine();
                            condicion = condicion.toUpperCase();
                            filesystem.restore(condicion.trim());
                            System.out.println(filesystem);
                            System.out.println();
                            break;
                        }
                        System.out.println("\nIngrese nombre del sistema \n");
                        break;

                    default:
                        System.out.println("\n" + numero1 + " no es una opcion valida. \n");
                        break;
                }
            }
            else {
                System.out.println("\nEl valor ingresado  no corresponde a un valor int. \n");
            }
        }
        while (true);
    }
    /**
     * Menu: void
     */
    private static void Menu(){
        System.out.println("--------------------Menu--------------------");
        System.out.println("0. Salir.");
        System.out.println("1. Crear sistema.");
        System.out.println("2. Ingresar unidad Drive.");
        System.out.println("3. Ingresar usuario.");
        System.out.println("4. Iniciar sesion.");
        System.out.println("5. Cerrar sesion.");
        System.out.println("6. Iniciar unidad Drive.");
        System.out.println("7. Crear carpeta.");
        System.out.println("8. Ingresar a una ruta.");
        System.out.println("9. Crear archivo.");
        System.out.println("10. Borrar elemento.");
        System.out.println("11. Copiar elemento.");
        System.out.println("12. Mover elemento.");
        System.out.println("13. Cambiar nombre.");
        System.out.println("14. Mostrar elementos.");
        System.out.println("15. Formatear unidad Drive.");
        System.out.println("16. Encriptar elemento.");
        System.out.println("17. Desencriptar elemnto.");
        System.out.println("18. Mostrar contenido de archivo.");
        System.out.println("19. Mostrar papelera.");
        System.out.println("20. Restaurar elemento.");
        System.out.println("--------------------------------------------");
        System.out.print("Ingrese opcion: ");
    }
    /**
     * esEntero: boolean
     * @param numero: String;
     * @return: String;
     */
    public static boolean esEntero(String numero) {
        return numero.matches("\\d+");
    }
    /**
     * esLetra: boolean
     * @param letra: String;
     * @return: String;
     */
    public static boolean esLetra(String letra) {
        return letra.matches("[a-zA-Z]");
    }
}