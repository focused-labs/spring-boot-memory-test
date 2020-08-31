Without any special settings or limits / resources

`jcmd 1 VM.flags`

> -XX:CICompilerCount=2 -XX:InitialHeapSize=216006656 -XX:MaxHeapSize=3430940672 -XX:MaxNewSize=1143603200 -XX:MinHeapDeltaBytes=196608 -XX:MinHeapSize=8388608 -XX:NewSize=71958528 -XX:NonNMethodCodeHeapSize=5826188 -XX:NonProfiledCodeHeapSize=122916026 -XX:OldSize=144048128 -XX:ProfiledCodeHeapSize=122916026 -XX:ReservedCodeCacheSize=251658240 -XX:+SegmentedCodeCache -XX:SoftMaxHeapSize=3430940672 -XX:+UseCompressedClassPointers -XX:+UseCompressedOops -XX:+UseFastUnorderedTimeStamps -XX:+UseSerialGC

`jcmd 1 VM.command_line`

> VM Arguments:
java_command: /app.jar
java_class_path (initial): /app.jar
Launcher Type: SUN_STANDARD

````
######## Memory Stats ########
Used Memory:0.03869016
Free Memory:0.17023861
Total Memory:0.20892876
Max Memory:3.3165803
##############################
````

After eat-memory?chunks=100&size=100

######## Memory Stats ########
Used Memory:3.2280521
Free Memory:0.088527806
Total Memory:3.3165803
Max Memory:3.3165803
##############################

3274981128 used
4641980415 max (this is suspect)

---

# 2 Replicas

Aug 30 20:43:34 memory-test-deployment-558bc598c5-7ckg4 memorytest ######## Memory Stats ########
Aug 30 20:43:34 memory-test-deployment-558bc598c5-7ckg4 memorytest Used Memory:0.039695185
Aug 30 20:43:34 memory-test-deployment-558bc598c5-7ckg4 memorytest Free Memory:0.16923359
Aug 30 20:43:34 memory-test-deployment-558bc598c5-7ckg4 memorytest Total Memory:0.20892876
Aug 30 20:43:34 memory-test-deployment-558bc598c5-7ckg4 memorytest Max Memory:3.3165803
Aug 30 20:43:34 memory-test-deployment-558bc598c5-7ckg4 memorytest ##############################

Aug 30 20:43:34 memory-test-deployment-558bc598c5-72wsm memorytest ######## Memory Stats ########
Aug 30 20:43:34 memory-test-deployment-558bc598c5-72wsm memorytest Used Memory:0.037618905
Aug 30 20:43:34 memory-test-deployment-558bc598c5-72wsm memorytest Free Memory:0.16724662
Aug 30 20:43:34 memory-test-deployment-558bc598c5-72wsm memorytest Total Memory:0.20486553
Aug 30 20:43:34 memory-test-deployment-558bc598c5-72wsm memorytest Max Memory:3.2740474
Aug 30 20:43:34 memory-test-deployment-558bc598c5-72wsm memorytest ##############################

After eat-memory?chunks=100&size=100

Aug 30 20:46:35 memory-test-deployment-558bc598c5-72wsm memorytest ######## Memory Stats ########
Aug 30 20:46:35 memory-test-deployment-558bc598c5-72wsm memorytest Used Memory:3.2218022
Aug 30 20:46:35 memory-test-deployment-558bc598c5-72wsm memorytest Free Memory:0.052244954
Aug 30 20:46:35 memory-test-deployment-558bc598c5-72wsm memorytest Total Memory:3.2740474
Aug 30 20:46:35 memory-test-deployment-558bc598c5-72wsm memorytest Max Memory:3.2740474
Aug 30 20:46:35 memory-test-deployment-558bc598c5-72wsm memorytest ##############################

Aug 30 20:49:38 memory-test-deployment-558bc598c5-7ckg4 memorytest ######## Memory Stats ########
Aug 30 20:49:38 memory-test-deployment-558bc598c5-7ckg4 memorytest Used Memory:3.225427
Aug 30 20:49:38 memory-test-deployment-558bc598c5-7ckg4 memorytest Free Memory:0.09115345
Aug 30 20:49:38 memory-test-deployment-558bc598c5-7ckg4 memorytest Total Memory:3.3165803
Aug 30 20:49:38 memory-test-deployment-558bc598c5-7ckg4 memorytest Max Memory:3.3165803
Aug 30 20:49:38 memory-test-deployment-558bc598c5-7ckg4 memorytest ##############################

---

