package DecisionTree;
import java.util.Comparator;


public class NodeIGComparator implements Comparator<Node> {
	@Override
    public int compare(Node n1,Node n2) {
		if (n1.IG < n2.IG) {
			return 1;
		}
		else if (n1.IG > n2.IG) {
			return -1;
		}
		else return 0;
	}
}