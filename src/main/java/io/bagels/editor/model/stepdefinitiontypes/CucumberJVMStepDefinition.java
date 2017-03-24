package io.bagels.editor.model.stepdefinitiontypes;

/**
 * Created by green on 24/3/17.
 */
public class CucumberJVMStepDefinition implements StepDefinition {

    @Override
    public String extension() {
        return "java";
    }

    @Override
    public String pattern() {
        return ".*@(Given|When|Then|And|But)\\s*\\((.*)\\)";
    }
}