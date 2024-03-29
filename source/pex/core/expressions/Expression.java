package pex.core.expressions;

import pex.core.Visitor;
import pex.core.Element;

import java.io.Serializable;
/**
 * Classe usada para representar uma expressao no programa
 *
 * @author Manuel e Goncalo
 */
public abstract class Expression implements Serializable, Element {
	private static final long serialVersionUID = 201608241029L;
	/**
	 * Retorna uma string que representa a expressao
	 *
	 * @return String Retorna uma string que representa a expressao
	 */
	public abstract String getAsText();
}