With 
ENTRYPOINT ["java","-XX:InitialRAMPercentage=50","-XX:MaxRAMPercentage=50","-jar","/app.jar"]

Aug 30 21:02:27 memory-test-deployment-558bc598c5-xcl5f memorytest ######## Memory Stats ########
Aug 30 21:02:27 memory-test-deployment-558bc598c5-xcl5f memorytest Used Memory:0.46633306
Aug 30 21:02:27 memory-test-deployment-558bc598c5-xcl5f memorytest Free Memory:6.164796
Aug 30 21:02:27 memory-test-deployment-558bc598c5-xcl5f memorytest Total Memory:6.6311293
Aug 30 21:02:27 memory-test-deployment-558bc598c5-xcl5f memorytest Max Memory:6.6311293
Aug 30 21:02:27 memory-test-deployment-558bc598c5-xcl5f memorytest ##############################

Kubernetes kills the pod rather then letting it OOM

---

With 
ENTRYPOINT ["java","-XX:InitialRAMPercentage=50","-XX:MaxRAMPercentage=50","-jar","/app.jar"]

  resources:
    limits:
      memory: 4Gi
    requests:
      memory: 2Gi

`jcmd 1 VM.flags`

> -XX:CICompilerCount=2 -XX:InitialHeapSize=2147483648 -XX:InitialRAMPercentage=50.000000 -XX:MaxHeapSize=2147483648 -XX:MaxNewSize=715784192 -XX:MaxRAM=4294967296 -XX:MaxRAMPercentage=50.000000 -XX:MinHeapDeltaBytes=196608 -XX:MinHeapSize=8388608 -XX:NewSize=715784192 -XX:NonNMethodCodeHeapSize=5826188 -XX:NonProfiledCodeHeapSize=122916026 -XX:OldSize=1431699456 -XX:ProfiledCodeHeapSize=122916026 -XX:ReservedCodeCacheSize=251658240 -XX:+SegmentedCodeCache -XX:SoftMaxHeapSize=2147483648 -XX:+UseCompressedClassPointers -XX:+UseCompressedOops -XX:+UseFastUnorderedTimeStamps -XX:+UseSerialGC

`jcmd 1 VM.command_line`

> VM Arguments:
jvm_args: -XX:InitialRAMPercentage=50 -XX:MaxRAMPercentage=50
java_command: /app.jar
java_class_path (initial): /app.jar
Launcher Type: SUN_STANDARD

Aug 30 21:58:48 memory-test-deployment-7877fb46bd-fphbh memorytest ######## Memory Stats ########
Aug 30 21:58:48 memory-test-deployment-7877fb46bd-fphbh memorytest Used Memory:0.20950332
Aug 30 21:58:48 memory-test-deployment-7877fb46bd-fphbh memorytest Free Memory:1.866415
Aug 30 21:58:48 memory-test-deployment-7877fb46bd-fphbh memorytest Total Memory:2.0759184
Aug 30 21:58:48 memory-test-deployment-7877fb46bd-fphbh memorytest Max Memory:2.0759184
Aug 30 21:58:48 memory-test-deployment-7877fb46bd-fphbh memorytest ##############################

OOM happens, leaving a stack trace and pod is not killed

Aug 30 22:02:34 memory-test-deployment-7877fb46bd-fphbh memorytest ######## Memory Stats ########
Aug 30 22:02:34 memory-test-deployment-7877fb46bd-fphbh memorytest Used Memory:1.9300007
Aug 30 22:02:34 memory-test-deployment-7877fb46bd-fphbh memorytest Free Memory:0.14591767
Aug 30 22:02:34 memory-test-deployment-7877fb46bd-fphbh memorytest Total Memory:2.0759184
Aug 30 22:02:34 memory-test-deployment-7877fb46bd-fphbh memorytest Max Memory:2.0759184
Aug 30 22:02:34 memory-test-deployment-7877fb46bd-fphbh memorytest ##############################

# Resources

* https://medium.com/@yortuc/jvm-memory-allocation-in-docker-container-a26bbce3a3f2
* https://medium.com/adorsys/jvm-memory-settings-in-a-container-environment-64b0840e1d9e
* https://stackoverflow.com/questions/54591870/mismatch-between-spring-actuators-jvm-memory-max-metric-and-runtime-getruntim
* https://merikan.com/2019/04/jvm-in-a-container/
* https://srvaroa.github.io/jvm/kubernetes/memory/docker/oomkiller/2019/05/29/k8s-and-java.html
* https://stackoverflow.com/questions/54292282/clarification-of-meaning-new-jvm-memory-parameters-initialrampercentage-and-minr