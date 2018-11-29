package C45;

import java.io.IOException;

public class RunClassification {
	
	public static void main(String[] args) throws IOException{
	
	
			
		Main.loadData("F:/Downloads/owls (1).csv");
		System.out.println(" ");
		
		for(int i = 0; i < Main.treeScores.size();i++){
			
			System.out.println(Main.treeScores.get(i).toString());
			
		}
		
		
		
	}

}
