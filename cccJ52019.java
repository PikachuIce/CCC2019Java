package StageFiles;
import java.util.*;

public class cccJ52019 {

	static class Conversion{
		int stepsCost;
		int ruleID;
		int index;
		String postStr;
		Conversion(int stepsCost, int ruleID,int index,String postStr){
			this.stepsCost = stepsCost;
			this.ruleID = ruleID;
			this.index = index;
			this.postStr = postStr;
		}
	}
	
	static Scanner sc = new Scanner(System.in);
	
	public static void main(String[] args) {

		String[][] rules = new String[4][];
		
		rules[1] = sc.nextLine().split(" ");
		rules[2] = sc.nextLine().split(" ");
		rules[3] = sc.nextLine().split(" ");
		
		String[] input = sc.nextLine().split(" ");
		
		int stepsNeed = Integer.parseInt(input[0]);
		
		String initStr = input[1];
		
		String destStr = input[2];

		LinkedList<Conversion> result = new LinkedList<>();
		
		HashSet<Conversion> visited = new HashSet<>();
		
		Queue<LinkedList<Conversion>> queue = new LinkedList<>();
		
		LinkedList<Conversion> first = new LinkedList<>();
		
		Conversion one = new Conversion(0,0,0,initStr);
	
		first.offer(one);
		
		queue.offer(first);
		
		while(!queue.isEmpty()) {
			
			LinkedList<Conversion> currentList = queue.poll();
			
			Conversion currentConv = currentList.getLast();
			
			if (currentConv.stepsCost == stepsNeed && currentConv.postStr.equals(destStr)) {
				result.addAll(currentList);
				break;
			}
			
			if (!visited.contains(currentConv) && currentConv.stepsCost < stepsNeed) {
				
				String currentStr = currentConv.postStr;

				for (int i = 1; i <= 3; i++) {
					for (int j = 0; j < currentStr.length(); j++) {
						String translatedStr = translate(rules, currentStr, i, j); 
						if (translatedStr != null) {
							LinkedList<Conversion> newList = new LinkedList<>();
							newList.addAll(currentList);
							newList.add(new Conversion(currentConv.stepsCost + 1, i, j, translatedStr));
							queue.offer(newList);
						}
					}
				}
				
			}
			
		}
		
		for (Conversion curr : result) {
			System.out.println(curr.ruleID + " " + (curr.index + 1) + " " + curr.postStr);
		}
		
	}

	static String translate(String[][] rules, String preStr, int ruleID, int index) {

		if (preStr.length() >= index + rules[ruleID][0].length()) {
			if (preStr.subSequence(index, index + rules[ruleID][0].length()).equals(rules[ruleID][0])) {
				return preStr.substring(0,index) + rules[ruleID][1] 
						+ preStr.substring(index + rules[ruleID][0].length());
			}
		}
		
		return null;
	}
}
