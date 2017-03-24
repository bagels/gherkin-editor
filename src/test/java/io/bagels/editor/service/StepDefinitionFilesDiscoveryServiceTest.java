package io.bagels.editor.service;

import com.google.common.jimfs.Configuration;
import com.google.common.jimfs.Jimfs;
import io.bagels.editor.model.stepdefinitiontypes.CucumberJVMStepDefinition;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.FileSystem;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.List;
import java.util.StringJoiner;
import java.util.stream.Collectors;

import static java.util.Collections.EMPTY_LIST;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.hasSize;

/**
 * Created by green on 24/3/17.
 */
class StepDefinitionFilesDiscoveryServiceTest {

    CucumberJVMStepDefinition stepDefinition = new CucumberJVMStepDefinition();
    StepDefinitionFilesDiscoveryService discoveryService = new StepDefinitionFilesDiscoveryService(stepDefinition);

    @Test
    void should_find_files_with_for_step_definition() throws IOException {
        //given
        String javaFile1 = "file1.java";
        String javaFile2 = "file3.java";
        String folder = "folder";
        List<Path> files = givenFiles(folder, Arrays.asList(javaFile1, "file2.vbs", "file3.txt", javaFile2));

        //when
        List<String> javaFiles = discoveryService.getStepDefinitionFiles(files);

        //then
        String file1 = getFullPath(folder, javaFile1);
        String file2 = getFullPath(folder, javaFile2);
        assertThat(javaFiles, hasSize(2));
        assertThat(javaFiles, containsInAnyOrder(file1, file2));
    }

    private String getFullPath(String folder, String javaFile1) {
        return new StringJoiner("/").add("").add(folder).add(javaFile1).toString();
    }

    @Test
    void should_return_empty_list_if_no_fils() throws IOException {
        //given
        String folder = "folder";
        List<Path> files = EMPTY_LIST;

        //when
        List<String> javaFiles = discoveryService.getStepDefinitionFiles(files);

        //then
        assertThat(javaFiles, hasSize(0));
    }

    private List<Path> givenFiles(String folder, List<String> files) throws IOException {
        Configuration configuration = Configuration.forCurrentPlatform().toBuilder().setWorkingDirectory("/").build();
        FileSystem jimfs = Jimfs.newFileSystem(configuration);
        Path path = jimfs.getPath(folder);
        Files.createDirectory(path);

        return files.stream()
                .map(path::resolve)
                .map(f -> {
                    try {
                        return Files.write(f, Arrays.asList(""), StandardOpenOption.CREATE_NEW);
                    } catch (IOException e) {
                        throw new RuntimeException();
                    }
                }).collect(Collectors.toList());
    }
}