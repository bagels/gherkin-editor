package io.bagels.editor.model.stepdefinitiontypes;

import org.junit.jupiter.api.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * Created by green on 24/3/17.
 */
class CucumberJVMStepDefinitionTest {

    CucumberJVMStepDefinition stepDefinition = new CucumberJVMStepDefinition();
    Pattern pattern = getPattern(stepDefinition);

    @Test
    void shold_fetch_simple_step_definition_from_feature_file() {
        //given
        String stepAnnotation = "Given";
        String stepDefinitionText = "^I have a cucumber step$";

        //when
        String text = "Line Text\n     @" + stepAnnotation + " (" + stepDefinitionText + ")\nLine 2 Text \n";
        Matcher matcher = pattern.matcher(text);

        //then
        assertThat(matcher.find(), is(true));
        assertThat(matcher.groupCount(), is(2));
        assertThat(matcher.group(1), is(stepAnnotation));
        assertThat(matcher.group(2), is(stepDefinitionText));
    }


    @Test
    void shold_fetch_complex_step_definition_from_feature_file() {
        //given
        String stepAnnotation = "Given";
        String stepDefinitionText = "^There (?:is|are) (\\\\d+) (?:cats?|ox|oxen) fed by (\\\\d+) (?:persons?|people)$";

        //when
        String text = "Line Text\n     @" + stepAnnotation + " (" + stepDefinitionText + ")\nLine 2 Text \n";
        Matcher matcher = pattern.matcher(text);

        //then
        assertThat(matcher.find(), is(true));
        assertThat(matcher.groupCount(), is(2));
        assertThat(matcher.group(1), is(stepAnnotation));
        assertThat(matcher.group(2), is(stepDefinitionText));
    }

    private Pattern getPattern(CucumberJVMStepDefinition stepDefinition) {
        String cucumberPattern = stepDefinition.pattern();
        return Pattern.compile(cucumberPattern);
    }
}