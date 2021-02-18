import java.awt.*;
import java.util.*;
/**
 * Plays a game of chess.
 * 
 * @author Max Lee
 * @version 05-05-19
 */
public class Game
{
    /**
     * Creates an instance of a Game
     */
    public Game()
    {

    }

    /**
     * Main method to run the game.
     * @param args the arguments
     */
    public static void main (String [] args)
    {
        Board board = new Board();
        Piece blackKing = new King (Color.BLACK, 
                "/Users/maxlee/Downloads/chessfiles/black_king.gif/");
        blackKing.putSelfInGrid(board, new Location(0, 4));

        Piece whiteKing = new King (Color.WHITE, 
                "/Users/maxlee/Downloads/chessfiles/white_king.gif/");
        whiteKing.putSelfInGrid(board, new Location(7, 4));

        Piece blackRook = new Rook (Color.BLACK, 
                "/Users/maxlee/Downloads/chessfiles/black_rook.gif/");
        blackRook.putSelfInGrid(board, new Location(0, 0));

        Piece blackRook2 = new Rook (Color.BLACK, 
                "/Users/maxlee/Downloads/chessfiles/black_rook.gif/");
        blackRook2.putSelfInGrid(board, new Location(0, 7));

        Piece whiteRook = new Rook (Color.WHITE, 
                "/Users/maxlee/Downloads/chessfiles/white_rook.gif/");
        whiteRook.putSelfInGrid(board, new Location(7, 0));

        Piece whiteRook2 = new Rook (Color.WHITE, 
                "/Users/maxlee/Downloads/chessfiles/white_rook.gif/");
        whiteRook2.putSelfInGrid(board, new Location(7, 7));

        for (int i = 0; i < 8; i++)
        {
            Piece whitePawn = new Pawn (Color.WHITE, 
                    "/Users/maxlee/Downloads/chessfiles/white_pawn.gif/");
            whitePawn.putSelfInGrid(board, new Location(6, i));
        }

        for (int i = 0; i < 8; i++)
        {
            Piece blackPawn = new Pawn (Color.BLACK, 
                    "/Users/maxlee/Downloads/chessfiles/black_pawn.gif/");
            blackPawn.putSelfInGrid(board, new Location(1, i));
        }

        for (int i =1; i <= 6; i+=5)
        {
            Piece whiteKnight = new Knight (Color.WHITE, 
                    "/Users/maxlee/Downloads/chessfiles/white_knight.gif/");
            Piece blackKnight = new Knight (Color.BLACK, 
                    "/Users/maxlee/Downloads/chessfiles/black_knight.gif/");
            whiteKnight.putSelfInGrid(board, new Location(7, i));
            blackKnight.putSelfInGrid(board, new Location(0, i));
        }

        for (int i = 2; i <=5; i+=3)
        {
            Piece whiteBishop = new Bishop (Color.WHITE, 
                    "/Users/maxlee/Downloads/chessfiles/white_bishop.gif/");
            Piece blackBishop = new Bishop (Color.BLACK, 
                    "/Users/maxlee/Downloads/chessfiles/black_bishop.gif/");
            whiteBishop.putSelfInGrid(board, new Location(7, i));
            blackBishop.putSelfInGrid(board, new Location(0, i));
        }

        Piece whiteQueen = new Queen (Color.WHITE,
                "/Users/maxlee/Downloads/chessfiles/white_queen.gif/");
        whiteQueen.putSelfInGrid(board, new Location(7, 3));

        Piece blackQueen = new Queen (Color.BLACK, 
                "/Users/maxlee/Downloads/chessfiles/black_queen.gif/");
        blackQueen.putSelfInGrid(board, new Location (0, 3));

        BoardDisplay display = new BoardDisplay(board);
        //         ArrayList<Location> possible = blackKing.destinations();
        //         for (int i = 0; i < possible.size(); i++)
        //         {
        //             display.setColor(possible.get(i), Color.WHITE);
        //         }
        //         
        //         ArrayList<Location> king = whiteKing.destinations();
        //         for (int i = 0; i < king.size(); i++)
        //         {
        //             display.setColor(king.get(i), Color.WHITE);
        //         }
        //         
        //         ArrayList<Location> rook = blackRook.destinations();
        //         for (int i = 0; i < rook.size(); i++)
        //         {
        //             display.setColor(rook.get(i), Color.WHITE);
        //         }
        // //         
        //         ArrayList<Location> rook2 = whiteRook.destinations();
        //         for (int i = 0; i < rook2.size(); i++)
        //         {
        //             display.setColor(rook2.get(i), Color.WHITE);
        //         }
        //         
        //         ArrayList<Location> rook3 = whiteRook2.destinations();
        //         for (int i = 0; i < rook3.size(); i++)
        //         {
        //             display.setColor(rook3.get(i), Color.WHITE);
        //         }
        //         ArrayList<Location> rook4 = blackRook2.destinations();
        //         for (int i = 0; i < rook4.size(); i++)
        //         {
        //             display.setColor(rook4.get(i), Color.WHITE);
        //         }
        //ArrayList<Move> whiteMoves = board.allMoves(Color.WHITE);
        //for (int i = 0; i < whiteMoves.size(); i++)
        //{
        //     display.setColor(whiteMoves.get(i).getDestination(), Color.WHITE);
        //}

        //ArrayList<Move> blackMoves = board.allMoves(Color.BLACK);
        //for (int i = 0; i < blackMoves.size(); i++)
        //{
        //     display.setColor(blackMoves.get(i).getDestination(), Color.WHITE);
        //}

        Player smart = new SmartPlayer(board, "player1", Color.BLACK);
        //         display.setColor(random.nextMove().getDestination(), Color.WHITE);

        Player human = new SmartPlayer(board, "max", Color.WHITE);

        play (board, display, human, smart);
    }

    /**
     * Sets display title as the player who is making the move.
     * Executes player's moves and shows previous location as black
     * and current location as white.
     * @param board the board the game is played on
     * @param display the display for the game
     * @param player the current player playing in the game
     */
    private static void nextTurn (Board board, BoardDisplay display, Player player)
    {
        try
        {
            Thread.sleep(500);
            display.setTitle(player.getName());
            Move next = player.nextMove();
            board.executeMove(next);
            display.clearColors();
            display.setColor(next.getSource(), Color.BLACK);
            display.setColor(next.getDestination(), Color.WHITE);
        }
        catch(InterruptedException e)
        {
            System.out.print("interrupted!");
        }
    }

    /**
     * Executes moves, alternating players.
     * @param board the board the game is played on
     * @param display the display for the game
     * @param white the player who is white
     * @param black the player who is black
     */
    public static void play (Board board, BoardDisplay display, Player white, Player black)
    {
        while (true)
        {
            nextTurn(board, display, white);
            nextTurn(board, display, black);
        }
    }
}
