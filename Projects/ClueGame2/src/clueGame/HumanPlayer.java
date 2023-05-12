package clueGame;

import java.util.Set;

/* 
 * HumanPlayer Class: This class extends the Player class. This is for the human 
 * player who makes their own decisions
 * It also updates the player's hand when the cards are dealt.
 * Authors: Kirana Irfano & Denisha Saviela
 * Collaborators: None
 * Date: 3/29/2023
 * Sources: None
 */

public class HumanPlayer extends Player {
	private boolean moved;
	private boolean isTurn;
	/*
	 * constructor
	 */
	public HumanPlayer(String name, String color, String type, BoardCell location) {
		super(name, color, type, location);
	}

	@Override
	public BoardCell doMove(Set<BoardCell> target, Board board) {
		// TODO Auto-generated method stub
		return this.location;
	}
	
	public boolean isMoved() {
		return moved;
	}

	public void setMoved(boolean moved) {
		this.moved = moved;
	}

	public boolean isTurn() {
		return isTurn;
	}

	public void setTurn(boolean isTurn) {
		this.isTurn = isTurn;
	}

	@Override
	protected Solution createSuggestion(Board board) {
		return null;
	}


}
