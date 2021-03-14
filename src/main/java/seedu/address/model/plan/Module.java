package seedu.address.model.plan;

import java.util.ArrayList;
import java.util.List;

//import org.json.simple.parser.JSONParser;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.plan.exceptions.ModuleExceptions;
import seedu.address.storage.JsonModule;

public class Module {

    private String moduleTitle;
    private String moduleCode;
    private int moduleCredits;
    private String descriptions;
    private boolean isDone;
    private String grade;

    private List<Module> prerequisites = new ArrayList<>();
    private List<Module> preclusions = new ArrayList<>();

    /**
     * Constructor for creating a module.
     * @param moduleTitle Title of module.
     * @param moduleCode Code of module.
     * @param moduleCredits Number of module credits module offers.
     */
    public Module (String moduleTitle, String moduleCode, int moduleCredits) {
        this.moduleCode = moduleCode;
        this.moduleTitle = moduleTitle;
        this.moduleCredits = moduleCredits;
        this.isDone = false;
    }

    public String getModuleTitle() {
        return moduleTitle;
    }

    public String getModuleCode() {
        return moduleCode;
    }

    public int getMCs() {
        return moduleCredits;
    }

    public String getDescriptions() {
        return descriptions;
    }

    public String getGrade() {
        return grade;
    }

    /**
     * Custom method to compare modules.
     * @param otherModule The module to compare to.
     * @return True if modules are the same.
     */
    public boolean isSameModule(Module otherModule) {
        if (this == otherModule) {
            return true;
        }
        return otherModule != null
                && otherModule.getModuleCode().equals(getModuleCode())
                && otherModule.getModuleTitle().equals(getModuleTitle());
    }

    @Override
    public boolean equals (Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Module)) {
            return false;
        }

        Module otherModule = (Module) other;
        return otherModule.getModuleTitle().equals(getModuleTitle())
                && otherModule.getModuleCode().equals(getModuleCode())
                && otherModule.getMCs() == getMCs()
                && otherModule.getDescriptions().equals(getDescriptions());
    }

    /**
     * mark module as done and give it a grade
     */
    public void doneModule (String grade) throws ModuleExceptions {
        if (isDone) {
            throw new ModuleExceptions("Module is already mark as done");
        } else if (grade.length() >= 2 || !Character.isUpperCase(grade.charAt(0))) {
            //grade length entered is longer than 2 or first letter is not upper case
            throw new ModuleExceptions("Invalid grade, please try again");
        }
        this.grade = grade;
        this.isDone = true;
    }

    /**
     * convert letter grade to CAP grade
     */
    public double convertGradeToCap() {
        String g = this.getGrade();
        double numGrade;
        switch (g) {
        case "A+":
        case "A":
            numGrade = 5;
            break;
        case "A-":
            numGrade = 4.5;
            break;
        case "B+":
            numGrade = 4;
            break;
        case "B":
            numGrade = 3.5;
            break;
        case "B-":
            numGrade = 3;
            break;
        case "C+":
            numGrade = 2.5;
            break;
        case "C":
            numGrade = 2;
            break;
        case "C-":
            numGrade = 1.5;
            break;
        default: numGrade = 0;
        }
        return numGrade;
    }

    /**
     * Add pre-reqs to module.
     * @param prereq a prerequisite module.
     */
    public void addPrerequisites(Module... prereq) {
        for (Module m : prereq) {
            prerequisites.add(m);
        }
    }

    /**
     * Add preclusions to module.
     * @param preclu a preclusion to the module.
     */
    public void addPreclusions (Module ... preclu) {
        for (Module m : preclu) {
            preclusions.add(m);
        }
    }

    public static Module seekModule(String moduleCode) throws ModuleExceptions{
        JsonModule[] moduleInfo = Model.getPlans().getModuleInfo();
        int l = moduleInfo.length;
        for (int i = 0; i < l; i++) {
            if (moduleInfo[i].getModuleCode().equals(moduleCode)) {
                String moduleTitle = moduleInfo[i].getModuleTitle();
                String code = moduleInfo[i].getModuleCode();
                int moduleCredit = Integer.parseInt(moduleInfo[i].getNumMc());
                return new Module(moduleTitle, code, moduleCredit);
            }
        }
        throw new ModuleExceptions("Cannot find module");
    }

    @Override
    public String toString() {
        return getModuleCode() + " " + getModuleTitle();
    }
}
