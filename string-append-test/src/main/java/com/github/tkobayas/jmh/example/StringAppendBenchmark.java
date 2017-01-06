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
    public String testConcatString() {
        String str = "";
        for ( int i = 0; i < loop; i++ ) {
            str += "ABC";
        }
        return str;
    }

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
        Options opt = new OptionsBuilder().include( StringAppendBenchmark.class.getSimpleName() ).warmupIterations( 5 ).measurementIterations( 5 ).forks( 2 )
                .build();

        new Runner( opt ).run();
    }
}