package StateDP;

public class StopState implements State {

        public void doAction(Context context) {
            System.out.println("Current state: stop state");
            context.setState(this);
        }

        public String toString() {
            return "Stop State";
        }
    }