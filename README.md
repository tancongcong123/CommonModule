# CommonModule
[![](https://jitpack.io/v/tancongcong123/CommonModule.svg)](https://jitpack.io/#tancongcong123/CommonModule)

指定依赖模块，指定版本，已解决依赖库版本冲突
configurations.all {
    resolutionStrategy.eachDependency { DependencyResolveDetails details ->
        def requested = details.requested
        if (requested.group == 'com.android.support') {
            if (!requested.name.startsWith("multidex")) {
                details.useVersion '25.3.0'
            }
        }
    }
}