package spelling;

import java.util.List;
import java.util.Queue;
import java.util.Set;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;

/** 
 * An trie data structure that implements the Dictionary and the AutoComplete ADT
 * @author You
 *
 */
public class AutoCompleteDictionaryTrie implements  Dictionary, AutoComplete {

    private TrieNode root;
    private int size;

    public AutoCompleteDictionaryTrie()
	{
		root = new TrieNode();
	}
	
	
	/** Insert a word into the trie.
	 * For the basic part of the assignment (part 2), you should ignore the word's case.
	 * That is, you should convert the string to all lower case as you insert it. */
	public boolean addWord(String word)
	{
	    //TODO: Implement this method.
		String wordLower = word.toLowerCase();
		TrieNode tmp = root;
		for (int i=0; i < wordLower.length(); ++i){
			char curr = wordLower.charAt(i);
			TrieNode tmp2 = tmp.insert(curr);
			if (tmp2 != null){
				tmp = tmp2;
			}
			else{
				tmp = tmp.getChild(curr);
				if (i == wordLower.length()-1 && tmp.endsWord()) return false;
			}
		}
		tmp.setEndsWord(true);
	    return true;
	}
	
	/** 
	 * Return the number of words in the dictionary.  This is NOT necessarily the same
	 * as the number of TrieNodes in the trie.
	 */
	public int size()
	{
	    //TODO: Implement this method
		int n = 0;
		TrieNode tmp = root;
		Queue<TrieNode> toBeVisited = new LinkedList<TrieNode>();
		toBeVisited.add(tmp);
		while(!toBeVisited.isEmpty()){
			TrieNode curr = toBeVisited.remove();
			if (curr.endsWord()){
				n++;
			}
			for (Character c : curr.getValidNextCharacters()){
				toBeVisited.add(curr.getChild(c));
			}
		}
		size = n;
	    return size;
	}
	
	
	/** Returns whether the string is a word in the trie */
	@Override
	public boolean isWord(String s) 
	{
	    // TODO: Implement this method
		TrieNode tmp = root;
		for (int i=0; i < s.length(); ++i){
			tmp = tmp.getChild(s.toLowerCase().charAt(i));
			if (tmp == null) return false;
		}
		return tmp.endsWord();
	}

	/** 
	 *  * Returns up to the n "best" predictions, including the word itself,
     * in terms of length
     * If this string is not in the trie, it returns null.
     * @param text The text to use at the word stem
     * @param n The maximum number of predictions desired.
     * @return A list containing the up to n best predictions
     */@Override
     public List<String> predictCompletions(String prefix, int numCompletions) 
     {
    	 // TODO: Implement this method
    	 // This method should implement the following algorithm:
    	 // 1. Find the stem in the trie.  If the stem does not appear in the trie, return an
    	 //    empty list
    	 // 2. Once the stem is found, perform a breadth first search to generate completions
    	 //    using the following algorithm:
    	 //    Create a queue (LinkedList) and add the node that completes the stem to the back
    	 //       of the list.
    	 //    Create a list of completions to return (initially empty)
    	 //    While the queue is not empty and you don't have enough completions:
    	 //       remove the first Node from the queue
    	 //       If it is a word, add it to the completions list
    	 //       Add all of its child nodes to the back of the queue
    	 // Return the list of completions
    	 List<String> result = new ArrayList<String>();
    	 TrieNode tmp = root;
    	 for (int i=0; i < prefix.length(); ++i){
    		 tmp = tmp.getChild(prefix.toLowerCase().charAt(i));
    		 if (tmp == null) return new ArrayList<String>();
    	 }
    	 Queue<TrieNode> toBeVisited = new LinkedList<TrieNode>();
    	 toBeVisited.add(tmp);
    	 while(!toBeVisited.isEmpty() && result.size() < numCompletions){
    		 TrieNode curr = toBeVisited.remove();
    		 if (curr.endsWord()) result.add(curr.getText());
    		 for (Character c : curr.getValidNextCharacters()){
        		 toBeVisited.add(curr.getChild(c));
        	 }
    	 }
         return result;
     }

 	// For debugging
 	public void printTree()
 	{
 		printNode(root);
 	}
 	
 	/** Do a pre-order traversal from this node down */
 	public void printNode(TrieNode curr)
 	{
 		if (curr == null) 
 			return;
 		
 		System.out.println(curr.getText());
 		
 		TrieNode next = null;
 		for (Character c : curr.getValidNextCharacters()) {
 			next = curr.getChild(c);
 			printNode(next);
 		}
 	}
 	

	
}