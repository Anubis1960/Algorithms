package DataStructures;

class TrieNode {
    TrieNode[] child = new TrieNode[26];
    boolean isEndOfWord;
}

public class Trie {

    // Trie node class

    private TrieNode root;

    // Constructor
    public Trie() {
        root = new TrieNode();
    }

    // Insert a word into the Trie
    public void insert(String word) {
        TrieNode node = root;
        for (char ch : word.toCharArray()) {
            if (node.child[ch - 'a'] == null) {
                node.child[ch - 'a'] = new TrieNode();
            }
            node = node.child[ch - 'a'];
        }
        node.isEndOfWord = true;
    }

    // Search for a word in the Trie
    public boolean search(String word) {
        TrieNode node = root;
        for (char ch : word.toCharArray()) {
            node = node.child[ch - 'a'];
            if (node == null) {
                return false;
            }
        }
        return node.isEndOfWord;
    }

    // Check if a prefix exists in the Trie
    public boolean startsWith(String prefix) {
        TrieNode node = root;
        for (char ch : prefix.toCharArray()) {
            node = node.child[ch - 'a'];
            if (node == null) {
                return false;
            }
        }
        return true;
    }

    // Driver code to test the Trie
    public static void main(String[] args) {
        Trie trie = new Trie();

        // Insert words
        trie.insert("bat");
        trie.insert("ball");
        trie.insert("doll");
        trie.insert("dork");
        trie.insert("do");
        trie.insert("dorm");

        // Search for words
        System.out.println(trie.search("bat"));    // true
        System.out.println(trie.search("ball"));   // true
        System.out.println(trie.search("doll"));   // true
        System.out.println(trie.search("dor"));    // false

        // Check for prefixes
        System.out.println(trie.startsWith("do"));  // true
        System.out.println(trie.startsWith("dor")); // true
        System.out.println(trie.startsWith("bat"));  // true
        System.out.println(trie.startsWith("cat")); // false
    }
}
