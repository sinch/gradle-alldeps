package com.sinch.gradle.alldeps

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.Task
import org.gradle.api.tasks.diagnostics.DependencyReportTask

@Suppress("unused", "MemberVisibilityCanBePrivate")
class AlldepsPlugin : Plugin<Project> {
    override fun apply(project: Project) {
        project.tasks.getByName("dependencies").dependsOn(
            project.subprojects.map { subproject ->
                subproject.tasks.create(
                    "dependenciesToAggregate",
                    DependencyReportTask::class.java,
                ) { it.group = "help" }
            },
        )
    }
}
