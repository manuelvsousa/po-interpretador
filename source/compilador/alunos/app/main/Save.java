package compilador.alunos.app.main;

import java.io.IOException;
import compilador.core.main.Handler;

import pt.utl.ist.po.ui.Command;
import pt.utl.ist.po.ui.Form;
import pt.utl.ist.po.ui.InputString;
import pt.utl.ist.po.ui.InvalidOperation;

/**
 * Save to file under current name (if unnamed, query for name).
 */
public class Save extends Command<Handler> {
    /**
     * @param receiver
     */
    public Save(Handler receiver) {
        super(Label.SAVE, receiver);
    }

    /** @see pt.utl.ist.po.ui.Command#execute() */
    @Override
    public final void execute() throws InvalidOperation {
        //FIXME implement
    }
}
