package com.acompagno.localcheck

import org.gradle.api.Project

/**
 * Extension that contains the checkLocal method.
 */
class LocalCheckExtension {

    Project project

    LocalCheckExtension(Project project) {
        this.project = project
    }

    /**
     * Tries to find a local copy of the project. Returns the project if it is
     * found. If the project is not found locally, it returns the remote
     * string passed in.
     *
     * @param remote String for the remote dependency.
     * @param local Name of the local dependency.
     * @return Project object for the local dependency if is found or the
     *         remote string passed in if it isn't.
     */
    def checkLocal(String remote, String local) {
        def localDep
        if (!project.hasProperty("forceRemote") && (localDep = findLocalPackage(local)) != null) {
            println "[checkLocal] Using local dependency ${localDep.path} for project ${project.name}"
            return localDep
        } else {
            println "[checkLocal] Using remote dependency ${remote} for project ${project.name}"
            return remote
        }
    }

    /**
     * Searches the root project for a project with the given name.
     *
     * @param local Name of the local dependency.
     * @return Project object for the local dependency if is found or null if
     *         it isn't.
     */
    def findLocalPackage(String local) {
        def matches = project.rootProject.subprojects.findAll{ project -> project.name == local }
        if (matches.size() == 1) {
            return matches.iterator().next()
        } else if (matches.size() > 1) {
            println "[checkLocal] Multiple projects named ${local} found"
        }
    }
}

