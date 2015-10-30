package main.java.group37.bejeweled.model;

import main.java.group37.bejeweled.board.Board;
import main.java.group37.bejeweled.board.FlameTile;
import main.java.group37.bejeweled.board.HypercubeTile;
import main.java.group37.bejeweled.board.StarTile;
import main.java.group37.bejeweled.board.Tile;
import main.java.group37.bejeweled.combination.Combination;
import main.java.group37.bejeweled.combination.Combination.Type;
import main.java.group37.bejeweled.combination.CombinationFinder;
import main.java.group37.bejeweled.view.Animation;
import main.java.group37.bejeweled.view.Main;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;


/**
 * Contains methods related to swappig tiles.
 * @author Group 37
 *
 */
public class SwapHandler {

  private static Board board;
  public static List<Tile> swapTiles;
  private static Tile[] swappedTiles;
  private static CombinationFinder finder;
  private static Main main;
  
  /**
   * .
   * @param bo a board object.
   */
  public SwapHandler(Board bo, Main ma) {
    board = bo;   
    main = ma;
    
    swapTiles = new ArrayList<Tile>();
    swappedTiles = new Tile[2];
    
    finder = new CombinationFinder(board);
  }
  
  /**
   * Add tile to swapTiles based on location from the mouseEvent.
   * @param loc location of tile
   */
  public static void addTile(Point loc) {
    int col = loc.x;
    int row = loc.y;
    if (!swapTiles.contains(board.getTileAt(col, row))) {
      swapTiles.add(board.getTileAt(col, row));
      main.setFocus(loc);
      if (swapTiles.size() == 2 && canSwap()) {              
        swapTiles(swapTiles);
        swapTiles.clear();
      }
    }
  }
  
  /**
   * Switch tile t0 and t1 on the board.
   * @param t0 first tile to swap
   * @param t1 second tile to swap
   */
  public static void swappedTiles(Tile t0, Tile t1) {
    Tile temp = board.getTileAt(t0.getX(), t0.getY());
    board.setTileAt(board.getTileAt(t1.getX(), t1.getY()), t0.getX(), t0.getY());
    board.setTileAt(temp, t1.getX(), t1.getY());

    int xc = t0.getX();
    int yc = t0.getY();
    t0.setLoc(t1.getX(),t1.getY());
    t1.setLoc(xc, yc);
    
    swappedTiles[0] = t0;
    swappedTiles[1] = t1;
  }
  
  /**
   * Swap two tiles if it result in a sequence of 3 of more tiles with the same color.
   */
  public static boolean canSwap() {
    Tile t0 = board.getTileAt(swapTiles.get(0).getX(), swapTiles.get(0).getY());
    Tile t1 = board.getTileAt(swapTiles.get(1).getX(), swapTiles.get(1).getY());

    swappedTiles(t0,t1);
    Combination combiX0 = finder.getSingleCombinationX(t0);
    Combination combiX1 = finder.getSingleCombinationX(t1);
    Combination combiY0 = finder.getSingleCombinationY(t0);
    Combination combiY1 = finder.getSingleCombinationY(t1);
    swappedTiles(t0,t1);

    Type type = null;
    if (!(combiX0 == null)) {
      type = combiX0.getType();
    } else if (!(combiX1 == null)) {
      type = combiX1.getType();
    } else if (!(combiY0 == null)) {
      type = combiY0.getType();
    } else if (!(combiY1 == null)) {
      type = combiY1.getType();
    }

    if (t0 instanceof HypercubeTile || t1 instanceof HypercubeTile) {
      return true;
    }

    if (type == null) {
      return false;
    }

    if (!isNeighbour(t0,t1)) {
      Logger.error("t0 and t1 are not neighbours.");
      return false;
    }
    return true;
  }
  
  /**
   * Return true if t0 and t1 are neighbours.
   * @param t0 tile 1.
   * @param t1 tile 2.
   * @return true if t0 and t1 are next to each other.
   */
  public static boolean isNeighbour(Tile t0, Tile t1) {
    if (Math.abs(t0.getX() - t1.getX()) == 1 && Math.abs(t0.getY() - t1.getY()) == 0) {
      return true;
    }
    if (Math.abs(t0.getX() - t1.getX()) == 0 && Math.abs(t0.getY() - t1.getY()) == 1) {
      return true;
    }
    return false;
  }
  
  /**
   * Gets the tiles that need to be deleted due to the detonating of the flame gem.
   * @param tile the flame gem
   * @return tiles, the list of tiles to be deleted.
   */
  public static List<Tile> getTilesToDeleteFlame(Tile tile) {
    List<Tile> tiles = new ArrayList<Tile>();
    final Point[] translations = {new Point(1,0), new Point(1,1), new Point(0,1),
                                  new Point(-1,1), new Point(-1,0), new Point(-1,-1),
                                  new Point(0,-1), new Point(1,-1)};
    int tx = tile.getX();
    int ty = tile.getY();
    tile.detonate = true;
    Tile ti = null;
    for (Point translation: translations) {
      if (board.validBorders(tx + translation.x, ty + translation.y)) {
        ti = board.getTileAt(tx + translation.x, ty + translation.y);
        if (!ti.detonate) {
          tiles.add(ti);
        }
      }
    }
    
    checkForSpecialTile(tiles);

    if (!tiles.contains(tile)) {
      tiles.add(tile);
    }
    return tiles;
  }
  
