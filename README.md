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

# Defaults

docker run -m 1GB openjdk:14 java \                                                                 master ✱
          -XX:+PrintFlagsFinal -version \
          | grep -E "UseContainerSupport | InitialRAMPercentage | MaxRAMPercentage | MinRAMPercentage | MinHeapFreeRatio | MaxHeapFreeRatio"
   double InitialRAMPercentage                     = 1.562500                                  {product} {default}
    uintx MaxHeapFreeRatio                         = 70                                     {manageable} {default}
   double MaxRAMPercentage                         = 25.000000                                 {product} {default}
    uintx MinHeapFreeRatio                         = 40                                     {manageable} {default}
   double MinRAMPercentage                         = 50.000000                                 {product} {default}
     bool UseContainerSupport                      = true                                      {product} {default}
openjdk version "14.0.2" 2020-07-14
OpenJDK Runtime Environment (build 14.0.2+12-46)
OpenJDK 64-Bit Server VM (build 14.0.2+12-46, mixed mode, sharing)

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

---

With 
ENTRYPOINT ["java","-XX:InitialRAMPercentage=50","-XX:MaxRAMPercentage=50","-jar","/app.jar"]

  resources:
    limits:
      memory: 4Gi
    requests:
      memory: 4Gi

`jcmd 1 VM.flags`

> -XX:CICompilerCount=2 -XX:InitialHeapSize=2147483648 -XX:InitialRAMPercentage=50.000000 -XX:MaxHeapSize=2147483648 -XX:MaxNewSize=715784192 -XX:MaxRAM=4294967296 -XX:MaxRAMPercentage=50.000000 -XX:MinHeapDeltaBytes=196608 -XX:MinHeapSize=8388608 -XX:NewSize=715784192 -XX:NonNMethodCodeHeapSize=5826188 -XX:NonProfiledCodeHeapSize=122916026 -XX:OldSize=1431699456 -XX:ProfiledCodeHeapSize=122916026 -XX:ReservedCodeCacheSize=251658240 -XX:+SegmentedCodeCache -XX:SoftMaxHeapSize=2147483648 -XX:+UseCompressedClassPointers -XX:+UseCompressedOops -XX:+UseFastUnorderedTimeStamps -XX:+UseSerialGC

`jcmd 1 VM.command_line`

> VM Arguments:
jvm_args: -XX:InitialRAMPercentage=50 -XX:MaxRAMPercentage=50
java_command: /app.jar
java_class_path (initial): /app.jar
Launcher Type: SUN_STANDARD

Aug 31 20:17:11 memory-test-deployment-7596dc4567-99k68 memorytest ######## Memory Stats ########
Aug 31 20:17:11 memory-test-deployment-7596dc4567-99k68 memorytest Used Memory:0.1980401
Aug 31 20:17:11 memory-test-deployment-7596dc4567-99k68 memorytest Free Memory:1.8778783
Aug 31 20:17:11 memory-test-deployment-7596dc4567-99k68 memorytest Total Memory:2.0759184
Aug 31 20:17:11 memory-test-deployment-7596dc4567-99k68 memorytest Max Memory:2.0759184
Aug 31 20:17:11 memory-test-deployment-7596dc4567-99k68 memorytest ##############################

OOM happens, leaving a stack trace and pod is not killed

Aug 31 20:20:42 memory-test-deployment-7596dc4567-99k68 memorytest ######## Memory Stats ########
Aug 31 20:20:42 memory-test-deployment-7596dc4567-99k68 memorytest Used Memory:1.931358
Aug 31 20:20:42 memory-test-deployment-7596dc4567-99k68 memorytest Free Memory:0.14456037
Aug 31 20:20:42 memory-test-deployment-7596dc4567-99k68 memorytest Total Memory:2.0759184
Aug 31 20:20:42 memory-test-deployment-7596dc4567-99k68 memorytest Max Memory:2.0759184
Aug 31 20:20:42 memory-test-deployment-7596dc4567-99k68 memorytest ##############################

---

With 
ENTRYPOINT ["java","-XX:InitialRAMPercentage=80","-XX:MaxRAMPercentage=80","-jar","/app.jar"]

  resources:
    limits:
      memory: 4Gi
    requests:
      memory: 4Gi

