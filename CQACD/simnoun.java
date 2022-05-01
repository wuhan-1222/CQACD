package MySimilarity;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import edu.mit.jwi.Dictionary;
import edu.mit.jwi.IDictionary;
import edu.mit.jwi.item.IIndexWord;
import edu.mit.jwi.item.ISynset;
import edu.mit.jwi.item.ISynsetID;
import edu.mit.jwi.item.IWord;
import edu.mit.jwi.item.IWordID;
import edu.mit.jwi.item.POS;
import edu.mit.jwi.item.Pointer;

public class simnoun {
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
	private static double ss = 0.2;
	private static Map<Integer,ISynsetID> mySynsetmap = new HashMap<Integer,ISynsetID>();
	private static ArrayList<String> list1 = new ArrayList<String>();
	private static	ArrayList<String> list2 = new ArrayList<String>();
	private static	ArrayList<String> listResult = new ArrayList<String>();
	private ArrayList<String> result=new ArrayList<String>();
	private static String path = "dict"; 
	private static File wnDir=new File(path);
    private static IDictionary dict=new Dictionary(wnDir);
	
	public static double mysimoun(String Word1, String Word2,IDictionary dict) throws IOException
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
	  				hypon+=getHyponyms(dict,wordtemp1[count-1]);
	  				path_1=n1+n2+hypon*ss/((wordlength+h)*1.0/3);
	  				result1=1;	
	  			}else{
	  				h=count-1;
	  				n1=wordtemp1.length-count;
	  				n2=wordtemp2.length-count;
	  				for(int w1=count-1;w1<wordtemp1.length-1;w1++){
	  	  				hypon+=getHyponyms(dict,wordtemp1[w1]);
	  	  				}
	  	  				for(int w1=count;w1<wordtemp2.length-1;w1++){
	  	  					hypon+=getHyponyms(dict,wordtemp2[w1]);
	  	  				}
	  			    depth=h+pa;
	  			  path_1=n1+n2+hypon*ss/((wordlength+h)*1.0/3);
	  				result1=java.lang.StrictMath.pow(Math.E,-0.2*path_1)*((java.lang.StrictMath.pow(Math.E,0.6*depth)-java.lang.StrictMath.pow(Math.E,-0.6*depth))*1.0/(java.lang.StrictMath.pow(Math.E,0.6*depth)+java.lang.StrictMath.pow(Math.E,-0.6*depth)));
	  			}
	  		}
	  		if(wordtemp1.length>wordtemp2.length){
	  			while(count < wordtemp2.length && wordtemp1[count].equals(wordtemp2[count])) {count++;}
	  			if(count==wordtemp2.length){
	  				h=wordtemp2.length-1;
	  				n1=wordtemp1.length-(h+1);
	  				n2=0;
	  				for(int w1=count-1;w1<wordtemp1.length-1;w1++){
	  	  				hypon+=getHyponyms(dict,wordtemp1[w1]);
	  	  				}
	  				depth=h+pa;
	  				path_1=n1+n2+hypon*ss/((wordlength+h)*1.0/3);
				    result1=java.lang.StrictMath.pow(Math.E,-0.2*path_1)*((java.lang.StrictMath.pow(Math.E,0.6*depth)-java.lang.StrictMath.pow(Math.E,-0.6*depth))*1.0/(java.lang.StrictMath.pow(Math.E,0.6*depth)+java.lang.StrictMath.pow(Math.E,-0.6*depth)));
	  			}else{
	  				h=count-1;
	  				n1=wordtemp1.length-count;
	  				n2=wordtemp2.length-count;
	  				for(int w1=count-1;w1<wordtemp1.length-1;w1++){
	  	  				hypon+=getHyponyms(dict,wordtemp1[w1]);
	  	  				}
	  	  				for(int w1=count;w1<wordtemp2.length-1;w1++){
	  	  					hypon+=getHyponyms(dict,wordtemp2[w1]);
	  	  				}
	  				depth=h+pa;
	  				path_1=n1+n2+hypon*ss/((wordlength+h)*1.0/3);
	  				result1=java.lang.StrictMath.pow(Math.E,-0.2*path_1)*((java.lang.StrictMath.pow(Math.E,0.6*depth)-java.lang.StrictMath.pow(Math.E,-0.6*depth))*1.0/(java.lang.StrictMath.pow(Math.E,0.6*depth)+java.lang.StrictMath.pow(Math.E,-0.6*depth)));
	  			}
	  		}
	  		if(wordtemp2.length>wordtemp1.length){
	  			while(count<wordtemp1.length && wordtemp1[count].equals(wordtemp2[count])) {count++;}
	  			if(count==wordtemp1.length){
	  				h=wordtemp1.length-1;
	  				n1=0;
	                n2=wordtemp2.length-(h+1);
	                for(int w1=count;w1<wordtemp2.length-1;w1++){
   					hypon+=getHyponyms(dict,wordtemp2[w1]);
   				}
	  				depth=h+pa;
	  				path_1=n1+n2+hypon*ss/((wordlength+h)*1.0/3);
	  				result1=java.lang.StrictMath.pow(Math.E,-0.2*path_1)*((java.lang.StrictMath.pow(Math.E,0.6*depth)-java.lang.StrictMath.pow(Math.E,-0.6*depth))*1.0/(java.lang.StrictMath.pow(Math.E,0.6*depth)+java.lang.StrictMath.pow(Math.E,-0.6*depth)));
	  			}else{
	  				h=count-1;
	  				n1=wordtemp1.length-count;
	  				n2=wordtemp2.length-count;
	  				for(int w1=count-1;w1<wordtemp1.length-1;w1++){
	  	  				hypon+=getHyponyms(dict,wordtemp1[w1]);
	  	  				}
	  	  				for(int w1=count;w1<wordtemp2.length-1;w1++){
	  	  					hypon+=getHyponyms(dict,wordtemp2[w1]);
	  	  				}
	  				depth=h+pa;
	  				path_1=n1+n2+hypon*ss/((wordlength+h)*1.0/3);
	  				result1=java.lang.StrictMath.pow(Math.E,-0.2*path_1)*((java.lang.StrictMath.pow(Math.E,0.6*depth)-java.lang.StrictMath.pow(Math.E,-0.6*depth))*1.0/(java.lang.StrictMath.pow(Math.E,0.6*depth)+java.lang.StrictMath.pow(Math.E,-0.6*depth)));
	  			}		
	  			}
	  		double result=result1;
	  		resultvalue[resultsum]=result;
	  	  	resultsum++;
	  		}
	  		}
	  		double temp=-1;
	  		for(int i=0;i<resultvalue.length;i++){  			
	  			if(resultvalue[i]>temp) temp=resultvalue[i];
	  		}
	  		return temp;
	}
	 public static void Hyper(String s,IDictionary dict){
		  
		   	IIndexWord idxWord = dict.getIndexWord(s, POS.NOUN);
		       for(int j=0;j<idxWord.getWordIDs().size();j++){
		       leastcommonsub="";
		       IWordID wordID = idxWord.getWordIDs().get(j); 
		       IWord word = dict.getWord(wordID); 
		       ISynset synset = word.getSynset(); 
		       strtemp="--->"+SynsetToWord(synset);
			   getHypernyms(dict,synset);
				 }
		  }
	public static  void getHypernyms(IDictionary dict,ISynset synset) {
        int mysize = mySynsetmap.size() + 1;
    	   mySynsetmap.put(mysize, synset.getID());
 	   List<ISynsetID> hypernyms =synset.getRelatedSynsets(Pointer.HYPERNYM);
 	   if((hypernyms.size()==0&&m==1)||(hypernyms.size()==0&&m==2)){
 		   hypernyms =synset.getRelatedSynsets(Pointer.HYPERNYM_INSTANCE);
 	   }
 	   if(hypernyms.size()>0){
 	    	strtemp=strtemp+"--->";
 	   }else{
 		   if(m==1){
 			if(strtemp.substring(strtemp.length()-6, strtemp.length()).equals("entity")){    
 		   list1.add(strtemp);      
 			}
 			strtemp = leastcommonsub;
 		   }
 		   if(m==2){
 			   if(strtemp.substring(strtemp.length()-6, strtemp.length()).equals("entity")){    
 				   list2.add(strtemp);
 	    			}
 			   
 			   strtemp = leastcommonsub;
 		   }		  
 	   } 
 	   if(hypernyms.size()>0){
 		   
 		   if(hypernyms.size()>=2){
 			   leastcommonsub = strtemp;
 		   }
 	    for( ISynsetID sid : hypernyms ){
 	    	strtemp = strtemp + SynsetToWord(dict.getSynset(sid));
 	    	
 	       getHypernyms(dict,dict.getSynset(sid));
 	 	   }
 	 
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
    public static  int getHyponyms(IDictionary dict,String sst){
     	  
   	   List<ISynsetID> hypernyms=null;
   	   
   	   Pattern p= Pattern.compile(",");
 	   		Matcher m = p.matcher(sst);
 	   		String[] ttt;
 	   		String tt="";
 	   	 
 	   		if(m.find()==true){
   	       ttt=sst.split(",");
   	       tt=ttt[ttt.length-1];
 	   		}else{
 	   			tt=sst.substring(sst.lastIndexOf(":")+1, sst.length());
 	   		}
 	   		
   	       IIndexWord idxWord = dict.getIndexWord(tt, POS.NOUN);
   	       
   	       for(int j=0;j<idxWord.getWordIDs().size();j++){
   	    	   
   	       IWordID wordID = idxWord.getWordIDs().get(j);
   	      IWord word = dict.getWord(wordID);
   	       ISynset synset = word.getSynset(); 
   	     
   	       ISynsetID ID=synset.getID();
   	       if(ID.toString().equals(sst.substring(0,14))){
   	    	   
   	      hypernyms =synset.getRelatedSynsets(Pointer.HYPONYM);
   	       } 
 }       
   	        if(hypernyms==null){	        	 
   	        	return 0;
   	        }else{  	
   	       return hypernyms.size();   
   	        }
 }
}
