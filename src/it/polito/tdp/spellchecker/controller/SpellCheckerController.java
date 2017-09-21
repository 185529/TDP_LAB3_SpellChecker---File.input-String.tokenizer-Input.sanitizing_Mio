/**
 * Sample Skeleton for 'SpellChecker.fxml' Controller Class
 */

package it.polito.tdp.spellchecker.controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.spellchecker.model.Dictionary;
import it.polito.tdp.spellchecker.model.RichWord;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

public class SpellCheckerController {
	
	private Dictionary dictionary = new Dictionary();

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="language"
    private ComboBox<String> language; // Value injected by FXMLLoader

    @FXML // fx:id="input"
    private TextArea input; // Value injected by FXMLLoader

    @FXML // fx:id="check"
    private Button check; // Value injected by FXMLLoader

    @FXML // fx:id="output"
    private TextArea output; // Value injected by FXMLLoader

    @FXML // fx:id="errors"
    private Label errors; // Value injected by FXMLLoader

    @FXML // fx:id="clear"
    private Button clear; // Value injected by FXMLLoader

    @FXML // fx:id="time"
    private Label time; // Value injected by FXMLLoader

    @FXML
    void doCheck(ActionEvent event) {
    	
    	Long start = System.nanoTime();
    	
    	// getting user language
    	
    	String lang = language.getValue();
    	
    	// loading language dictionary
    	
    	dictionary.loadDictionary(lang);
    	
    	System.out.println("doCheck() - OK -> Dictionary loaded.");
    	
    	String inputText = input.getText();
    	
    	System.out.println("doCheck() - OK -> Text parsed.");

    	List<RichWord> checked = dictionary.check(inputText);
    	
    	System.out.println("doCheck() - OK -> Input checked.");
    	
    	if(checked==null){
    		output.setText("Insert some text.");
    		return;
    	}
    	
    	int errorsNumber = 0;
    	
    	output.clear();
    	
    	for(RichWord w : checked){
    		if(!w.isCorrect()){
    			output.appendText(w.getWord()+"\n");
    			errorsNumber++;
    		}
    	}
    	
    	System.out.println("doCheck() - OK -> Results displayed.");
    	
    	Long stop = System.nanoTime();
    	
    	errors.setText("The text contains "+errorsNumber+" error(s).");
    	time.setText("Spell check completed in "+(stop-start)/1e9+" seconds.");
    	
    	return;
    	
    }

    @FXML
    void doClear(ActionEvent event) {
    	
    	input.setText("");
    	output.setText("");
    	
    	doInit();

    }
    
    private void doInit(){
    	
    	language.getItems().clear();
    	
    	language.getItems().add("English");
    	language.getItems().add("Italian");
    	
    	language.setValue("English");
    	
    	errors.setText("");
    	time.setText("");
    	
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert language != null : "fx:id=\"language\" was not injected: check your FXML file 'SpellChecker.fxml'.";
        assert input != null : "fx:id=\"input\" was not injected: check your FXML file 'SpellChecker.fxml'.";
        assert check != null : "fx:id=\"check\" was not injected: check your FXML file 'SpellChecker.fxml'.";
        assert output != null : "fx:id=\"output\" was not injected: check your FXML file 'SpellChecker.fxml'.";
        assert errors != null : "fx:id=\"errors\" was not injected: check your FXML file 'SpellChecker.fxml'.";
        assert clear != null : "fx:id=\"clear\" was not injected: check your FXML file 'SpellChecker.fxml'.";
        assert time != null : "fx:id=\"time\" was not injected: check your FXML file 'SpellChecker.fxml'.";
        
        doInit();

    }
}
