package fa.dfa;

import java.util.HashMap;

import fa.State;

/**
 * This class extends State class.
 * The main function for this class is adding transitions.
 * 
 * @author Ahram Kim, Ella Lee
 *
 */
public class DFAState extends State {
	
	protected HashMap<Character, DFAState> transitions;
	
	/**
	 * Constructor
	 * @param stateName
	 */
	public DFAState(String stateName){
		name = stateName;
		this.transitions = new HashMap<Character, DFAState>();
	}
	
	public DFAState() {
		transitions = new HashMap<Character, DFAState>();
	}
	
	/**
	 * Adding the transition
	 * @param alphabet
	 * @param state 
	 */
	public void setTransition(char alphabet, DFAState state){
		transitions.put(alphabet, state);
	}
	
	/**
	 * Get the transition according to the alphabet
	 * @param alphabet
	 * @return DFAState
	 */
	public DFAState getTransitionState(char alphabet){
		return transitions.get(alphabet);
	}
	
	/**
	 * Get the transition 
	 * @return HashMap
	 */
	public HashMap<Character, DFAState> getTransition(){
		return transitions;
	}

}