package songpack;

import java.util.ArrayList;

/**
 * An implementation of a BinarySearchTree of Song Objects
 * Written for Assignment 5 of COS285 Data Structures w/ Dr. Behrooz Mansouri @ The University of Southern Maine
 * @author Clayton Durepos
 * @version 10.18.2024
 */
public class BinarySearchTree {
    protected Node<Song> root;

    /**
     * Constructor
     */
    public BinarySearchTree() {
        root = null;
    }

    /**
     * Overloaded Constructor
     * @param root
     *      the root node of this BinarySearchTree
     */
    public BinarySearchTree(Node<Song> root) {
        this.root = root;
    }

    /**
     * The wrapper method for recursive insertion method
     * @param item
     *      the Song to be inserted into this BinarySearchTree
     */
    public void insert(Song item) {

        root = insert(item, this.root);
    }

    /**
     * Recursively insert a new Song
     * @param item
     *      the song to be inserted into this tree
     * @param root
     *      the root node of the tree in which 'item' is to be inserted
     * @return Node<Song>
     *      the root of the current tree
     */
    private Node<Song> insert(Song item, Node<Song> root) {

        if (root == null) {
            return new Node<>(item);
        }

        if (item.compareTo(root.data) < 0) {
            root.left = insert(item, root.left);
        } else if (item.compareTo(root.data) >= 0) {
            root.right = insert(item, root.right);
        }

        return root;
    }

    /**
     * A wrapper method for recursive method
     * @param views
     *      a number of views
     * @return ArrayList<Song>
     *     an ArrayList of Songs that have an amount of views equal to or greater than 'views'
     */
    public ArrayList<Song> search(int views) {
        ArrayList<Song> result = new ArrayList<>();
        search(views, root, result);
        return result;
    }

    /**
     * Recursively find all songs with an amount of views greater than or equal to 'views'
     * @param views
     *      an amount of views
     * @param root
     *      the root of the tree to be searched
     * @param result
     *      the ArrayList of Songs that satisfy the search condition; the ArrayList that will be added to
     */
    private void search(int views, Node<Song> root, ArrayList<Song> result) {
        if (root == null) {
            return;
        }

        if (root.data.getViews() < views) {
            search(views, root.right, result);
        }

        if (root.data.getViews() >= views) {
            search(views, root.left, result);
            result.add(root.data);
            search(views, root.right, result);
        }


    }

    /**
     * Wrapper method for recursive method
     * @return boolean
     *      TRUE: if this tree is a valid BinarySearchTree
     *      FALSE: if this tree is an invalid BinarySearchTree
     */
    public boolean isBST() {
        return isBST(this.root);
    }

    /**
     * Recursively determine if this tree is a valid BinarySearchTree
     * @param root
     *      the root of the tree to be validated
     * @return boolean
     *     TRUE: if this tree is a valid BinarySearchTree
     *     FALSE: if this tree is an invalid BinarySearchTree
     */
    private boolean isBST(Node<Song> root) {
        if (root == null) {
            return true;
        }

        if (root.left != null
                && root.left.data.getViews() >= root.data.getViews() ) {
            return false;
        }

        if (root.right != null
                && root.right.data.getViews() < root.data.getViews()) {
            return false;
        }

        return isBST(root.left) && isBST(root.right);
    }

    /**
     * Wrapper method for recursive method
     * Convert this tree into a sortedArrayList of songs
     * @return ArrayList<Song>
     *     all Songs in this tree, sorted in ascending order by number of views
     */
    public ArrayList<Song> toSortedArrayList() {
        ArrayList<Song> result = new ArrayList<>();
        toSortedArraylist(root, result);
        return result;
    }

    /**
     * Recursive method to convert a tree into a sortedArrayList
     * @param root
     *      the root of the tree to be converted into a sortedArrayList
     * @param result
     *      the ArrayList to be added to
     */
    private void toSortedArraylist(Node<Song> root, ArrayList<Song> result) {
        if (root == null) {
            return;
        }

        toSortedArraylist(root.left, result);
        result.add(root.data);
        toSortedArraylist(root.right, result);

    }

