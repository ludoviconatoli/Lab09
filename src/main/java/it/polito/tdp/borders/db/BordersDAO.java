package it.polito.tdp.borders.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import it.polito.tdp.borders.model.Border;
import it.polito.tdp.borders.model.Country;

public class BordersDAO {

	public void loadAllCountries(Map<Integer, Country> mappa, int anno) {

		String sql = "SELECT distinct co.CCode, co.StateAbb, co.StateNme "
				+ "FROM contiguity c, country co "
				+ "WHERE c.year <= ? AND c.conttype = 1 AND co.CCode = c.state1no";
		
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, anno);
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				if(!mappa.containsKey(rs.getInt("co.CCode"))) {
					Country c = new Country(rs.getInt("co.CCode"), rs.getString("co.StateAbb"), rs.getString("co.StateNme"));
					mappa.put(c.getCodice(), c);
				}
			}
			
			rs.close();
			st.close();
			conn.close();

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Errore connessione al database");
			throw new RuntimeException("Error Connection Database");
		}
	}

	public List<Border> getCountryPairs(Map<Integer, Country> idMap, int anno) {

		String sql = "SELECT state1no, state2no, year "
				+ "FROM contiguity c "
				+ "WHERE c.year <= ? AND conttype = 1 AND state1no<state2no ";
		
		List<Border> soluzione = new ArrayList<>();
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, anno);
			ResultSet rs = st.executeQuery();
			
			while(rs.next()) {
				Country c1 = idMap.get(rs.getInt("state1no"));
				Country c2 = idMap.get(rs.getInt("state2no"));
				Border b = new Border(c1, c2, rs.getInt("year"));
				soluzione.add(b);
			}
			
			rs.close();
			st.close();
			conn.close();
			return soluzione;
		}catch(SQLException e){
			System.out.println("Errore query getCountryPairs");
		}
		return soluzione;
	}
}
