package DecisionTree;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

class Main {
	public static void main(String[] args) {
		Comparator<Node> igcomp = new NodeIGComparator(); 
		Comparator<Node> igcompplus = new NodeIGComparatorPlus();
		Global global = new Global();
		ArrayList<Integer> att = new ArrayList<Integer>();
		//Switch between igcomp and igcompplus
		PriorityQueue<Node> q = new PriorityQueue<Node>(100,igcompplus);
		
		Node root = new Node(att,Global.TrainDoc);
		root.chooseAttribute();
		q.add(root);
		
		for (int j = 0; j< 10; j++) {
			Node nd = q.poll();
			if (nd == null) break;
			
			if (nd.splitable == false ) {
				j--; continue;
			}
			nd.split();
			q.add(nd.ct);
			q.add(nd.nc);  
			
			int correct= 0;
			for (int i = 1; i < 708; i++){
				ArrayList<Integer> wlst = Global.TestDocWord.get(i);
				if (wlst==null) wlst = new ArrayList<Integer>();
				Node node = root;
				while (node.leaf != true) {
					if(wlst.contains(node.word)) node = node.ct;
					else node = node.nc;
				}
				if (node.cat == Global.TestDocCat.get(i)) correct++;
			}
			double per = (double)correct / 707.0;
			System.out.print( per+"\n");
		}
	}
}