    /**
     * Wrapper method for recursive method
     * @return songpack.BinarySearchTree
     *      a clone of this songpack.BinarySearchTree
     */
    public BinarySearchTree clone() {
        return new BinarySearchTree(clone(this.root));
    }

    /**
     * Recursively clone the tree of a given root node
     * @param root
     *      the root node of a given tree
     * @return Node<Song>
     *     a clone of the tree of a given root node
     */
    private Node<Song> clone(Node<Song> root) {
        if (root == null) {
            return null;

        }

        Node<Song> newNode = new Node<>(root.data);
        newNode.left = clone(root.left);
        newNode.right = clone(root.right);
        return newNode;
    }

    /**
     * Wrapper method for recursive method
     * @return ArrayList<String>
     *      An ArrayList of all artist names who have published a song with the greatest amount of views in this tree
     */
    public ArrayList<String> popularArtists() {
        ArrayList<String> result = new ArrayList<>();
        popularArtists(getMaxViews(this.root), root, result);
        return result;
    }

    /**
     * Recursively find all artist names who have published a song with the greatest amount of views in this tree
     * @param views
     *      the number of views to be searched for
     * @param root
     *      the node to navigate from
     * @param result
     *      the ArrayList of artist names' to be added to
     */
    public void popularArtists(int views, Node<Song> root, ArrayList<String> result) {
        if (root == null) {
            return;
        }

        if (root.data.getViews() < views) {
            popularArtists(views, root.right, result);
        }

        if (root.data.getViews() == views) {
            popularArtists(views, root.left, result);
            result.add(root.data.getArtist());
            popularArtists(views, root.right, result);
        }


    }

    /**
     * Find the largest number of views in this tree
     * @param root
     *      the node to navigate from
     * @return int
     *      the largest number of views in this tree
     */
    private int getMaxViews(Node<Song> root) {
        while (root.right != null) {
            root = root.right;
        }

        return root.data.getViews();
    }

    /**
     * A wrapper method for recursive method
     * @param minViews
     *      the minimum number of views that the resulting tree is to have
     * @param maxViews
     *      the maximum number of views that the resulting tree is to have
     * @return Node<Song>
     *     the root node of the filtered tree
     */
    public Node<Song> filterByViews(int minViews, int maxViews) {
        return this.root = filterByViews(minViews, maxViews, this.root);
    }

    /**
     *
     * @param minViews
     *     the minimum number of views that the resulting tree is to have
     * @param maxViews
     *     the maximum number of views that the resulting tree is to have
     * @param root
     *      the node to navigate from
     * @return Node<Song>
     *      the root of the filtered tree
     */
    private Node<Song> filterByViews(int minViews, int maxViews, Node<Song> root) {
        if (root == null) {
            return null;
        }

        if (root.data.getViews() >= minViews && root.data.getViews() <= maxViews) {
            Node<Song> newRoot = new Node<>(root.data);

            newRoot.left = filterByViews(minViews, maxViews, root.left);
            newRoot.right = filterByViews(minViews, maxViews, root.right);

            return newRoot;

        } else if (root.data.getViews() < minViews) {
            return filterByViews(minViews, maxViews, root.right);
        } else if (root.data.getViews() > maxViews) {
            return filterByViews(maxViews, minViews, root.left);
        }

        return null;
    }

    protected int height(Node<Song> node) {return (node==null) ? -1 : node.height;}

    /**
     * A node class for BinarySearchTree
     */
    protected static class Node<T> {
        protected final T data;
        protected Node<T> left, right;
        protected int height;

        public Node() {
            this.data = null;
            this.left = this.right = null;
            this.height = 0;
        }

        public Node(T data) {
            this.data = data;
            this.left = this.right = null;
            this.height = 0;

        }
    }

}
