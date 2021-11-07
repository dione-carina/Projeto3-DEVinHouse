package game.rpg;

import java.util.Scanner;

import characters.Advogado;
import characters.Alquimista;
import characters.Armeiro;
import characters.Chefao;
import characters.DevInHouser;
import characters.Gender;
import characters.ICharacter;
import characters.Samurai;
import resources.GameMode;
import resources.MotivationType;
import resources.Weapon;

public class Game {
	private static GameMode gameMode;
	private static Gender gender = Gender.Masculino;
	private static MotivationType motivationType;
	private static Boolean isGameOver = false;
	private static Scanner scanner;
	
	private static int showOptions(String message, String[] options, String errorOnInput)
	{
		int result = 0;
		while(true)
		{
			System.out.println(message);
			for (int i = 0; i < options.length; i++) {
				System.out.printf("[%s] - %s\n", i+1, options[i]);
			}
			int i = scanner.nextInt();
			if (i < 1 || i > 3)
				System.out.println(errorOnInput);
			else {
				result = i;
				break;
			}
		}
		return result;
	}
	
	public static void main(String[] args) {
		scanner = new Scanner(System.in);

		System.out.println("Seja bem vindo(a) � BATALHA FINAL!");

		int selectGameMode = showOptions("Escolha o n�vel de dificuldade:", new String[] { "F�cil", "Normal", "Dif�cil" }, "N�vel de dificuldade incorreto");
		gameMode = GameMode.values()[selectGameMode - 1];
		
		System.out.println("Informe o nome do personagem:");
		String characterName = scanner.next();

		while (true) {
			System.out.println("Informe o g�nero do personagem:");
			System.out.println("[1] - Masculino");
			System.out.println("[2] - Feminino");
			int i = scanner.nextInt();
			if (i < 1 || i > 2)
				System.out.println("G�nero inv�lido");
			else {
				gender = Gender.values()[i - 1];
				break;
			}
		}

		ICharacter character = null;

		int selectCharacter = showOptions("Selecione uma classe para o seu personagem:", new String[] { "Samurai", "Advogado", "DevInHouser" }, "Personagem inv�lido.");
		
		switch (selectCharacter) {
		case 1:
			character = new Samurai();
			break;
		case 2:
			character = new Advogado();
			break;
		case 3:
			character = new DevInHouser();
			break;
		default:
			break;
		}
		
		character.setName(characterName);
		character.setGender(gender);

		while (true) {
			System.out.println("Selecione uma arma para o seu personagem:");
			int auxWeapon = 1;
			for (Weapon weapon : character.weapons()) {
				System.out.printf("[%s] Arma : %s\n    Poder: %s\n", auxWeapon, weapon.getNome(), weapon.getPoder());
				auxWeapon++;
			}
			int i = scanner.nextInt();
			if (i < 1 || i > auxWeapon - 1)
				System.out.println("Arma inv�lida");
			else {
				character.setWeapon(i - 1);
				break;
			}
		}

		character.showCharacterData();

		System.out.printf(
				"\r\n A noite se aproxima, a lua j� surge no c�u, estrelas v�o se acendendo, e sob a luz do crep�sculo\r\n"
						+ "voc� est� prestes a entrar na fase final da sua miss�o. Voc� olha para o portal � sua frente, e\r\n"
						+ "sabe que a partir desse ponto, sua vida mudar� para sempre.\r\n"
						+ "Mem�rias do caminho percorrido para chegar at� aqui invadem sua mente.\r\n"
						+ "Voc� se lembra de todos os inimigos j� derrotados para alcan�ar o covil do l�der maligno.\r\n"
						+ "Olha para seu equipamento de combate, j� danificado e desgastado depois de tantas lutas.\r\n"
						+ "Voc� est� a um passo de encerrar para sempre esse mal.\r\n");
		while (true) {
			System.out.println("Buscando uma inje��o de �nimo, voc� se for�a a lembrar o que te trouxe at� aqui:");
			System.out.println("[1] - Gl�ria");
			System.out.println("[2] - Vingan�a");
			int i = scanner.nextInt();
			if (i < 1 || i > 2)
				System.out.println("Motiva��o inv�lida.");
			else {
				motivationType = MotivationType.values()[i - 1];
				if (motivationType == MotivationType.GLORIA)
					System.out.println(
							"Voc� j� consegue visualizar na sua mente o povo da cidade te recebendo de bra�os\r\n"
									+ "abertos, bardos criando can��es sobre seus feitos her�icos, nobres te presenteando com j�ias e\r\n"
									+ "diversas riquezas, taberneiros se recusando a cobrar por suas bebedeiras e comilan�as.\r\n"
									+ "Desde j�, voc� sente o amor do p�blico, te louvando a cada passo que d� pelas ruas, depois de destruir\r\n"
									+ "o vil�o que tanto assombrou a paz de todos. Por�m, voc� sabe que ainda falta o �ltimo ato dessa\r\n"
									+ "hist�ria. Voc� se concentra na miss�o. A gl�ria o aguarda, mas n�o antes da �ltima batalha.");
				else
					System.out
							.println("Imagens daquela noite tr�gica invadem sua mente. Voc� nem precisa se esfor�ar\r\n"
									+ "para lembrar, pois essas mem�rias est�o sempre presentes, mesmo que de pano de fundo,\r\n"
									+ "quando voc� tem outros pensamentos em foco, elas nunca o deixaram. Elas s�o o combust�vel\r\n"
									+ "que te fizeram chegar at� aqui. E voc� sabe que n�o ir� desistir at� ter vingado a morte daqueles\r\n"
									+ "que foram - e pra sempre ser�o - sua fonte de amor e desejo de continuar vivo. O maldito l�der\r\n"
									+ "finalmente pagar� por tanto mal causado na vida de tantos (e principalmente na sua).");
				break;
			}
		}

		while (true) {
			System.out.println(
					"Inspirado pelo motivo que te trouxe at� aqui, voc� sente seu cora��o ardendo em chamas, suas\r\n"
							+ "m�os formigarem em volta da sua arma. Voc� a segura com firmeza. Seu foco est� renovado.\r\n"
							+ "Voc� avan�a pelo portal. A escurid�o te envolve. Uma ilumina��o muito fraca entra pelo portal �s suas costas. \r\n"
							+ "� sua frente, s� � poss�vel perceber que voc� se encontra em um corredor extenso. Voc� s� pode ir � frente, ou desistir.");
			System.out.println("[1] - Seguir em frente");
			System.out.println("[2] - Desistir");
			int i = scanner.nextInt();
			if (i < 1 || i > 2)
				System.out.println("Escolha inv�lida.");
			else {
				if (i == 1)
					System.out.println(
							"Voc� caminha, atento a todos os seus sentidos, por v�rios metros, at� visualizar a frente\r\n"
									+ "uma fonte de luz, que voc� imagina ser a chama de uma tocha, vindo de dentro de uma porta aberta.");
				else
					gameOver();
				break;
			}
		}

		if (isGameOver) {
			scanner.close();
			return;
		}

		Battle b = new Battle(character, gameMode, motivationType);

		while (true) {
			System.out.println(
					"Voc� se pergunta se dentro dessa sala pode haver inimigos, ou alguma armadilha, e pondera\r\n"
							+ "sobre como passar pela porta.");
			System.out.println("[1] - Andando cuidadosamente");
			System.out.println("[2] - Correndo");
			System.out.println("[3] - Saltando");
			int i = scanner.nextInt();
			if (i < 1 || i > 3)
				System.out.println("Escolha inv�lida.");
			else {
				switch (i) {
				case 1:
					System.out.println(
							"Voc� toma cuidado e vai caminhando vagarosamente em dire��o � luz. Quando voc�\r\n"
									+ "pisa exatamente embaixo da porta, voc� sente o ch�o ceder levemente, como se tivesse pisado\r\n"
									+ "em uma pedra solta. Voc� ouve um ru�do de mecanismos se movimentando, e uma escotilha se\r\n"
									+ "abre no teto atr�s de voc�, no corredor. Flechas voam da escotilha em sua dire��o, e voc� salta\r\n"
									+ "para dentro da sala, por�m uma delas te acerta na perna.\n");
					b.walkingDamage();
					break;
				case 2:
					System.out.println("Voc� respira fundo e desata a correr em dire��o � sala. \r\n"
							+ "Quando passa pela porta, sente que pisou em uma pedra solta, mas n�o d� muita import�ncia e segue para dentro da sala,\r\n"
							+ "olhando ao redor � procura de inimigos. N�o tem ningu�m, mas voc� ouve sons de flechas\r\n"
							+ "batendo na pedra atr�s de voc�, e quando se vira, v� v�rias flechas no ch�o. Espiando pela porta,\r\n"
							+ "voc� entende que pisou em uma armadilha que soltou flechas de uma escotilha aberta no teto,\r\n"
							+ "mas por sorte voc� entrou correndo e conseguiu escapar desse ataque surpresa");
					break;
				case 3:
					System.out.println(
							"Voc� se concentra e pula em dire��o � luz, saltando de antes da porta at� o interior da sala.");
					break;
				}
			}
			break;
		}

		while (true) {

			if (b.numberOfKeys == 2) {
				System.out.println(
						"De volta � sala das portas, voc� se dirige � porta final. Coloca as chaves nas fechaduras e a porta se abre. Seu cora��o acelera, \r\n "
						+ "mem�rias de toda a sua vida passam pela sua mente, e voc� percebe que est� muito pr�ximo ao seu objetivo final. Segura sua \r\n"
						+ "arma com mais firmeza, foca no combate que voc� sabe que ir� se seguir, e adentra a porta.");
				System.out.println(
						"L� dentro, voc� ve o l�de sentado em uma poltrona dourada, com duas fogueiras de cada lado, e prisioneiros acorrentados �s paredes. \r\n "
						+ "Ele percebe sua chegada e se levanta com um salto, apanhando seu machado de guerra de l�mina dupla.");
				Chefao bigBoss = new Chefao();
				bigBoss.setWeapon(0);
				b.setEnemy(bigBoss, 3);
				b.startBattle();
				if (!b.isEndGame()) {
					System.out.println("Voc� conseguiu!");
					if (motivationType == MotivationType.GLORIA) {
						System.out.println("O �xtase em que voc� se encontra n�o cabe dentro de si. Voc� se ajoelha e grita de alegria. "
								+ "A gl�ria o aguarda, voc� a consquistou.");
					} else {
						System.out.print("Voc� obteve sua vingan�a. Voc� se ajoelha e cai no choro, invadido por uma sensa��o de al�vio e felicidade.\r\n "
								+ "Voc� se vingou, tudo que sempre quis, est� feito. /r/n "
								+ "Agora voc� pode seguir sua vida.");
					}
					System.out.print("Voc� se levanta, olha para os prisioneiros, vai de um em um e os liberta,\r\n"
							+ " e todos voc�s saem em dire��o � noite, retornando � cidade."
							+ "Seu dever est� cumprido. GAME OVER.");
				}
				break;
			} else {
				System.out.println(
						"Voc� se encontra sozinho em uma sala quadrada, contendo uma porta em cada parede. \r\n"
								+ "Uma delas foi aquela pela qual voc� entrou, que estava aberta, e as outras tr�s est�o fechadas. \r\n"
								+ "A porta � sua frente � a maior das quatro, com inscri��es em seu entorno em uma l�ngua que voc�\r\n"
								+ "n�o sabe ler, mas reconhece como sendo a l�ngua antiga utilizada pelo inimigo. Voc� se aproxima\r\n"
								+ "da porta e percebe que ela est� trancada por duas fechaduras douradas, e voc� entende que\r\n"
								+ "precisar� primeiro derrotar o que estiver nas outras duas portas laterais, antes de conseguir\r\n"
								+ "enfrentar o l�der. Qual porta voc� escolhe?");
				System.out.println("[1] - Porta da Direita");
				System.out.println("[2] - Porta a Frente");
				System.out.println("[3] - Porta da Esquerda");
				int i = scanner.nextInt();
				if (i < 1 || i > 3)
					System.out.println("Escolha inv�lida.");
				else {
					if (b.isDoorOpen(i)) {
						System.out.println("Voc� j� possui a chave desta batalha.");
					} else {
						switch (i) {
						case 1:
							// direita
							System.out.println(
									"Voc� escolheu a porta da direita. Voc� se aproxima, tentando ouvir o que acontece porta adentro, mas n�o\r\n"
									+ "escuta nada. Segura com mais for�a sua arma com uma m�o, enquanto empurra a porta com a\r\n"
									+ "outra. Ao entrar, voc� se depara com uma sala espa�osa, com v�rios equipamentos de batalha\r\n"
									+ "pendurados nas paredes e dispostos em arm�rios e mesas. Voc� imagina que este seja o arsenal\r\n"
									+ "do inimigo, onde est�o guardados os equipamentos que seus soldados utilizam quando saem\r\n"
									+ "para espalhar o terror nas cidades e vilas da regi�o.\r\n"
									+ "Enquanto seu olhar percorre a sala, voc� ouve a porta se fechando e gira rapidamente para olhar\r\n"
									+ "para tr�s. Ali, de p� entre voc� e a porta fechada, bloqueando o caminho do seu destino, est� um\r\n"
									+ "dos capit�es do inimigo. Um orque horrendo, de armadura, capacete e espada em punho, em\r\n"
									+ "posi��o de combate. Ele avan�a em sua dire��o.");
							Armeiro armeiro = new Armeiro();
							armeiro.setWeapon(0);
							b.setEnemy(armeiro, i);
							b.startBattle();
							if (!b.isEndGame()) {
								System.out.println(
										"Apos derrotar o Armeiro, voc� percebe que seus equipamentos est�o muito danificados, e olha em volta, encarando todas \r\n "
										+ "aquelas pe�as de armaduras resistentes e em �timo estado.");
								while (true) {
									System.out.println("Escolha a op��o abaixo:");
									System.out.println("[1] - Pegar armaduras novas");
									System.out.println("[2] - N�o pegar armaduras novas");
									i = scanner.nextInt();
									if (i < 1 || i > 2)
										System.out.println("Escolha inv�lida.");
									else {
										if (i == 1) {
											b.increaseDefensePoints(5);
											System.out.println(
													"Voc� resolve usar os equipamentos do inimigo contra ele, e trocar algumas pe�as suas, que estavam danificadas, \r\n"
													+ "pelas pe�as de armaduras existentes na sala. De armadura nova voc� se sente mais protegido para os desafios "
													+ "a sua frente.");
											System.out.println(
													"Com a sua escolha seus pontos de defesa aumenataram 5 pontos\n");
										} else {
											System.out.println(
													"Voc� decide que n�o precisa utilizar nada que venha das m�os do inimigo.\n");
										}

									}
									break;
								}
								System.out.println(
										"Em uma mesa, voc� encontra uma chave dourada, e sabe que aquela chave abre uma das fechaduras da porta do l�der inimigo. \r\n"
										+ "Voc� pega a chave e guarda numa pequena bolsa que leva presa ao cinto.");
							}
							break;
						case 2:
							// frente
							if (b.numberOfKeys < 2) {
								System.out.println("Voc� ainda n�o possui todas as chaves para abrir esta porta.");
								break;
							} else {
								Chefao chefao = new Chefao();
								chefao.setWeapon(0);
								b.setEnemy(chefao, i);
								b.startBattle();
								break;
							}
						case 3:
							// esquerda
							System.out.println(
									"Voc� entrou na porta da esquerda e se aproxima, tentando ouvir o que acontece porta adentro, mas n�o escuta nada. Segura com mais\r\n"
									+ "for�a sua arma com uma m�o, enquanto empurra a porta com a outra. Ao entrar, voc� se depara\r\n"
									+ "com uma sala parecida com a do arsenal, mas em vez de armaduras, existem v�rios potes e\r\n"
									+ "garrafas de vidro com conte�dos misteriosos e de cores diversas, e voc� entende que o capit�o\r\n"
									+ "que vive ali, realiza experimentos com diversos ingredientes, criando po��es utilizadas pelos\r\n"
									+ "soldados para aterrorizar a regi�o.\r\n"
									+ "No fundo da sala, olhando em sua dire��o, est� outro dos capit�es do inimigo. Um orque\r\n"
									+ "horrendo, de armadura, cajado em punho, em posi��o de combate. Ele avan�a em sua dire��o.");
							Alquimista alquimista = new Alquimista();
							alquimista.setWeapon(0);
							b.setEnemy(alquimista, i);
							b.startBattle();
							if (!b.isEndGame()) {
								System.out.println(
										"Apos derrotar o Alquimista, voc� oLha em volta, tentando reconhecer alguma po��o do estoque do inimigo.\r\n "
										+ "Em uma mesa, voc� reconhece uma pequena garrafa de vidro contendo um l�quido levemente rosado, "
										+ "pega a garrafa e pondera se deve beber um gole.");
								while (true) {
									System.out.println("Escolha a op��o abaixo:");
									System.out.println("[1] - Beber a po��o");
									System.out.println("[2] - N�o beber a po��o");
									i = scanner.nextInt();
									if (i < 1 || i > 2)
										System.out.println("Escolha inv�lida.");
									else {
										if (i == 1) {
											b.recoverEnergy();
											System.out.println(
													"Voc� se sente revigorado para seguir a diante! E recupera 100% dos pontos de vida.");
										} else {
											System.out.println(
													"Voc� fica receoso de beber algo produzido pelo inimigo.\n");
										}

									}
									break;
								}
								System.out.println(
										"Ao lado da porta, voc� v� uma chave dourada em cima da mesa, e sabe que aquela chave abre uma das fechaduras \r\n "
										+ "da porta do l�der inimigo.\nVoc� pega a chave e guarda na pequena bolsa que leva presa ao cinto.");
							}
							break;
						}
					}
					if (b.isEndGame())
						break;
				}
			}
		}
		scanner.close();
	}

	private static void gameOver() {
		System.out.printf(
				"O medo invade o seu cora��o e voc� sente que ainda n�o est� a altura do desafio. Voc� se volta para a noite e corre em dire��o a seguran�a.\n GAME OVER");
		isGameOver = true;
	}
}