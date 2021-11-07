package characters;

import resources.Weapon;
import resources.WeaponType;

public class Armeiro extends Character {

	@Override
	public Weapon[] weapons() {
		Weapon[] armas = new Weapon[1];
		armas[0] = new Weapon(WeaponType.Espada, 1);
		return armas;
	}
	@Override
	public int attackPoints() {
		return 10;
	}
	
	@Override
	public int defensePoints() {
		// TODO Auto-generated method stub
		return 1;
	}
}
