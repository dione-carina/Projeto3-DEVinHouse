package characters;

import resources.Weapon;
import resources.WeaponType;

public class Samurai extends Character {

	@Override
	public Weapon[] weapons() {
		Weapon[] armas = new Weapon[3];
		armas[0] = new Weapon(WeaponType.Espada, 2);
		armas[1] = new Weapon(WeaponType.Machado, 3);
		armas[2] = new Weapon(WeaponType.Martelo, 1);
		return armas;
	}
	@Override
	public int attackPoints() {
		return 40;
	}
	@Override
	public int defensePoints() {
		// TODO Auto-generated method stub
		return 4;
	}
}
