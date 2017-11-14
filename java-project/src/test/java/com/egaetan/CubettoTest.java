package com.egaetan;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import org.assertj.core.api.Assertions;
import org.junit.Test;

public class CubettoTest extends AbstractTestRunner {

	public CubettoTest() {
		super(() -> new Cubetto().main(), "Bataille gagnée 🃏");
	}
	
	@Test
	public void test() {
		runFromData("Cubetto", new CubettoData(
				  "......"
				+ ".>..O."
				+ "......"
				+ "......"
				+ "......"
				+ "......"
						  ));
	}

	static class CubettoData implements Data {

		boolean possible = true;
		String map;
		
		enum Bloc {
			B,
			V,
			J,
			R;
		}
		
		enum Case {
			PLAIN,
			BLOCKED;
		}
		
		enum Orientation {
			HAUT(p -> new Position(p.x, p.y + 1)),
			GAUCHE(p -> new Position(p.x - 1, p.y)),
			BAS(p -> new Position(p.x, p.y - 1)),
			DROITE(p -> new Position(p.x + 1, p.y));
			
			final Function<Position, Position> move;
			
			Orientation(Function<Position, Position> move) {
				this.move = move;
			}
			
			Position move(Position from) {
			    return move.apply(from);
			}
			
			Orientation left() {
				return Orientation.values()[(this.ordinal() + 4 - 1) % 4];
			}

			Orientation right() {
				return Orientation.values()[(this.ordinal() + 1) % 4];
			}
		}
		
		static class Robot {
			Position position;
			Orientation orientation;
			
			public Robot(Position position, Orientation orientation) {
				super();
				this.position = position;
				this.orientation = orientation;
			}

			public void move() {
				this.position = this.orientation.move(this.position);
			}

			public void left() {
				this.orientation = this.orientation.left();
			}

			public void right() {
				this.orientation = this.orientation.right();
			}
			
		}
		
		static class World {
			final Robot cubetto;
			final Case[][] grille;
			final List<Position> passages;
			final Position arrivee;
			
			public World(Robot cubetto, Case[][] grille, List<Position> passages, Position arrivee) {
				super();
				this.cubetto = cubetto;
				this.grille = grille;
				this.passages = passages;
				this.arrivee = arrivee;
			}
			
			
		}
		
		static class Position {
			final int x;
			final int y;
			
			public Position(int x, int y) {
				super();
				this.x = x;
				this.y = y;
			}
			
			@Override
			public boolean equals(Object obj) {
				if (obj instanceof Position) {
					Position other = (Position) obj;
					return other.x == x && other.y == y;
				}
				return super.equals(obj);
			}
			
			@Override
			public int hashCode() {
				return x * 13 + y;
			}
			
			@Override
			public String toString() {
				return x + " " + y;
			}
		}
		
		static class Board {
			List<Bloc> main = new ArrayList<>();
			List<Bloc> function = new ArrayList<>();
			
			public void check() {
				assertThat(main.size()).as("Taille du main %d", main.size()).isLessThanOrEqualTo(12);
				assertThat(function.size()).as("Taille de la fonction %d", main.size()).isLessThanOrEqualTo(4);
			}
		}
		
		public CubettoData(String map) {
			super();
			this.map = 
					map.substring(0, 6) + "\n" +
					map.substring(6, 12) + "\n" +
					map.substring(12, 18) + "\n" +
					map.substring(18, 24) + "\n" +
					map.substring(24, 30) + "\n" +
					map.substring(30, 36) + "\n";
		}

		@Override
		public void check(String actual) {
			checkNb(actual);
			Board board = new Board();
			List<Bloc> current = board.main;
			for (int i = 0; i < actual.length(); i++) {
				String ordre = actual.substring(i, i+1);
				if ("#".equals(ordre)) {
					current = board.function;
					continue;
				}
				Bloc b = Bloc.valueOf(ordre);
				current.add(b);
				
			}
			board.check();
			
			World world = readWorld(map);
			List<Position> path = new ArrayList<>();
			int steps = run(board, board.main, world, path, 0);
			
			assertThat(world.cubetto.position).as("Position").isEqualTo(world.arrivee);
			assertThat(path).as("Chemin").containsAll(world.passages);
			
		}

		public int run(Board board, List<Bloc> current, World world, List<Position> path, int steps) {
			for (int i = 0; i < current.size(); i++) {
				steps = action(board, world, path, current.get(i), steps);
				if (world.arrivee.equals(world.cubetto.position)) {
					break;
				}
				if (steps > 10_000) {
					break;
				}
			}
			
			return steps;
		}

		public int action(Board board, World world, List<Position> path, Bloc bloc, int steps) {
			switch (bloc) {
			case V:
				world.cubetto.move();
				steps++;
				break;
			case J:
				world.cubetto.left();
				steps++;
				break;
			case R:
				world.cubetto.right();
				steps++;
				break;
			case B:
				steps++;
				run(board, board.function, world, path, steps);
				break;

			}
			path.add(world.cubetto.position);
			return steps;
		}

		private World readWorld(String mapLines) {
			String[] lines = mapLines.split("\n");
			Case[][] world = new Case[6][6];
			for (int i = 0; i < 6; i++) {
				world[i] = new Case[6];
			}
			List<Position> passages = new ArrayList<>();
			Position arrivee = null;
			Robot cubetto = null;
			
			for (int iy = 0; iy < 6; iy++) {
				String line = lines[iy];
				System.err.println(line);
				for (int ix = 0; ix < 6; ix++) {
					Position current = new Position(ix, iy);
					switch (line.charAt(ix)) {
					case 'X':
						world[ix][iy] = Case.BLOCKED;
						break;
					case 'O':
						arrivee = current;
						break;
					case 'P':
						passages.add(current);
						break;
					case '^':
						cubetto = new Robot(current, Orientation.HAUT);
						break;
					case '<':
						cubetto = new Robot(current, Orientation.GAUCHE);
						break;
					case 'v':
						cubetto = new Robot(current, Orientation.BAS);
						break;
					case '>':
						cubetto = new Robot(current, Orientation.DROITE);
						break;

					case '.':
					default:
						world[ix][iy] = Case.PLAIN;
						break;
					}
				}
			}			
			return new World(cubetto, world, passages, arrivee);
		}

		private void checkNb(String actual) {
			int verts= 0;
			int jaunes = 0;
			int rouges = 0;
			int bleus = 0;
			for (int i = 0; i < actual.length(); i++) {
				switch (actual.charAt(i)) {
				case 'V':
					verts ++;
				case 'R':
					rouges ++;
				case 'J':
					jaunes ++;
				case 'B':
					bleus ++;
					break;
				case '#':
					break;
				default:
					Assertions.fail("'" + actual.charAt(i) + "' innatendu");
					break;
				}
			}
			assertThat(verts).as("Nombre de verts: %d", verts).isLessThanOrEqualTo(4);
			assertThat(jaunes).as("Nombre de jaunes: %d", verts).isLessThanOrEqualTo(4);
			assertThat(rouges).as("Nombre de rouges: %d", verts).isLessThanOrEqualTo(4);
			assertThat(bleus).as("Nombre de bleus: %d", verts).isLessThanOrEqualTo(4);
		}

		@Override
		public String input() {
			return map;
		}
		
	}
	
	
}
