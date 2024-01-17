import java.util.NoSuchElementException;

public class LinkedBinarySearchTree<K extends Comparable<? super K>, V>
        implements BinarySearchTree<K, V> {

    private Node<K, V> root;

    static class Node<K extends Comparable<? super K>, V> {

        private final K key;
        private V value;
        private Node<K, V> left;
        private Node<K, V> right;
        private Node<K, V> parent;

        Node(Node<K, V> left, K key, V value, Node<K, V> right, Node<K, V> parent) {
            this.key = key;
            this.value = value;
            this.left = left;
            this.right = right;
            this.parent = parent;
        }
    }

    public LinkedBinarySearchTree() {
        this.root = null;
    }

    private LinkedBinarySearchTree(Node<K, V> root) {
        this.root = root;
    }

    @Override
    public LinkedBinarySearchTree<K, V> left() {
        return new LinkedBinarySearchTree<>(root.left);
    }

    @Override
    public LinkedBinarySearchTree<K,V> right() {
        return new LinkedBinarySearchTree<>(root.right);
    }

    private static int size(Node<?, ?> root) {
        if (root == null) {
            return 0;
        } else {
            return 1 + size(root.left) + size(root.right);
        }
    }

    @Override
    public void add(K key, V value) {
        var node = new Node<>(null, key, value, null, null);
        if (isEmpty()) {
            root = node;
        } else {
            addNode(node);
        }
    }

    private void addNode(Node<K,V> node) {
        var cursor = root;
        while (true) {
            if (cursor.key.equals(node.key)) {
                cursor.value = node.value;
                break;
            } else {
                if (node.key.compareTo(cursor.key) > 0 && cursor.right == null) {
                    node.parent = cursor;
                    cursor.right = node;
                    break;
                } else if (node.key.compareTo(cursor.key) < 0 && cursor.left == null) {
                    node.parent = cursor;
                    cursor.left = node;
                    break;
                } else {
                    cursor = (node.key.compareTo(cursor.key) > 0) ? cursor.right : cursor.left;
                }
            }
        }
    }

    @Override
    public K getRoot() {
        return root.key;
    }

    @Override
    public boolean isEmpty() {
        return size(root) == 0;
    }

    @Override
    public void remove(K key) {
        boolean found = false;
        if (isEmpty())
            throw new NoSuchElementException("empty tree");
        var cursor = root;
        while (cursor != null && !found) {
            if (cursor.key.equals(key)) {
                remove(cursor);
                found = true;
            }
            cursor = (key.compareTo(cursor.key) > 0) ? cursor.right : cursor.left;
        }
        if (!found)
            throw new  NoSuchElementException("Key not found");
    }

    private void remove(Node<K, V> node) {
        if (isLeaf(node)) {
            removeLeave(node);
        } else if (hasOneChild(node)) {
            modifyParent(node);
        } else {
            removeAndSwap(node);
        }
    }

    private void removeAndSwap(Node<K,V> node) {
        var max = findMax(node.left);
        if (node.key.equals(root.key)) {
            root.left.parent = max;
            root.right.parent = max;
            max.left = root.left;
            max.right = root.right;
            max.parent.right = null;
            root = max;
        } else {
            var parent = node.parent;
            if (isRigthChild(node)) {
                parent.right = max;
            } else {
                parent.left = max;
            }
            max.parent.right = null;
            max.right = node.right;
            max.parent = parent;
            node.right.parent = max;
            max.left = (max.key.equals(node.left.key)) ? max.left : node.left;
        }
        node.left = null;
        node.right = null;
        node.parent = null;
    }

    private Node<K, V> findMax(Node<K,V> node) {
        var max = node;
        while (max.right != null) {
            max = max.right;
        }
        return max;
    }

    private void modifyParent(Node<K,V> node) {
        if (node.key.equals(root.key)) {
            root = (node.left != null) ? node.left : node.right;
        } else {
            var parent = node.parent;
            if (isRigthChild(node)) {
                parent.right = (node.left != null) ? node.left : node.right;
            } else {
                parent.left = (node.left != null) ? node.left : node.right;
            }
        }
        node.left = null;
        node.right = null;
    }

    private boolean isRigthChild(Node<K,V> node) {
        var parent = node.parent;
        return parent.right.key.equals(node.key);
    }

    private void removeLeave(Node<K,V> node) {
        if (node.key.equals(root.key)) {
            root = null;
        } else {
            var parent = node.parent;
            if (parent.right.key.equals(node.key)) {
                parent.right = null;
            } else {
                parent.left = null;
            }
        }
    }

    private boolean hasOneChild(Node<K,V> node) {
        return (node.left != null && node.right == null) || (node.left == null && node.right != null);
    }

    private boolean isLeaf(Node<K,V> node) {
        return node.left == null && node.right == null;
    }

    @Override
    public V getValue(K key) {
        if (isEmpty())
            throw new NoSuchElementException("empty tree");
        var cursor = root;
        while (cursor != null) {
            if (cursor.key.equals(key)) {
                return cursor.value;
            }
            cursor = (key.compareTo(cursor.key) > 0) ? cursor.right : cursor.left;
        }
        throw new  NoSuchElementException("Key not found");
    }

    @Override
    public int size() {
        return size(root);
    }
}
