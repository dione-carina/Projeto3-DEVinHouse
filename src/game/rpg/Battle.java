package game.rpg;

import java.util.ArrayList;
import java.util.Scanner;

import characters.Gender;
import characters.ICharacter;
import resources.GameMode;
import resources.MotivationType;
import resources.Weapon;

public class Battle {
	private ICharacter character = null;
	private ICharacter enemy = null;
	private GameMode gameMode;
	private int minAttack = 1;
	private int maxAttack = 20;
	private int additionalDefensePoints = 0;
	private MotivationType motivationType;
	Scanner scanner = new Scanner(System.in);
	public int numberOfKeys = 0;
	ArrayList<Integer> doors = new ArrayList<>();
	private Boolean hasQuit = false;

	public Battle(ICharacter character, GameMode gameMode, MotivationType motivationType) {
		this.character = character;
		this.gameMode = gameMode;
		this.motivationType = motivationType;
	}

	public void setEnemy(ICharacter enemy, int door) {
		this.enemy = enemy;
		doors.add(door);
	}

	public Boolean isDoorOpen(int door) {
		return doors.contains(door);
	}

	public Battle(ICharacter character, ICharacter enemy, GameMode gameMode, MotivationType motivationType) {
		this.character = character;
		this.enemy = enemy;
		this.gameMode = gameMode;
		this.motivationType = motivationType;
	}

	private int calculateDamage(int damage, Boolean isEnemy) {
		Double calculatedDamage = (double) damage;
		if (isEnemy) {
			if (gameMode == GameMode.Facil)
				calculatedDamage = damage * 0.8;
		} else {
			if (gameMode == GameMode.Dificil)
				calculatedDamage = damage * 0.9;
		}

		return calculatedDamage.intValue();
	}

	public Boolean isEndGame() {
		if (!character.hasEnergy() || hasQuit)
			return true;
		return false;
	}

	private void enemyAttack() {
		int attackPower = generateRandonNumber();
		int damage = calculateDamage(attackPower + this.character.attackPoints() + this.character.weapon().getPoder(),
				false);
		switch (attackPower) {
		case 1:
			System.out.println("Voc� errou o seu ataque! O inimigo n�o sofreu dano algum.");
			break;
		case 20:
			enemy.takeDamage(damage);
			showCriticalAttack(character.weapon(), damage, true);
			break;
		default:
			if (enemy.defensePoints() > damage)
				damage = 0;
			else
				damage -= enemy.defensePoints();
			enemy.takeDamage(damage);
			showCriticalAttack(character.weapon(), damage, false);
			break;
		}
	}

	private void characterAttack() {
		int attackPower = generateRandonNumber();
		int damage = calculateDamage(attackPower + this.enemy.attackPoints() + this.enemy.weapon().getPoder(), true);
		switch (attackPower) {
		case 1:
			System.out.println("O inimigo errou o ataque! Voc� n�o sofreu dano.");
			break;
		case 20:
			character.takeDamage(damage);
			showCriticalEnemyCriticalAttack(damage, character.energy());
			break;
		default:
			damage -= (character.defensePoints() + this.additionalDefensePoints);
			character.takeDamage(damage);
			showEnemyAttack(damage, character.energy());
			break;
		}
	}

	public void increaseDefensePoints(int points) {
		this.additionalDefensePoints += points;
	}

	public void startBattle() {
		// enquanto o personagem e o inimigo possu�rem energia a batalha n�o para
		while (character.hasEnergy() && enemy.hasEnergy()) {
			if (continueAndAttack()) {

				enemyAttack();

				if (!enemy.hasEnergy()) {
					System.out.println("O inimigo n�o � p�reo para o seu hero�smo, e jaz im�vel aos seus p�s.");
					numberOfKeys++;
					break;
				}

				characterAttack();

				if (!character.hasEnergy()) {
					noEnergyEndGame();
					break;
				}
			} else {
				System.out.println(
						"Voc� n�o estava preparado para a for�a do inimigo e decide fugir para que possa tentar novamente em uma nova pr�xima vez. GAME OVER");
				break;
			}
		}
	}

	private void noEnergyEndGame() {
		if (motivationType == MotivationType.GLORIA)
			if (this.character.characterGender() == Gender.Masculino)
				System.out.println(
						"Voc� n�o estava preparado para a for�a do inimigo. A gl�ria que buscavas n�o ser� sua, e a cidade aguarda por seu pr�ximo her�i.");
			else
				System.out.println(
						"Voc� n�o estava preparado para a for�a do inimigo. A gl�ria que buscavas n�o ser� sua, e a cidade aguarda por sua pr�xima hero�na.");
		else
			System.out.println(
					"Voc� n�o estava preparado para a for�a do inimigo. N�o foi poss�vel concluir sua vingan�a, e agora resta saber se algu�m se vingar� por voc�.");
	}

	private void showCriticalEnemyCriticalAttack(int damage, int lifePoints) {
		System.out.printf(
				"O inimigo acertou um ataque cr�tico! Voc� voc� sofreu %s de dano e agora poss�i %s pontos de vida.\n",
				damage, lifePoints);
	}

	private void showCriticalAttack(Weapon weapon, int damage, Boolean isCritical) {
		if (isCritical)
			System.out.printf("Voc� acertou um ataque cr�tico!");
		switch (weapon.getTipoArma()) {
		case ArcoFlecha:
			System.out.printf("Voc� atacou com Arco e a flecha atingiu o inimigo e causou %s de dano!\n", damage);
		case Besta:
			System.out.printf("Voc� atacou com Besta e a virote atingiu o inimigo e causou %s de dano!\n", damage);
			break;
		case Cajado:
			System.out.printf("Voc� atacou com o seu Cajado, lan�ando uma bola de fogo e causou %s de dano!\n", damage);
			break;
		case Livro:
			System.out.printf(
					"Voc� atacou aborvendo energia do Livro com uma m�o e liberando com a outra e causou %s de dano!\n",
					damage);
			break;
		default:
			System.out.printf("Voc� atacou com %s e causou %s de dano ao inimigo!\n", weapon.getNome(), damage);
			break;
		}
	}

	private void showEnemyAttack(int damage, int lifePoints) {
		System.out.printf("O inimigo atacou! Voc� sofreu %s de dano e agora possui %s pontos de vida\n", damage,
				lifePoints);
	}

	private Boolean continueAndAttack() {
		while (true) {
			System.out.println("Voc� deseja [1] Atacar ou [2] Fugir?");
			int i = scanner.nextInt();
			if (i < 1 || i > 2)
				System.out.println("Op��o inv�lida");
			else {
				if (i == 2) {
					hasQuit = true;
					return false;
				}
				return true;
			}
		}
	}

	public void recoverEnergy() {
		this.character.recoverEnergy();
	}

	private int generateRandonNumber() {
		return randomNumber(this.minAttack, this.maxAttack);
	}

	private int randomNumber(int min, int max) {
		return (int) ((Math.random() * (max - min)) + min);
	}

	public void walkingDamage() {
		int damage = randomNumber(1, 10);
		this.character.takeDamage(damage);
		System.out.printf("O inimigo atacou! Voc� sofreu %s de dano e agora possui %s pontos de vida\n\n", damage,
				character.energy());
	}

}
