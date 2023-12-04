/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package pp2_alisonguillen;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

class Libro {
    private String nombre;
    private String autor;
    private String id;
    private String estado; 
    private String dueno;

    public Libro(String nombre, String autor) {
        this.nombre = nombre;
        this.autor = autor;
        this.id = generarID();
        this.estado = "disponible";
        this.dueno = "";
    }

    public String getNombre() {
        return nombre;
    }

    public String getAutor() {
        return autor;
    }

    public String getId() {
        return id;
    }

    public String getEstado() {
        return estado;
    }

    public String getDueno() {
        return dueno;
    }

    public void prestar(String dueno) {
        if (estado.equals("disponible")) {
            estado = "prestado";
            this.dueno = dueno;
            System.out.println("Libro prestado a: " + dueno);
        } else {
            System.out.println("El libro no puede ser prestado ahora");
        }
    }

    public void regresar() {
        if (estado.equals("prestado")) {
            estado = "disponible";
            dueno = "";
            System.out.println("Libro devuelto.");
        } else {
            System.out.println("El libro no esta prestado.");
        }
    }

    private String generarID() {
        Random rand = new Random();
        return String.valueOf(rand.nextInt(1000) + 1);
    }
}

class Biblioteca {
    private ArrayList<Libro> libros = new ArrayList<>();

    public void agregarLibro(String nombre, String autor) {
        Libro nuevoLibro = new Libro(nombre, autor);
        libros.add(nuevoLibro);
        System.out.println("Libro agregado correctamente.");
    }

    public void solicitarLibro(String dueno, int posicion) {
        if (posicion >= 0 && posicion < libros.size()) {
            Libro libro = libros.get(posicion);
            libro.prestar(dueno);
        } else {
            System.out.println("Posicion invalida.");
        }
    }

    public void regresarLibro(int posicion) {
        if (posicion >= 0 && posicion < libros.size()) {
            Libro libro = libros.get(posicion);
            libro.regresar();
        } else {
            System.out.println("Posicion invalida ");
        }
    }

    public void buscarLibroPorNombre(String nombre) {
        for (Libro libro : libros) {
            if (libro.getNombre().equalsIgnoreCase(nombre)) {
                System.out.println("Libro encontrado:\n" +
                        "Nombre: " + libro.getNombre() + "\n" +
                        "Autor: " + libro.getAutor() + "\n" +
                        "ID: " + libro.getId() + "\n" +
                        "Estado: " + libro.getEstado() + "\n" +
                        "Dueño: " + libro.getDueno());
                return;
            }
        }
        System.out.println("Libro no encontrado.");
    }

    public void listarLibrosDisponibles() {
        System.out.println("Libros Disponibles:");
        for (Libro libro : libros) {
            if (libro.getEstado().equals("disponible")) {
                System.out.println(libro.getNombre() + " - " + libro.getAutor());
            }
        }
    }

    public void listarLibrosPrestados() {
        System.out.println("Libros Prestados:");
        for (Libro libro : libros) {
            if (libro.getEstado().equals("prestado")) {
                System.out.println(libro.getNombre() + " - " + libro.getAutor() + " - Prestado a: " + libro.getDueno());
            }
        }
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner rd = new Scanner(System.in);
        Biblioteca biblioteca = new Biblioteca();

        int opcion;
        do {
            System.out.println("\nMenú:");
            System.out.println("1. Agregar Nuevo libro a la biblioteca");
            System.out.println("2. Solicitar libro");
            System.out.println("3. Regresar libro");
            System.out.println("4. Buscar libro por nombre");
            System.out.println("5. Listar libros disponibles");
            System.out.println("6. Listar libros prestados");
            System.out.println("0. Salir");
            System.out.print("Ingrese su opción: ");
            opcion = rd.nextInt();


            switch (opcion) {
                case 1:
                    rd.nextLine(); // Consumir el salto de línea pendiente
                    System.out.print("Ingrese el nombre del libro: ");
                    String nombreLibro = rd.nextLine();
                    System.out.print("Ingrese el autor del libro: ");
                    String autorLibro = rd.nextLine();
                    biblioteca.agregarLibro(nombreLibro, autorLibro);
                    break;
                case 2:
                    rd.nextLine(); // Consumir el salto de línea pendiente
                    System.out.print("Ingrese su nombre: ");
                    String nombreSolicitante = rd.nextLine();
                    System.out.print("Ingrese la posición del libro que desea solicitar: ");
                    int posicionSolicitada = rd.nextInt();
                    biblioteca.solicitarLibro(nombreSolicitante, posicionSolicitada);
                    break;
                case 3:
                    System.out.print("Ingrese la posición del libro que desea regresar: ");
                    int posicionDevuelta = rd.nextInt();
                    biblioteca.regresarLibro(posicionDevuelta);
                    break;
                case 4:
                    rd.nextLine(); 
                    System.out.print("Ingrese el nombre del libro que desea buscar: ");
                    String nombreBuscado = rd.nextLine();
                    biblioteca.buscarLibroPorNombre(nombreBuscado);
                    break;
                case 5:
                    biblioteca.listarLibrosDisponibles();
                    break;
                case 6:
                    biblioteca.listarLibrosPrestados();
                    break;
                case 0:
                    System.out.println("Gracias por utilizar el programa! ");
                    break;
                default:
                    System.out.println("Ingrese una opcion valida");
            }

        } while (opcion != 0);
    }
}


