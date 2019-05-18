import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

public class MyTrieSet implements TrieSet61B {
  //  private static final int R = 128;
    private Node root;
  //  private int n;

    private static class Node {
        private boolean isKey;
        private HashMap<Character, Node> map;
        private Node() {
            isKey = false;
            map = new HashMap<>();
        }

    }

    public MyTrieSet() {
        clear();
    }

    @Override
    public void clear() {
        root = new Node();
    }



    @Override
    public boolean contains(String key) {
        if (key == null) {
            throw new IllegalArgumentException(); //return false?
        }
        Node current = root;
        for (int i = 0; i < key.length(); i++) {
            char c = key.charAt(i);
            if (!current.map.containsKey(c)) {
                return false;
            } else {
                current = current.map.get(c);
            }
        }
        return current.isKey;

    }



    @Override
    public void add(String key) {
        if (key == null || key.length() < 1) {
            return;
        }
        Node curr = root;
        for (int i = 0, n = key.length(); i < n; i++) {
            char c = key.charAt(i);
            if (!curr.map.containsKey(c)) {
                curr.map.put(c, new Node());
            }
            curr = curr.map.get(c);
        }
        curr.isKey = true;
    }

    @Override
    public List<String> keysWithPrefix(String prefix) {
        Node current = root;
        for (int i = 0; i < prefix.length(); i++) {
            char c = prefix.charAt(i);
            if (!current.map.containsKey(c)) {
                return new ArrayList<>();
            } else {
                current = current.map.get(c);
            }
        }
        return helper(prefix, new ArrayList<>(), current);
    }

    private List<String> helper(String x, List<String> ls, Node n) {
        if (n.isKey) {
            ls.add(x);
        }
        for (Character c : n.map.keySet()) {
            helper(x + c, ls, n.map.get(c));
        }
        return ls;
    }

    @Override
    public String longestPrefixOf(String key) {
        throw new UnsupportedOperationException();
    }

}
