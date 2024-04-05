package design_pattern.state;

public class StateDemo {
	Context context = new Context();
	
	StartState ss = new StartState();
	ss.doAction(context);
	
}