`jcmd 1 VM.flags`

> -XX:CICompilerCount=2 -XX:InitialHeapSize=3437232128 -XX:InitialRAMPercentage=80.000000 -XX:MaxHeapSize=3437232128 -XX:MaxNewSize=1145700352 -XX:MaxRAM=4294967296 -XX:MaxRAMPercentage=80.000000 -XX:MinHeapDeltaBytes=196608 -XX:MinHeapSize=8388608 -XX:NewSize=1145700352 -XX:NonNMethodCodeHeapSize=5826188 -XX:NonProfiledCodeHeapSize=122916026 -XX:OldSize=2291531776 -XX:ProfiledCodeHeapSize=122916026 -XX:ReservedCodeCacheSize=251658240 -XX:+SegmentedCodeCache -XX:SoftMaxHeapSize=3437232128 -XX:+UseCompressedClassPointers -XX:+UseCompressedOops -XX:+UseFastUnorderedTimeStamps -XX:+UseSerialGC

`jcmd 1 VM.command_line`

> VM Arguments:
jvm_args: -XX:InitialRAMPercentage=80 -XX:MaxRAMPercentage=80
java_command: /app.jar
java_class_path (initial): /app.jar
Launcher Type: SUN_STANDARD

Aug 31 20:30:06 memory-test-deployment-7596dc4567-4qqnd memorytest ######## Memory Stats ########
Aug 31 20:30:06 memory-test-deployment-7596dc4567-4qqnd memorytest Used Memory:0.274837
Aug 31 20:30:06 memory-test-deployment-7596dc4567-4qqnd memorytest Free Memory:3.0478382
Aug 31 20:30:06 memory-test-deployment-7596dc4567-4qqnd memorytest Total Memory:3.3226752
Aug 31 20:30:06 memory-test-deployment-7596dc4567-4qqnd memorytest Max Memory:3.3226752
Aug 31 20:30:06 memory-test-deployment-7596dc4567-4qqnd memorytest ##############################

OOM happens, leaving a stack trace and pod is not killed

Aug 31 20:33:05 memory-test-deployment-7596dc4567-4qqnd memorytest ######## Memory Stats ########
Aug 31 20:33:05 memory-test-deployment-7596dc4567-4qqnd memorytest Used Memory:3.2271194
Aug 31 20:33:05 memory-test-deployment-7596dc4567-4qqnd memorytest Free Memory:0.09555536
Aug 31 20:33:05 memory-test-deployment-7596dc4567-4qqnd memorytest Total Memory:3.3226752
Aug 31 20:33:05 memory-test-deployment-7596dc4567-4qqnd memorytest Max Memory:3.3226752
Aug 31 20:33:05 memory-test-deployment-7596dc4567-4qqnd memorytest ##############################

---

With 
ENTRYPOINT ["java","-XX:MaxRAMPercentage=80","-jar","/app.jar"]

  resources:
    limits:
      memory: 4Gi
    requests:
      memory: 4Gi

`jcmd 1 VM.flags`

> -XX:CICompilerCount=2 -XX:InitialHeapSize=67108864 -XX:MaxHeapSize=3437232128 -XX:MaxNewSize=1145700352 -XX:MaxRAM=4294967296 -XX:MaxRAMPercentage=80.000000 -XX:MinHeapDeltaBytes=196608 -XX:MinHeapSize=8388608 -XX:NewSize=22347776 -XX:NonNMethodCodeHeapSize=5826188 -XX:NonProfiledCodeHeapSize=122916026 -XX:OldSize=44761088 -XX:ProfiledCodeHeapSize=122916026 -XX:ReservedCodeCacheSize=251658240 -XX:+SegmentedCodeCache -XX:SoftMaxHeapSize=3437232128 -XX:+UseCompressedClassPointers -XX:+UseCompressedOops -XX:+UseFastUnorderedTimeStamps -XX:+UseSerialGC

`jcmd 1 VM.command_line`

> VM Arguments:
jvm_args: -XX:MaxRAMPercentage=80
java_command: /app.jar
java_class_path (initial): /app.jar
Launcher Type: SUN_STANDARD