  private static void checkForSpecialTile(List<Tile> list) {
    List<Tile> res = new ArrayList<Tile>();
    
    List<Tile> tempTiles = null;
    for (Tile t : list) {
      tempTiles = null;
      if (t instanceof FlameTile && !t.detonate) {
        tempTiles = getTilesToDeleteFlame(t);
      } else if (t instanceof StarTile && !t.detonate) {
        tempTiles = getTilesToDeleteStar(t);
      }      

      if (tempTiles != null) {
        for (Tile tt: tempTiles) {
          if (!list.contains(tt)) {
            res.add(tt);
          }
        }
      }
    }
    
    GameLogic.addTiles(res,list);
  }
  
  
  /**
   * Gets the tiles that need to be deleted due to the detonating of the hypercube gem.
   * @param t1 the hypercube gem
   * @return tiles, the list of tiles to be deleted.
   */
  public static List<Tile> getTilesToDeleteHypercube(Tile t1, Tile hyper) {
    List<Tile> tiles = new ArrayList<Tile>();
    tiles.add(hyper);
    hyper.detonate = true;
    int index = t1.getIndex();
    
    Tile tempTile = null;
    for (int row = 0; row < board.getWidth(); row++) {        //loop through board
      for (int col = 0; col < board.getHeight(); col++) {
        tempTile = board.getTileAt(row, col);
        if (index == tempTile.getIndex() && !tempTile.detonate) {  
          //add tile tile if colors are the same
          tiles.add(tempTile);
        }
      }    
    }
    return tiles;
  }
  
  /**
   * Gets the tiles that need to be deleted due to the detonating of the hypercube gem.
   * @param tile the hypercube gem
   * @return tiles, the list of tiles to be deleted.
   */
  public static List<Tile> getTilesToDeleteStar(Tile tile) {
    List<Tile> tiles = new ArrayList<Tile>();
    tile.detonate = true;
    tiles.add(tile);
    
    int tx = tile.getX();
    int ty = tile.getY();
    for (int col = 0; col < board.getHeight(); col++) {
      if (col != tx && !board.getTileAt(col,ty).detonate) {
        tiles.add(board.getTileAt(col,ty));
      }
    }
    for (int row = 0; row < board.getWidth(); row++) {
      if (row != ty && !board.getTileAt(tx,row).detonate) {
        tiles.add(board.getTileAt(tx,row));
      }
    }
    checkForSpecialTile(tiles);
    return tiles;
  }
  
  public static List<Tile> getSwapTiles() {
    return swapTiles;
  }
  

  /**
   * Check if two tiles can be swapped and
   * what kind of jewel should be created based on the size of the found sequence.
   * @param t0 first tile to swap
   * @param t1 second tile to swap
   * @return true iff swapping tiles t0 and t1 results in a valid combination.
   */
  public static boolean createsCombination(Tile t0, Tile t1) {
    boolean res = false;;
    String c1 = Tile.colors[board.getTileAt(t0.getX(), t0.getY()).getIndex()];
    String c2 = Tile.colors[board.getTileAt(t1.getX(), t1.getY()).getIndex()];
    Tile tile = null;
    String color = null;
    //swap tiles to look in the rows where the tile will be in case it can be switched
    swappedTiles(t0,t1);

    for (int i = 1; i < 3; i++) {
      if (i == 1) {
        tile = t0;
        color = c1;
      }
      if (i == 2) {
        tile = t1;
        color = c2;
      }

      //check x direction
      int sum = 1;
      for (int q = tile.getX() + 1; q < board.getHeight(); q++) {
        if (Tile.colors[board.getTileAt(q, tile.getY()).getIndex()].equals(color)) {
          sum++;
        } else {
          break;
        }
      }
      for (int q = tile.getX() - 1; q >= 0; q--) {
        if (Tile.colors[board.getTileAt(q, tile.getY()).getIndex()].equals(color)) {
          sum++;
        } else {
          break;
        }
      }
      if (sum > 2 && sum < 6) {
        res = true;
      }

      //check y direction
      sum = 1;
      for (int q = tile.getY() + 1; q < board.getHeight(); q++) {
        if (Tile.colors[board.getTileAt(tile.getX(), q).getIndex()].equals(color)) {
          sum++;
        } else {
          break;
        }
      }
      for (int q = tile.getY() - 1; q >= 0; q--) {
        if (Tile.colors[board.getTileAt(tile.getX(), q).getIndex()].equals(color)) {
          sum++;
        } else {
          break;
        }
      }

      if (sum > 2 && sum < 6) {
        res = true;
      }
    }
    //swap the tiles back to original position
    swappedTiles(t0,t1);
    return res;
  }
  

  /**
   * swap the tiles in the list.
   * @param swapTiles the list of (two) tiles that should be swapped.
   */
  public static void swapTiles(List<Tile> swapTiles) {
    main.animations.setType(Animation.Type.SWAP);
    main.animations.swapAnimation.setTiles(swapTiles.get(0),swapTiles.get(1));
    main.animations.start();
    Logger.log("Swap tiles: " + swapTiles.get(0).getLoc() + ", " + swapTiles.get(1).getLoc());
  }
  
}
