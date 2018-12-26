package re;

import fa.State;
import fa.nfa.NFA;
import fa.nfa.NFAState;

/**
 * 11.29.2018
 * CS361 Project 1 Part 3 (Regular Expressions)
 * 
 * @author Ella Lee, Ahram Kim
 */
public class RE implements REInterface {

    private String input; 
    private int stateCount = 0; 

    /**
     * Constructor
     * 
     * @param regEx
     */
    public RE(String regEx){
        this.input = regEx;
    }

    @Override
    public NFA getNFA() {
        return regex();
    }

    /**
     * Returns the next item of input without consuming it
     * 
     * @return next item
     */
    private char peek(){
        return input.charAt(0);
    }

    /**
     * Consumes the next item of input, failing if not equal to item
     * 
     * @param c
     */
    private void eat(char c){
        if(peek() == c){
            input = input.substring(1);
        } else {
            throw new RuntimeException("Expected: " + c + "; got: " + peek());
        }
    }

   /**
    * Returns the next item of input and consumes it
    * 
    * @return next item
    */
    private char next() {
        char c = peek();
        eat(c);
        return c;
    }

    
    /**
     * If more items exist
     * 
     * @return true if more
     */
    private boolean more() {
        return input.length() > 0;
    }

    
    /**
     * For regex() method, we know that we must parse at least
     * one term, and whether we parse another depends only on
     * what we find afterward
     * 
     * @return NFA
     */
    private NFA regex() {
        NFA term = term();
        if(more() && peek() == '|'){
            eat('|');
            return choice(term, regex());
        } else {
            return term;
        }
    }
    
    
	/**
	 * Methods for implementing '|'
	 * 
	 * @param thisOne
	 * @param thatOne
	 * @return NFA
	 */
    private NFA choice(NFA thisOne, NFA thatOne) {
        NFAState state1 = (NFAState) thisOne.getStartState();
        NFAState state2 = (NFAState) thatOne.getStartState();

        // Combine thisOne and thatOne
        thisOne.addNFAStates(thatOne.getStates());
        thisOne.addAbc(thatOne.getABC());

        // Add new state to link empty string with thisOne
        String name = Integer.toString(stateCount++);
        thisOne.addStartState(name);
        thisOne.addTransition(name, 'e', state1.getName());
        thisOne.addTransition(name, 'e', state2.getName());
        return thisOne;
    }
    


    /**
     * term() has to check that it has not reached the boundary 
     * of a term or the end of the input
     * 
     * @return NFA
     */
    private NFA term() {
        NFA factor = new NFA();
        String name = Integer.toString(stateCount++);
        
        factor.addStartState(name);
        factor.addFinalState(name);

        while (more() && peek() != ')' && peek() != '|'){
            NFA next = factor();
            factor =  sequence(factor, next);
        }
        return factor;
    }
    

    /**
     * Method for implementing concatenation
     * 
     * @param first
     * @param second
     * @return NFA
     */
	private NFA sequence(NFA first, NFA second) {

        for (State nfa : first.getFinalStates()) {
            ((NFAState) nfa).setNonFinal();
            ((NFAState) nfa).addTransition('e', (NFAState) second.getStartState());
        }

        first.addNFAStates(second.getStates());
		first.addAbc(second.getABC());
		return first;
	}
    

	/**
	 * Method for checking '*'
	 * 
	 * @return NFA
	 */
    private NFA factor() {
        NFA base = base();
        while (more() && peek() == '*'){
            eat('*');
            base = repetition(base);
        }
        return base;
    }
    

    /**
     * Method for implementing '*' operation
     * 
     * @param nfa
     * @return NFA
     */
	private NFA repetition(NFA nfa) {
		NFAState startState = (NFAState) nfa.getStartState();
		
		// From final state to one after start state
		for(State finals : nfa.getFinalStates()){
		    nfa.addTransition(finals.getName(), 'e', startState.getName());
        }

		// Creating a new start state
		String newStart = Integer.toString(stateCount++);
		nfa.addStartState(newStart);
		nfa.addFinalState(newStart);
		nfa.addTransition(newStart, 'e', startState.getName());
		return nfa;
	}
    
    
    /**
     * The implementation of base() checks to see which of the 
     * cases it has encountered
     * 
     * @return NFA
     */
    private NFA base(){
        NFA nfa;
        switch (peek()){
            case '(':
                eat('(');
                nfa = regex();
                eat(')');
                return nfa;

            default:
            	return primitive(next());
        }
    }
    
    /**
     * Helper function for base
     * 
     * @param c
     * @return NFA
     */
    private NFA primitive(char c) {
    	NFA nfa = new NFA();
        String sName = Integer.toString(stateCount++);
        nfa.addStartState(sName);
        String fName = Integer.toString(stateCount++);
        nfa.addFinalState(fName);
        nfa.addTransition(sName, c, fName);
        return nfa;
	}


}