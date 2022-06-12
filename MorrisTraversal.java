package com.dataStructure.binaryTree;

public class MorrisTraversal {
    static class Node {
        int val;
        Node left, right;

        Node(int data) {
            this.val = data;
            left = right = null;
        }
    }

    public static void main(String[] args) {
        Node root = new Node(10);
        root.left = new Node(5);
        root.right = new Node(15);
        root.right.right = new Node(18);
        root.left.left = new Node(3);
        root.left.right = new Node(7);

        morrisIn(root);
    }

    private static void morrisIn(Node root) {
        Node curr = root;
        while (curr != null) {
            if (curr.left == null) { // if there is no left, print the current and move right
                System.out.print(curr.val + " ");
                curr = curr.right;
            } else { // if there is left, then we need to make threads
                // but before actually moving to left, make a thread between current root and its inorder successor
                Node thread = curr.left;
                while (thread.right != null && thread.right != curr) thread = thread.right;
                if (thread.right == null) { // we reached inorder successor, make thread and move left
                    thread.right = curr;
                    curr = curr.left;
                } else { // this means that left sub-tree was already visited, break thread and move right
                    thread.right = null;
                    // before moving right, print node
                    System.out.print(curr.val + " ");
                    curr = curr.right;
                }
            }
        }
    }
}
