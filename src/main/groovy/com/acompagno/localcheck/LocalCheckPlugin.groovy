package com.acompagno.localcheck

import org.gradle.api.Project
import org.gradle.api.Plugin

/**
 * Gradle plugin which injects the LocalCheckExtension object into the project
 * as an extension.
 */
class LocalCheckPlugin implements Plugin<Project> {

    /**
     * Method called which applies the plugin to the project
     *
     * @param project Project which the plugin is being applied to.
     */
    void apply(Project project) {
        project.extensions.add('LC', new LocalCheckExtension(project))
    }
}
