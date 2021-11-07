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

		System.out.println("Seja bem vindo(a) à BATALHA FINAL!");

		int selectGameMode = showOptions("Escolha o nível de dificuldade:", new String[] { "Fácil", "Normal", "Difícil" }, "Nível de dificuldade incorreto");
		gameMode = GameMode.values()[selectGameMode - 1];
		
		System.out.println("Informe o nome do personagem:");
		String characterName = scanner.next();

		while (true) {
			System.out.println("Informe o gênero do personagem:");
			System.out.println("[1] - Masculino");
			System.out.println("[2] - Feminino");
			int i = scanner.nextInt();
			if (i < 1 || i > 2)
				System.out.println("Gênero inválido");
			else {
				gender = Gender.values()[i - 1];
				break;
			}
		}

		ICharacter character = null;

		int selectCharacter = showOptions("Selecione uma classe para o seu personagem:", new String[] { "Samurai", "Advogado", "DevInHouser" }, "Personagem inválido.");
		
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
				System.out.println("Arma inválida");
			else {
				character.setWeapon(i - 1);
				break;
			}
		}

		character.showCharacterData();

		System.out.printf(
				"\r\n A noite se aproxima, a lua já surge no céu, estrelas vão se acendendo, e sob a luz do crepúsculo\r\n"
						+ "você está prestes a entrar na fase final da sua missão. Você olha para o portal à sua frente, e\r\n"
						+ "sabe que a partir desse ponto, sua vida mudará para sempre.\r\n"
						+ "Memórias do caminho percorrido para chegar até aqui invadem sua mente.\r\n"
						+ "Você se lembra de todos os inimigos já derrotados para alcançar o covil do líder maligno.\r\n"
						+ "Olha para seu equipamento de combate, já danificado e desgastado depois de tantas lutas.\r\n"
						+ "Você está a um passo de encerrar para sempre esse mal.\r\n");
		while (true) {
			System.out.println("Buscando uma injeção de ânimo, você se força a lembrar o que te trouxe até aqui:");
			System.out.println("[1] - Glória");
			System.out.println("[2] - Vingança");
			int i = scanner.nextInt();
			if (i < 1 || i > 2)
				System.out.println("Motivação inválida.");
			else {
				motivationType = MotivationType.values()[i - 1];
				if (motivationType == MotivationType.GLORIA)
					System.out.println(
							"Você já consegue visualizar na sua mente o povo da cidade te recebendo de braços\r\n"
									+ "abertos, bardos criando canções sobre seus feitos heróicos, nobres te presenteando com jóias e\r\n"
									+ "diversas riquezas, taberneiros se recusando a cobrar por suas bebedeiras e comilanças.\r\n"
									+ "Desde já, você sente o amor do público, te louvando a cada passo que dá pelas ruas, depois de destruir\r\n"
									+ "o vilão que tanto assombrou a paz de todos. Porém, você sabe que ainda falta o último ato dessa\r\n"
									+ "história. Você se concentra na missão. A glória o aguarda, mas não antes da última batalha.");
				else
					System.out
							.println("Imagens daquela noite trágica invadem sua mente. Você nem precisa se esforçar\r\n"
									+ "para lembrar, pois essas memórias estão sempre presentes, mesmo que de pano de fundo,\r\n"
									+ "quando você tem outros pensamentos em foco, elas nunca o deixaram. Elas são o combustível\r\n"
									+ "que te fizeram chegar até aqui. E você sabe que não irá desistir até ter vingado a morte daqueles\r\n"
									+ "que foram - e pra sempre serão - sua fonte de amor e desejo de continuar vivo. O maldito líder\r\n"
									+ "finalmente pagará por tanto mal causado na vida de tantos (e principalmente na sua).");
				break;
			}
		}

		while (true) {
			System.out.println(
					"Inspirado pelo motivo que te trouxe até aqui, você sente seu coração ardendo em chamas, suas\r\n"
							+ "mãos formigarem em volta da sua arma. Você a segura com firmeza. Seu foco está renovado.\r\n"
							+ "Você avança pelo portal. A escuridão te envolve. Uma iluminação muito fraca entra pelo portal às suas costas. \r\n"
							+ "À sua frente, só é possível perceber que você se encontra em um corredor extenso. Você só pode ir à frente, ou desistir.");
			System.out.println("[1] - Seguir em frente");
			System.out.println("[2] - Desistir");
			int i = scanner.nextInt();
			if (i < 1 || i > 2)
				System.out.println("Escolha inválida.");
			else {
				if (i == 1)
					System.out.println(
							"Você caminha, atento a todos os seus sentidos, por vários metros, até visualizar a frente\r\n"
									+ "uma fonte de luz, que você imagina ser a chama de uma tocha, vindo de dentro de uma porta aberta.");
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
					"Você se pergunta se dentro dessa sala pode haver inimigos, ou alguma armadilha, e pondera\r\n"
							+ "sobre como passar pela porta.");
			System.out.println("[1] - Andando cuidadosamente");
			System.out.println("[2] - Correndo");
			System.out.println("[3] - Saltando");
			int i = scanner.nextInt();
			if (i < 1 || i > 3)
				System.out.println("Escolha inválida.");
			else {
				switch (i) {
				case 1:
					System.out.println(
							"Você toma cuidado e vai caminhando vagarosamente em direção à luz. Quando você\r\n"
									+ "pisa exatamente embaixo da porta, você sente o chão ceder levemente, como se tivesse pisado\r\n"
									+ "em uma pedra solta. Você ouve um ruído de mecanismos se movimentando, e uma escotilha se\r\n"
									+ "abre no teto atrás de você, no corredor. Flechas voam da escotilha em sua direção, e você salta\r\n"
									+ "para dentro da sala, porém uma delas te acerta na perna.\n");
					b.walkingDamage();
					break;
				case 2:
					System.out.println("Você respira fundo e desata a correr em direção à sala. \r\n"
							+ "Quando passa pela porta, sente que pisou em uma pedra solta, mas não dá muita importância e segue para dentro da sala,\r\n"
							+ "olhando ao redor à procura de inimigos. Não tem ninguém, mas você ouve sons de flechas\r\n"
							+ "batendo na pedra atrás de você, e quando se vira, vê várias flechas no chão. Espiando pela porta,\r\n"
							+ "você entende que pisou em uma armadilha que soltou flechas de uma escotilha aberta no teto,\r\n"
							+ "mas por sorte você entrou correndo e conseguiu escapar desse ataque surpresa");
					break;
				case 3:
					System.out.println(
							"Você se concentra e pula em direção à luz, saltando de antes da porta até o interior da sala.");
					break;
				}
			}
			break;
		}

		while (true) {

			if (b.numberOfKeys == 2) {
				System.out.println(
						"De volta à sala das portas, você se dirige à porta final. Coloca as chaves nas fechaduras e a porta se abre. Seu coração acelera, \r\n "
						+ "memórias de toda a sua vida passam pela sua mente, e você percebe que está muito próximo ao seu objetivo final. Segura sua \r\n"
						+ "arma com mais firmeza, foca no combate que você sabe que irá se seguir, e adentra a porta.");
				System.out.println(
						"Lá dentro, você ve o líde sentado em uma poltrona dourada, com duas fogueiras de cada lado, e prisioneiros acorrentados às paredes. \r\n "
						+ "Ele percebe sua chegada e se levanta com um salto, apanhando seu machado de guerra de lâmina dupla.");
				Chefao bigBoss = new Chefao();
				bigBoss.setWeapon(0);
				b.setEnemy(bigBoss, 3);
				b.startBattle();
				if (!b.isEndGame()) {
					System.out.println("Você conseguiu!");
					if (motivationType == MotivationType.GLORIA) {
						System.out.println("O êxtase em que você se encontra não cabe dentro de si. Você se ajoelha e grita de alegria. "
								+ "A glória o aguarda, você a consquistou.");
					} else {
						System.out.print("Você obteve sua vingança. Você se ajoelha e cai no choro, invadido por uma sensação de alívio e felicidade.\r\n "
								+ "Você se vingou, tudo que sempre quis, está feito. /r/n "
								+ "Agora você pode seguir sua vida.");
					}
					System.out.print("Você se levanta, olha para os prisioneiros, vai de um em um e os liberta,\r\n"
							+ " e todos vocês saem em direção à noite, retornando à cidade."
							+ "Seu dever está cumprido. GAME OVER.");
				}
				break;
			} else {
				System.out.println(
						"Você se encontra sozinho em uma sala quadrada, contendo uma porta em cada parede. \r\n"
								+ "Uma delas foi aquela pela qual você entrou, que estava aberta, e as outras três estão fechadas. \r\n"
								+ "A porta à sua frente é a maior das quatro, com inscrições em seu entorno em uma língua que você\r\n"
								+ "não sabe ler, mas reconhece como sendo a língua antiga utilizada pelo inimigo. Você se aproxima\r\n"
								+ "da porta e percebe que ela está trancada por duas fechaduras douradas, e você entende que\r\n"
								+ "precisará primeiro derrotar o que estiver nas outras duas portas laterais, antes de conseguir\r\n"
								+ "enfrentar o líder. Qual porta você escolhe?");
				System.out.println("[1] - Porta da Direita");
				System.out.println("[2] - Porta a Frente");
				System.out.println("[3] - Porta da Esquerda");
				int i = scanner.nextInt();
				if (i < 1 || i > 3)
					System.out.println("Escolha inválida.");
				else {
					if (b.isDoorOpen(i)) {
						System.out.println("Você já possui a chave desta batalha.");
					} else {
						switch (i) {
						case 1:
							// direita
							System.out.println(
									"Você escolheu a porta da direita. VocÊ se aproxima, tentando ouvir o que acontece porta adentro, mas não\r\n"
									+ "escuta nada. Segura com mais força sua arma com uma mão, enquanto empurra a porta com a\r\n"
									+ "outra. Ao entrar, você se depara com uma sala espaçosa, com vários equipamentos de batalha\r\n"
									+ "pendurados nas paredes e dispostos em armários e mesas. Você imagina que este seja o arsenal\r\n"
									+ "do inimigo, onde estão guardados os equipamentos que seus soldados utilizam quando saem\r\n"
									+ "para espalhar o terror nas cidades e vilas da região.\r\n"
									+ "Enquanto seu olhar percorre a sala, você ouve a porta se fechando e gira rapidamente para olhar\r\n"
									+ "para trás. Ali, de pé entre você e a porta fechada, bloqueando o caminho do seu destino, está um\r\n"
									+ "dos capitães do inimigo. Um orque horrendo, de armadura, capacete e espada em punho, em\r\n"
									+ "posição de combate. Ele avança em sua direção.");
							Armeiro armeiro = new Armeiro();
							armeiro.setWeapon(0);
							b.setEnemy(armeiro, i);
							b.startBattle();
							if (!b.isEndGame()) {
								System.out.println(
										"Apos derrotar o Armeiro, você percebe que seus equipamentos estão muito danificados, e olha em volta, encarando todas \r\n "
										+ "aquelas peças de armaduras resistentes e em ótimo estado.");
								while (true) {
									System.out.println("Escolha a opção abaixo:");
									System.out.println("[1] - Pegar armaduras novas");
									System.out.println("[2] - Não pegar armaduras novas");
									i = scanner.nextInt();
									if (i < 1 || i > 2)
										System.out.println("Escolha inválida.");
									else {
										if (i == 1) {
											b.increaseDefensePoints(5);
											System.out.println(
													"Você resolve usar os equipamentos do inimigo contra ele, e trocar algumas peças suas, que estavam danificadas, \r\n"
													+ "pelas peças de armaduras existentes na sala. De armadura nova você se sente mais protegido para os desafios "
													+ "a sua frente.");
											System.out.println(
													"Com a sua escolha seus pontos de defesa aumenataram 5 pontos\n");
										} else {
											System.out.println(
													"Você decide que não precisa utilizar nada que venha das mãos do inimigo.\n");
										}

									}
									break;
								}
								System.out.println(
										"Em uma mesa, você encontra uma chave dourada, e sabe que aquela chave abre uma das fechaduras da porta do líder inimigo. \r\n"
										+ "Você pega a chave e guarda numa pequena bolsa que leva presa ao cinto.");
							}
							break;
						case 2:
							// frente
							if (b.numberOfKeys < 2) {
								System.out.println("Você ainda não possui todas as chaves para abrir esta porta.");
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
									"Você entrou na porta da esquerda e se aproxima, tentando ouvir o que acontece porta adentro, mas não escuta nada. Segura com mais\r\n"
									+ "força sua arma com uma mão, enquanto empurra a porta com a outra. Ao entrar, você se depara\r\n"
									+ "com uma sala parecida com a do arsenal, mas em vez de armaduras, existem vários potes e\r\n"
									+ "garrafas de vidro com conteúdos misteriosos e de cores diversas, e você entende que o capitão\r\n"
									+ "que vive ali, realiza experimentos com diversos ingredientes, criando poções utilizadas pelos\r\n"
									+ "soldados para aterrorizar a região.\r\n"
									+ "No fundo da sala, olhando em sua direção, está outro dos capitães do inimigo. Um orque\r\n"
									+ "horrendo, de armadura, cajado em punho, em posição de combate. Ele avança em sua direção.");
							Alquimista alquimista = new Alquimista();
							alquimista.setWeapon(0);
							b.setEnemy(alquimista, i);
							b.startBattle();
							if (!b.isEndGame()) {
								System.out.println(
										"Apos derrotar o Alquimista, você oLha em volta, tentando reconhecer alguma poção do estoque do inimigo.\r\n "
										+ "Em uma mesa, você reconhece uma pequena garrafa de vidro contendo um líquido levemente rosado, "
										+ "pega a garrafa e pondera se deve beber um gole.");
								while (true) {
									System.out.println("Escolha a opção abaixo:");
									System.out.println("[1] - Beber a poção");
									System.out.println("[2] - Não beber a poção");
									i = scanner.nextInt();
									if (i < 1 || i > 2)
										System.out.println("Escolha inválida.");
									else {
										if (i == 1) {
											b.recoverEnergy();
											System.out.println(
													"Você se sente revigorado para seguir a diante! E recupera 100% dos pontos de vida.");
										} else {
											System.out.println(
													"Você fica receoso de beber algo produzido pelo inimigo.\n");
										}

									}
									break;
								}
								System.out.println(
										"Ao lado da porta, você vê uma chave dourada em cima da mesa, e sabe que aquela chave abre uma das fechaduras \r\n "
										+ "da porta do líder inimigo.\nVocê pega a chave e guarda na pequena bolsa que leva presa ao cinto.");
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
				"O medo invade o seu coração e você sente que ainda não está a altura do desafio. Você se volta para a noite e corre em direção a segurança.\n GAME OVER");
		isGameOver = true;
	}
}