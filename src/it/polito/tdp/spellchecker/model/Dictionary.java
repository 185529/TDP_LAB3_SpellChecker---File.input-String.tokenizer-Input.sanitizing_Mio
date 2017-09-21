package it.polito.tdp.spellchecker.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Dictionary {
	
	private List<String> dictionary;
	private List<String> inputTextList;
	private List<RichWord> outputList;
	
	public Dictionary(){
		this.dictionary = new ArrayList<String>();
		this.inputTextList = new ArrayList<String>();
	}

	public void loadDictionary(String lang) {

		try{
    		
    		FileReader fr = new FileReader("rsc/"+lang+".txt");
    		BufferedReader br = new BufferedReader(fr);
    		
    		String word;
    		
    		while((word = br.readLine()) != null){
    			
    			dictionary.add(word);
    			
    		}
    		
    		br.close();
    		fr.close();
    		
    		return;
    		
    	}catch(IOException e){
    		
    		System.out.println("ERR - loadDictionary() -> Error occured loading dictionary.");
    		return;
    		
    	}
		
	}

	public List<RichWord> check(String inputText) {

		inputTextList.clear();
		
		StringTokenizer st = new StringTokenizer(inputText, " ");
		
		if(!st.hasMoreTokens())
			return null;
		
		while(st.hasMoreTokens()){
			inputTextList.add(st.nextToken().replaceAll("[ \\p{Punct}]", "").trim().toLowerCase());
		}
		
		System.out.println("check() - OK -> Input tokenized. Calling spellCheckText() ...");
		
		List<RichWord> checked = spellCheckText(inputTextList);
		
		System.out.println("check() - OK -> spellCheckText() returned OK.");
		
		return checked;
		
	}

	private List<RichWord> spellCheckText(List<String> inputTextList) {

		outputList = new ArrayList<RichWord>();
		
		for(String s : inputTextList){
			
			RichWord word = new RichWord(s);
			
			if(dictionary.contains(s)){
				word.setCorrect(true);
			}else{
				word.setCorrect(false);
			}
			
			outputList.add(word);
			
		}
		
		return outputList;
		
	}

}
