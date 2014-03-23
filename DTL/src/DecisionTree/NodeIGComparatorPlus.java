package DecisionTree;
import java.util.Comparator;


public class NodeIGComparatorPlus implements Comparator<Node> {
	@Override
    public int compare(Node n1,Node n2) {
		int s1 = n1.examples.size();
		int s2 = n2.examples.size();
		
		if ( ( n1.IG * s1 ) < ( n2.IG * s2 ) ) {
			return 1;
		}
		else if ( ( n1.IG * s1 ) > ( n2.IG * s2 )) {
			return -1;
		}
		else return 0;
	}
}