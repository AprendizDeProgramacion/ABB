import java.util.NoSuchElementException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        var abb = new LinkedBinarySearchTree<Integer, Integer>();
        Scanner sc = new Scanner(System.in);
        int userOption;
        boolean exit = false;

        System.out.println("CREADOR DE ARBOL BINARIO DE BUSQUEDA");
        while (!exit) {
            showMenu();
            do {
                System.out.print("Opcion: ");
                userOption = sc.nextInt();

            } while (userOption < 0 || userOption > 5);

            switch (userOption) {
                case 1:
                    addElement(abb, sc);
                    promptEnterKey();
                    break;
                case 2:
                    try {
                        removeElement(abb, sc);
                    } catch (NoSuchElementException nse) {
                        System.out.println("ERROR: key not found");
                    }
                    promptEnterKey();
                    break;
                case 3:
                    try {
                        getValue(abb, sc);
                    } catch (NoSuchElementException nso) {
                        System.out.println("ERROR: key not found");
                    }
                    promptEnterKey();
                    break;
                case 4:
                    System.out.print("Mostrando arbol en preorden: ");
                    try {
                        printTreePreOrder(abb);
                    } catch (NoSuchElementException nse) {
                        System.out.println("ERROR: can't print empty tree");
                    }
                    System.out.println();
                    promptEnterKey();
                    break;
                case 5:
                    System.out.println("Saliendo . . .");
                    exit = true;
                    break;
            }
        }
    }

    private static void getValue(LinkedBinarySearchTree<Integer, Integer> abb, Scanner sc) {
        System.out.print("Inserte la clave a consultar: ");
        int key = sc.nextInt();
        System.out.println("Clave: " + key + " --> Valor: " + abb.getValue(key));
    }

    private static void removeElement(LinkedBinarySearchTree<Integer, Integer> abb, Scanner sc) {
        System.out.print("Inserte la clave a eliminar: ");
        int key = sc.nextInt();
        abb.remove(key);
    }

    private static void promptEnterKey() {
        System.out.println("Pulsa \"ENTER\" para continuar ...");
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
    }

    private static void showMenu() {
        System.out.println("¿Que desea hacer?");
        System.out.println("1: Insertar elemento");
        System.out.println("2: Eliminar elemento");
        System.out.println("3: Consultar valor de una clave");
        System.out.println("4: Mostrar el arbol en preorden");
        System.out.println("5: Salir");
    }

    private static void addElement(LinkedBinarySearchTree<Integer, Integer> abb, Scanner sc) {
        System.out.print("Inserte clave valor: ");
        abb.add(sc.nextInt(), sc.nextInt());
    }

    public static void printTreePreOrder(LinkedBinarySearchTree<Integer, Integer> abb) {
        if (abb.isEmpty())
            throw new NoSuchElementException();

        System.out.print(abb.getRoot() + " ");
        if (!abb.left().isEmpty()) {
            printTreePreOrder(abb.left());
        }
        if (!abb.right().isEmpty()) {
            printTreePreOrder(abb.right());
        }
    }
}
