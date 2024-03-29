package pex.core;

import pex.core.WrongTypeException;
import pex.core.expressions.Expression;
import pex.core.Visitor;

public interface Element {
	public Expression accept(Visitor v) throws WrongTypeException;
}