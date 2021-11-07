package characters;

import resources.Weapon;

abstract class Character implements ICharacter
{
	private String name;
	private Gender gender;
	private Weapon weapon;
	public int Energy = 100;
	public int Defense = 0;
	public Character(Gender genero)
	{
		this.gender = genero;
	}
	public Character()
	{
		
	}
	
	public void recoverEnergy()
	{
		this.Energy = 100;
	}
	public int energy() 
	{
		return Energy;
	}
	
	public Gender characterGender()
	{
		return this.gender;
	}
	
	
	public void takeDamage(int dano) {
		this.Energy -= dano;
	}
	
	public void setWeapon(int arma) {
		this.weapon = this.weapons()[arma];
	}
	
	public Boolean hasEnergy()
	{
		return  this.Energy > 0;
	}
	
	public Weapon weapon()
	{
		return this.weapon;
	}
	
	public String getNome() {
		return name;
	}
	
	public void setName(String nome) {
		this.name = nome;
	}

	public void setGender(Gender genero) {
		this.gender = genero;
	}

	public Weapon getArma() {
		return weapon;
	}

	public void setArma(Weapon arma) {
		this.weapon = arma;
	}
	
	public void showCharacterData()
	{
		System.out.println("################################");
		System.out.printf("Nome  : %s\nGênero: %s\nArma  : %s\nPoder : %s", this.name, this.gender, this.weapon.getNome(), this.weapon.getPoder());
	}
	
	
}
