LocalCheckPlugin
===
Gradle plugin that automatically switches between using local and remote dependencies

Usage
---
```groovy
dependencies {
    compile LC.checkLocal('blue.walker:beacon-lib:0.1.+', 'BeaconLib')
}
```
In order to force gradle to use the remote dependencies, simply add -PforceRemote when running gradle commands.
```bash
gradle -PforceRemote assemble
```

Adding to build.gradle
---
```groovy
buildscript {
    repositories {
        jcenter()
    }

    dependencies {
        classpath 'com.acompagno:localcheck:0.1.+'
    }
}

apply plugin: 'com.acompagno.localcheck'
```

Example output
---
```bash
$ gradle
[checkLocal] Using local dependency :Libraries:BeaconLib for project GlassApplication
[checkLocal] Using local dependency :Libraries:Core for project GlassApplication
[checkLocal] Using local dependency :Libraries:BeaconLib for project BeaconLibExample
[checkLocal] Using local dependency :Libraries:BeaconLib for project BeaconLibGlassExample
:help

Welcome to Gradle 2.2.1.

To run a build, run gradle <task> ...

To see a list of available tasks, run gradle tasks

To see a list of command-line options, run gradle --help

BUILD SUCCESSFUL

Total time: 7.864 secs
```
```bash
$ gradle -PforceRemote
[checkLocal] Using remote dependency blue.walker:beacon-lib:0.1.+ for project GlassApplication
[checkLocal] Using remote dependency blue.walker:core:0.1.+ for project GlassApplication
[checkLocal] Using remote dependency blue.walker:beacon-lib:0.1.+ for project BeaconLibExample
[checkLocal] Using remote dependency blue.walker:beacon-lib:0.1.+ for project BeaconLibGlassExample
:help

Welcome to Gradle 2.2.1.

To run a build, run gradle <task> ...

To see a list of available tasks, run gradle tasks

To see a list of command-line options, run gradle --help

BUILD SUCCESSFUL

Total time: 7.319 secs
```
