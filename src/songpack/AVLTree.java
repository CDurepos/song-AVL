package songpack;

import java.util.ArrayList;

public class AVLTree extends BinarySearchTree {
    private AVLNode<Song> root;
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

    private AVLNode<Song> insert(Song item, AVLNode<Song> node) {
        if (node == null) {
            return new AVLNode<>(item);
        }

        if (item.compareTo(node.data) < 0) {
            node.left = insert(item, node.left);
        } else if (item.compareTo(node.data) >= 0) {
            node.right = insert(item, node.right);
        }

        updateHeight(node);
        return balance(node);
    }

    private int getHeight(AVLNode<Song> node) {
        return (node == null) ? -1 : node.height;
    }

    private void updateHeight(AVLNode<Song> node) {
        node.height = 1 + Math.max(getHeight(node.left), getHeight(node.right));
    }

    private int getBalance(AVLNode<Song> node) {
        return (node == null) ? 0 : getHeight(node.left) - getHeight(node.right);
    }

    private AVLNode<Song> rotateRight(AVLNode<Song> node) {
        AVLNode<Song> child = node.left;
        node.left = child.right;
        child.right = node;

        updateHeight(node);
        updateHeight(child);
        return child;

    }

    private AVLNode<Song> rotateLeft(AVLNode<Song> node) {
        AVLNode<Song> child = node.right;
        node.right = child.left;
        child.left = node;

        updateHeight(node);
        updateHeight(child);
        return child;
    }

    private AVLNode<Song> balance(AVLNode<Song> node) {
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

//            if (getBalance(node.left) < 0) {
//                node.left = rotateLeft(node.left);
//            }
//
//            node = rotateRight(node);
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

//            if (getBalance(node.right) > 0) {
//                node.right = rotateRight(node.right);
//            }
//
//            node = rotateLeft(node);
        }

        return node;
    }

    private static class AVLNode<T> extends Node<T> {
        private int height;
        private AVLNode<T> left, right;

        public AVLNode() {
            height = 0;
            this.left = this.right = null;
        }

        public AVLNode(T data) {
            super(data);
            height = 0;
        }
    }
}
