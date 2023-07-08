package org.example;
import java.util.*;
import java.util.Objects;
import java.util.Scanner;

public class Main{
    public static void main(String[] args) {
        var filesystem = new FileSystem("");
        Scanner s = new Scanner(System.in);

        String eleccion;
        do {
            Menu();
            eleccion = s.nextLine();
            if (esEntero(eleccion)) {
                int numero1 = Integer.parseInt(eleccion);
                switch (numero1) {

                    case 0:
                        System.out.println("Salir \n");
                        System.exit(0);
                        break;

                    case 1:
                            System.out.println("ingrese nombre del sistema: ");
                            var nombre = s.nextLine();
                            if (!nombre.trim().isEmpty()) {
                                if (!Objects.equals(nombre, "")) {
                                    String mayus = nombre.toUpperCase();
                                    filesystem = new FileSystem(mayus);
                                    System.out.println(filesystem);
                                    System.out.println();
                                    break;
                                }
                                System.out.println("Nombre de sistema no valido \n");
                                break;
                            }
                            System.out.println("No se ingresó ninguna cadena de texto \n");
                            break;

                    case 2:
                        if(!Objects.equals(FileSystem.getNombre(), "")) {
                            System.out.println("Ingrese letra de la unidad: ");
                            var letra = s.nextLine();
                            if (letra.length() == 1) {
                                if (esLetra(letra)) {
                                    char letra1 = letra.charAt(0);
                                    String letraString = Character.toString(letra1);
                                    String mayus = letraString.toUpperCase();
                                    System.out.println("Ingrese nombre de la unidad: ");
                                    var name = s.nextLine();
                                    String mayus_n = name.toUpperCase();
                                    System.out.println("Ingrese capacidad de la unidad: ");
                                    var capacidad = s.nextLine();
                                    if (esEntero(capacidad)) {
                                        int numero = Integer.parseInt(capacidad);
                                        filesystem.addDrive(mayus.trim(), mayus_n.trim(), numero);
                                    }
                                    else {
                                        System.out.println("El valor ingresado en la capacidad no corresponde a un valor int. \n");
                                        break;
                                    }
                                }
                                else {
                                    System.out.println("El valor ingresado para la letra de la unidad no corresponde a una letra. \n");
                                    break;
                                }
                            }
                            else {
                                System.out.println("Ingrese exactamente una letra \n");
                                break;
                            }
                            System.out.println(filesystem);
                            System.out.println();
                            break;
                        }
                        System.out.println("Ingrese nombre del sistema \n");
                        break;

                    case 3:
                        if(!Objects.equals(FileSystem.getNombre(), "")) {
                            System.out.println("Ingrese nombre del usuario: ");
                            var user_name = s.nextLine();
                            String mayus_u = user_name.toUpperCase();
                            filesystem.register(mayus_u.trim());
                            System.out.println(filesystem);
                            System.out.println();
                            break;
                        }
                        System.out.println("Ingrese nombre del sistema \n");
                        break;

                    case 4:
                        if(!Objects.equals(FileSystem.getNombre(), "")) {
                            System.out.println("Ingrese nombre del usuario con el que quiere iniciar sesión: ");
                            var user_log = s.nextLine();
                            String mayus_u = user_log.toUpperCase();
                            filesystem.login(mayus_u.trim());
                            System.out.println(filesystem);
                            System.out.println();
                            break;
                        }
                        System.out.println("ingrese nombre del sistema \n");
                        break;

                    case 5:
                        if(!Objects.equals(FileSystem.getNombre(), "")) {
                            filesystem.logout();
                            System.out.println(filesystem);
                            System.out.println();
                            break;
                        }
                        System.out.println("Ingrese nombre del sistema \n");
                        break;

                    case 6:
                        if(!Objects.equals(FileSystem.getNombre(), "")) {
                            System.out.println("Ingrese letra de la unidad a iniciar: ");
                            var letra_switch = s.nextLine();
                            if (esLetra(letra_switch)) {
                                char letra1 = letra_switch.charAt(0);
                                String letraString = Character.toString(letra1);
                                String mayus = letraString.toUpperCase();
                                filesystem.switchdrive(mayus.trim());
                            } else {
                                System.out.println("El valor ingresado para la letra a iniciar no corresponde a una letra. \n");
                            }
                            System.out.println(filesystem);
                            System.out.println();
                            break;
                        }
                        System.out.println("Ingrese nombre del sistema \n");
                        break;

                    case 7:
                        if(!Objects.equals(FileSystem.getNombre(), "")) {
                            System.out.println("Ingrese nombre de la carpeta: ");
                            var carpeta = s.nextLine();
                            String mayus_c = carpeta.toUpperCase();
                            filesystem.mkdir(mayus_c.trim());
                            System.out.println(filesystem);
                            System.out.println();
                            break;
                        }
                        System.out.println("Ingrese nombre del sistema \n");
                        break;

                    case 8:
                        if(!Objects.equals(FileSystem.getNombre(), "")) {
                            System.out.println("Ingrese parametro: ");
                            var carpeta = s.nextLine();
                            String mayus_c = carpeta.toUpperCase();
                            filesystem.cd(mayus_c.trim());
                            System.out.println(filesystem);
                            System.out.println();
                            break;
                        }
                        System.out.println("Ingrese nombre del sistema \n");
                        break;

                    case 9:
                        if(!Objects.equals(FileSystem.getNombre(), "")) {
                            System.out.println("Ingrese nombre del archivo: ");
                            var name = s.nextLine();
                            var mayus = name.toUpperCase();
                            System.out.println("Ingrese la extension del archivo: ");
                            var extension = s.nextLine();
                            System.out.println("Ingrese el contenido del archivo: ");
                            var contenido = s.nextLine();
                            var extension_si = "." + extension;
                            extension_si = extension_si.toLowerCase();
                            if(extension_si.equals(".txt")) {
                                File MyFile = new Texto(mayus.trim(), extension_si.trim(), contenido.trim());
                                filesystem.addFile(MyFile);
                                System.out.println(filesystem);
                                System.out.println();
                                break;
                            }
                            if (extension_si.equals(".py") || extension_si.equals(".rkt") || extension_si.equals(".java")) {
                                File MyFile = new Archivo(mayus.trim(), extension_si.trim(), contenido.trim());
                                filesystem.addFile(MyFile);
                                System.out.println(filesystem);
                                System.out.println();
                                break;
                            }
                            if (extension_si.equals(".docx") || extension_si.equals(".pdf") || extension_si.equals(".word")) {
                                File MyFile = new Documento(mayus.trim(), extension_si.trim(), contenido.trim());
                                filesystem.addFile(MyFile);
                                System.out.println(filesystem);
                                System.out.println();
                                break;
                            }
                            else {
                                System.out.println("No existe este tipo de extensión");
                            }
                        }
                        System.out.println("Ingrese nombre del sistema \n");
                        break;

                    case 10:
                        if(!Objects.equals(FileSystem.getNombre(), "")) {
                            System.out.println("Ingrese condicion: ");
                            var name = s.nextLine();
                            String mayus_c = name.toUpperCase();
                            filesystem.del(mayus_c.trim());
                            System.out.println(filesystem);
                            System.out.println();
                            break;
                        }
                        System.out.println("Ingrese nombre del sistema \n");
                        break;

                    case 11:
                        if(!Objects.equals(FileSystem.getNombre(), "")) {
                            System.out.println("Ingrese archivo a copiar: ");
                            var name = s.nextLine();
                            System.out.println("Ingrese ruta donde copiar: ");
                            var ruta = s.nextLine();
                            String mayus_c = name.toUpperCase();
                            ruta = ruta.toUpperCase();
                            filesystem.copy(mayus_c.trim(), ruta);
                            System.out.println(filesystem);
                            System.out.println();
                            break;
                        }
                        System.out.println("Ingrese nombre del sistema \n");
                        break;

                    case 12:
                        if(!Objects.equals(FileSystem.getNombre(), "")) {
                            System.out.println("Ingrese archivo a mover: ");
                            var name = s.nextLine();
                            System.out.println("Ingrese ruta donde mover: ");
                            var ruta = s.nextLine();
                            String mayus_c = name.toUpperCase();
                            ruta = ruta.toUpperCase();
                            filesystem.move(mayus_c.trim(), ruta);
                            System.out.println(filesystem);
                            System.out.println();
                            break;
                        }
                        System.out.println("Ingrese nombre del sistema \n");
                        break;

                    case 13:
                        if(!Objects.equals(FileSystem.getNombre(), "")) {
                            System.out.println("Ingrese nombre del archvio: ");
                            var name = s.nextLine();
                            System.out.println("Ingrese nuevo nombre: ");
                            var ruta = s.nextLine();
                            String mayus_c = name.toUpperCase();
                            ruta = ruta.toUpperCase();
                            filesystem.ren(mayus_c.trim(), ruta.trim());
                            System.out.println(filesystem);
                            System.out.println();
                            break;
                        }
                        System.out.println("Ingrese nombre del sistema \n");
                        break;

                    case 14:
                        if(!Objects.equals(FileSystem.getNombre(), "")) {
                            List<String> listaCadenas = new ArrayList<>();
                            System.out.println("Ingrese paramtro 1: ");
                            var name = s.nextLine();
                            name = name.toUpperCase().trim();
                            if (name.equals("")){
                                filesystem.dir(listaCadenas);
                                System.out.println();
                            }
                            else if ("/s".equals(name)){
                                listaCadenas.add(name);
                                System.out.println("Desea ingrese paramtro 2 (si la respuesta es no deje la vacio): ");
                                var name1 = s.nextLine();
                                name1 = name1.toUpperCase().trim();
                                if (!name1.equals("")){
                                    listaCadenas.add(name1);
                                }
                                filesystem.dir(listaCadenas);
                                System.out.println();
                            }
                            else {
                                listaCadenas.add(name);
                                filesystem.dir(listaCadenas);
                                System.out.println();
                            }
                            break;
                        }
                        System.out.println("Ingrese nombre del sistema \n");
                        break;

                    case 15:
                        if(!Objects.equals(FileSystem.getNombre(), "")) {
                            System.out.println("Ingrese letra: ");
                            var name = s.nextLine();
                            System.out.println("Ingrese nuevo nombre: ");
                            var ruta = s.nextLine();
                            String mayus_c = name.toUpperCase();
                            ruta = ruta.toUpperCase();
                            filesystem.format(mayus_c.trim(), ruta.trim());
                            System.out.println(filesystem);
                            System.out.println();
                            break;
                        }
                        System.out.println("Ingrese nombre del sistema \n");
                        break;

                    case 16:
                        if(!Objects.equals(FileSystem.getNombre(), "")) {
                            System.out.println("Ingrese contraseña: ");
                            var name = s.nextLine();
                            System.out.println("Ingrese ruta o archivo a encriptar: ");
                            var ruta = s.nextLine();
                            ruta = ruta.toUpperCase();
                            filesystem.encrypt(name.trim(), ruta.trim());
                            System.out.println();
                            break;
                        }
                        System.out.println("Ingrese nombre del sistema \n");
                        break;

                    case 17:
                        if(!Objects.equals(FileSystem.getNombre(), "")) {
                            System.out.println("Ingrese contraseña: ");
                            var name = s.nextLine();
                            System.out.println("Ingrese ruta o archivo a desencriptar: ");
                            var ruta = s.nextLine();
                            ruta = ruta.toUpperCase();
                            filesystem.decrypt(name.trim(), ruta.trim());
                            System.out.println();
                            break;
                        }
                        System.out.println("Ingrese nombre del sistema \n");
                        break;

                    case 18:
                        if(!Objects.equals(FileSystem.getNombre(), "")) {
                            System.out.println("Ingrese palabra: ");
                            var name = s.nextLine();
                            System.out.println("Ingrese ruta o archivo: ");
                            var ruta = s.nextLine();
                            ruta = ruta.toUpperCase();
                            filesystem.grep(name.trim(), ruta.trim());
                            System.out.println();
                            break;
                        }
                        System.out.println("Ingrese nombre del sistema \n");
                        break;

                    case 19:
                        if(!Objects.equals(FileSystem.getNombre(), "")) {
                            filesystem.viewtrash();
                            System.out.println();
                            break;
                        }
                        System.out.println("Ingrese nombre del sistema \n");
                        break;

                    case 20:
                        if(!Objects.equals(FileSystem.getNombre(), "")) {
                            System.out.println("Ingrese condicion: ");
                            var name = s.nextLine();
                            String mayus_c = name.toUpperCase();
                            filesystem.restore(mayus_c.trim());
                            System.out.println(filesystem);
                            System.out.println();
                            break;
                        }
                        System.out.println("Ingrese nombre del sistema \n");
                        break;

                    default:
                        System.out.println(numero1 + " no es una opción valida \n");
                        break;
                }
            }
            else {
                System.out.println("El valor ingresado  no corresponde a un valor int. \n");
            }
        }
        while (true);
    }
    private static void Menu(){
        System.out.println("Menu");
        System.out.println("0. Salir");
        System.out.println("1. Ingresar nombre sistema");
        System.out.println("2. Ingresar unidad Drive");
        System.out.println("3. Ingresar usuario");
        System.out.println("4. Ingresar usuario para iniciar sesión");
        System.out.println("5. Cerrar sesión");
        System.out.println("6. Ingresar letra para iniciar ruta");
        System.out.println("7. Ingresar nombre de la carpeta a crear");
        System.out.println("8. Ingresar a una ruta");
        System.out.println("9. Ingresar archivo");
        System.out.println("10. Ingresar condicion para borrar");
        System.out.println("11. Ingresar datos para copiar");
        System.out.println("12. Ingresar datos para mover");
        System.out.println("13. Ingresar datos cambiar nombre");
        System.out.println("14. mostrar elementos");
        System.out.println("15. Ingresar unidad a formatear");
        System.out.println("16. encriptar elemento");
        System.out.println("17. desencriptar elemnto");
        System.out.println("18. mostrar contenido de archivo");
        System.out.println("19. mostrar papelera");
        System.out.println("20. ingrese elemento a restaurar");
    }
    public static boolean esEntero(String input) {
        try {
            Integer.parseInt(input);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    public static boolean esLetra(String input) {
        if (input.length() != 1) {
            return false;
        }
        char caracter = input.charAt(0);
        return Character.isLetter(caracter);
    }
}