package fa.nfa;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Set;
import fa.State;

/**
 * CS361 P2
 * NFAState Class
 * @author Ahram Kim & Ella Lee
 *
 */
public class NFAState extends State{

	private HashMap<Character,Set<NFAState>> delta;
	private boolean isFinal;

	/**
	 * Constructor
	 * @param name - name of NFA State
	 */
	public NFAState(String name){
		initDefault(name);
		isFinal = false;
	}

	/**
	 * Constructor for final NFAState
	 * @param name
	 * @param isFinal
	 */
	public NFAState(String name, boolean isFinal){
		initDefault(name);
		this.isFinal = isFinal;
	}

	/**
	 * Sets name and instantiates delta for NFAState
	 * @param name-name of NFAState
	 */
	private void initDefault(String name ){
		this.name = name;
		delta = new HashMap<Character, Set<NFAState>>();
	}

	/**
	 * boolean if NFAState is final or not
	 * @return isFinal
	 */
	public boolean isFinal(){
		return isFinal;
	}

	/**
	 * transition with alphabet to another NFAState
	 * @param onSymb
	 * @param toState
	 */
	public void addTransition(char onSymb, NFAState toState){
		if(delta.get(onSymb)==null){
			Set<NFAState> trans = new LinkedHashSet<NFAState>();
			trans.add(toState);
			delta.put(onSymb, trans);

		}
		delta.get(onSymb).add(toState) ;
	}

	/**
	 * every possible transition 
	 * @param symb
	 * @return set
	 */
	public Set<NFAState> getTo(char symb){
		return delta.get(symb);
	}
}