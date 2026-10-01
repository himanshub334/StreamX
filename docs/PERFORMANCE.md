# Performance methodology

## async-profiler
Build the Java module, then profile a representative workload:

```bash
./profiler.sh -e cpu -d 30 -f flamegraph.html -- java -jar target/streamx.jar
```

Look for allocation/hot-path evidence before changing code. For allocation profiling, use the profiler's allocation event and compare identical workloads before/after.

## PostgreSQL
Run:

```sql
EXPLAIN (ANALYZE, BUFFERS) SELECT * FROM events WHERE event_type='click' AND processed=false ORDER BY created_at DESC LIMIT 100;
```

Compare the plan before/after the partial index in `sql/schema.sql`.

## Redis Streams
Use a stream plus consumer group, acknowledge messages only after successful processing, and measure pending entries during a burst. This gives explicit backpressure/ack semantics unlike fire-and-forget Pub/Sub.

## Reporting benchmark results
Record hardware, JVM, dataset size, concurrency, warm-up, number of iterations, p50/p95/p99 latency, throughput and error count. Only then add numeric results to a resume.
