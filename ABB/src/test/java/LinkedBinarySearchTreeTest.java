import org.junit.Test;
import java.util.NoSuchElementException;
import static org.junit.Assert.*;


public class LinkedBinarySearchTreeTest {

    @Test
    public void isEmpty_on_empty_tree_should_return_true() {
        var empty = generateEmptyTree();
        assertTrue(empty.isEmpty());
    }

    @Test
    public void isEmpty_on_non_empty_tree_should_return_false() {
        var tree = generateTree();
        assertFalse(tree.isEmpty());
    }

    @Test
    public void size_on_empty_tree_should_be_0() {
        var empty = generateEmptyTree();
        assertEquals(0, empty.size());
    }

    @Test
    public void size_on_tree_should_be_6() {
        var tree = generateTree();
        assertEquals(6, tree.size());
    }

    @Test
    public void left_on_empty_tree_should_throw_NSE_exception() {
        var empty = generateEmptyTree();
        assertThrows(
                NoSuchElementException.class,
                () -> empty.left()
        );
    }

    @Test
    public void left_on_tree_should_return_the_left_tree() {
        var tree = generateTree();
        assertEquals(4, (int) tree.left().getRoot());
        assertEquals(3, tree.left().size());
    }

    @Test
    public void right_on_empty_tree_should_throw_NSE_exception() {
        var empty = generateEmptyTree();
        assertThrows(
                NoSuchElementException.class,
                () -> empty.right()
        );
    }

    @Test
    public void right_on_tree_should_return_the_right_tree() {
        var tree = generateTree();
        assertTrue(tree.right().getRoot() == 10);
        assertEquals(2, tree.right().size());
    }

    @Test
    public void getValue_on_empty_tree_should_throw_NSE_exception() {
        var empty = generateEmptyTree();
        assertThrows(
                NoSuchElementException.class,
                () -> empty.getValue(3)
        );
    }

    @Test
    public void getValue_should_throw_NSE_exception_if_the_key_is_not_found() {
        var tree = generateTree();
        assertThrows(
                NoSuchElementException.class,
                () -> tree.getValue(9)
        );
    }

    @Test
    public void getValue_should_return_the_associated_value_of_a_key() {
        var tree = generateTree();
        assertEquals((int) tree.getValue(7), 1);
        assertEquals((int) tree.getValue(4), 1);
        assertEquals((int) tree.getValue(10), 1);
        assertEquals((int) tree.getValue(2), 1);
        assertEquals((int) tree.getValue(6), 1);
        assertEquals((int) tree.getValue(20), 1);
    }

    @Test
    public void adding_a_new_key_should_increment_size() {
        var tree = generateTree();
        tree.add(50, 1);
        assertEquals(7, tree.size());
    }

    @Test
    public void adding_an_existing_key_should_change_value() {
        var tree = generateTree();
        tree.add(7, 2);
        assertEquals(2, (int) tree.getValue(7));
    }

    @Test
    public void remove_on_empty_tree_should_throw_NSE_exception() {
        var empty = generateEmptyTree();
        assertThrows(
                NoSuchElementException.class,
                () -> empty.remove(7)
        );
    }

    @Test
    public void remove_a_non_existing_key_should_throw_NSE_exception() {
        var tree = generateTree();
        assertThrows(
                NoSuchElementException.class,
                () -> tree.remove(50)
        );
    }

    @Test
    public void remove_a_key_should_decrement_size() {
        var tree = generateTree();
        tree.remove(7);
        assertEquals(5, tree.size());
    }

    @Test
    public void remove_node_with_one_child() {
        var tree = generateTree();
        tree.remove(10);
        assertEquals(20, (int) tree.right().getRoot());
    }

    @Test
    public void remove_node_with_two_child() {
        var tree = generateTree();
        tree.remove(7);
        assertEquals(6, (int) tree.getRoot());
    }

    private static LinkedBinarySearchTree<Integer, Integer> generateEmptyTree() {
        return new LinkedBinarySearchTree<>();
    }

    private static LinkedBinarySearchTree<Integer, Integer> generateTree() {
        /*
        Generate tree
                  7
                /   \
               4    10
             /   \    \
            2     6    20
         */
        var tree = new LinkedBinarySearchTree<Integer, Integer>();
        tree.add(7, 1);
        tree.add(4, 1);
        tree.add(10, 1);
        tree.add(2, 1);
        tree.add(6, 1);
        tree.add(20, 1);
        return tree;
    }
}
