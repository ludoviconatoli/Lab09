package it.polito.tdp.borders.model;

public class Border {

	private Country stato1;
	private Country stato2;
	private int anno;
	
	public Border(Country stato1, Country stato2, int anno) {
		super();
		this.stato1 = stato1;
		this.stato2 = stato2;
		this.anno = anno;
	}

	public Country getStato1() {
		return stato1;
	}

	public void setStato1(Country stato1) {
		this.stato1 = stato1;
	}

	public Country getStato2() {
		return stato2;
	}

	public void setStato2(Country stato2) {
		this.stato2 = stato2;
	}

	public int getAnno() {
		return anno;
	}

	public void setAnno(int anno) {
		this.anno = anno;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((stato1 == null) ? 0 : stato1.hashCode());
		result = prime * result + ((stato2 == null) ? 0 : stato2.hashCode());
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
		Border other = (Border) obj;
		if (stato1 == null) {
			if (other.stato1 != null)
				return false;
		} else if (!stato1.equals(other.stato1))
			return false;
		if (stato2 == null) {
			if (other.stato2 != null)
				return false;
		} else if (!stato2.equals(other.stato2))
			return false;
		return true;
	}
	
}
