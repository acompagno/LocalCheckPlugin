package com.acompagno.localcheck

import org.gradle.api.Project
import org.gradle.testfixtures.ProjectBuilder
import spock.lang.Specification

/**
 * Unit Tests for LocalCheckExtension.
 */
class LocalCheckExtensionSpec extends Specification {

    def root
    def project
    def lc

    def "setup"() {
        root = ProjectBuilder.builder()
                .withName('root')
                .build()
        project = ProjectBuilder.builder()
                .withParent(root)
                .withName('ProjA')
                .build()
        project.apply plugin: 'com.acompagno.localcheck'

        lc = new LocalCheckExtension(project)
    }

    def "testFindLocalDependency"() {
        given:
        def depProjectA = ProjectBuilder.builder()
                .withParent(root as Project)
                .withName('DepA')
                .build()

        expect:
        lc.checkLocal('remote:dep:a', 'DepA') as Project == depProjectA
    }

    def "testUseRemoteDependency"() {
        given:
        ProjectBuilder.builder()
                .withParent(root as Project)
                .withName('DepB')
                .build()

        expect:
        lc.checkLocal('remote:dep:a', 'DepA') == 'remote:dep:a'
    }

    def "testForceRemote"() {
        given:
        ProjectBuilder.builder()
                .withParent(root as Project)
                .withName('DepA')
                .build()

        project.ext.forceRemote = null

        expect:
        lc.checkLocal('remote:dep:a', 'DepA') == 'remote:dep:a'
    }

    def "testUseRemoteAmbiguousLocalDependencies"() {
        given:
        ProjectBuilder.builder()
                .withParent(root as Project)
                .withName('DepA')
                .build()
        def libs = ProjectBuilder.builder()
                .withParent(root as Project)
                .withName('Libs')
                .build()
        ProjectBuilder.builder()
                .withParent(libs)
                .withName('DepA')
                .build()

        expect:
        lc.checkLocal('remote:dep:a', 'DepA') == 'remote:dep:a'
    }
}
