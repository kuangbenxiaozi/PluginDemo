package com.imooc.gradle.study

import org.gradle.api.Plugin
import org.gradle.api.Project

/**
 * 自定义Plugin
 */
class GradleStudyPlugin implements Plugin<Project> {

    /**
     * 唯一需要实现的就是这个方法，参数就是引入了当前插件的Project对象
     * @param project
     */
    @Override
    void apply(Project project) {
        //创建扩展属性
        println("GradleStudyPlugin  =========")
        project.extensions.create('imoocReleaseInfo',
                ReleaseInfoExtension)
        //创建Task
//        project.tasks.create('imoocReleaseInfoTask',
//                ReleaseInfoTask)

        project.task("imoocReleaseInfoTask", type: ReleaseInfoTask) {

        }

        project.afterEvaluate {
            println "task afterEvaluate!"
            def task1 = project.tasks.findByName("preBuild")

            if (null != task1) {
                println "task exist!"
                task1.dependsOn "imoocReleaseInfoTask"
            }
        }
    }
}