package characters;

import resources.Weapon;
import resources.WeaponType;

public class Alquimista extends Character {

	@Override
	public Weapon[] weapons() {
		Weapon[] armas = new Weapon[1];
		armas[0] = new Weapon(WeaponType.Cajado, 2);
		return armas;
	}
	
	@Override
	public int attackPoints() {
		return 13;
	}

	@Override
	public int defensePoints() {
		// TODO Auto-generated method stub
		return 2;
	}
}
