package com.acompagno.localcheck

import org.gradle.api.Project
import org.gradle.testfixtures.ProjectBuilder
import org.junit.After
import org.junit.Before
import org.junit.Test

/**
 * Unit Tests for LocalCheckExtension
 */
class LocalCheckExtensionTest {

    private Project root
    private Project project
    private LocalCheckExtension lc

    @Before
    public void setup() {
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

    @After
    public void cleanup() {
        root = null
        project = null
        lc = null
    }

    @Test
    public void testFindLocalDependency() {
        Project depProjectA = ProjectBuilder.builder()
                .withParent(root)
                .withName('DepA')
                .build()

        assert lc.checkLocal('remote:dep:a', 'DepA') == depProjectA
    }

    @Test
    public void testUseRemoteDependency() {
        Project depProjectB = ProjectBuilder.builder()
                .withParent(root)
                .withName('DepB')
                .build()

        assert lc.checkLocal('remote:dep:a', 'DepA') == 'remote:dep:a'
    }

    @Test
    public void testForceRemote() {
        Project depProjectA = ProjectBuilder.builder()
                .withParent(root)
                .withName('DepA')
                .build()

        project.ext.forceRemote = null

        assert lc.checkLocal('remote:dep:a', 'DepA') == 'remote:dep:a'
    }

    @Test
    public void testUseRemoteAmbiguousLocalDependencies() {
        Project depProjectA = ProjectBuilder.builder()
                .withParent(root)
                .withName('DepA')
                .build()
        Project libs = ProjectBuilder.builder()
                .withParent(root)
                .withName('Libs')
                .build()
        Project otherDepProjectA = ProjectBuilder.builder()
                .withParent(libs)
                .withName('DepA')
                .build()

        assert lc.checkLocal('remote:dep:a', 'DepA') == 'remote:dep:a'
    }
}