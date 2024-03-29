package pex.core.expressions;

import pex.core.Program;
import pex.core.expressions.CompositeExpression;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public abstract class VariadicExpression extends CompositeExpression {
	private List<Expression> _expressions;
	private Program _prog;

	/**
	 * Retorna a expressao de indice indicado
	 * @param index O index da expressao a retornar
	 *
	 * @return Expression Retorna a expressao de indice indicado
	 */
	public List<Expression> getArguments() {
		return _expressions;
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
	 * Associa um novo programa
	 *
	 * @param prog O programa a associar
	 */
	public void setProgram(Program prog) {
		_prog = prog;
	}

	/**
	 * Devolve o programa
	 *
	 * @return Program O programa associado
	 */
	public Program getProgram() {
		return _prog;
	}

	/**
	 * Retorna o nome do operador
	 *
	 * @return String Retorna uma string que representa o nome do operador
	 */
	@Override
	public String getAsText() {
		StringBuilder sb = new StringBuilder();
		sb.append("(" + getOperatorName());
		for (Expression exp : getArguments()) {
			sb.append(" " + exp.getAsText());
		}
		sb.append(")");
		return sb.toString();
	}
}