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
			System.out.println("Você errou o seu ataque! O inimigo não sofreu dano algum.");
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
			System.out.println("O inimigo errou o ataque! Você não sofreu dano.");
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
		// enquanto o personagem e o inimigo possuírem energia a batalha não para
		while (character.hasEnergy() && enemy.hasEnergy()) {
			if (continueAndAttack()) {

				enemyAttack();

				if (!enemy.hasEnergy()) {
					System.out.println("O inimigo não é páreo para o seu heroísmo, e jaz imóvel aos seus pés.");
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
						"Você não estava preparado para a força do inimigo e decide fugir para que possa tentar novamente em uma nova próxima vez. GAME OVER");
				break;
			}
		}
	}

	private void noEnergyEndGame() {
		if (motivationType == MotivationType.GLORIA)
			if (this.character.characterGender() == Gender.Masculino)
				System.out.println(
						"Você não estava preparado para a força do inimigo. A glória que buscavas não será sua, e a cidade aguarda por seu próximo herói.");
			else
				System.out.println(
						"Você não estava preparado para a força do inimigo. A glória que buscavas não será sua, e a cidade aguarda por sua próxima heroína.");
		else
			System.out.println(
					"Você não estava preparado para a força do inimigo. Não foi possível concluir sua vingança, e agora resta saber se alguém se vingará por você.");
	}

	private void showCriticalEnemyCriticalAttack(int damage, int lifePoints) {
		System.out.printf(
				"O inimigo acertou um ataque crítico! Você você sofreu %s de dano e agora possúi %s pontos de vida.\n",
				damage, lifePoints);
	}

	private void showCriticalAttack(Weapon weapon, int damage, Boolean isCritical) {
		if (isCritical)
			System.out.printf("Você acertou um ataque crítico!");
		switch (weapon.getTipoArma()) {
		case ArcoFlecha:
			System.out.printf("Você atacou com Arco e a flecha atingiu o inimigo e causou %s de dano!\n", damage);
		case Besta:
			System.out.printf("Você atacou com Besta e a virote atingiu o inimigo e causou %s de dano!\n", damage);
			break;
		case Cajado:
			System.out.printf("Você atacou com o seu Cajado, lançando uma bola de fogo e causou %s de dano!\n", damage);
			break;
		case Livro:
			System.out.printf(
					"Você atacou aborvendo energia do Livro com uma mão e liberando com a outra e causou %s de dano!\n",
					damage);
			break;
		default:
			System.out.printf("Você atacou com %s e causou %s de dano ao inimigo!\n", weapon.getNome(), damage);
			break;
		}
	}

	private void showEnemyAttack(int damage, int lifePoints) {
		System.out.printf("O inimigo atacou! Você sofreu %s de dano e agora possui %s pontos de vida\n", damage,
				lifePoints);
	}

	private Boolean continueAndAttack() {
		while (true) {
			System.out.println("Você deseja [1] Atacar ou [2] Fugir?");
			int i = scanner.nextInt();
			if (i < 1 || i > 2)
				System.out.println("Opção inválida");
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
		System.out.printf("O inimigo atacou! Você sofreu %s de dano e agora possui %s pontos de vida\n\n", damage,
				character.energy());
	}

}
