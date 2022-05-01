package mytry;

import java.io.IOException;

public class semmatch {
public int mysemmatch(String word1,String word2) throws IOException{
	double STH = 0.75;
	Sim mysim = new Sim();
	if(mysim.mySim(word1,word2) > STH){
		return 1;
	}else{
		return 0;
	}
}
}
