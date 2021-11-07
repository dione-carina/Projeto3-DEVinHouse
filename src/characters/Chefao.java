package characters;

import resources.Weapon;
import resources.WeaponType;

public class Chefao extends Character {

	@Override
	public Weapon[] weapons() {
		Weapon[] armas = new Weapon[1];
		armas[0] = new Weapon(WeaponType.Machado, 3);
		return armas;
	}
	@Override
	public int attackPoints() {
		return 15;
	}
	@Override
	public int defensePoints() {
		// TODO Auto-generated method stub
		return 3;
	}
}
