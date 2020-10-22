@echo off
call "jvm.bat" -classpath .\test\target\classes com.sharpjvm.jvm.test.FibonacciTest
call "jvm.bat" -classpath .\test\target\classes com.sharpjvm.jvm.test.FloatTest
call "jvm.bat" -classpath .\test\target\classes com.sharpjvm.jvm.test.InnerClassTest
call "jvm.bat" -classpath .\test\target\classes com.sharpjvm.jvm.test.IntTest
call "jvm.bat" -classpath .\test\target\classes com.sharpjvm.jvm.test.LongTest
call "jvm.bat" -classpath .\test\target\classes com.sharpjvm.jvm.test.MergeSortTest
call "jvm.bat" -classpath .\test\target\classes com.sharpjvm.jvm.test.complex.Main