Aug 31 20:37:24 memory-test-deployment-7596dc4567-pn8h8 memorytest ######## Memory Stats ########
Aug 31 20:37:24 memory-test-deployment-7596dc4567-pn8h8 memorytest Used Memory:0.0291762
Aug 31 20:37:24 memory-test-deployment-7596dc4567-pn8h8 memorytest Free Memory:0.035769977
Aug 31 20:37:24 memory-test-deployment-7596dc4567-pn8h8 memorytest Total Memory:0.064946175
Aug 31 20:37:24 memory-test-deployment-7596dc4567-pn8h8 memorytest Max Memory:3.3226752
Aug 31 20:37:24 memory-test-deployment-7596dc4567-pn8h8 memorytest ##############################

After eat-memory?chunks=100&size=100
OOM happens, leaving a stack trace and pod is not killed

Aug 31 20:40:02 memory-test-deployment-7596dc4567-pn8h8 memorytest ######## Memory Stats ########
Aug 31 20:40:02 memory-test-deployment-7596dc4567-pn8h8 memorytest Used Memory:3.225735
Aug 31 20:40:02 memory-test-deployment-7596dc4567-pn8h8 memorytest Free Memory:0.0969403
Aug 31 20:40:02 memory-test-deployment-7596dc4567-pn8h8 memorytest Total Memory:3.3226752
Aug 31 20:40:02 memory-test-deployment-7596dc4567-pn8h8 memorytest Max Memory:3.3226752
Aug 31 20:40:02 memory-test-deployment-7596dc4567-pn8h8 memorytest ##############################

---

With 
ENTRYPOINT ["java","-XX:InitialRAMPercentage=80","-XX:MaxRAMPercentage=80","-jar","/app.jar"]

  resources:
    limits:
      memory: 4Gi
    requests:
      memory: 4Gi

`jcmd 1 VM.flags`

> -XX:CICompilerCount=2 -XX:InitialHeapSize=3867148288 -XX:InitialRAMPercentage=90.000000 -XX:MaxHeapSize=3867148288 -XX:MaxNewSize=1289027584 -XX:MaxRAM=4294967296 -XX:MaxRAMPercentage=90.000000 -XX:MinHeapDeltaBytes=196608 -XX:MinHeapSize=8388608 -XX:NewSize=1289027584 -XX:NonNMethodCodeHeapSize=5826188 -XX:NonProfiledCodeHeapSize=122916026 -XX:OldSize=2578120704 -XX:ProfiledCodeHeapSize=122916026 -XX:ReservedCodeCacheSize=251658240 -XX:+SegmentedCodeCache -XX:SoftMaxHeapSize=3867148288 -XX:+UseCompressedClassPointers -XX:+UseCompressedOops -XX:+UseFastUnorderedTimeStamps -XX:+UseSerialGC

`jcmd 1 VM.command_line`

> VM Arguments:
jvm_args: -XX:InitialRAMPercentage=90 -XX:MaxRAMPercentage=90
java_command: /app.jar
java_class_path (initial): /app.jar
Launcher Type: SUN_STANDARD

Aug 31 20:42:24 memory-test-deployment-7596dc4567-rc94z memorytest ######## Memory Stats ########
Aug 31 20:42:24 memory-test-deployment-7596dc4567-rc94z memorytest Used Memory:0.28749803
Aug 31 20:42:24 memory-test-deployment-7596dc4567-rc94z memorytest Free Memory:3.4508066
Aug 31 20:42:24 memory-test-deployment-7596dc4567-rc94z memorytest Total Memory:3.7383046
Aug 31 20:42:24 memory-test-deployment-7596dc4567-rc94z memorytest Max Memory:3.7383046
Aug 31 20:42:24 memory-test-deployment-7596dc4567-rc94z memorytest ##############################

After eat-memory?chunks=100&size=100
OOM happens, leaving a stack trace and pod is not killed

