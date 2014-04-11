package org.paymentserviceframework.states;

import java.util.ArrayList;
import java.util.List;

public class StateMachine {

	private List<State> states;
	
	public StateMachine () {
		states = new ArrayList<State>();
	}
	
	public StateMachine addState(State state) {
		states.add(state);
		return this;
	}
	
	public State getState(int i){
		return states.get(i);
	}
	
	public List<State> getStates(){
		return states;
	}
	
}
