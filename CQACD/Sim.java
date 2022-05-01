package mytry;

import java.io.File;
import java.io.IOException;

import edu.mit.jwi.Dictionary;
import edu.mit.jwi.IDictionary;
public class Sim {
	private static String path = "dict"; 
	private static File wnDir=new File(path);
    private static IDictionary dict=new Dictionary(wnDir);
	public double mySim(String Word1,String Word2) throws IOException{
		synonyms mys = new synonyms();
		simnoun mysimn = new simnoun();
		pathlen mypatl = new pathlen();
		SimilartoSimilarity mysimto = new SimilartoSimilarity();
		if(mys.mysynonyms(Word1, Word2, dict)!=0){
			return 1;
		}else{
			if(Word1.contains("/NN")){
				return mysimn.mysimoun(Word1, Word2, dict);
			}else{
				if(Word1.contains("/VB") && mypatl.mypathlen(Word1, Word2, dict) != 0){
					return 0.8;
				}else{
					if((Word1.contains("/JJ") || Word1.contains("/RB")) && mysimto.cal_similarity(Word1, Word2, dict) > 0){
						return 0.9;
					}else{
						return 0;
					}
				}
			}
		}
		
	}
}
