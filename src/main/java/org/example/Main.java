package org.example;

public class Main {
    public static void main(String args[]) {

        BinaryTree tree = new BinaryTree();

        tree.insert(20);
        tree.insert(10);
        tree.insert(30);
        tree.insert(5);
        tree.insert(15);
        tree.insert(25);
        tree.insert(35);
    }
}

class BinaryTree {
    Node root;
    class Node {
        boolean isRed;
        int value;
        Node left;
        Node right;

    }

    public boolean find(int value) {
        Node node = root;
        while (node != null) {
            if (node.value == value) {
                return true;
            }
            if (node.value < value) {
                node = node.right;
            } else {
                node = node.left;
            }
        }
        return false;
    }

    public void insert(int value) {
        if (root == null) {
            root = new Node();
            root.value = value;
        } else {
            insert(root, value);
            root = balance(root);
        }
    }

    private boolean insert(Node node, int value) {
        if (node.value == value) {
            return false;
        }
        if (node.value < value) {
            if (node.right == null) {
                node.right = new Node();
                node.right.value = value;
                node.isRed = true;
                return true;
            } else {
                boolean inserted = insert(node.right, value);
                node.right = balance(node.right);
                return inserted;

            }
        } else {
            if (node.left == null) {
                node.left = new Node();
                node.left.value = value;
                node.isRed = true;
                return true;
            } else {
                boolean inserted = insert(node.left, value);
                node.left = balance(node.left);
                return inserted;
            }
        }
    }

    private void colorSwithc(Node node) {
        node.isRed = true;
        node.right.isRed = false;
        node.left.isRed = false;
    }

    public Node rightTurn(Node node) {
        Node rightChild = node.right;
        Node medChild = rightChild.left;
        rightChild.left = node;
        node.right = medChild;
        rightChild.isRed = node.isRed;
        node.isRed = true;
        return rightChild;
    }

    public Node leftTurn(Node node) {
        Node leftChild = node.left;
        Node medChild = leftChild.right;
        leftChild.right = node;
        node.left = medChild;
        leftChild.isRed = node.isRed;
        node.isRed = true;
        return leftChild;
    }

    public Node balance(Node node) {
        Node current = node;
        boolean isNotBalanced;
        do {
            isNotBalanced = false;
            if (current.right != null && current.right.isRed && (current.left == null || !current.left.isRed)) {
                isNotBalanced = true;
                current = rightTurn(current);
            }
            if (current.left != null && current.left.isRed && current.left.left != null && current.left.left.isRed) {
                isNotBalanced = true;
                current = leftTurn(current);
            }
            if (current.left != null && current.left.isRed && current.right != null && current.right.isRed) {
                isNotBalanced = true;
                colorSwithc(current);
            }
        }
        while (isNotBalanced);
        return current;
    }
}
