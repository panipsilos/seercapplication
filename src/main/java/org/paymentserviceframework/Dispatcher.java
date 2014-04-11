package org.paymentserviceframework;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jast.ast.ASTError;
import org.jast.ast.ASTReader;
import org.jast.ast.NodeError;
import org.paymentserviceframework.actions.IAction;
import org.paymentserviceframework.states.State;
import org.paymentserviceframework.states.StateMachine;

public class Dispatcher {
	
	private static Dispatcher dispatcher = null;
	
	// keeps track of the states and the information about the action to be called each time.
	private StateMachine stateMachine;
	
	private State currentState;
	
	private int stateIndex;
		
	public State getCurrentState() {
		return currentState;
	}

	public void setCurrentState(State state) {
		this.currentState = state;
	}

	/*
	 * singleton pattern 
	 */
	public static Dispatcher getDispatcher()
	{
		if(dispatcher==null)
		{
			dispatcher = new Dispatcher();
		}
		return dispatcher;
	}
	
	public Dispatcher()
	{
		//parse the State.xml and populates the stateTable. Then he knows at each time which action to call.
	    File xmlFile = new File("C:\\StateMachine.xml");  // Or whatever file
	    ASTReader reader = null;
		try {
			reader = new ASTReader(xmlFile);
			reader.usePackage("org.paymentserviceframework.states");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    try {
			 stateMachine = (StateMachine) reader.readDocument();
			
		} catch (IOException | ASTError | NodeError e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    try {
			reader.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		
		//set currentState to the first State as it appears in the State.xml (first key of the hashMap )
		this.setCurrentState(stateMachine.getState(stateIndex));
	}
	
	/*
	 * dispatch request. According to current state, it chooses the right action to perform the task.
	 */
	public void dispatchRequest(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, InstantiationException, IllegalAccessException
	{
		// get current action
		Class clazz = Class.forName(this.getCurrentState().getAction());
		IAction action = (IAction)clazz.newInstance();
		
		// execute action
		action.execute(request, response);
		
		//update currentState, check if the process has finished
		stateIndex++;
		if(stateIndex < stateMachine.getStates().size())
		{
			this.setCurrentState(stateMachine.getState(stateIndex));
		}
		else{
			System.out.println("state machine reached finish state");
		}
		
	}
}
