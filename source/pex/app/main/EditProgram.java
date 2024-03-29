package pex.app.main;

import pex.core.Handler;
import pex.core.Program;
import pex.core.Interpreter;
import pex.support.app.main.*;

import pex.app.evaluator.EvaluatorMenu;

import pt.utl.ist.po.ui.Command;
import pt.utl.ist.po.ui.InputString;
import pt.utl.ist.po.ui.Menu;
import pt.utl.ist.po.ui.Display;
import pt.utl.ist.po.ui.Form;
import pex.AppIO;

/**
 * Open menu for managing programs.
 */
public class EditProgram extends Command<Handler> {

    /**
     * @param receiver
     */
    public EditProgram(Handler receiver) {
        super(Label.MANAGE_PROGRAM, receiver);
    }

    /**
     * Permite editar um programa
     */
    @Override
    public final void execute() {
        String param_1 = entity().requestString(Message.requestProgramId());

        if (entity().checkProgram(param_1)) {
            Program prog = entity().editProgram(param_1);
            EvaluatorMenu menu = new EvaluatorMenu(prog);
            menu.open();
        } else {
            (new Display(title())).add(Message.noSuchProgram(param_1)).display();
        }
    }

}
