#!/bin/bash

SHARP_JVM_HOME=$HOME/projects/sharpjvm-jvm
export SHARP_JVM_HOME

java -classpath $SHARP_JVM_HOME/test/target/classes:$SHARP_JVM_HOME/test/target/test-classes:$SHARP_JVM_HOME/memory/target/classes:$SHARP_JVM_HOME/bytecode/target/classes:$SHARP_JVM_HOME/bytecode/target/test-classes:$SHARP_JVM_HOME/memory/target/classes:$SHARP_JVM_HOME/memory/target/test-classes:$SHARP_JVM_HOME/interpreter/target/classes:$SHARP_JVM_HOME/interpreter/target/test-classes:$SHARP_JVM_HOME/jvm/target/classes:$SHARP_JVM_HOME/jvm/target/resources:$SHARP_JVM_HOME/jvm/target/test-classes com.sharpjvm.jvm.JVM $@
