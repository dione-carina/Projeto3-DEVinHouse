package resources;

public class Weapon {
	
	private String nome;
	private int poder;
	private WeaponType tipoArma;
	
	
	public Weapon(WeaponType arma, int poder)
	{
		this.tipoArma = arma;
		this.nome = arma.toString();
		this.poder = poder;
	}

	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getPoder() {
		return poder;
	}

	public void setPoder(int poder) {
		this.poder = poder;
	}

	public WeaponType getTipoArma() {
		return tipoArma;
	}

	public void setTipoArma(WeaponType tipoArma) {
		this.tipoArma = tipoArma;
	}	
}
