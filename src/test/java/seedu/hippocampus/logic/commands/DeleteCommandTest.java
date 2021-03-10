package seedu.hippocampus.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.hippocampus.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.hippocampus.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.hippocampus.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.hippocampus.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.hippocampus.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.hippocampus.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.hippocampus.commons.core.Messages;
import seedu.hippocampus.commons.core.index.Index;
import seedu.hippocampus.model.Model;
import seedu.hippocampus.model.ModelManager;
import seedu.hippocampus.model.UserPrefs;
import seedu.hippocampus.model.person.Person;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DeleteCommand}.
 */
public class DeleteCommandTest {

    // TODO add testcases for DeleteTagCommand and multiple index

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Person personToDelete = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        DeleteCommand deleteCommand = new DeleteContactCommand(List.of(INDEX_FIRST_PERSON));

        String expectedMessage = String.format(DeleteContactCommand.MESSAGE_DELETE_PERSON_SUCCESS,
                personToDelete.getName());

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.deletePerson(personToDelete);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
        DeleteCommand deleteCommand = new DeleteContactCommand(List.of(outOfBoundIndex));

        assertCommandFailure(deleteCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);

        Person personToDelete = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        DeleteCommand deleteCommand = new DeleteContactCommand(List.of(INDEX_FIRST_PERSON));

        String expectedMessage = String.format(DeleteContactCommand.MESSAGE_DELETE_PERSON_SUCCESS,
                personToDelete.getName());

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.deletePerson(personToDelete);
        showNoPerson(expectedModel);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);

        Index outOfBoundIndex = INDEX_SECOND_PERSON;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getPersonList().size());

        DeleteCommand deleteCommand = new DeleteContactCommand(List.of(outOfBoundIndex));

        assertCommandFailure(deleteCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        DeleteCommand deleteFirstCommand = new DeleteContactCommand(List.of(INDEX_FIRST_PERSON));
        DeleteCommand deleteSecondCommand = new DeleteContactCommand(List.of(INDEX_SECOND_PERSON));

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // same values -> returns true
        DeleteCommand deleteFirstCommandCopy = new DeleteContactCommand(List.of(INDEX_FIRST_PERSON));
        assertTrue(deleteFirstCommand.equals(deleteFirstCommandCopy));

        // different types -> returns false
        assertFalse(deleteFirstCommand.equals(1));

        // null -> returns false
        assertFalse(deleteFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(deleteFirstCommand.equals(deleteSecondCommand));
    }

    /**
     * Updates {@code model}'s filtered list to show no one.
     */
    private void showNoPerson(Model model) {
        model.updateFilteredPersonList(p -> false);

        assertTrue(model.getFilteredPersonList().isEmpty());
    }
}