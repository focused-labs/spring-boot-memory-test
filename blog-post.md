# The concise Guide to JDK 14 memory on Kubernetes

The JVM as of JDK 14 has `UseContainerSupport` turned on by default. Here are some interesting defaults.

You can see these yourself by running,

```
docker run -m 1GB openjdk:14 java \
            -XX:+PrintFlagsFinal -version \
            | grep -E "UseContainerSupport | InitialRAMPercentage | MaxRAMPercentage | MinRAMPercentage | MinHeapFreeRatio | MaxHeapFreeRatio"
```

Outputs:

>    double InitialRAMPercentage                     = 1.562500                                  {product} {default}
      uintx MaxHeapFreeRatio                         = 70                                     {manageable} {default}
     double MaxRAMPercentage                         = 25.000000                                 {product} {default}
      uintx MinHeapFreeRatio                         = 40                                     {manageable} {default}
     double MinRAMPercentage                         = 50.000000                                 {product} {default}
       bool UseContainerSupport                      = true                                      {product} {default}
  openjdk version "14.0.2" 2020-07-14
  OpenJDK Runtime Environment (build 14.0.2+12-46)
  OpenJDK 64-Bit Server VM (build 14.0.2+12-46, mixed mode, sharing)


`MaxRAMPercentage` is key here. The JVM defaults to 25%.

`MinRAMPercentage` and `InitialRAMPercentage` are tricky, [this Stackoverflow answer is the best explanation I've read so far](https://stackoverflow.com/a/54297753). 
 
`InitialRAMPercentage` - Used if `InitialHeapSize` and `Xms` are not set. In this case, if `InitialHeapSize` is 0. [Source reference](http://hg.openjdk.java.net/jdk-updates/jdk11u/file/a7f53869e42b/src/hotspot/share/runtime/arguments.cpp#l1816). *I've never had much luck getting this one to work as I would expect.**
`MinRAMPercentage` - Used if `MaxHeapSize` and `Xmx` are not set. In this case, not set meaning, default value for `MaxHeapSize` and `Xmx` absent. [Source reference](http://hg.openjdk.java.net/jdk-updates/jdk11u/file/a7f53869e42b/src/hotspot/share/runtime/arguments.cpp#l1750).

# MaxRAMPercentage and Kubernetes

The observations below are based on some tests using a [simple memory testing application I wrote](https://github.com/JamesMcMahon/spring-boot-memory-test).

## If no limit is set on the pod

The JVM will use up to the MaxRAMPercentage of the Node's memory. If the pod approaches the Node's memory limit, Kubernetes will kill the pod rather than throwing an OOM Exception.

# Limits set on the pod

## For less than 100%

The JVM will use up to the MaxRAMPercentage of the limit. The pod is not killed, instead of throwing an OOM exception.

## For 100%

The JVM will use up 100% of the memory limit. I saw this occasionally throw an OOM exception, continued memory pressure, and Kubernetes will kill the pod.

# What to set as max?

25% as a default seems relatively low for `MaxRAMPercentage`. So what should you set it to for your application?

This question doesn't have a cut and dry answer. I can say 100% is a bad idea, but what's optimal is going to depend on the memory footprint of your application (with the JVM using system memory for [Metaspace](https://www.baeldung.com/java-permgen-metaspace)) and the container you are using.

I've heard some advice that states that you should leave at least 1gig for the OS at all times. I've also heard 75% recommended. The truth is you are going to need to test out a few different configurations for your app and see what works for you.

That is the short version of a bunch of research, and as close to TLDR I could get for a complex topic. With the JDK 15 right around, I will be looking to revise if there are any notable changes.

Any tips I missed? Let me know below. 

# Additional Resources / References

*Note that some of these pertain to the JDK pre-14.*

* https://medium.com/@yortuc/jvm-memory-allocation-in-docker-container-a26bbce3a3f2
* https://medium.com/adorsys/jvm-memory-settings-in-a-container-environment-64b0840e1d9e
* https://stackoverflow.com/questions/54591870/mismatch-between-spring-actuators-jvm-memory-max-metric-and-runtime-getruntim
* https://merikan.com/2019/04/jvm-in-a-container/
* https://srvaroa.github.io/jvm/kubernetes/memory/docker/oomkiller/2019/05/29/k8s-and-java.html
* https://stackoverflow.com/questions/54292282/clarification-of-meaning-new-jvm-memory-parameters-initialrampercentage-and-minr
* https://blog.nebrass.fr/playing-with-the-jvm-inside-docker-containers/