package com.egaetan;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.StrictAssertions.assertThat;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.assertj.core.api.Assertions;
import org.junit.Test;

public class CubettoTest extends AbstractTestRunner {

	public CubettoTest() {
		super(() -> new Cubetto().main(), "Bataille gagnée 🃏");
	}
	
	@Test
	public void test() {
		/*runFromData("Tout droit", new CubettoData(
				  "......"
				+ ".>..O."
				+ "......"
				+ "......"
				+ "......"
				+ "......"
						  ));*/
		runFromData("Tourne", new CubettoData(
						  "..X..."
						+ ".>X.O."
						+ "..X..."
						+ "......"
						+ "......"
						+ "......"
				));
	}
	
	public static void main(String[] args) {
		CubettoData d = new CubettoData(
				  "..X..."
				+ ".>X.O."
				+ "..X..."
				+ "......"
				+ "......"
				+ "......"
		);
		long start = java.lang.System.currentTimeMillis();
		d.solve();
		long end = java.lang.System.currentTimeMillis();
		java.lang.System.out.println((end - start) + "ms elapsed.");
	}

	static class CubettoData implements Data {

		boolean possible = true;
		String map;
		
		enum Bloc {
			B,
			V,
			J,
			R,
			U,
			SEPARATOR,
			END;
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
			
