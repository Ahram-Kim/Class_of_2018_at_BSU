package fa.dfa;

import java.util.LinkedHashSet;
import java.util.Set;

import fa.FAInterface;
import fa.State;

/**
 * Implementation of DFA class to be used
 * in p1p2
 * @author elenasherman
 *
 */
public class DFA implements FAInterface, DFAInterface{
	private Set<DFAState> states;
	private DFAState start;
	private Set<Character> ordAbc;
	
	public DFA(){
		states = new LinkedHashSet<DFAState>();
		ordAbc = new LinkedHashSet<Character>();
	}
	
	/* (non-Javadoc)
	 * @see p1.DFAInterface#addStartState(java.lang.String)
	 */
	@Override
	public void addStartState(String name){
		DFAState s = getState(name);
		if(s == null){
			s = new DFAState(name);
			addState(s);
		}
		start = s;
	}
	/* (non-Javadoc)
	 * @see p1.DFAInterface#addState(java.lang.String)
	 */
	@Override
	public void addState(String name){
		DFAState s = new DFAState(name);
		addState(s);
	}
	
	/* (non-Javadoc)
	 * @see p1.DFAInterface#addFinalState(java.lang.String)
	 */
	@Override
	public void addFinalState(String name){
		DFAState s = new DFAState(name, true);
		addState(s);
	}
	
	private void addState(DFAState s){
		states.add(s);
	}
	
	/* (non-Javadoc)
	 * @see p1.DFAInterface#addTransition(p1.State, char, p1.State)
	 */
	@Override
	public void addTransition(String fromState, char onSymb, String toState){
		
		(getState(fromState)).addTransition(onSymb, getState(toState));
		if(!ordAbc.contains(onSymb)){
			ordAbc.add(onSymb);
		}
	}

	private DFAState getState(String name){
		DFAState ret = null;
		for(DFAState s : states){
			if(s.getName().equals(name)){
				ret = s;
				break;
			}
		}
		return ret;
	}
	
	/** (non-Javadoc)
	 * @see p1.DFAInterface#toString()
	 **/
	@Override
	public String toString(){
		
		String s = "Q = { ";
		String fStates = "F = { ";
		for(DFAState state : states){
			s += state.toString();
			s +=" ";
			if(state.isFinal()){
				fStates +=state.toString();
				fStates += " ";
			}
		}
		s += "}\n";
		fStates += "}\n";
		s += "Sigma = { ";
		for(char c : ordAbc){
			s += c + " ";
		}
		s += "}\n";
		//create transition table
		s += "delta =\n"+String.format("%10s", "");;
		for(char c : ordAbc){
			s += String.format("%10s", c);
		}
		s+="\n";
		for(DFAState state : states){
			s += String.format("%10s",state.toString());
			for(char c : ordAbc){
				s += String.format("%10s", state.getTo(c).toString());
			}
			s+="\n";
		}
		//start state
		s += "q0 = " + start + "\n";
		s += fStates;
		return s;
	}

	@Override
	public boolean accepts(String input) {
		boolean ret = false;
		char[] inputString = input.toCharArray();
		DFAState currState = start;
		//iterate over the chars
		if(!(inputString.length==1 && inputString[0] == 'e')){
			for(char c : inputString){
				currState = currState.getTo(c);
			}
		}
		if(currState.isFinal()){
			ret = true;
		} 
		return ret;
	}

	@Override
	public Set<DFAState> getStates() {
		return states;
	}

	@Override
	public Set<DFAState> getFinalStates() {
		Set<DFAState> ret = new LinkedHashSet<DFAState>();
		for(DFAState s : states){
			if(s.isFinal()){
				ret.add(s);
			}
		}
		return ret;
	}

	@Override
	public DFAState getStartState() {
		return start;
	}

	@Override
	public DFAState getToState(DFAState from, char onSymb) {
		return from.getTo(onSymb);
	}

	@Override
	public Set<Character> getABC() {
		return ordAbc;
	}
}
