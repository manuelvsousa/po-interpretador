package pex.core.expressions.operators;

import pex.core.Visitor;
import pex.core.WrongTypeException;

import pex.core.expressions.TrenaryExpression;
import pex.core.expressions.Expression;
import pex.core.expressions.LiteralInt;
import pex.core.expressions.LiteralString;

/**
 * Classe usada para representar um operador If
 *
 * @author Manuel e Goncalo
 */
public class If extends TrenaryExpression {
	/** Serial number for serialization. */
	private static final long serialVersionUID = 201608241029L;
	/**
	 * Contrutor: Inicia as expressoes como as expressoes recebidas
	 *
	 * @param exp_1 Expressao a associar a _expressao_1
	 * @param exp_2 Expressao a associar a _expressao_2
	 * @param exp_3 Expressao a associar a _expressao_3
	 */
	public If(Expression exp_1, Expression exp_2, Expression exp_3) {
		super.setArguments(exp_1, exp_2, exp_3);
		super.setOperatorName("if");
	}

	public Expression accept(Visitor v) throws WrongTypeException {
		return v.visit(this);
	}
}