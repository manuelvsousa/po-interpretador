package pex.core.expressions;

import pex.core.expressions.Expression;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public abstract class VariadicExpression extends Expression {
	private List<Expression> _expressions;

	/**
     * Retorna a expressao de indice indicado
     * @param index O index da expressao a retornar
     *
     * @return Retorna a expressao de indice indicado
     */
	public Expression getArgument(int index){
		try {
			return _expressions.get(index);
		}
		catch (Exception e) {
			System.out.println("Nao conseguiu buscar argumento");
			return null;
		}
	}

	/**
     * Associa as expressoes aos valores dados
     * @param expressions Vetor de expressoes
     */
	public void setArguments(ArrayList<Expression> expressions) {
		_expressions = new ArrayList<Expression>();
		_expressions.addAll(expressions);
	}

	/**
     * Verifica se as expressoes recebidas sao validas.
     * @return Retorna true se as expressoes recebidas forem validas
     */
	public abstract boolean verifyArguments();
}