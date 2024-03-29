package pex.app.main;

import pex.core.Interpreter;
import pex.core.Handler;
import pex.support.app.main.*;

import pt.utl.ist.po.ui.Command;
import pt.utl.ist.po.ui.Display;
import pt.utl.ist.po.ui.Form;
import pt.utl.ist.po.ui.InputBoolean;

/**
 * Command for creating a new interpreter.
 */
public class New extends Command<Handler> {
    /**
     * @param receiver
     */
    public New(Handler receiver) {
        super(Label.NEW, receiver);
    }

    /**
     * Cria um novo interpretador
     */
    @Override
    public final void execute() {
        entity().newInterpreter();
    }
}
