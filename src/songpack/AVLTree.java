package songpack;

import java.util.ArrayList;

public class AVLTree extends BinarySearchTree {
    public int leftRotations = 0, rightRotations = 0, leftRightRotations = 0, rightLeftRotations = 0;

    public AVLTree() {
        this.root = null;
    }

    public int height() {
        return this.root.height;
    }

    @Override
    public void insert(Song item) {
        this.root = insert(item, this.root);
    }

    private Node<Song> insert(Song item, Node<Song> node) {
        if (node == null) {
            return new Node<>(item);
        }

        if (item.compareTo(node.data) < 0) {
            node.left = insert(item, node.left);
        } else if (item.compareTo(node.data) >= 0) {
            node.right = insert(item, node.right);
        }

        updateHeight(node);
        return balance(node);
    }

    private void updateHeight(Node<Song> node) {
        node.height = 1 + Math.max(height(node.left), height(node.right));
    }

    private int getBalance(Node<Song> node) {
        return (node == null) ? 0 : height(node.left) - height(node.right);
    }

    private Node<Song> rotateRight(Node<Song> node) {
        Node<Song> child = node.left;
        node.left = child.right;
        child.right = node;

        updateHeight(node);
        updateHeight(child);
        return child;

    }

    private Node<Song> rotateLeft(Node<Song> node) {
        Node<Song> child = node.right;
        node.right = child.left;
        child.left = node;

        updateHeight(node);
        updateHeight(child);
        return child;
    }

    private Node<Song> balance(Node<Song> node) {
        int balance = getBalance(node);

        if (balance > 1) {
            if (getBalance(node.left) >= 0) {
                node = rotateRight(node);
                rightRotations++;
            } else {
                node.left = rotateLeft(node.left);
                node = rotateRight(node);
                leftRightRotations++;
            }
        }

        else if (balance < -1) {
            if (getBalance(node.right) <= 0) {
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
