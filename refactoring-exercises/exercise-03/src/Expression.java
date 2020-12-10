public interface Expression {

	// bad smells:
	// switch statement (replace conditional with polymorphism, replace polymorphism with extract interface)
	// divergent change

	int evaluate();
}

class Constant implements Expression {

	private int constant;

	public Constant(int constant) {
		this.constant = constant;
	}

	@Override
	public int evaluate() {
		return constant;
	}
}

class Add implements Expression {

	private Expression left;
	private Expression right;

	public Add(Expression left, Expression right) {
		this.left = left;
		this.right = right;
	}

	@Override
	public int evaluate() {
		return left.evaluate() + right.evaluate();
	}
}

class Subtract implements Expression {

	private Expression left;
	private Expression right;

	public Subtract(Expression left, Expression right) {
		this.left = left;
		this.right = right;
	}

	@Override
	public int evaluate() {
		return left.evaluate() - right.evaluate();
	}
}

class Divide implements Expression {

	private Expression left;
	private Expression right;

	public Divide(Expression left, Expression right) {
		this.left = left;
		this.right = right;
	}

	@Override
	public int evaluate() {
		return left.evaluate() / right.evaluate();
	}
}

class Multiply implements Expression {

	private Expression left;
	private Expression right;

	public Multiply(Expression left, Expression right) {
		this.left = left;
		this.right = right;
	}

	@Override
	public int evaluate() {
		return left.evaluate() * right.evaluate();
	}
}