# StreamX — High-Performance Stream Processing Engine

A self-contained Java stream-processing engine demonstrating Strategy, Observer, and Command patterns, Redis Streams consumer groups, PostgreSQL persistence, Python analytics, Docker, JUnit 5, and reproducible performance experiments.

> Benchmark numbers in the resume should be treated as targets/claims to reproduce. This repository does not fabricate measured results.

## Architecture

```text
Producer -> Pipeline<T,R> -> Stage strategies -> Observer events
                         |-> PostgreSQL persistence
                         |-> Redis Streams consumer group
                         |-> Metrics / Python analytics
```

## Java core
- `Pipeline<T,R>` generic composition
- `Stage<T,R>` Strategy abstraction
- Observer-based lifecycle/event notifications
- Command objects for configuration mutations with undo/redo
- Thread-local reusable `Event` pool for allocation experiments
- Backpressure-aware bounded queue

## Run tests

```bash
cd streamx-core
mvn test
```

## Run demo

```bash
cd streamx-core
mvn -q exec:java -Dexec.mainClass=com.streamx.demo.StreamXDemo
```

## Start infrastructure

```bash
docker compose up -d postgres redis
```

## Analytics

```bash
python -m venv .venv
# Windows: .venv\\Scripts\\activate
source .venv/bin/activate
pip install -r analytics/requirements.txt
python analytics/analyze.py
```

The analytics script consumes a CSV export and reports latency percentiles, throughput, event-type distributions, and before/after benchmark comparisons.

## Performance experiments

See `docs/PERFORMANCE.md` for async-profiler commands, PostgreSQL `EXPLAIN ANALYZE` workflow, and Redis Streams burst testing. Do not publish benchmark figures until they are measured on your target hardware/configuration.

## GitHub Actions

CI runs Maven tests and Python analytics tests on every push/PR.
# StreamX