Aug 31 20:47:25 memory-test-deployment-7596dc4567-rc94z memorytest ######## Memory Stats ########
Aug 31 20:47:25 memory-test-deployment-7596dc4567-rc94z memorytest Used Memory:3.6418762
Aug 31 20:47:25 memory-test-deployment-7596dc4567-rc94z memorytest Free Memory:0.09642781
Aug 31 20:47:25 memory-test-deployment-7596dc4567-rc94z memorytest Total Memory:3.7383046
Aug 31 20:47:25 memory-test-deployment-7596dc4567-rc94z memorytest Max Memory:3.7383046
Aug 31 20:47:25 memory-test-deployment-7596dc4567-rc94z memorytest ##############################

After eat-memory?chunks=100&size=100
(tried again, still up, OOM happens again but pod stays up)

---

With 
ENTRYPOINT ["java","-XX:InitialRAMPercentage=100","-XX:MaxRAMPercentage=100","-jar","/app.jar"]

  resources:
    limits:
      memory: 4Gi
    requests:
      memory: 4Gi

`jcmd 1 VM.flags`

> -XX:CICompilerCount=2 -XX:InitialHeapSize=4294967296 -XX:InitialRAMPercentage=100.000000 -XX:MaxHeapSize=4294967296 -XX:MaxNewSize=1431633920 -XX:MaxRAM=4294967296 -XX:MaxRAMPercentage=100.000000 -XX:MinHeapDeltaBytes=196608 -XX:MinHeapSize=8388608 -XX:NewSize=1431633920 -XX:NonNMethodCodeHeapSize=5826188 -XX:NonProfiledCodeHeapSize=122916026 -XX:OldSize=2863333376 -XX:ProfiledCodeHeapSize=122916026 -XX:ReservedCodeCacheSize=251658240 -XX:+SegmentedCodeCache -XX:SoftMaxHeapSize=4294967296 -XX:+UseCompressedClassPointers -XX:+UseCompressedOops -XX:+UseFastUnorderedTimeStamps -XX:+UseSerialGC

`jcmd 1 VM.command_line`

>VM Arguments:
jvm_args: -XX:InitialRAMPercentage=100 -XX:MaxRAMPercentage=100
java_command: /app.jar
java_class_path (initial): /app.jar
Launcher Type: SUN_STANDARD

Aug 31 21:03:16 memory-test-deployment-7596dc4567-6jcxv memorytest ######## Memory Stats ########
Aug 31 21:03:16 memory-test-deployment-7596dc4567-6jcxv memorytest Used Memory:0.31827793
Aug 31 21:03:16 memory-test-deployment-7596dc4567-6jcxv memorytest Free Memory:3.8335588
Aug 31 21:03:16 memory-test-deployment-7596dc4567-6jcxv memorytest Total Memory:4.151837
Aug 31 21:03:16 memory-test-deployment-7596dc4567-6jcxv memorytest Max Memory:4.151837
Aug 31 21:03:16 memory-test-deployment-7596dc4567-6jcxv memorytest ##############################

After eat-memory?chunks=100&size=100
OOM happens, leaving a stack trace and pod is not killed

Aug 31 21:06:01 memory-test-deployment-7596dc4567-6jcxv memorytest ######## Memory Stats ########
Aug 31 21:06:01 memory-test-deployment-7596dc4567-6jcxv memorytest Used Memory:4.045038
Aug 31 21:06:01 memory-test-deployment-7596dc4567-6jcxv memorytest Free Memory:0.10679836
Aug 31 21:06:01 memory-test-deployment-7596dc4567-6jcxv memorytest Total Memory:4.151837
Aug 31 21:06:01 memory-test-deployment-7596dc4567-6jcxv memorytest Max Memory:4.151837
Aug 31 21:06:01 memory-test-deployment-7596dc4567-6jcxv memorytest ##############################

