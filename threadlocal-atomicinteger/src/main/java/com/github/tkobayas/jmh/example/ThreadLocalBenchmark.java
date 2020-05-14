package com.github.tkobayas.jmh.example;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Warmup;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

@BenchmarkMode(Mode.SingleShotTime)
//@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
//@Warmup(iterations = 2, time = 2, timeUnit = TimeUnit.SECONDS)
//@Measurement(iterations = 2, time = 2, timeUnit = TimeUnit.SECONDS)
public class ThreadLocalBenchmark {

    private static final int loop = 10000;

    private static final ThreadLocal<Integer> count = new ThreadLocal<>();

    private static final ThreadLocal<AtomicInteger> atomicCount = new ThreadLocal<>();

    @Benchmark
    public int testInteger() {
        count.set(0);
        for (int i = 0; i < loop; i++) {
            count.set(count.get() + 1);
        }
        return count.get().intValue();
    }

    @Benchmark
    public int testAtomicInteger() {
        atomicCount.set(new AtomicInteger(0));
        for (int i = 0; i < loop; i++) {
            atomicCount.get().getAndIncrement();
        }
        return atomicCount.get().intValue();
    }

    public static void main(String[] args) throws RunnerException, IOException {
        Options opt = new OptionsBuilder().include(ThreadLocalBenchmark.class.getSimpleName()).warmupIterations(2).measurementIterations(2).forks(1)
                                          .build();

        new Runner(opt).run();
    }
}
