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
import java.util.Iterator;
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

public class SimilartoSimilarity {	
	public static double cal_similarity(String targetWord, String compareWord,IDictionary dict)
	{
		double result=0;
		boolean judgepath = false;
		IIndexWord idxWord1 = dict.getIndexWord(targetWord, POS.ADJECTIVE);//get the first word IndexWord
	    IIndexWord idxWord2 = dict.getIndexWord(compareWord, POS.ADJECTIVE);//get the second word IndexWord
		
		if(idxWord1!=null&&idxWord2!=null){
			for(int i=0;i<idxWord1.getWordIDs().size();++i)
			{//System.out.println(i);
				for(int j=0;j<idxWord2.getWordIDs().size();++j)
				{	
					IWordID wordID1 = idxWord1.getWordIDs().get(i); 
					IWord word1 = dict.getWord(wordID1); 
					ISynset synset1 = word1.getSynset(); 
					
					IWordID wordID2 = idxWord2.getWordIDs().get(j);
					IWord word2 = dict.getWord(wordID2); 
					ISynset synset2 = word2.getSynset(); 
					
					List<IWordID> idList1 =dict.getWord(wordID1).getRelatedWords(Pointer.ANTONYM);
					List<IWordID> idList2 =dict.getWord(wordID2).getRelatedWords(Pointer.ANTONYM);
					if(synset1.toString().equals(synset2.toString())){
						return 0;
					}
					for(int h=0;h<idList1.size();h++){					
							if(idList1.get(h).equals(wordID2)){
								double sim = 1.4/(1.4 + 1);
								return sim;
							}
					}
					for(int h=0;h<idList2.size();h++){					
						if(idList2.get(h).equals(wordID1)){
							double sim = 1.4/(1.4 + 1);
							return sim;
						}
				    }
					List<ISynsetID> idList3 =synset1.getRelatedSynsets(Pointer.SIMILAR_TO);
					List<ISynsetID> idList4 =synset2.getRelatedSynsets(Pointer.SIMILAR_TO);
					
					for(int h=0;h<idList3.size();h++){					
						if(idList3.get(h).equals(synset2.getID())){
							double sim = 6.3/(6.3 + 1);
							return sim;
						}
				    }
					for(int h=0;h<idList4.size();h++){					
						if(idList4.get(h).equals(synset1.getID())){
							double sim = 6.3/(6.3 + 1);
							return sim;
						}
				    }
				}
			}	
		}
	    	  return result;
	}
}








