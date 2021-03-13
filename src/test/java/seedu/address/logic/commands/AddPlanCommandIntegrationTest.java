package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.plan.Plan;
import seedu.address.testutil.PersonBuilder;

/**
 * Contains integration tests (interaction with the Model) for {@code AddCommand}.
 */
public class AddPlanCommandIntegrationTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    }

    @Test
    public void execute_newPerson_success() {
        Plan validPlan = new PersonBuilder().build();

        Model expectedModel = new ModelManager(model.getPlans(), new UserPrefs());
        expectedModel.addPlan(validPlan);

        assertCommandSuccess(new AddPlanCommand(validPlan), model,
                String.format(AddPlanCommand.MESSAGE_SUCCESS, validPlan), expectedModel);
    }

    @Test
    public void execute_duplicatePerson_throwsCommandException() {
        Plan planInList = model.getPlans().getPersonList().get(0);
        assertCommandFailure(new AddPlanCommand(planInList), model, AddPlanCommand.MESSAGE_DUPLICATE_PLAN);
    }

}
