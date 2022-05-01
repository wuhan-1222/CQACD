package MySimilarity;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.mit.jwi.Dictionary;
import edu.mit.jwi.IDictionary;
import edu.mit.jwi.item.IIndexWord;
import edu.mit.jwi.item.ISynset;
import edu.mit.jwi.item.ISynsetID;
import edu.mit.jwi.item.IWord;
import edu.mit.jwi.item.IWordID;
import edu.mit.jwi.item.POS;
import edu.mit.jwi.item.Pointer;

public class synonyms {
	public static int mysynonyms(String targetWord, String compareWord,IDictionary dict)
	{
		int result=0;
		IIndexWord idxWord1 = dict.getIndexWord(targetWord, POS.NOUN);
	    IIndexWord idxWord2 = dict.getIndexWord(compareWord, POS.NOUN);
		if(idxWord1!=null&&idxWord2!=null){
			for(int i=0;i<idxWord1.getWordIDs().size();++i)
			{
				for(int j=0;j<idxWord2.getWordIDs().size();++j)
				{	 
					IWordID wordID1 = idxWord1.getWordIDs().get(i); 
					IWord word1 = dict.getWord(wordID1); 
					ISynset synset1 = word1.getSynset(); 					
					IWordID wordID2 = idxWord2.getWordIDs().get(j); 
					IWord word2 = dict.getWord(wordID2); 
					ISynset synset2 = word2.getSynset(); 					
					if(synset1.equals(synset2))
					{
					return 1;
					}
				}
			}
			return result;
		}
	    	  return result;
	}
}








