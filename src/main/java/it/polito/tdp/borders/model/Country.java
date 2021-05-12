package it.polito.tdp.borders.model;

/**
 * @author ludov
 *
 */
public class Country {

	private int codice;
	private String abbreviazione;
	private String nome;
	
	public Country(int codice, String abbreviazione, String nome) {
		super();
		this.codice = codice;
		this.abbreviazione = abbreviazione;
		this.nome = nome;
	}

	public int getCodice() {
		return codice;
	}

	public void setCodice(int codice) {
		this.codice = codice;
	}

	public String getAbbreviazione() {
		return abbreviazione;
	}

	public void setAbbreviazione(String abbreviazione) {
		this.abbreviazione = abbreviazione;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + codice;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Country other = (Country) obj;
		if (codice != other.codice)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return codice + ", " + abbreviazione + ", " + nome ;
	}
	
}
