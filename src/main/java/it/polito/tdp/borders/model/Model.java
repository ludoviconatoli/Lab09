package it.polito.tdp.borders.model;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.event.ConnectedComponentTraversalEvent;
import org.jgrapht.event.EdgeTraversalEvent;
import org.jgrapht.event.TraversalListener;
import org.jgrapht.event.VertexTraversalEvent;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleGraph;
import org.jgrapht.traverse.BreadthFirstIterator;
import org.jgrapht.traverse.DepthFirstIterator;

import it.polito.tdp.borders.db.BordersDAO;

public class Model {

	private Graph<Country, DefaultEdge> grafo;
	private BordersDAO dao;
	private Map<Integer, Country> idMap;
	
	public Model() {
		dao = new BordersDAO();
		idMap = new TreeMap<>();
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
	
	public List<Country> trovaPercorso(Country c1){
		List<Country> percorso = new LinkedList<>();
		
		/*BreadthFirstIterator<Country, DefaultEdge> bfv = new BreadthFirstIterator<>(this.grafo, c1);
		
		bfv.addTraversalListener(new TraversalListener<Country, DefaultEdge>(){

			@Override
			public void connectedComponentFinished(ConnectedComponentTraversalEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void connectedComponentStarted(ConnectedComponentTraversalEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			//Ci salviamo le visite
			public void edgeTraversed(EdgeTraversalEvent<DefaultEdge> e) {
				
			}

			@Override
			public void vertexTraversed(VertexTraversalEvent<Country> e) {
				//prendo il vertice attraversato
				Country nuovo = e.getVertex();
			}

			@Override
			public void vertexFinished(VertexTraversalEvent<Country> e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		while(bfv.hasNext()) {
			Country c = bfv.next();
			if(!percorso.contains(c))
				percorso.add(c);
		}
		
			
		return percorso;*/
		
		//ALTERNATIVA DFV
		DepthFirstIterator<Country, DefaultEdge> dfv = new DepthFirstIterator<>(grafo, c1);
		dfv.addTraversalListener(new TraversalListener<Country, DefaultEdge>(){

			@Override
			public void connectedComponentFinished(ConnectedComponentTraversalEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void connectedComponentStarted(ConnectedComponentTraversalEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void edgeTraversed(EdgeTraversalEvent<DefaultEdge> e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void vertexTraversed(VertexTraversalEvent<Country> e) {
				Country nuovo = e.getVertex();
				
			}

			@Override
			public void vertexFinished(VertexTraversalEvent<Country> e) {
				// TODO Auto-generated method stub
				
			}
			
		});
		
		while(dfv.hasNext()) {
			Country c = dfv.next();
			if(!percorso.contains(c))
				percorso.add(c);
		}
		
		return percorso;
	}

}