After eat-memory?chunks=100&size=1000
OOM happens, leaving a stack trace and pod IS killed
(tested this one agin on a fresh start and it's the size 1000 isn't what immediatly kills it, it's the repeat)

---

(To shrink a JVM)

ENTRYPOINT ["java","-XX:MaxHeapFreeRatio=10","-XX:InitialRAMPercentage=90","-XX:MaxRAMPercentage=90","-jar","/app.jar"]

> kubectl -n james-test logs  memory-test-deployment-7596dc4567-q5rhf

MinHeapFreeRatio (40) must be less than or equal to MaxHeapFreeRatio (10)
MaxHeapFreeRatio (10) must be greater than or equal to MinHeapFreeRatio (40)
Error: Could not create the Java Virtual Machine.
Error: A fatal exception has occurred. Program will exit.

ENTRYPOINT ["java","-XX:MinHeapFreeRatio=20","-XX:MaxHeapFreeRatio=20","-XX:InitialRAMPercentage=90","-XX:MaxRAMPercentage=90","-jar","/app.jar"]

Aug 31 21:35:58 memory-test-deployment-7596dc4567-sm9sd memorytest ######## Memory Stats ########
Aug 31 21:35:58 memory-test-deployment-7596dc4567-sm9sd memorytest Used Memory:0.28749928
Aug 31 21:35:58 memory-test-deployment-7596dc4567-sm9sd memorytest Free Memory:3.4508052
Aug 31 21:35:58 memory-test-deployment-7596dc4567-sm9sd memorytest Total Memory:3.7383046
Aug 31 21:35:58 memory-test-deployment-7596dc4567-sm9sd memorytest Max Memory:3.7383046
Aug 31 21:35:58 memory-test-deployment-7596dc4567-sm9sd memorytest ##############################

After eat-memory?chunks=100&size=100
OOM happens, leaving a stack trace and pod is not killed

When does it shrink? (Seems not fast)

Aug 31 21:37:22 memory-test-deployment-7596dc4567-sm9sd memorytest ######## Memory Stats ########
Aug 31 21:37:22 memory-test-deployment-7596dc4567-sm9sd memorytest Used Memory:3.6418724
Aug 31 21:37:22 memory-test-deployment-7596dc4567-sm9sd memorytest Free Memory:0.09643176
Aug 31 21:37:22 memory-test-deployment-7596dc4567-sm9sd memorytest Total Memory:3.7383046
Aug 31 21:37:22 memory-test-deployment-7596dc4567-sm9sd memorytest Max Memory:3.7383046
Aug 31 21:37:22 memory-test-deployment-7596dc4567-sm9sd memorytest ##############################
Aug 31 21:40:11 memory-test-deployment-7596dc4567-sm9sd memorytest ######## Memory Stats ########
Aug 31 21:40:11 memory-test-deployment-7596dc4567-sm9sd memorytest Used Memory:3.6421561
Aug 31 21:40:11 memory-test-deployment-7596dc4567-sm9sd memorytest Free Memory:0.09614837
Aug 31 21:40:11 memory-test-deployment-7596dc4567-sm9sd memorytest Total Memory:3.7383046
Aug 31 21:40:11 memory-test-deployment-7596dc4567-sm9sd memorytest Max Memory:3.7383046
Aug 31 21:40:11 memory-test-deployment-7596dc4567-sm9sd memorytest ##############################
Aug 31 22:00:17 memory-test-deployment-7596dc4567-sm9sd memorytest ######## Memory Stats ########
Aug 31 22:00:17 memory-test-deployment-7596dc4567-sm9sd memorytest Used Memory:3.6423526
Aug 31 22:00:17 memory-test-deployment-7596dc4567-sm9sd memorytest Free Memory:0.09595139
Aug 31 22:00:17 memory-test-deployment-7596dc4567-sm9sd memorytest Total Memory:3.7383046
Aug 31 22:00:17 memory-test-deployment-7596dc4567-sm9sd memorytest Max Memory:3.7383046
Aug 31 22:00:17 memory-test-deployment-7596dc4567-sm9sd memorytest ##############################

# Resources

* https://medium.com/@yortuc/jvm-memory-allocation-in-docker-container-a26bbce3a3f2
* https://medium.com/adorsys/jvm-memory-settings-in-a-container-environment-64b0840e1d9e
* https://stackoverflow.com/questions/54591870/mismatch-between-spring-actuators-jvm-memory-max-metric-and-runtime-getruntim
* https://merikan.com/2019/04/jvm-in-a-container/
* https://srvaroa.github.io/jvm/kubernetes/memory/docker/oomkiller/2019/05/29/k8s-and-java.html
* https://stackoverflow.com/questions/54292282/clarification-of-meaning-new-jvm-memory-parameters-initialrampercentage-and-minr