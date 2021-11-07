package characters;

import resources.Weapon;
import resources.WeaponType;

public class DevInHouser extends Character  {

	@Override
	public Weapon[] weapons() {
		Weapon[] armas = new Weapon[3];
		armas[0] = new Weapon(WeaponType.Livro, 3);
		armas[1] = new Weapon(WeaponType.Besta, 2);
		armas[2] = new Weapon(WeaponType.ArcoFlecha, 1);
		return armas;
	}

	@Override
	public int attackPoints() {
		return 60;
	}

	@Override
	public int defensePoints() {
		// TODO Auto-generated method stub
		return 6;
	}

}
