package seedu.hippocampus.logic.commands;

import seedu.hippocampus.model.Model;

/**
 * Terminates the program.
 */
public class ExitCommand extends Command {

    public static final String COMMAND_WORD = "exit";

    public static final String MESSAGE_EXIT_ACKNOWLEDGEMENT = "Exiting HippoCampus as requested ...";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Exits the application. ";

    @Override
    public CommandResult execute(Model model) {
        return new CommandResult(MESSAGE_EXIT_ACKNOWLEDGEMENT, true);
    }

}