import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

class Huffman {
	
	public static JPanel panel = new JPanel();

	public static void printCode(HuffmanNode root, String s) {
		
		if (root == null) {
			return;
		}

		if (root.left == null && root.right == null && Character.isLetter(root.c)) {
			JLabel huff = new JLabel(root.c + ":" + s);
			panel.add(huff);
			return;
		}

		printCode(root.left, s + "0");
		printCode(root.right, s + "1");
	}

	public static void main(String[] args) {

		String inputString = JOptionPane.showInputDialog("Digite um texto:");


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
		
		JFrame frame = new JFrame("Huffman");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.add(Box.createVerticalStrut(10));
		frame.getContentPane().add(panel);
		frame.setSize(1000, 1000);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
		printCode(root, "");

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
