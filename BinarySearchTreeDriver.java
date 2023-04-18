import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class BinarySearchTreeDriver {
    public static void main(String[] args) {
        // Check for valid input file
        if (args.length != 1) {
            System.out.println("Usage: java BinarySearchTreeDriver <input-file>");
            return;
        }

        // Read input file and create a BinarySearchTree based on the user's input
        try {
            Scanner fileScanner = new Scanner(new File(args[0]));
            Scanner inputScanner = new Scanner(System.in);

            System.out.print("Enter list type (i - int, d - double, s - string): ");
            String listType = inputScanner.nextLine();

            BinarySearchTree<?> bst;
            if (listType.equals("i")) {
                bst = new BinarySearchTree<Integer>();
                while (fileScanner.hasNextInt()) {
                    int value = fileScanner.nextInt();
                    ((BinarySearchTree<Integer>) bst).insert(value);
                }
            } else if (listType.equals("d")) {
                bst = new BinarySearchTree<Double>();
                while (fileScanner.hasNextDouble()) {
                    double value = fileScanner.nextDouble();
                    ((BinarySearchTree<Double>) bst).insert(value);
                }
            } else if (listType.equals("s")) {
                bst = new BinarySearchTree<String>();
                while (fileScanner.hasNext()) {
                    String value = fileScanner.next();
                    ((BinarySearchTree<String>) bst).insert(value);
                }
            } else {
                System.out.println("Invalid list type.");
                return;
            }

            

            // Allow the user to interactively modify the tree based on given commands
            String command;
            do {
                System.out.println("\nCommands:");
                System.out.println("  (i) - Insert Item");
                System.out.println("  (d) - Delete Item");
                System.out.println("  (p) - Print Tree");
                System.out.println("  (r) - Retrieve Item");
                System.out.println("  (l) - Count Leaf Nodes");
                System.out.println("  (s) - Find Single Parents");
                System.out.println("  (c) - Find Cousins");
                System.out.println("  (q) - Quit program");
                System.out.print("Enter command: ");
                command = inputScanner.next();

                switch (command) {
                    case "i":
                        if (listType.equals("i")) {
                            int value = inputScanner.nextInt();
                            ((BinarySearchTree<Integer>) bst).insert(value);
                        } else if (listType.equals("d")) {
                            double value = inputScanner.nextDouble();
                            ((BinarySearchTree<Double>) bst).insert(value);
                        } else if (listType.equals("s")) {
                            String value = inputScanner.next();
                            ((BinarySearchTree<String>) bst).insert(value);
                        }
                        break;
                    case "d":
                        if (listType.equals("i")) {
                            int value = inputScanner.nextInt();
                            ((BinarySearchTree<Integer>) bst).delete(value);
                        } else if (listType.equals("d")) {
                            double value = inputScanner.nextDouble();
                            ((BinarySearchTree<Double>) bst).delete(value);
                        } else if (listType.equals("s")) {
                            String value = inputScanner.next();
                            ((BinarySearchTree<String>) bst).delete(value);
                        }
                        break;
                    case "p":
                        System.out.println("In-order traversal:");
                        bst.inOrder();
                        break;
                    case "r":
                        boolean found = false;
                        if (listType.equals("i")) {
                            int value = inputScanner.nextInt();
                            found = ((BinarySearchTree<Integer>) bst).retrieve(value);
                        } else if (listType.equals("d")) {
                            double value = inputScanner.nextDouble();
                            found = ((BinarySearchTree<Double>) bst).retrieve(value);
                        } else if (listType.equals("s")) {
                            String value = inputScanner.next();
                            found = ((BinarySearchTree<String>) bst).retrieve(value);
                        }
                        System.out.println("Item is " + (found ? "present" : "not present") + " in the tree.");
                        break;
                    case "l":
                        bst.getNumLeafNodes();
                        break;
                    case "s":
                        bst.getSingleParent();
                        break;
                    case "c":
                        System.out.print("Enter value to find cousins: ");
                        if (listType.equals("i")) {
                            int value = inputScanner.nextInt();
                            ((BinarySearchTree<Integer>) bst).getCousins(value);
                        } else if (listType.equals("d")) {
                            double value = inputScanner.nextDouble();
                            ((BinarySearchTree<Double>) bst).getCousins(value);
                        } else if (listType.equals("s")) {
                            String value = inputScanner.next();
                            ((BinarySearchTree<String>) bst).getCousins(value);
                        }
                        break;
                    case "q":
                        System.out.println("Exiting the program.");
                        break;
                    default:
                        System.out.println("Invalid command.");
                }
            } while (!command.equals("q"));

        } catch (FileNotFoundException e) {
            System.out.println("Error: Unable to open file " + args[0]);
        }
    }
}
