package compilador.core.operators.base;

import compilador.core.expressions.Expression;

public abstract class UnaryExpression extends Expression
{
	private Expression _expression_1;

	public abstract Expression getArgument();

	public abstract void setArgument(Expression expression);

	public abstract boolean verifyArgument();
}