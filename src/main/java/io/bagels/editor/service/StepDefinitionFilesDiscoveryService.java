package io.bagels.editor.service;

import io.bagels.editor.model.stepdefinitiontypes.StepDefinition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by green on 24/3/17.
 */

@Service
public class StepDefinitionFilesDiscoveryService implements FileDiscoveryService {

    @Value("${getStepDefinitionFiles.root.folder}")
    private String rootFolder;

    private final StepDefinition stepDefinition;

    @Autowired
    public StepDefinitionFilesDiscoveryService(StepDefinition stepDefinition) {
        this.stepDefinition = stepDefinition;
        this.rootFolder = rootFolder;
    }

    public List<String> getStepDefinitionFiles(List<Path> files) {
        return files.stream()
                .map(f -> f.toAbsolutePath().toString())
                .filter(f -> f.endsWith(stepDefinition.extension()))
                .collect(Collectors.toList());
    }


}
