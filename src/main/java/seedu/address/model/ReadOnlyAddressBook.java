package seedu.address.model;

import javafx.collections.ObservableList;
import seedu.address.model.plan.Plan;
import seedu.address.storage.JsonModule;

/**
 * Unmodifiable view of an address book
 */
public interface ReadOnlyAddressBook {

    /**
     * Returns an unmodifiable view of the persons list.
     * This list will not contain any duplicate persons.
     */
    ObservableList<Plan> getPersonList();

    /**
     * Returns　an array of module information from moduleinfo.json
     */
    JsonModule[] getModuleInfo();
}
