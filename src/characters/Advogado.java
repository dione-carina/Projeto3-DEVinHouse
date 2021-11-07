package characters;

import resources.Weapon;
import resources.WeaponType;

public class Advogado extends Character {

	@Override
	public Weapon[] weapons() {
		Weapon[] armas = new Weapon[3];
		armas[0] = new Weapon(WeaponType.Vademecum, 5);
		armas[1] = new Weapon(WeaponType.Cajado, 4);
		armas[2] = new Weapon(WeaponType.Clava, 3);
		return armas;
	}

	@Override
	public int attackPoints() {
		return 50;
	}

	@Override
	public int defensePoints() {
		// TODO Auto-generated method stub
		return 5;
	}

	

	
}
