/**
 * A generic Binary Search Tree (BST) class that allows storing elements of any
 * type that implements the Comparable interface.
 *
 * @param <T> The type of the elements stored in the tree. T must implement
 *            Comparable.
 */
public class BinarySearchTree<T extends Comparable<T>> {
    
    /**
     * The root node of the binary search tree.
     */
    private NodeType<T> root;

    /**
     * Constructs an empty binary search tree.
     */
    public BinarySearchTree() {
        root = null;
    }

    /**
     * Inserts the specified key into the binary search tree.
     *
     * @param key The key to be inserted.
     */
    public void insert(T key) {
        if (retrieve(key)) {
            System.out.println("The item already exists in the tree.");
        }
        else {
            root = insertRecursively(root, key);
        }
    }

    // Helper method for insert
    private NodeType<T> insertRecursively(NodeType<T> node, T key) {
        if (node == null) {
            node = new NodeType<>();
            node.info = key;
            node.left = node.right = null;
        } else {
            int comparison = key.compareTo(node.info);

            if (comparison < 0) {
                node.left = insertRecursively(node.left, key);
            } else if (comparison > 0) {
                node.right = insertRecursively(node.right, key);
            } else {
                // Duplicate values are not allowed
            }
        }
        return node;
    }

    /**
     * Deletes the specified key from the binary search tree.
     *
     * @param key The key to be deleted.
     */
    public void delete(T key) {
         if (!retrieve(key)) {
            System.out.println("The number is not present in the tree");
        }
        else {
            root = deleteRecursively(root, key);
        }
    }

    // Helper method for delete
    private NodeType<T> deleteRecursively(NodeType<T> node, T key) {
        if (node == null) {
            return null;
        }

        int comparison = key.compareTo(node.info);

        if (comparison < 0) {
            node.left = deleteRecursively(node.left, key);
        } else if (comparison > 0) {
            node.right = deleteRecursively(node.right, key);
        } else {
            if (node.left == null) {
                return node.right;
            } else if (node.right == null) {
                return node.left;
            } else {
                node.info = getMinValue(node.right);
                node.right = deleteRecursively(node.right, node.info);
            }
        }

        return node;
    }

    // Helper method for getting the minimum value in a subtree
    private T getMinValue(NodeType<T> node) {
        T minValue = node.info;
        while (node.left != null) {
            minValue = node.left.info;
            node = node.left;
        }
        return minValue;
    }

    /**
     * Retrieves the specified item from the binary search tree.
     *
     * @param item The item to be retrieved.
     * @return true if the item is found, false otherwise.
     */
    public boolean retrieve(T item) {
        return retrieveRecursively(root, item) != null;
    }

    // Helper method for retrieval
    private NodeType<T> retrieveRecursively(NodeType<T> node, T item) {
        if (node == null) {
            return null;
        }

        int comparison = item.compareTo(node.info);

        if (comparison < 0) {
            return retrieveRecursively(node.left, item);
        } else if (comparison > 0) {
            return retrieveRecursively(node.right, item);
        } else {
            return node;
        }
    }

    /**
     * Performs an in-order traversal of the binary search tree and prints the
     * elements.
     */
    public void inOrder() {
        inOrderRecursively(root);
        System.out.println();
    }

    // Helper method for in-order traversal
    private void inOrderRecursively(NodeType<T> node) {
        if (node != null) {
            inOrderRecursively(node.left);
            System.out.print(node.info + " ");
            inOrderRecursively(node.right);
        }
    }

    /**
     * Prints the nodes with a single parent (nodes that have either left or right
     * child, but not both).
     */
    public void getSingleParent() {
        System.out.println("Single-parent nodes:");
        getSingleParent(root);
    }

    // Helper method for getting single parent nodes
    private void getSingleParent(NodeType<T> node) {
        if (node == null) {
            return;
        }

        if ((node.left != null && node.right == null) || (node.left == null && node.right != null)) {
            System.out.println(node.info);
        }

        getSingleParent(node.left);
        getSingleParent(node.right);
    }

    /**
     * Prints the number of leaf nodes (nodes with no children) in the binary search
     * tree.
     */
    public void getNumLeafNodes() {
        int count = getNumLeafNodes(root);
        System.out.println("Number of leaf nodes: " + count);
    }

    // Helper method for getting the number of leaf nodes
    private int getNumLeafNodes(NodeType<T> node) {
        if (node == null) {
            return 0;
        }

        if (node.left == null && node.right == null) {
            return 1;
        }

        return getNumLeafNodes(node.left) + getNumLeafNodes(node.right);
    }

    /**
     * Prints the cousins of the specified item (nodes that are at the same level
     * and have different parents).
     *
     * @param item The item whose cousins are to be found.
     */
    public void getCousins(T item) {
        System.out.println("Cousins of " + item + ":");
        NodeType<T> target = retrieveRecursively(root, item);
        if (target == null) {
            System.out.println("Node not found");
            return;
        }
        if (root.info.compareTo(item) == 0) {
           // System.out.println("No cousins for root node");
            return;
        }
        NodeType<T> parent = getParent(root, item);
        int level = getLevel(root, parent.info, 0);
        getCousinsAtLevel(root, parent, level, target.info);
    }

    // Helper methods for getting cousins
    private NodeType<T> getParent(NodeType<T> node, T item) {
        if (node == null || node.info.compareTo(item) == 0) {
            return null;
        }
        if (node.left != null && node.left.info.compareTo(item) == 0 ||
                node.right != null && node.right.info.compareTo(item) == 0) {
            return node;
        }
        NodeType<T> leftParent = getParent(node.left, item);
        if (leftParent != null) {
            return leftParent;
        }
        return getParent(node.right, item);
    }

    // Helper methods for getting cousins
    private void getCousinsAtLevel(NodeType<T> node, NodeType<T> parent, int level, T target) {
        if (node == null || level < 0) {
            return;
        }
        if (level == 0) {
            if (node != parent && node.left != null && node.left != parent && node.left.info.compareTo(target) != 0) {
                System.out.println(node.left.info);
            }
            if (node != parent && node.right != null && node.right != parent
                    && node.right.info.compareTo(target) != 0) {
                System.out.println(node.right.info);
            }
            return;
        }
        getCousinsAtLevel(node.left, parent, level - 1, target);
        getCousinsAtLevel(node.right, parent, level - 1, target);
    }

    // Helper methods for getting cousins
    private int getLevel(NodeType<T> node, T item, int level) {
        if (node == null) {
            return -1;
        }
        if (node.info.compareTo(item) == 0) {
            return level;
        }
        int leftLevel = getLevel(node.left, item, level + 1);
        if (leftLevel >= 0) {
            return leftLevel;
        }
        return getLevel(node.right, item, level + 1);
    }

}
