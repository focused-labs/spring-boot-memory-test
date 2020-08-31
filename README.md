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
```

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