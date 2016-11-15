package pex.alunos.app.main;

import pex.core.main.Handler;
import pex.support.app.main.Label;

import pt.utl.ist.po.ui.Command;
import pt.utl.ist.po.ui.Menu;

/**
 * Menu builder.
 * Represents the main menu of this application. This is the first menu
 * shown to the users.
*/
public class MainMenu extends Menu {

    /**
     * @param receiver
     */
    public MainMenu(Handler receiver) {
        super("Compilador XPTO",
          	new Command<?>[] { //
            new New(receiver), //
            new Open(receiver), //
            new Save(receiver), //
            new NewProgram(receiver), //
            new ReadProgram(receiver), //
            new WriteProgram(receiver), //
            new EditProgram(receiver), //
            });
    }
}
