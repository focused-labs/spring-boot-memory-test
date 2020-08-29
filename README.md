Without any special settings or limits / resources

`jcmd 1 VM.flags`

> -XX:CICompilerCount=2 -XX:InitialHeapSize=216006656 -XX:MaxHeapSize=3430940672 -XX:MaxNewSize=1143603200 -XX:MinHeapDeltaBytes=196608 -XX:MinHeapSize=8388608 -XX:NewSize=71958528 -XX:NonNMethodCodeHeapSize=5826188 -XX:NonProfiledCodeHeapSize=122916026 -XX:OldSize=144048128 -XX:ProfiledCodeHeapSize=122916026 -XX:ReservedCodeCacheSize=251658240 -XX:+SegmentedCodeCache -XX:SoftMaxHeapSize=3430940672 -XX:+UseCompressedClassPointers -XX:+UseCompressedOops -XX:+UseFastUnorderedTimeStamps -XX:+UseSerialGC

`jcmd 1 VM.command_line`

> VM Arguments:
java_command: /app.jar
java_class_path (initial): /app.jar
Launcher Type: SUN_STANDARD