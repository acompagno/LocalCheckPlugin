package com.acompagno.localcheck

import org.gradle.api.Project
import org.gradle.testfixtures.ProjectBuilder
import org.junit.Test

/**
 * Unit tests for LocalCheckPlugin
 */
class LocalCheckPluginTest {

    @Test
    public void checkExtensionAdded() {
        Project project = ProjectBuilder.builder().build()
        project.apply plugin: 'com.acompagno.localcheck'

        assert project.getExtensions().getByName('LC') instanceof LocalCheckExtension
    }
}
