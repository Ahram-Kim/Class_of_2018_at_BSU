package fa.dfa;

import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;

import fa.FAInterface;
import fa.State;


/**
 * CS361 Project 1 - Deterministic Finite Automata
 * @author Ahram Kim, Ella Lee
 *
 */
public class DFA implements DFAInterface, FAInterface{

	private Set<DFAState> finalSet; //final set
	private Set<DFAState> stateSet; //State set
	private Set<Character> alp; //sigma set 
	private DFAState q0; //Start state
	private Set<DFAState> stateSetInOrder; //stateSet : just for toString()


	/**
	 * Constructor
	 */
	public DFA() {
		this.finalSet = new HashSet<DFAState>();
		this.stateSet = new HashSet<DFAState>();
		this.alp = new HashSet<Character>();
		this.stateSetInOrder = new LinkedHashSet<DFAState>();
	}

	@Override
	public boolean accepts(String s) {
		//Condition for empty string
		if(s.charAt(0) == 'e'){
			if(finalSet.contains(findState(q0.getName()))){
				return true;
			}
			else{
				return false;
			}
		}
		//starting from q0
		DFAState currentState = getToState(q0, s.charAt(0));
		try {
			DFAState next;
		for (int i = 0; i < s.length(); i++){
			next = getToState(currentState, s.charAt(i));
			currentState = next;
			if(currentState == null) {
				System.err.println("ERROR!");
			}
		}
		}
		catch (NullPointerException e)
		{
			if(finalSet.isEmpty())
			{
				return false;
			}
		}
		
		//if finalset doesn't contain the last state -> no
		return finalSet.contains(currentState);
	}

	@Override
	public DFAState getToState(DFAState from, char onSymb) {

		DFAState fromState = new DFAState(from.getName());
		DFAState toState = fromState.getTransitionState(onSymb);

		return toState;
	}

	@Override
	public void addStartState(String name) {
	
		this.q0 = new DFAState(name);
		
		if(!finalSet.contains(findState(name))){
			stateSet.add(q0); //add a0 to Q
			stateSetInOrder.add(q0);
		}
	}

	@Override
	public void addState(String name) {

		stateSet.add(new DFAState (name));
		stateSetInOrder.add(new DFAState(name));
	}

	@Override
	public void addFinalState(String name) {

		DFAState state = new DFAState(name);
		finalSet.add(state);
		stateSet.add(state);
		stateSetInOrder.add(new DFAState(name));
	}

	/**
	 * This function is finding a state in Q with a name.
	 * @param name
	 * @return DFAState
	 */
	private DFAState findState(String name){

		DFAState ret = null;
		for(DFAState s : stateSet){
			if(s.getName().equals(name)){
				ret = s;
			}
		}
		return ret;
	}

	@Override
	public void addTransition(String fromState, char onSymb, String toState)
	{	
		//add onSymb to alphabet
		if (!alp.contains(onSymb)){
			alp.add(onSymb); 
		}

		DFAState firstState = findState(fromState);
		DFAState secondState = findState(toState);
		firstState.setTransition(onSymb, secondState);

	}

	@Override
	public Set<? extends State> getStates() {
		return stateSet;
	}

	@Override
	public Set<? extends State> getFinalStates() {

		return finalSet;
	}

	@Override
	public State getStartState() {

		return q0;
	}

	@Override
	public Set<Character> getABC() {
		return alp;
	}


	/**
	 * toString method 
	 * Sample output:
	 * Q = { b a }
	 * Sigma = { 0 1 }
	 * delta =
	 *				0	 1
	 *			b	a	 b
	 *			a	a	 b
	 * q0 = a
	 * F = { b }
	 */
	public String toString() {
		StringBuilder entireSt = new StringBuilder();

		// Q
		StringBuilder statesSt = new StringBuilder();

		for(DFAState st : stateSetInOrder){
			statesSt.append(" ");
			statesSt.append(st.getName());
		}

		String q = "Q = {"+statesSt.toString()+" }";
		entireSt.append(q);

		// Sigma
		StringBuilder sigmaSt = new StringBuilder();
		for(Character al : alp){
			sigmaSt.append(" ");
			sigmaSt.append(al);
		}
		String sig = "Sigma = {"+sigmaSt.toString()+" }";
		entireSt.append("\n");
		entireSt.append(sig);

		// delta
		StringBuilder deltaSt = new StringBuilder();
		Iterator<Character> iterAlp = alp.iterator();

		deltaSt.append("delta = " + "\n");
		deltaSt.append("\t\t" + iterAlp.next() + "\t" + iterAlp.next() + "\n");

		for(DFAState s : stateSetInOrder) 
		{
			deltaSt.append("\t" + s.getName() + "\t");
			for(Character y : alp)
			{
				deltaSt.append(findState(s.getName()).getTransitionState(y)+"\t");
			}
			deltaSt.delete(deltaSt.length()-1, deltaSt.length());
			deltaSt.append("\n");
		}
		entireSt.append("\n");
		entireSt.append(deltaSt);


		//q0
		String q0 = "q0 = "+this.q0.getName();
		entireSt.append(q0);

		//F
		StringBuilder fSt = new StringBuilder();
		for(int i = 0; i < finalSet.size(); i++){
			fSt.append(" ");
			fSt.append(stateSetInOrder.toArray()[i]);
		}

		String f = "F = {"+fSt.toString()+" }";
		entireSt.append("\n");
		entireSt.append(f);
		entireSt.append("\n");

		return entireSt.toString();

	}


}