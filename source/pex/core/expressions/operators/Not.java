package pex.core.expressions.operators;

import pex.core.expressions.UnaryExpression;
import pex.core.expressions.Expression;

/**
 * Classe usada para representar um operador Not
 *
 * @author Manuel e Goncalo
 */
public class Not extends UnaryExpression {

	/**
     * Contrutor: Inicia a expressao como a expressao recebida
     * 
     * @param exp Expressao a associar a _expressao
     */
	public Not(Expression exp) {
		super.setArgument(exp);
	}

	/**
     * Retorna a expressao
     *
     * @return Retorna uma expressao que representa o valor da expressao
     */
	@Override
	public Expression getArgument() {
		return super.getArgument();
	}

	/**
     * Verifica se a expressao recebida e valida.
     * @return Retorna true se a expressao recebida for valida.
     */
	@Override
	public boolean verifyArgument() {
		return true;
	}

	/**
     * Retorna o nome do operador
     *
     * @return Retorna uma string que representa o nome do operador
     */
	@Override
	public String getAsText() {
		return "Not";
	}

	/**
     * Retorna o valor da expressao
     *
     * @return Retorna uma expressao que representa o valor avaliado
     */
	@Override
	public Expression evaluate() {
		return super.getArgument();
	}
}