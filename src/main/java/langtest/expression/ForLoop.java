package langtest.expression;

import java.util.ArrayList;

import processing.core.PApplet;
import langtest.expression.math.Math;
import langtest.expression.math.MathBinding;
import langtest.expression.math.Number;

public class ForLoop extends Expression {
	private MathBinding iterator;
	private Math amount;
	private Expression expression;
	
	public ForLoop(MathBinding iterator, Math amount, Expression expression) {
		super();
		this.iterator = iterator;
		this.amount = amount;
		this.expression = expression;
	}
	
	public ArrayList<Expression> getChildren() {
		ArrayList<Expression> res = new ArrayList<Expression>();
		res.add(iterator);
		res.add(amount);
		res.add(expression);
		return res;
	}
	
	public void draw(PApplet p) {
		ArrayList<MathBinding> iteratorOccurences = new ArrayList<MathBinding>();
		for(Expression x : expression.getSubTree()) {
			if(x instanceof MathBinding && ((MathBinding) x).getIdentifier().equals(iterator.getIdentifier())) 
				iteratorOccurences.add((MathBinding) x);
		}
		for (int i = 0; i < amount.eval(); i++) {
			for(MathBinding x : iteratorOccurences) x.setValue(new Number(i));
			expression.draw(p);
		}
	}
	
	public MathBinding getIterator() {
		return iterator;
	}
	public Math getAmount() {
		return amount;
	}
	public Expression getExpression() {
		return expression;
	}

	@Override
	public String toString() {
		String res = "for\n";
		String body = iterator.toString() + "\n" + amount.toString() + "\n" + expression.toString();
		for(String x : body.split("\n")) res += "\t" + x + "\n";
		return res;
	}
}
