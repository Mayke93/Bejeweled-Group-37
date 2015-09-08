package bejeweled37.test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import com.sun.xml.internal.bind.v2.schemagen.xmlschema.List;

import bejeweled37.Combination;
import bejeweled37.Tile;

/**
 * @author Mayke
 * Simple basic tests for class Combination
 * Not all methods tested yet, because random colors are assignes to Tiles 
 */
public class CombinationTest {
	
	public Combination combinationMaker() {
		ArrayList<Tile> tiles = new ArrayList<Tile>();
		Tile a = new Tile(1, 1);
		Tile b = new Tile(1, 2);
		Tile c = new Tile(1, 3);
		tiles.add(a);
		tiles.add(b);
		tiles.add(c);
		Combination x = new Combination(Tile.State.NORMAL, tiles);
		return x;
	}

	@Test
	public void testGetState() {
		Combination x = combinationMaker();
		assertEquals(Tile.State.NORMAL, x.getState());
	}
	
	@Test
	public void testSetState() {
		Combination x = combinationMaker();
		assertEquals(Tile.State.NORMAL, x.getState());
		x.setState(Tile.State.FLAME);
		assertEquals(Tile.State.FLAME, x.getState());
	}
	

	/*This needs to be tested when Tile class has been changed.
	 * As if now, all tiles are random colors.
	 * 
	 * @Test
	public void testGetTiles() {
		Combination x = combinationMaker();
		ArrayList<Tile> tiles = new ArrayList<Tile>();
		Tile a = new Tile(1, 1);
		Tile b = new Tile(1, 2);
		Tile c = new Tile(1, 3);
		tiles.add(a);
		tiles.add(b);
		tiles.add(c);
		assertEquals(tiles, x.getTiles());
		
	}*/
	
	@Test
	public void testSetTiles() {
		Combination x = combinationMaker();
		ArrayList<Tile> tiles = new ArrayList<Tile>();
		Tile a = new Tile(1, 1);
		tiles.add(a);
		x.setTiles(tiles);
		assertEquals(tiles, x.getTiles());
	}
	
}
