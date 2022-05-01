package MySimilarity;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
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

public class pathlen {
	private static String strtemp = "";
	private static String leastcommonsub = "";
	private static String wwlcp="";
	private int i = 0;
	private int index = 0;
	private static int m = 0;
	private static int h = 0;
	private static int n1 = 0;
	private static int n2 = 0;
	private double H = 0;
	private double N1 = 0;
	private double N2 = 0;
	private static double pa = 0.0;
	private double ss = 0.0;
	private static Map<Integer,ISynsetID> mySynsetmap = new HashMap<Integer,ISynsetID>();
	private static ArrayList<String> list1 = new ArrayList<String>();
	private static	ArrayList<String> list2 = new ArrayList<String>();
	private static	ArrayList<String> listResult = new ArrayList<String>();
	private ArrayList<String> result=new ArrayList<String>();
	private static String path = "dict"; 
	private static File wnDir=new File(path);
    private static IDictionary dict=new Dictionary(wnDir);
	public static int mypathlen(String Word1, String Word2,IDictionary dict) throws IOException
	{
		   dict.open();
	  	   list1.clear();
	  	   list2.clear();
	  	   mySynsetmap.clear();
	  	   listResult.clear();
	  	   m = 1;
	  	   Hyper(Word1,dict);
	  	   strtemp = "";
	  	   leastcommonsub = "";
	  	   m=2;
	  	   Hyper(Word2,dict);
	  		
	  		for(int i=0;i<list1.size();i++){
	  			if(list1.get(i).length()==0){
	  				list1.remove(i);
	  			}else{
	  			}
	  		}
	  		for(int i=0;i<list2.size();i++){
	  			if(list2.get(i).length()==0){
	  				list2.remove(i);
	  			}else{
	  			}
	  		}
	  		
	  		double resultvalue[]=new double[list1.size()*list2.size()];
	  		int resultsum=0;
	  		for(int j=0;j<list1.size();j++){
	  			String[] wordtemp1 = list1.get(j).substring(4, list1.get(j).length()).split("--->");
	  		for(int x = 0;x < wordtemp1.length/2;x++){
				String temp = wordtemp1[x];
				wordtemp1[x] = wordtemp1[wordtemp1.length-1-x];
				wordtemp1[wordtemp1.length-1-x]=temp;
			}
	  		
	  		for(int z=0;z<list2.size();z++){
	  			String[] wordtemp2=list2.get(z).substring(4, list2.get(z).length()).split("--->");
	  	  		for(int y=0;y<wordtemp2.length/2;y++){
	  				String temp=wordtemp2[y];
	  				wordtemp2[y]=wordtemp2[wordtemp2.length-1-y];
	  				wordtemp2[wordtemp2.length-1-y]=temp;
	  			}
	  		 h=0;n1=0;n2=0;
	  		int count=0,hypon=0;
	  		double result1 = 0;
	  		double depth=0,path_1=0;
	  		int leafheight=0;
	  		wwlcp="";
	  		int wordlength=wordtemp1.length+wordtemp2.length-2;
	  		if(wordtemp1.length==wordtemp2.length){
	  			while(count<wordtemp1.length && wordtemp1[count].equals(wordtemp2[count])) {count++;}
	  			if(count==wordtemp1.length){
	  				h=wordtemp1.length-1;
	  				n1=0;
	  				n2=0;
	  				depth=h;
	  				path_1=n1+n2;
	  			}else{
	  				if(count==0){
	  					result1=0;	
	  				}else{
		  				h=count-1;
		  				n1=wordtemp1.length-count;
		  				n2=wordtemp2.length-count;
		  			    depth=h+pa;
		  			    path_1=n1+n2;
	  				}
	  			}
	  		}
	  		if(wordtemp1.length>wordtemp2.length){
	  			while(count < wordtemp2.length && wordtemp1[count].equals(wordtemp2[count])) {count++;}
	  			if(count==wordtemp2.length){
	  				h=wordtemp2.length-1;
	  				n1=wordtemp1.length-(h+1);
	  				n2=0;
	  				depth=h+pa;
	  				path_1=n1+n2;
	  			}else{
	  				if(count==0){
	  					path_1=0;
	  				}else{
		  				h=count-1;
		  				n1=wordtemp1.length-count;
		  				n2=wordtemp2.length-count;
		  				depth=h+pa;
		  				path_1=n1+n2;
	  				}
	  			}
	  		}
	  		if(wordtemp2.length>wordtemp1.length){
	  			while(count<wordtemp1.length && wordtemp1[count].equals(wordtemp2[count])) {count++;}
	  			if(count==wordtemp1.length){
	  				h=wordtemp1.length-1;
	  				n1=0;
	                                                                n2=wordtemp2.length-(h+1);
	  				depth=h+pa;
	  				path_1=n1+n2;
	  			}else{
	  				if(count==0){
	  					path_1=0;
	  				}else{
		  				h=count-1;
		  				n1=wordtemp1.length-count; 
		  				n2=wordtemp2.length-count;
		  				depth=h+pa;
		  				path_1=n1+n2;
	  				}
	  			}		
	  			}
	  		double myresult=path_1;
	  		resultvalue[resultsum]=myresult;
	  	  	resultsum++;
	  		}
	  		}
	  		double temp=-1;
	  		for(int i=0;i<resultvalue.length;i++){
	  			if(resultvalue[i] > 0 && resultvalue[i] <= 2) {
	  				return 1;
	  			}
	  		}
	  		return 0;
	}
     public static  void getHypernyms2(IDictionary dict,ISynset synset) {	
 	    List<ISynsetID> hypernyms =synset.getRelatedSynsets(Pointer.HYPERNYM);
 	    if(hypernyms.size()>0){
 		   strtemp=strtemp+"--->";
 	   }else{
 		   if(m==1){
 			if(!strtemp.equals("--->")){    
 		    list1.add(strtemp);
 			}
 			strtemp=leastcommonsub;
 		   }
 		   if(m==2){
 			   if(!strtemp.equals("--->")){    
 				   list2.add(strtemp);
 	    			}
 			   
 			  strtemp=leastcommonsub;
 		   }
 		  
 	   } 
 	   if(hypernyms.size()>0){
 		   
 		   if(hypernyms.size()>=2){
 			  leastcommonsub=strtemp;
 		   }
 	    for( ISynsetID sid : hypernyms ){
 	    	strtemp=strtemp+SynsetToWord(dict.getSynset(sid));
 	    	
 	       getHypernyms2(dict,dict.getSynset(sid));
 	 	   }
 	 
     }
    }
    public static void Hyper(String s,IDictionary dict){	  
   	IIndexWord idxWord = dict.getIndexWord(s, POS.VERB);
       for(int j=0;j<idxWord.getWordIDs().size();j++){
       leastcommonsub="";
       IWordID wordID = idxWord.getWordIDs().get(j); 
       IWord word = dict.getWord(wordID); 
       ISynset synset = word.getSynset(); 
       strtemp="--->"+SynsetToWord(synset);
	   getHypernyms2(dict,synset);
		 }
    }
    public static String SynsetToWord(ISynset wordsynset){
	   ISynsetID id = wordsynset.getID();
		String stemp="";
		List <IWord > words ;
		 words = wordsynset.getWords(); 
	    
	     for( Iterator<IWord > i = words.iterator(); i.hasNext();){
	        stemp = stemp + i.next().getLemma().toString();
	        if(i. hasNext ()){
	           stemp = stemp + ", ";
	        }
	     }
	     return id.toString() + ":" + stemp;
	}
}
