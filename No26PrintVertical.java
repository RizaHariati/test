import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class Node{
	int data;
	int height;
	Node left;
	Node right;
	
}

public class No26PrintVertical {
	
	public Node createNode(int k) {
		Node a = new Node();
		a.data = k;
		a.left = null;
		a.right = null;
		return a;
	}
	
	public static void main(String[] args) {
		No26PrintVertical bin = new  No26PrintVertical();
		 Node root = bin.createNode(2);
		 root.left = bin.createNode(7);
		 root.right = bin.createNode(5);
		 root.left.left = bin.createNode(12);
		 root.left.right = bin.createNode(6);
		 root.left.right.left = bin.createNode(15);
		 root.left.right.right = bin.createNode(11);
		 root.right.right = bin.createNode(9);
		 root.right.right.left = bin.createNode(4);
		 
		 calculate(root);
		 
	}

	private static void calculate(Node root) {
		if(root == null) {
			return;
		}
		Queue<Node> list = new LinkedList<Node>();
		List <Vertical> ver = new ArrayList<Vertical>();
		list.add(root);
		root.height = 0;
		
		
		while(list.size()!= 0) {
			
			Node temp = list.remove();
			int h = temp.height;
			ver.add(new Vertical (h, temp.data));
			if(temp.left != null) {
				list.add(temp.left);
				temp.left.height = h - 1;
			}
			if(temp.right != null) {
				list.add(temp.right);
				temp.right.height = h + 1;
			}
		}
		Collections.sort(ver,new Comparator <Vertical>() {

			@Override
			public int compare(Vertical o1, Vertical o2) {
				int a = o1.getKey();
				int b = o2.getKey();
				if(a > b) {
					return 1;
				}
				else if(b > a) {
					return -1;
				}
				else {
					return 0;
				}
			}
			
		});
		printing(ver);
	}

	@SuppressWarnings("unused")
	private static void printing(List<Vertical> ver) {
		int i = 1;
		
		ArrayList<Integer> list = new ArrayList<Integer>();
		list.add(ver.get(0).value);
		int h = ver.get(0).value;
		while(i < ver.size()) {
			
			if(ver.get(i).key > ver.get(i - 1).key) {
				System.out.println(list);
				list = new ArrayList<Integer>();
		//		list.add(ver.get(i).getValue());
				h = 0;
			}
			
			
			list.add(ver.get(i).getValue());
			h += ver.get(i).value;
			i++;
		}
		System.out.println(list);
	}
	
}

class Vertical{
	int key;
	int value;
	public Vertical(int key, int value) {
		
		this.key = key;
		this.value = value;
	}
	public int getKey() {
		return key;
	}
	public int getValue() {
		return value;
	}
	
}
class Comparing implements Comparator<Vertical>{

	@Override
	public int compare(Vertical v1, Vertical v2) {
		
		return v1.getKey() - v2.getKey();
	}
	
}
