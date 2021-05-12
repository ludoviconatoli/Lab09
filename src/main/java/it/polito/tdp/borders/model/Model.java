package it.polito.tdp.borders.model;

import java.util.HashMap;
import java.util.Map;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;

import it.polito.tdp.borders.db.BordersDAO;

public class Model {

	private Graph<Country, DefaultEdge> grafo;
	private BordersDAO dao;
	private Map<Integer, Country> idMap;
	
	public Model() {
		dao = new BordersDAO();
		idMap = new HashMap<>();
	}
	
	public Graph<Country, DefaultEdge> creaGrafo(int anno) {
		
		grafo = new SimpleGraph<>(DefaultEdge.class);
		this.dao.loadAllCountries(idMap, anno);
		
		for(Country c: idMap.values())
			grafo.addVertex(c);
		
		for(Border b: dao.getCountryPairs(idMap, anno)) {
			DefaultEdge e = grafo.getEdge(b.getStato1(), b.getStato2());
			
			if(e == null && grafo.containsVertex(b.getStato1()) && grafo.containsVertex(b.getStato2()))
			{
				grafo.addEdge(b.getStato1(), b.getStato2());
			}
		}
		
		return grafo;
	}

}
