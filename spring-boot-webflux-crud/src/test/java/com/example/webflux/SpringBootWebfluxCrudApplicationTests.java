package com.example.webflux;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Optional;
import java.util.Random;

@SpringBootTest
class SpringBootWebfluxCrudApplicationTests {

    @Test
    void flux() throws InterruptedException {
        Flux.just("Hello", "World").subscribe(System.out::println);
        Flux.fromArray(new Integer[]{1, 2, 3}).subscribe(System.out::println);
        Flux.empty().subscribe(System.out::println);
        Flux.range(1, 4).subscribe(System.out::println);
        Flux.interval(Duration.of(1, ChronoUnit.SECONDS)).subscribe(System.out::println);
        // 线程延迟关闭，不然最后一个例子木有输出
        Thread.currentThread().join(10000);
    }

    @Test
    void flux1() {
        Flux.generate(sink -> {
            sink.next("Hello");
            sink.complete();
        }).subscribe(System.out::println);


        final Random random = new Random();
        Flux.generate(ArrayList::new, (list, sink) -> {
            int value = random.nextInt(100);
            list.add(value);
            sink.next(value);
            if (list.size() == 10) {
                sink.complete();
            }
            return list;
        }).subscribe(System.out::println);
    }

    @Test
    void flux2() {
        Flux.create(sink -> {
            for (int i = 0; i < 10; i++) {
                sink.next(i);
            }
            sink.complete();
        }).subscribe(System.out::println);
    }

    @Test
    void mono() {
        Mono.just("are").subscribe(System.out::println);
        Mono.empty().subscribe(System.out::println);
        Mono.fromSupplier(() -> "you").subscribe(System.out::println);
        Mono.justOrEmpty(Optional.of("ok")).subscribe(System.out::println);
    }


    @Test
    void buff() {
        //输出 5个包含 20 个元素的数组
        Flux.range(1, 100).buffer(20).subscribe(System.out::println);
        //bufferUntil 会一直收集直到 Predicate 返回为 true。使得 Predicate 返回 true 的那个元素可以选择添加到当前集合或下一个集合中；
        // 输出的是 5 个包含 2 个元素的数组
        // 每当遇到一个偶数就会结束当前的收集
        Flux.range(1, 10).bufferUntil(i -> i % 2 == 0).subscribe(System.out::println);
        // 第四行语句输出的是 5 个包含 1 个元素的数组
        // 数组里面包含的只有偶数。
        Flux.range(1, 10).bufferWhile(i -> i % 2 == 0).subscribe(System.out::println);
    }
}
