package io.bagels.editor.model.stepdefinitiontypes;

import org.springframework.stereotype.Component;

/**
 * Created by green on 24/3/17.
 */
@Component
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