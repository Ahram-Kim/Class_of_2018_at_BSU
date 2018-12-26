package fa.nfa;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import fa.State;
import fa.dfa.DFA;

/**
 * CS361 P2
 * Nondeterministic Finite Automata
 * This class implements fa.FAInterface and fa.nfa.NFAInterface 
 * 
 * @author Ahram Kim & Ella Lee
 *
 */
public class NFA implements fa.FAInterface, fa.nfa.NFAInterface {

	private NFAState start; //start state
	private Set<Character> abc; //alphabets
	private Set<NFAState> states; //sets

	/**
	 * Constructor
	 */
	public NFA() {
		abc = new LinkedHashSet<Character>();
		states = new LinkedHashSet<NFAState>();
	}

	/**
	 * Computes an equivalent DFA
	 */
	public DFA getDFA() {
		//initiate variables
		DFA dfa = new DFA();
		List<Set<NFAState>> search = new LinkedList<>();
		Set<Set<NFAState>> check = new LinkedHashSet<>();
		HashMap<Set<NFAState>, String> dfaStates = new HashMap<>();		
		Set<NFAState> start = eClosure(getStart());
		
		//taking care of start state first!
		search.add(start);
		dfa.addStartState(start.toString());
		dfaStates.put(start, start.toString());
		if (containsFinal(start)) {
			dfa.addFinalState(start.toString());
		}
				
		while (!search.isEmpty()) {
			Set<NFAState> state = search.remove(0);
			check.add(state);

			for (char c : abc) {
				Set<NFAState> temp = new LinkedHashSet<>();
				for (NFAState states : state) {
					if (states.getTo(c) != null) {
						for (NFAState toState : states.getTo(c)) {
							temp.addAll(eClosure(toState));
						}
					}
				}
				
				//if toState is part of finals 
				if (!check.contains(temp)) {
					if (containsFinal(temp)) {
						dfaStates.put(temp, temp.toString());
						dfa.addFinalState(dfaStates.get(temp));
						check.add(temp);
					}
					
					//if toState is not part of finals
					else {
						dfaStates.put(temp, temp.toString());
						dfa.addState(dfaStates.get(temp));
						check.add(temp);
					}
					
					//Add new state to search list
					if (!search.contains(temp))
						search.add(temp);
				}
				
				//Add transition to new state
				dfa.addTransition(dfaStates.get(state), c, dfaStates.get(temp));
			}
		}
		return dfa;

	}
	
	/**
	 * Add start state to the state set
	 */
	public void addStartState(String name) {
		start = getState(name);
		if (start == null) {
			start = new NFAState(name);
			states.add(start);
		}
	}

	/**
	 * Add final states to the state set
	 */
	public void addFinalState(String name) {
		NFAState finals = new NFAState(name, true); //using final check constructor
		states.add(finals);
	}
	
	/**
	 * Get start state
	 * @return start state
	 */
	private NFAState getStart() {
		return start;
	}

	/**
	 * Add state with name
	 */
	public void addState(String name) {
		NFAState state = new NFAState(name);
		states.add(state);

	}
	
	/**
	 * Get state with corresponding name
	 * @param name
	 * @return NFAstate
	 */
	private NFAState getState(String name) {
		NFAState ret = null;
		for (NFAState s : states) {
			if (s.getName().equals(name)) {
				ret = s;
				break;
			}
		}
		return ret;
	}

	
	/**
	 * Add transition from one state to another state with alphabets
	 */
	public void addTransition(String fromState, char onSymb, String toState) {
		
		(getState(fromState)).addTransition(onSymb, getState(toState));
		if (!abc.contains(onSymb) && onSymb != 'e') {
			abc.add(onSymb);
		}

	}


	/**
	 * check if state set has final
	 * @param states
	 * @return final
	 */
	private boolean containsFinal(Set<NFAState> states) {
		
		boolean hasFinal = false;
		for (NFAState state : states) {
			if (state.isFinal()) {
				hasFinal = true;
				break;
			}
		}
		return hasFinal;
	}

	@Override
	public Set<NFAState> getToState(NFAState from, char onSymb) {
		return from.getTo(onSymb);
	}

	@Override
	public Set<NFAState> eClosure(NFAState s) {
		List<NFAState> temp = new LinkedList<>();
		Set<NFAState> closure = new LinkedHashSet<>();
		temp.add(s);
		closure.add(s);

		while (!temp.isEmpty()) {
			NFAState n = temp.remove(0); //check one by one
			closure.add(n); //itself
			
			if (n.getTo('e') == null) {
				return closure;
			}
			
			for (NFAState states : n.getTo('e')) 
				if (!closure.contains(states)) {
					temp.add(states);
				}
		}
		return closure;
	}

	@Override
	public Set<State> getStates() {
		Set<State> ret = new LinkedHashSet<State>();
		ret.addAll(states);
		return ret;
	}

	@Override
	public Set<State> getFinalStates() {
		Set<State> ret = new LinkedHashSet<State>();
		for (NFAState s : states) {
			if (s.isFinal()) {
				ret.add(s);
			}
		}
		return ret;
	}

	@Override
	public State getStartState() {
		return start;
	}

	@Override
	public Set<Character> getABC() {
		return abc;
	}

}