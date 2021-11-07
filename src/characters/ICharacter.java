package characters;

import resources.Weapon;

public interface ICharacter {
	public Weapon[] weapons();
	public void setWeapon(int arma);
	public void showCharacterData();
	public void setName(String nome);
	public void setGender(Gender genero);
	public Boolean hasEnergy();
	public Weapon weapon();
	public int attackPoints();
	public void takeDamage(int dano);
	public int energy();
	public void recoverEnergy();
	public int defensePoints();
	public Gender characterGender();
}
