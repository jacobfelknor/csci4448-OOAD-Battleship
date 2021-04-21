import edu.colorado.fantasticfour.game.Game;
import edu.colorado.fantasticfour.game.LocalGame;
import edu.colorado.fantasticfour.game.Player;
import edu.colorado.fantasticfour.location.Location;

import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Assert;
import org.testng.annotations.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import static junit.framework.TestCase.fail;

public class TerminalGridTest {
    Player player = new Player();

    @Test
    public void printsGrid(){
        player.tGrid.FillGrid();

    }
//        int[] tmp = new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
//        boolean playerLaser = player.hasSunkOpponentShip();
//        boolean playerSonar = player.getSonar().canUseSonar();
//        int gridRow = 10;
//        int gridCol = 10;
//        String[][] gridSurf = new String[gridRow][gridCol];
//        System.out.println();
//        System.out.println("mandem" + "                                  " + " opps");
//
//        System.out.println("   0  1  2  3  4  5  6  7  8  9" + "          " + "   0  1  2  3  4  5  6  7  8  9");
//
//        for (int i = 0; i < gridRow; i++){
//            System.out.print(tmp[i] + "  ");
//
//            for (int j = 0; j < gridCol; j++){
//
//                System.out.print(gridSurf[i][j] + "  ");
//                if (j == 9){
//                    System.out.print("|       " + tmp[i] + "  ");
//                    for (int z = 0; z < gridCol; z++ ){
//                        System.out.print(gridSurfHit[i][z] + "  ");
//                        if (z == 9) {
//                            System.out.println("|");
//
//                        }
//                    }
//                }
//            }
//        }
//        System.out.println();
//        System.out.println("           Laser Enabled: " + playerLaser + "           Sonar Enabled: " + playerSonar);
//        System.out.println();
//    }
}