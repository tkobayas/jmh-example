package com.github.tkobayas.jmh.example;

import java.io.IOException;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

public class StringAppendBenchmark {
    private static final int loop = 1000;

    @Benchmark
    public String testPlusString() {
        String str = "";
        for ( int i = 0; i < loop; i++ ) {
            str += "ABC";
        }
        return str;
    }

    //    @Benchmark
    //    public String testConcatString() {
    //        String str = "";
    //        for ( int i = 0; i < loop; i++ ) {
    //            str = str.concat( "ABC" );
    //        }
    //        return str;
    //    }

    @Benchmark
    public String testStringBuffer() {
        StringBuffer buffer = new StringBuffer();
        for ( int i = 0; i < loop; i++ ) {
            buffer.append( "ABC" );
        }
        return buffer.toString();
    }

    @Benchmark
    public String testStringBuilder() {
        StringBuilder builder = new StringBuilder();
        for ( int i = 0; i < loop; i++ ) {
            builder.append( "ABC" );
        }
        return builder.toString();
    }

    public static void main( String[] args ) throws RunnerException, IOException {
        Options opt = new OptionsBuilder().include( StringAppendBenchmark.class.getSimpleName() ).warmupIterations( 2 ).measurementIterations( 2 ).forks( 1 )
                .build();

        new Runner( opt ).run();
    }
}