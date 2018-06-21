import java.util.*;
import com.google.gson.GsonBuilder;

public class Blockchain {
	public static ArrayList<Block> bc = new ArrayList<Block>();
	public static int difficulty = 5;

	public static void main(String[] args) {
		bc.add(new Block("First Block", "0") );
		System.out.println("Trying to Mine block 1...");
		bc.get(0).mining(difficulty);
		
		bc.add(new Block("Second Block", bc.get(bc.size()-1).hash) );
		System.out.println("Trying to Mine block 2...");
		bc.get(1).mining(difficulty);
		bc.add(new Block("Third Block", bc.get(bc.size()-1).hash) );
		System.out.println("Trying to Mine block 3...");
		bc.get(2).mining(difficulty);
		
		System.out.println("\nBlockchain is Valid: " + isChainValid());
		
		
		String blockchainJson = new GsonBuilder().setPrettyPrinting().create().toJson(bc);
		System.out.println("\nThe block chain: ");
		System.out.println(blockchainJson);

	
	}
	public static Boolean isChainValid() {
		Block currentBlock;
		Block previousBlock;
		String hashTarget = new String(new char[difficulty]).replace('\0', '0');
		for(int i =1;i<bc.size();i++) 
		{
			currentBlock=bc.get(i);
			previousBlock=bc.get(i-1);
			if(!currentBlock.hash.equals(currentBlock.calculateHash())) {
				System.out.println("Current hash not equal");
				return false;
			}
			if(!previousBlock.hash.equals(currentBlock.previousHash)) {
				System.out.println("Previous hash not equal");
				return false;
			}
			if(!currentBlock.hash.substring(0,difficulty).equals(hashTarget)) {
				System.out.println("The block hasn't been mined");
				return false;
			}
				
			
			
		}
		return true;
			
	}

}
