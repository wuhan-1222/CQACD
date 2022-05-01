package mytry;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Scanner;
import javax.swing.JOptionPane;
import edu.stanford.nlp.tagger.maxent.MaxentTagger;
import edu.stanford.nlp.util.logging.Redwood;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
public class textentailment {
	private static double allMatch = 0;
	private static double MatchValue = 0;
    public static void printResult(Map<String, Integer> map) {
if(map.size() > 0) {
	for (Iterator<Map.Entry<String, Integer>> iter = map
			.entrySet().iterator(); iter.hasNext();) {
		Entry<String, Integer> entry = iter.next();		
		System.out.println(entry.getKey() + " -> " + entry.getValue());
	}
}
     }
     public static double lcsMatch(List<String> wordsInT, List<String> wordsInH) {
String[] wordsArrayInT = wordsInT.toArray(new String[0]);
String[] wordsArrayInH = wordsInH.toArray(new String[0]);
int minLen = 2;
while (minLen < wordsArrayInH.length) {
	Map<String, Integer> wordsSequenceMatch = wordsSequence(wordsArrayInT, wordsArrayInH, minLen);
	
	if(wordsSequenceMatch.size() > 0) {
		allMatch += sumMatch(wordsSequenceMatch, wordsArrayInH.length, minLen);
		printResult(wordsSequenceMatch);
	}
	minLen++;
}

return allMatch / (wordsArrayInH.length - 1);
}
public static double sumMatch(Map<String, Integer> sequenceMatch, int hLen, int len) {
int sum = 0;	
for(Entry<String, Integer> entry : sequenceMatch.entrySet()) {
	sum += entry.getValue();
}
return (sum * 1.0) / (hLen - len + 1);
}
public static Map<String, Integer> wordsSequence(String[] wordsArrayInT,
	String[] wordsArrayInH, int len) {
Map<String, Integer> map = new HashMap<String, Integer>();
for (int i = 0; i < wordsArrayInH.length - len + 1; i++) {
	for (int j = 0; j < wordsArrayInT.length - len + 1; j++) {
		int originI = i; 
		int originJ = j; 

		int index = 1; 
		StringBuilder t = new StringBuilder();
		StringBuilder h = new StringBuilder();

		while (index <= len) {
			h.append(wordsArrayInH[i++]);
			h.append(" ");
			
			t.append(wordsArrayInT[j++]);
			t.append(" ");
			
			index++;
		}
		String strT = t.toString().substring(0, t.length() - 1);
		String strH = h.toString().substring(0, h.length() - 1); 

		i = originI;
		j = originJ;

		if (strH.equalsIgnoreCase(strT)) {
			if (map.containsKey(strH)) {
				continue;
			} else {
				map.put(strH, 1);
			}
		}
	}
}

return map;
}
public static double spMatch(Map<String, Integer> match, int len, String entailment) {
int sum = 0;	
for(Entry<String,Integer> entry : match.entrySet()) {
	sum += entry.getValue();
}
return (sum * 1.0) / len;
}
public static Map<String, Integer> singleWordMatch(List<String> wordsInT, List<String> wordsInH) {
HashMap<String, Integer> match = new HashMap<String, Integer>(); 

for (int i = 0; i < wordsInH.size(); i++) {
	for (int j = 0; j < wordsInT.size(); j++) {
		if (wordsInH.get(i).equals(wordsInT.get(j))) { 
			if (match.containsKey(wordsInH.get(i))) {
				continue;
			} else {
				match.put(wordsInH.get(i), 1);
			}
		}
	}
}

return match;
}
public static List<String> getWords(String str) {   
String lowercase = str.toLowerCase();
Pattern pattern = Pattern.compile("\\b[\\w-']+\\b");
Matcher matcher = pattern.matcher(lowercase); 
List<String> words = new ArrayList<String>();
while (matcher.find()) {
	String word = matcher.group(); 
	words.add(word);
}

return words;
}
public static void countSimilarWords(String text, String hypothesis, String id, String entailment) {
List<String> wordsInText = getWords(text);
List<String> wordsInHypothesis = getWords(hypothesis);
Map<String, Integer> singleWordMatch = singleWordMatch(wordsInText,wordsInHypothesis);
double result = spMatch(singleWordMatch, wordsInHypothesis.size(), entailment);
double lcsResult = lcsMatch(wordsInText, wordsInHypothesis);
MatchValue = (2* result * lcsResult)/(result + lcsResult);
allMatch = 0;
}
}
