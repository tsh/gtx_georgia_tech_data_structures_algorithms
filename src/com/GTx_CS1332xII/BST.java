package com.GTx_CS1332xII;

import java.util.NoSuchElementException;
import java.lang.IllegalArgumentException;

/**
 * Your implementation of a BST.
 */
public class BST<T extends Comparable<? super T>> {

    /*
     * Do not add new instance variables or modify existing ones.
     */
    private BSTNode<T> root;
    private int size;

    /*
     * Do not add a constructor.
     */

    /**
     * Adds the data to the tree.
     *
     * This must be done recursively.
     *
     * The new data should become a leaf in the tree.
     *
     * Traverse the tree to find the appropriate location. If the data is
     * already in the tree, then nothing should be done (the duplicate
     * shouldn't get added, and size should not be incremented).
     *
     * Should be O(log n) for best and average cases and O(n) for worst case.
     *
     * @param data The data to add to the tree.
     * @throws java.lang.IllegalArgumentException If data is null.
     */
    public void add(T data) {
        if (data == null){
            throw new IllegalArgumentException();
        }
        root = _add(root, data);
    }

    private BSTNode<T> _add (BSTNode<T> current, T data){
        if (current == null){
            size++;
            return new BSTNode<T>(data);
        }
        if (current.getData().compareTo(data) < 0){
            current.setRight(_add(current.getRight(), data));
        }
        if (current.getData().compareTo(data) > 0){
            current.setLeft(_add(current.getLeft(), data));
        }
        return current;
    };

    /**
     * Removes and returns the data from the tree matching the given parameter.
     *
     * This must be done recursively.
     *
     * There are 3 cases to consider:
     * 1: The node containing the data is a leaf (no children). In this case,
     * simply remove it.
     * 2: The node containing the data has one child. In this case, simply
     * replace it with its child.
     * 3: The node containing the data has 2 children. Use the SUCCESSOR to
     * replace the data. You should use recursion to find and remove the
     * successor (you will likely need an additional helper method to
     * handle this case efficiently).
     *
     * Do NOT return the same data that was passed in. Return the data that
     * was stored in the tree.
     *
     * Hint: Should you use value equality or reference equality?
     *
     * Must be O(log n) for best and average cases and O(n) for worst case.
     *
     * @param data The data to remove.
     * @return The data that was removed.
     * @throws java.lang.IllegalArgumentException If data is null.
     * @throws java.util.NoSuchElementException   If the data is not in the tree.
     */
    public T remove(T data) {
        if (data == null){
            throw new IllegalArgumentException();
        }
        BSTNode<T> returnNode = new BSTNode<T>(null);
        root = _remove(root, data, returnNode);
        size --;
        return returnNode.getData();
    }

    private BSTNode<T> _remove(BSTNode<T> current, T data, BSTNode<T> returnNode){
        if (current == null){
            throw new NoSuchElementException();
        }

        if (current.getData().compareTo(data) == 0){
            // Remove
            BSTNode<T> leftChild = current.getLeft();
            BSTNode<T> rightChild = current.getRight();
            returnNode.setData(current.getData());


            if (leftChild == null && rightChild == null){
                return null;
            }
            if (leftChild == null && rightChild != null){
                return rightChild;
            }
            if (leftChild != null && rightChild == null){
                return leftChild;
            }
            if (leftChild != null && rightChild != null){
                BSTNode<T> dummy = new BSTNode<T>(null);
                current.setRight(successor(current.getRight(), dummy));
                current.setData(dummy.getData());
                return current;
            }
        }
        // Search
        if (current.getData().compareTo(data) < 0){
            BSTNode<T> right = _remove(current.getRight(), data, returnNode);
            current.setRight(right);
            return current;
        }
        if (current.getData().compareTo(data) > 0){
            BSTNode<T> left = _remove(current.getLeft(), data, returnNode);
            current.setLeft(left);
            return current;
        }
        return current;
    };

    private BSTNode<T> successor(BSTNode<T> current, BSTNode<T> dummy){
        if (current.getLeft() == null){
            dummy.setData(current.getData());
            return current.getRight();
        }
        BSTNode<T> left = successor(current.getLeft(), dummy);
        current.setLeft(left);
        return current;
    };

    /**
     * Returns the root of the tree.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return The root of the tree
     */
    public BSTNode<T> getRoot() {
        // DO NOT MODIFY THIS METHOD!
        return root;
    }

    /**
     * Returns the size of the tree.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return The size of the tree
     */
    public int size() {
        // DO NOT MODIFY THIS METHOD!
        return size;
    }
}
