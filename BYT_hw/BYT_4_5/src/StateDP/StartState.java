package StateDP;

    public class StartState implements State {

        public void doAction(Context context) {
            System.out.println("Current state: start state");
            context.setState(this);
        }

        public String toString() {
            return "Start State";
        }
    }