			@Override
			public World clone() {
				Robot cubettoClone = new Robot(cubetto.position, cubetto.orientation);
				return new World(cubettoClone, grille, passages, arrivee);
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
			
			@Override
			public boolean equals(Object obj) {
				if (obj instanceof Board) {
					Board other = (Board) obj;
					return compare(main, other.main) && compare(other.function, function);
				}
				return super.equals(obj);
			}
			
			@Override
			public int hashCode() {
				return toString().hashCode();
			}
			
			@Override
			public Board clone() {
				Board o = new Board();
				o.main.addAll(main);
				o.function.addAll(function);
				return o;
			}
			
			public void check() {
				assertThat(main.size()).as("Taille du main %d", main.size()).isLessThanOrEqualTo(12);
				assertThat(function.size()).as("Taille de la fonction %d", main.size()).isLessThanOrEqualTo(4);
			}
			
			@Override
			public String toString() {
				return main.stream().map(Object::toString).collect(Collectors.joining()) + " # " + function.stream().map(Object::toString).collect(Collectors.joining());
			}
			
			public int score() {
				
				return main.size() + 
						(function.size() > 0 ? (100 + function.size()) : 0);
			}

			public String text() {
				cleanBoard(true);
				
				return toString();
			}

			public void cleanBoard(boolean aggressive) {
				if (!main.contains(Bloc.B)) {
					function.clear();
				}
				else if (function.contains(Bloc.B)) {
					int ib = main.indexOf(Bloc.B);
					main = main.subList(0, ib);

					int ibb = function.indexOf(Bloc.B);
					function = function.subList(0, ibb);
				}

				clean(function, aggressive);
				
				if (function.size() == 1 && function.get(0) == Bloc.B) {
					function.clear();
				}
				
				if (function.size() == 0) {
					boolean removed = main.remove(Bloc.B) && main.remove(Bloc.B) && main.remove(Bloc.B) && main.remove(Bloc.B);
				}
				clean(main, aggressive);
			}

			public void clean(List<Bloc> src, boolean agressive) {
				boolean again = true;
				while (again) {
					again = false;
					boolean nothing = true;
					
					Bloc prev = Bloc.END;
					for (int i = 0; i < src.size(); i++) {
						Bloc b = src.get(i);
						if (b == Bloc.V || b == Bloc.B) {
							nothing = false;
						}
						if (prev == Bloc.J && b == Bloc.R) {
							again = true;
							src.remove(i);
							src.remove(i - 1);
							break;
						}
						if (prev == Bloc.R && b == Bloc.J) {
							again = true;
							src.remove(i);
							src.remove(i - 1);
							break;
						}
						if (agressive) {
							if (i >= 3-1 && src.get(i-2)==src.get(i-1)&&src.get(i-1)==src.get(i)) {
								if (src.get(i) == Bloc.J) {
									src.add(i-2, Bloc.R);
									src.remove(i-1);
									src.remove(i-1);
									src.remove(i-1);
									again = true;
									break;
								}
								else if (src.get(i) == Bloc.R) {
									src.add(i-2, Bloc.J);
									src.remove(i-1);
									src.remove(i-1);
									src.remove(i-1);
									again = true;
									break;
								}
							}
							if (prev == Bloc.R && b == Bloc.R) {
								again = true;
								src.add(i-1, Bloc.U);
								src.remove(i);
								src.remove(i);
								break;
							}
							if (prev == Bloc.J && b == Bloc.J) {
								again = true;
								src.add(i-1, Bloc.U);
								src.remove(i);
								src.remove(i);
								break;
							}
							if (prev == Bloc.U && b == Bloc.J) {
								again = true;
								src.add(i-1, Bloc.R);
								src.remove(i);
								src.remove(i);
								break;
							}
							if (prev == Bloc.J && b == Bloc.U) {
								again = true;
								src.add(i-1, Bloc.R);
								src.remove(i);
								src.remove(i);
								break;
							}
							if (prev == Bloc.U && b == Bloc.R) {
								again = true;
								src.add(i-1, Bloc.R);
								src.remove(i);
								src.remove(i);
								break;
							}
							if (prev == Bloc.R && b == Bloc.U) {
								again = true;
								src.add(i-1, Bloc.J);
								src.remove(i);
								src.remove(i);
								break;
							}
						}
						if (i >= 4-1 && src.get(i-3)== src.get(i-2) && src.get(i-2)==src.get(i-1)&&src.get(i-1)==src.get(i)) {
							if (src.get(i) == Bloc.J) {
								src.remove(Bloc.J);
								src.remove(Bloc.J);
								src.remove(Bloc.J);
								src.remove(Bloc.J);
								again = true;
								break;
							}
							else if (src.get(i) == Bloc.R) {
								src.remove(Bloc.R);
								src.remove(Bloc.R);
								src.remove(Bloc.R);
								src.remove(Bloc.R);
								again = true;
								break;
							}
						}
						
						prev = b;
					}
					if (!again && nothing) {
						src.clear();
					}
				}
			}

			public boolean compare(List<Bloc> b1, List<Bloc> b2) {
				if (b1.size() != b2.size()) {
					return false;
				}
				for (int i = 0; i < b1.size() ; i++) {
					if (b1.get(i) != b2.get(i)) {
						return false;
					}
				}
				return true;
			}

			public boolean startWith(List<Bloc> b1, List<Bloc> b2) {
				if (b1.size() > b2.size()) {
					return false;
				}
				for (int i = 0; i < b1.size() ; i++) {
					if (b1.get(i) != b2.get(i)) {
						return false;
					}
				}
				return true;
			}
			
			public boolean match(Set<Board> alreadyFailed) {
				clean(function, false);
				return alreadyFailed.stream()
				.anyMatch(f-> compare(f.function, function) && startWith(f.main, main));
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

		
		public static <T extends Comparable<T>> boolean nextPerm(List<T> a) {
            int i = a.size() - 2;
            while (i >= 0 && a.get(i).compareTo(a.get(i + 1))>=0)
                   i--;

            if (i < 0)
                   return false;

            int j = a.size() - 1;
            while (a.get(i).compareTo(a.get(j)) >= 0)
                   j--;

            Collections.swap(a, i, j);
            Collections.reverse(a.subList(i + 1, a.size()));
            return true;
      }


		
		private boolean solve() {
			List<Bloc> alls = new ArrayList<>();
			for (int i = 0; i < 4; i++) {
				alls.add(Bloc.V);
				alls.add(Bloc.J);
				alls.add(Bloc.R);
				alls.add(Bloc.B);
			}
			//alls.add(Bloc.SEPARATOR);
			//alls.add(Bloc.END);
			alls.sort(Comparator.naturalOrder());
			
			Board solution = null;
			int minBoard = 999_999;
			long nbTested = 0;
			Set<Board> solutions = new HashSet<>();
			Set<String> already = new HashSet<>();
			//Set<Board> alreadyFailed = new HashSet<>(); // already prefix
			World worldOrigine = readWorld(map);
			do {
				World world = worldOrigine.clone();
				List<Position> path = new ArrayList<>();
				Board boardo = new Board();
				List<Bloc> current = boardo.main;
				for (int i = 0; i< 16; i++) {
					Bloc b = alls.get(i);
					if (b == Bloc.END) {
						break;
					}
					if (i == 12) {
						current = boardo.function;
					}
					
					if (b == Bloc.SEPARATOR) {
						current = boardo.function;
						continue;
					}
					current.add(b);
				}
				/*if (boardo.match(alreadyFailed)) {
					nbTested ++;
					continue;
				}*/
				Board board = boardo.clone();
				
				if (!already.add(board.text())) {
					continue;
				}
				try {
					nbTested ++;
					//java.lang.System.err.println("try "+ board + " \t\t" + nbTested);
					run(board, board.main, world, path);
				
					assertThat(world.cubetto.position).as("Position").isEqualTo(world.arrivee);
					assertThat(path).as("Chemin").containsAll(world.passages);
					
					boardo.cleanBoard(false);
					
					for (int i = 1; i < boardo.main.size(); i++) {
						Board board2 = boardo.clone();
						board2.main = board2.main.subList(0, i);
						board2.cleanBoard(false);
						
						World world2 = worldOrigine.clone();
						List<Position> path2 = new ArrayList<>();
						try {
							run(board2, board2.main, world2, path2);

							assertThat(world2.cubetto.position).as("Position").isEqualTo(world2.arrivee);
							assertThat(path2).as("Chemin").containsAll(world2.passages);

							solutions.add(board2);
							System.err.println(board2);
							if (minBoard>board2.score()) {
								minBoard = board2.score();
								solution = board2;
							}
							break;
						}
						catch (AssertionError  e) {
							// 
						}
					}
					
					
					
				}
				catch (AssertionError  e) {
					// 
					if (e.getMessage().contains("n'est pas autorisé")
							|| e.getMessage().contains("est invalide")) {
					//	alreadyFailed.add(board);
						
					} else if (e.getMessage().contains("[Position] expected:")) {
						
					}
					else {
						e.printStackTrace();
						System.err.println();
						
					}
				}
				
			} while (nextPerm(alls));

			solutions.stream().forEach(s -> java.lang.System.out.println(s + "  : "+ s.score() + " "+s.text()));
			if (solution != null) {
				java.lang.System.out.println("**** "+ solution);
				return true;
			}
			return false;
			
		}
		
		
		@Override
		public void check(String actual) {
			boolean possible = solve();
			
			World world = readWorld(map);
			
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
			
			List<Position> path = new ArrayList<>();
			run(board, board.main, world, path);
			
			assertThat(world.cubetto.position).as("Position").isEqualTo(world.arrivee);
			assertThat(path).as("Chemin").containsAll(world.passages);
			
		}

		static class Pointer {
			int steps = 0;
			int indx = 0;
			boolean inFunction = false;
			int inMain;
			
			public void jumpFunction() {
				if (!inFunction) {
					inMain = indx;
				}
				inFunction = true;
				indx = 0;
			}

			public boolean hasNext(Board b) {
				if (inFunction) {
					return b.function.size() > indx;
				}
				return b.main.size() > indx;
			}
			
			public Bloc next(Board b) {
				steps++;
				if (inFunction) {
					Bloc bloc = b.function.get(indx);
					indx ++;
					if (indx == b.function.size()) {
						indx = inMain;
						inFunction = false;
					}
					return bloc;
				}
				Bloc bloc = b.main.get(indx);
				indx ++;
				return bloc;
			}
			
		}
		
		public void run(Board board, List<Bloc> current, World world, List<Position> path) {
			Pointer p = new Pointer();
			while (p.hasNext(board)) {
				action(board, world, path, p);
				if (world.arrivee.equals(world.cubetto.position)) {
					break;
				}
				if (world.cubetto.position.x < 0 || world.cubetto.position.x >= 6) {
					Assertions.fail(world.cubetto.position + " est invalide");
				}
				if (world.cubetto.position.y < 0 || world.cubetto.position.y >= 6) {
					Assertions.fail(world.cubetto.position + " est invalide");
				}
				
				if (world.grille[world.cubetto.position.x][world.cubetto.position.y] == Case.BLOCKED) {
					Assertions.fail(world.cubetto.position + " n'est pas autorisé");
					break;
				}
				if (p.steps > 1_000) {
					break;
				}
			}
		}

		public void action(Board board, World world, List<Position> path, Pointer p) {
			Bloc bloc = p.next(board);
			switch (bloc) {
			case V:
				world.cubetto.move();
				break;
			case J:
				world.cubetto.left();
				break;
			case R:
				world.cubetto.right();
				break;
			case B:
				p.jumpFunction();
				break;

			}
			path.add(world.cubetto.position);
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
					break;
				case 'R':
					rouges ++;
					break;
				case 'J':
					jaunes ++;
					break;
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
