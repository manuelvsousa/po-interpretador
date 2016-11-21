package pex.core.expressions.operators;

import pex.core.expressions.Expression;

/**
 * Classe usada para representar um operador ReadI
 *
 * @author Manuel e Goncalo
 */
public class ReadI extends Expression {

	public ReadI() {

	}

	/**
	 * Retorna o nome do operador
	 *
	 * @return String Retorna uma string que representa o nome do operador
	 */
	public String getAsText() {
		return "readi";
	}

	/**
	 * Retorna o valor da expressao
	 *
	 * @return Expression Retorna uma expressao que representa o valor avaliado
	 */
	public Expression evaluate() {
		return null;
	}
}