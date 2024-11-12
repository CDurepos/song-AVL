package songpack;

import java.io.IOException;

public class program7 {
    public static void main(String[] args) throws IOException {
        long start = System.currentTimeMillis();
        AVLTree avl = MyDataReader.readFileToAVL(args[0], args[1]);
        long end = System.currentTimeMillis();

        System.out.println(avl.leftRotations + " left rotations");
        System.out.println(avl.rightRotations + " right rotations");
        System.out.println(avl.leftRightRotations + " left-right rotations");
        System.out.println(avl.rightLeftRotations + " right-left rotations");
        System.out.println("The height of this tree is: " + avl.height());
        System.out.println( (end-start) + " milliseconds to build the AVL tree for " + args[1] + " songs");

        searches(avl);

        start = System.currentTimeMillis();
        BinarySearchTree bst = MyDataReader.readFileToBST(args[0], args[1]);
        end = System.currentTimeMillis();
        System.out.println( (end-start) + " milliseconds to build the BST for " + args[1] + " songs");
        System.out.println("The height of this tree is: " + bst.height());

        searches(bst);



    }

    private static void searches(BinarySearchTree tree) {
        int[] views = new int[]{-2, 12345, 2, 5000, 1000000};

        for (int i = 0; i < views.length; i++) {

            long start = System.nanoTime();
            tree.search(views[i]);
            long end = System.nanoTime();

            System.out.println((end-start) + " nanoseconds for search " + (i+1));
        }
    }
}
