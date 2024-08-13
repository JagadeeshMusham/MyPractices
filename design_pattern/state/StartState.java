package design_pattern.state;

public class StartState implements State{

	@Override
	public void doAction(Context context) {
		System.out.println("This is in start state");
		context.setState(this);
	}
}
