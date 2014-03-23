package DecisionTree;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;

public class Global{
	public static ArrayList< ArrayList<Integer> > TrainDocWord;
	public static ArrayList< ArrayList<Integer> > TestDocWord;
	public static ArrayList<Integer> TrainDocCat;
	public static ArrayList<Integer> TestDocCat;
	public static ArrayList<Integer> TrainWord;
	public static ArrayList<Integer> TrainDoc;
	
	public Global(){
		TrainDocWord = new ArrayList< ArrayList<Integer> >();
		TestDocWord = new ArrayList< ArrayList<Integer> >();
		TrainDocCat = new ArrayList<Integer>();
		TestDocCat = new ArrayList<Integer>();
		TrainWord = new ArrayList<Integer>();
		TrainDoc = new ArrayList<Integer>();
		
		for (int i = 0; i < 1062; i++) {
			TrainDocWord.add(null);
		}
		for (int i = 0; i < 708; i++) {
			TestDocWord.add(null);
		}
		
		try {
			Scanner s = new Scanner(new File("trainData.txt"));
			TrainDocWord.add(0,null);
			int i = s.nextInt();
			
			while (s.hasNextInt()) {
				ArrayList<Integer> arr = new ArrayList<Integer>();
				int first = s.nextInt();
				arr.add(first);
				if (!TrainWord.contains(first)) TrainWord.add(first);
				
				int index = s.nextInt();
			    while (index == i) {
			    	int word = s.nextInt();
			    	arr.add(word);
			    	if (!TrainWord.contains(word)) TrainWord.add(word);
			    	if (s.hasNextInt()) index = s.nextInt();
			    	else break;
			    }
			    TrainDocWord.add(i,arr);
			    TrainDoc.add(i);
			    i = index;
			}
			
			s.close();
		} catch (FileNotFoundException e) {
			System.out.print("Cannot find file trainData.txt\n");
		}
		
		try {
			Scanner s = new Scanner(new File("trainLabel.txt"));
			TrainDocCat.add(0,0);
			
			while (s.hasNextInt()) {
			    TrainDocCat.add(s.nextInt());
			}
			
			s.close();
		} catch (FileNotFoundException e) {
			System.out.print("Cannot find file trainLabel.txt\n");
		}
		
		try {
			Scanner s = new Scanner(new File("testData.txt"));
			TestDocWord.add(0,null);
			int i = s.nextInt();
			
			while (s.hasNextInt()) {
				ArrayList<Integer> arr = new ArrayList<Integer>();
				int first = s.nextInt();
				arr.add(first);
				
				int index = s.nextInt();
			    while (index == i) {
			    	arr.add(s.nextInt());
			    	if (s.hasNextInt()) index = s.nextInt();
			    	else break;
			    }
			    TestDocWord.add(i,arr);
			    i = index;
			}
			
			s.close();
		} catch (FileNotFoundException e) {
			System.out.print("Cannot find file testData.txt\n");
		}
		
		try {
			Scanner s = new Scanner(new File("testLabel.txt"));
			TestDocCat.add(0,0);
			
			while (s.hasNextInt()) {
			    TestDocCat.add(s.nextInt());
			}
			
			s.close();
		} catch (FileNotFoundException e) {
			System.out.print("Cannot find file testLabel.txt\n");
		}	
	}
}