public interface BinarySearchTree<K extends Comparable<? super K>, V> {
    LinkedBinarySearchTree<K, V> left();
    LinkedBinarySearchTree<K, V> right();
    void add(K key, V value);
    boolean isEmpty();
    void remove(K key);
    K getRoot();
    V getValue(K key);
    int size();
}
