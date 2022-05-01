import java.util.Collection;
import java.util.List;
import java.io.StringReader;

import com.chaoticity.dependensee.Main;

import edu.stanford.nlp.process.Tokenizer;
import edu.stanford.nlp.process.TokenizerFactory;
import edu.stanford.nlp.process.CoreLabelTokenFactory;
import edu.stanford.nlp.process.DocumentPreprocessor;
import edu.stanford.nlp.process.PTBTokenizer;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.ling.HasWord;
import edu.stanford.nlp.ling.Sentence;
import edu.stanford.nlp.trees.*;
import edu.stanford.nlp.parser.lexparser.LexicalizedParser;
public class DependencyNeg {
	  public static int judgedependency(String myquestion,String temp) {
		    String text = myquestion;
		    String text1 = temp;
		    String parserModel = "edu/stanford/nlp/models/lexparser/englishPCFG.ser.gz";
		    LexicalizedParser lp = LexicalizedParser.loadModel(parserModel);
		    EnglishGrammaticalStructureFactory egst = new EnglishGrammaticalStructureFactory();
		    TokenizerFactory<CoreLabel> tokenizerFactory =
		            PTBTokenizer.factory(new CoreLabelTokenFactory(), "");
		    List<CoreLabel> wordList = tokenizerFactory.getTokenizer(new StringReader(text)).tokenize();
		    Tree tree = lp.apply(wordList);    
		    EnglishGrammaticalStructure egs = egst.newGrammaticalStructure(tree);
		    Collection<TypedDependency> tdl = egs.typedDependenciesCollapsed();
		    String result1 = tdl.toString(); 
		    System.out.println(result1);
		    //s2
		    EnglishGrammaticalStructureFactory egst1 = new EnglishGrammaticalStructureFactory();
		    TokenizerFactory<CoreLabel> tokenizerFactory1 =
		            PTBTokenizer.factory(new CoreLabelTokenFactory(), "");
		    List<CoreLabel> wordList1 = tokenizerFactory.getTokenizer(new StringReader(text1)).tokenize();
		    Tree tree1 = lp.apply(wordList1);    
		    EnglishGrammaticalStructure egs1 = egst1.newGrammaticalStructure(tree1);
		    Collection<TypedDependency> tdl1 = egs1.typedDependenciesCollapsed();
		    String result2 = tdl1.toString(); 
		    System.out.println(result2);
		    if(result1.contains("neg")==result2.contains("neg")){
		    	return 1;
		    }else{
		    	return 0;
		    }
		  }
}
