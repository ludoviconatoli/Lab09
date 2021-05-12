
package it.polito.tdp.borders;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.Set;

import org.jgrapht.Graph;
import org.jgrapht.alg.connectivity.ConnectivityInspector;
import org.jgrapht.graph.DefaultEdge;

import it.polito.tdp.borders.model.Country;
import it.polito.tdp.borders.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLController {

	private Model model;
	
    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="txtAnno"
    private TextField txtAnno; // Value injected by FXMLLoader

    @FXML // fx:id="txtResult"
    private TextArea txtResult; // Value injected by FXMLLoader

    @FXML
    void doCalcolaConfini(ActionEvent event) {
    	this.txtResult.clear();
    	int anno;
    	try{
    		anno = Integer.parseInt(this.txtAnno.getText());
    	}catch(NumberFormatException e) {
    		this.txtResult.setText("Devi inserire un numero");
    		return;
    	}
    	
    	if(anno <= 2016 && anno > 1815) {
    		Graph<Country, DefaultEdge> grafo = this.model.creaGrafo(anno);
    		for(Country c: grafo.vertexSet()) {
    			this.txtResult.appendText(c + " con un numero di paesi confinanti: " + grafo.degreeOf(c) + "\n");
    		}
    		
    		ConnectivityInspector<Country, DefaultEdge> ci = new ConnectivityInspector<>(grafo); 
    		
    		int numConnessioni = 0;
    		for(Set<Country> s: ci.connectedSets())
    			numConnessioni += s.size();
    				 
    		this.txtResult.appendText("\n\nNumero di componenti connesse: " + numConnessioni);
    	}else {
    		this.txtResult.setText("L'anno inserito non è presente nel database");
    		return;
    	}
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert txtAnno != null : "fx:id=\"txtAnno\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Scene.fxml'.";

    }
    
    public void setModel(Model model) {
    	this.model = model;
    	this.txtResult.setStyle("-fx-font-family: monospace");
    }
}
