package DecisionTree;

import java.util.ArrayList;
import java.lang.Math;

public class Node {
	boolean leaf,splitable;
	
	int word;
	ArrayList<Integer> examples;
	ArrayList<Integer> usedAttr;
	
	Node ct;
	Node nc;
	
	int cat;
	double IG;
	
	public Node(ArrayList<Integer> uA, ArrayList<Integer> allDocs){

		examples = allDocs;
		leaf = true;
		usedAttr = uA;
	}
	
	public void chooseAttribute(){
		double maxIG = -10.0;
		for (int i = 0; i < Global.TrainWord.size(); i++) {
			int w = Global.TrainWord.get(i);
			if (usedAttr.contains(w)) continue;
				
			int ct1 = 1,ct2 = 1,nc1 = 1,nc2 = 1;
			for (int j = 0; j < examples.size(); j++) {
				if (Global.TrainDocWord.get(examples.get(j)).contains(w)) {
					if (Global.TrainDocCat.get(examples.get(j)) == 1 ) {
						ct1++;
					}
					else {
						ct2++;
					}
				}
				else {
					if (Global.TrainDocCat.get(examples.get(j)) == 1 ) {
						nc1++;
					}
					else {
						nc2++;
					}
				}
			}			
			double ctr = (double)(ct1+ct2) / (double)(ct1+ct2+nc1+nc2);
			double ncr = (double)(nc1+nc2) / (double)(ct1+ct2+nc1+nc2);//getI((double)(ct1+nc1), (double)(ct2+nc2))
			double lIG = 1 - ctr * getI((double)ct1,(double)ct2) - ncr * getI((double)nc1,(double)nc2);
			if (lIG > maxIG) {
				maxIG = lIG;
				cat = ((ct1+nc1)>(ct2+nc2))?1:2;				
				word = w;   
				if ((ct1+nc1) == 2 || (ct2+nc2) == 2) {
					splitable = false;
				}
				else {
					splitable = true;
				}
			}
		}
		IG = maxIG;
	}
	
	
	double getI(double p,double n) {
		double rp = -1 * p/(p+n) * Math.log(p/(p+n)) / Math.log(2);
		double rn = -1 * n/(p+n) * Math.log(n/(n+p)) / Math.log(2);
		return rp+rn;
	}

	public void split(){
		ArrayList<Integer> ctlst = new ArrayList<Integer>();
		ArrayList<Integer> nclst = new ArrayList<Integer>();
		usedAttr.add(word);
		
		for (int j = 0; j < examples.size(); j++) {
			if (Global.TrainDocWord.get(examples.get(j)).contains(word)) {
				ctlst.add(examples.get(j));
			}
			else {
				nclst.add(examples.get(j));
			}
		}			
		
		leaf = false;
		ct = new Node(usedAttr, ctlst);
		nc = new Node(usedAttr, nclst);
		ct.chooseAttribute();
		nc.chooseAttribute();
	} 
	
	public int print(Node n) {
		if (n == null) {
			System.out.print("null\n");
			return 0;
		}
		else {
			System.out.print(n.word +" IG: "+ n.IG + " examples: "+n.examples.size()+"\n");
			print(n.ct);
			print(n.nc);
		}
		return 1;
	}

}
