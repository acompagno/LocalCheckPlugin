package com.acompagno.localcheck

import org.gradle.testfixtures.ProjectBuilder
import spock.lang.Specification

/**
 * Unit tests for LocalCheckPlugin.
 */
class LocalCheckPluginSpec extends Specification {

    def "checkExtensionAdded"() {
        given:
        def project = ProjectBuilder.builder().build()
        project.apply plugin: 'com.acompagno.localcheck'

        expect:
        project.getExtensions().getByName('LC') instanceof LocalCheckExtension
    }
}
