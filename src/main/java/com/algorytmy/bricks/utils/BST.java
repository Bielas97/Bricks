package com.algorytmy.bricks.utils;

import java.awt.*;

/**
 * zaimplementowane drzewo
 */
public class BST {
    private Node root = null;      // korzeń naszego drzewa

    // Wyjątki wyrzucane przez drzewo
    private class TreeException extends Throwable {
        TreeException() {
        }

        TreeException(String msg) {
            super(msg);
        }
    }

    // Klasa węzeł - która jest używana jako struktura
    private class Node {
        Point[] key;
        Node left, right, parent = null;

        Node(Point[] key) {
            this.key = key;
        }
    }

    private int valueOfPointInMatrix(Point[] result){
        return result[0].x + result[0].y + result[1].x + result[1].y;
    }

    /* Dodawanie elementów */
    public void insert(Point[] key) {
        if (root == null)
            root = new Node(key);
        else {
            Node actual = root;
            Node parent = null;
            while (actual != null) {
                parent = actual;
                //porownywanie wartosci:
                //wartosc bloczka = wiersz dla punktu 1 + kolumna dla punktu 1 + wiersz dla punktu 2 + kolumna dla punktu 2
                actual = (valueOfPointInMatrix(actual.key) > valueOfPointInMatrix(key)) ? actual.left : actual.right;
            }
            if (valueOfPointInMatrix(parent.key) > valueOfPointInMatrix(key)) {
                parent.left = new Node(key);
                parent.left.parent = parent;
            } else {
                parent.right = new Node(key);
                parent.right.parent = parent;
            }
        }
    }

    /**********************     end BSTInsert       *******************************/

    /* Wyszukiwanie elementu */
    public Node search(Point[] key) throws TreeException {
        Node actual = root;
        while (actual != null && valueOfPointInMatrix(actual.key) != valueOfPointInMatrix(key))
            actual = (valueOfPointInMatrix(actual.key) > valueOfPointInMatrix(key)) ? actual.left : actual.right;
        if (actual == null)
            throw new TreeException("Not Found Key");
        return actual;
    }

    /**********************     end BSTSearch       *******************************/

// Znajdowanie minimalnego klucza
    private Node min(Node node) {
        while (node.left != null)
            node = node.left;
        return node;
    }

    /**********************     end BST Min     ***********************************/

// Znajdowanie minimalnego klucza
    private Node max(Node node) {
        while (node.right != null)
            node = node.right;
        return node;
    }

    /**********************     end BST MAX     ***********************************/

    /*  Znajdowanie następnika  */
    private Node successor(Point[] key) throws TreeException {
        Node node = this.search(key);
// Szukanie następnika gdy węzeł ma prawego potomka
        if (node.right != null) {
            node = node.right;
            while (node.left != null)
                node = node.left;
            return node;
        }
// Szukanie następnika gdy węzeł nie ma prawgo potomka
        else if (node.right == null && node != root && node != this.max(root)) {
            Node parent = node.parent;
            while (parent != root && valueOfPointInMatrix(parent.key) < valueOfPointInMatrix(node.key))
                parent = parent.parent;
            return parent;
        } else
            throw new TreeException("Not Found Successor");
    }

    /*********************      end BST Successor       ***************************/

    /*  Znajodwanie poprzednika */
    private Node predecessor(Point[] key) throws TreeException {
        Node node = this.search(key);
// Szukanie poprzednika gdy węzeł ma lewego potomka
        if (node.left != null) {
            node = node.left;
            while (node.right != null)
                node = node.right;
            return node;
        }
// Szukanie poprzednika gdy węzeł nie ma lewego potomka
        else if (node.left == null && node != root && node != this.min(root)) {
            Node parent = node.parent;
            while (parent != root && valueOfPointInMatrix(parent.key) > valueOfPointInMatrix(node.key))
                parent = parent.parent;
            return parent;
        } else
            throw new TreeException("Not Found Predecessor");
    }

    /*********************      end BST Predecessor     *****************************/

    /* Usuwanie elementu */
    public Node remove(Point[] key) throws TreeException {
        Node node = this.search(key);
        Node parent = node.parent;
        Node tmp;
        if (node.left != null && node.right != null) {
            tmp = this.remove(this.successor(key).key);
            tmp.left = node.left;
            if (tmp.left != null)
                tmp.left.parent = tmp;
            tmp.right = node.right;
            if (tmp.right != null)
                tmp.right.parent = tmp;
        } else
            tmp = (node.left != null) ? node.left : node.right;
        if (tmp != null)
            tmp.parent = parent;
        if (parent == null)
            root = tmp;
        else if (parent.left == node)
            parent.left = tmp;
        else
            parent.right = tmp;
        return node;
    }

    /*************************      end BSTRemove       ***************************/

    /*  InOrder */
    public void inOrder(Node node) {
        if (node != null) {
            inOrder(node.left);
            System.out.print(node.key + ", ");
            inOrder(node.right);
        }
    }

    /*************************      end InOrder         ***************************/

    /* preOrder */
    public void preOrder(Node node) {
        if (node != null) {
            System.out.print(node.key + ", ");
            if (node.left != null)
                System.out.print(node.left.key + ", ");
            else
                System.out.print("-, ");
            if (node.right != null)
                System.out.println(node.right.key);
            else
                System.out.println("-");

            preOrder(node.left);
            preOrder(node.right);
        }
    }

    /*************************      end Preorder        ***************************/

    /*  PostOrder   */
    public void postOrder(Node node) {
        if (node != null) {
            postOrder(node.left);
            postOrder(node.right);
            System.out.print(node.key + ", ");
            if (node.left != null)
                System.out.print(node.left.key + ", ");
            else
                System.out.print("-, ");
            if (node.right != null)
                System.out.println(node.right.key);
            else
                System.out.println("-");
        }
    }
}
