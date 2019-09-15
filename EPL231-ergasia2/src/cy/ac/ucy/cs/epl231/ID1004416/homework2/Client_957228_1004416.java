/* Author: Andreas Iacovou 1004416
 * Author: Andreas Panteli 957228
 * Date: 4/11/15
 * This program takes as input 2 files from the arguments. the first file contains a text
 * and its words are stored in a binary tree and an avl tree.The second file contains
 * the words which are going to be searched in the text. The program prints the time
 * in milliseconds needed to insert all the words in each of the trees. Then the program searches
 * each word in the text and prints in which positions in the text the word was found. The program also 
 * prints the time in milliseconds the searching algorithm of the words took for each tree. 
 * 
 */

package cy.ac.ucy.cs.epl231.ID1004416.homework2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.net.URL;
import java.util.Scanner;



public class Client_957228_1004416 {
	
	public static final boolean DEBUG_BINARY = false;
	public static final boolean DEBUG_AVL = false;

	public static void main(String[] args) throws Exception {
		
		
		String textFile = "text.txt";
		String wordsFile = "wordsToSearch.txt";
		
		if(!DEBUG_BINARY && !DEBUG_AVL){
			if(args.length!=2){
				System.out.println("wrong command. you have to give 2 files with correct names");
				return;
			}
			
			textFile = args[0];
			wordsFile = args[1];
			
			File exist1 = new File(textFile);
			File exist2 = new File(wordsFile);
			if(!exist1.exists() || !exist2.exists()){
				System.out.println("wrong command. you have to give 2 files with correct names");
				return;
			}
		}
		
		
		
		FileReader file1 = new FileReader(textFile);
		BufferedReader reader1 = new BufferedReader(file1);
		
		FileReader file2 = new FileReader(wordsFile);
		BufferedReader reader2 = new BufferedReader(file2);
		
		//Code to read the text and store it in variable
		String text = "";
		String line = reader1.readLine();
		while(line!=null){
			text+= line;
			line = reader1.readLine();
		}
		if(DEBUG_BINARY || !DEBUG_AVL){
			BinaryTree_957228_1004416 tree = new BinaryTree_957228_1004416();
			
			//Code to create Binary Tree
			int CharCounter = 0;
			char character = '\0';
			String word = "";
			boolean toInsert = false;
			long start = System.currentTimeMillis();
			for(int i=0; i<text.length(); i++){
				character = text.charAt(i);
				if(character != ' '){
					word+=character;
					CharCounter++;
					toInsert=true;
				}
				else{
					if(toInsert){
						tree.insert(word, CharCounter-word.length()+1);
					}
					CharCounter++;
					word="";
					toInsert=false;
				}
			}
			long end = System.currentTimeMillis();
			if(!DEBUG_BINARY)
			System.out.println("to create the binary tree we needed: " + (end-start) + " milliseconds");
			String searchWord="";
			searchWord = reader2.readLine();
			start = System.currentTimeMillis();
			while(searchWord!=null){
				tree.print(searchWord,DEBUG_BINARY);
				searchWord = reader2.readLine();
			}
			end = System.currentTimeMillis();
			if(!DEBUG_BINARY)
				System.out.println("The time to search binary tree in milliseconds is: "+ (end-start));
			if(DEBUG_BINARY){
				System.out.println("You can now enter your own words to search. Type EXIT to finish");
				Scanner scanner = new Scanner(System.in);
				String Searchword = scanner.nextLine();
				while(!Searchword.equals("EXIT")){
					tree.print(Searchword, DEBUG_BINARY);
					Searchword = scanner.nextLine();
				}
			}
		}	
		if(DEBUG_AVL || !DEBUG_BINARY){
			AvlTree_957228_1004416 Avltree = new AvlTree_957228_1004416();
			//Code to create AVL tree
			int CharCounter = 0;
			char character = '\0';
			String word = "";
			boolean toInsert = false;
			long start = System.currentTimeMillis();
			for(int i=0; i<text.length(); i++){
				character = text.charAt(i);
				if(character != ' '){
					word+=character;
					CharCounter++;
					toInsert=true;
				}
				else{
					if(toInsert){
						Avltree.insert(word, CharCounter-word.length()+1);
					}
					CharCounter++;
					word="";
					toInsert=false;
				}
			}
			long end = System.currentTimeMillis();
			if(!DEBUG_AVL)
			System.out.println("to create the AVL tree we needed: " + (end-start) + " milliseconds");
			String searchWord="";
			searchWord = reader2.readLine();
			start = System.currentTimeMillis();
			while(searchWord!=null){
				Avltree.print(searchWord,DEBUG_AVL);
				searchWord = reader2.readLine();
			}
			end = System.currentTimeMillis();
			if(!DEBUG_AVL)
				System.out.println("The time needed to search AVL tree in milliseconds is: " + (end-start));
			if(DEBUG_AVL){
				System.out.println("You can now enter your own words to search. Type EXIT to finish");
				Scanner scanner = new Scanner(System.in);
				String Searchword = scanner.nextLine();
				while(!Searchword.equals("EXIT")){
					Avltree.print(Searchword, DEBUG_AVL);
					Searchword = scanner.nextLine();
				}
			}
		}
		
		
		
		reader1.close();
		reader2.close();
		
	}

}
