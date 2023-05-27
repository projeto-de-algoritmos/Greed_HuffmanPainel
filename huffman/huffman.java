import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Scanner;

class Huffman {

    public static void printCode(HuffmanNode root, String s) {
        if (root == null) {
            return;
        }

        if (root.left == null && root.right == null && Character.isLetter(root.c)) {
            System.out.println(root.c + ":" + s);
            return;
        }

        printCode(root.left, s + "0");
        printCode(root.right, s + "1");
    }

    public static void main(String[] args) {

        Scanner ler = new Scanner(System.in);

        String inputString;
        inputString = ler.nextLine();

        Map<Character, Integer> charFreqMap = new HashMap<>();
        StringBuilder charArrayBuilder = new StringBuilder();

        for (char c : inputString.toCharArray()) {
            if (!charFreqMap.containsKey(c)) {
                charArrayBuilder.append(c);
            }
            charFreqMap.put(c, charFreqMap.getOrDefault(c, 0) + 1);
        }

        char[] charArray = charArrayBuilder.toString().toCharArray();

        int n = charArray.length;

        PriorityQueue<HuffmanNode> q = new PriorityQueue<>(charFreqMap.size(), new MyComparator());

        for (char c : charArray) {
            HuffmanNode hn = new HuffmanNode();
            hn.c = c;
            hn.data = charFreqMap.get(c);
            hn.left = null;
            hn.right = null;
            q.add(hn);
        }

        HuffmanNode root = null;
        while (q.size() > 1) {
            HuffmanNode x = q.poll();
            HuffmanNode y = q.poll();
            HuffmanNode f = new HuffmanNode();
            f.data = x.data + y.data;
            f.left = x;
            f.right = y;
            root = f;
            q.add(f);
        }

        printCode(root, "");

        ler.close();
    }
}

class HuffmanNode {

    int data;
    char c;
    HuffmanNode left;
    HuffmanNode right;
}

class MyComparator implements Comparator<HuffmanNode> {
    public int compare(HuffmanNode x, HuffmanNode y) {
        return x.data - y.data;
    }
}
