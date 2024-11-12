package songpack;

public class AVLTree extends BinarySearchTree {
    public int leftRotations = 0, rightRotations = 0, leftRightRotations = 0, rightLeftRotations = 0;

    /**
     * Constructor
     */
    public AVLTree() {
        root = null;
    }

    /**
     * The wrapper method for recursive insertion method
     * @param item
     *      the Song to be inserted into this AVLTree
     */
    @Override
    public void insert(Song item) {
        root = insert(item, root);
    }

    /**
     * Recursively insert a new Song
     * @param item
     *      the song to be inserted into this tree
     * @param node
     *      the root node of the tree in which 'item' is to be inserted
     * @return Node<Song>
     *      the root of the current tree
     */
    private Node<Song> insert(Song item, Node<Song> node) {
        if (node == null) return new Node<>(item);

        if (item.compareTo(node.data) < 0) {
            node.left = insert(item, node.left);
        } else if (item.compareTo(node.data) >= 0) {
            node.right = insert(item, node.right);
        }

        updateHeight(node);
        return balance(node);
    }

    /**
     * Perform a right rotation on an AVLTree
     * @param node
     *      the root of the AVLTree to be rotated
     * @return Node<Song>
     *      the root of the rotated AVLTree
     */
    private Node<Song> rotateRight(Node<Song> node) {
        Node<Song> child = node.left;
        node.left = child.right;
        child.right = node;

        updateHeight(node);
        updateHeight(child);
        return child;

    }

    /**
     * Perform a left rotation on an AVLTree
     * @param node
     *      the root of the AVLTree to be rotated
     * @return Node<Song>
     *      the root of the rotated AVLTree
     */
    private Node<Song> rotateLeft(Node<Song> node) {
        Node<Song> child = node.right;
        node.right = child.left;
        child.left = node;

        updateHeight(node);
        updateHeight(child);
        return child;
    }

    /**
     * Get the balance factor of a node
     * @param node
     *      the node whose balance factor is to be calculated
     * @return int
     *      the balance factor of 'node'
     */
    private int balanceFactor(Node<Song> node) {
        return (node == null) ? 0 : height(node.left) - height(node.right);
    }

    /**
     * Balance an AVLTree
     * @param node
     *      the root node of the AVLTree to be balanced
     * @return Node<Song>
     *      the root of the balanced AVLTree
     */
    private Node<Song> balance(Node<Song> node) {
        int balance = balanceFactor(node);

        if (balance > 1) {
            if (balanceFactor(node.left) >= 0) {
                node = rotateRight(node);
                rightRotations++;
            } else {
                node.left = rotateLeft(node.left);
                node = rotateRight(node);
                leftRightRotations++;
            }
        }

        else if (balance < -1) {
            if (balanceFactor(node.right) <= 0) {
                node = rotateLeft(node);
                leftRotations++;
            } else {
                node.right = rotateRight(node.right);
                node = rotateLeft(node);
                rightLeftRotations++;
            }
        }

        return node;
    }
}
