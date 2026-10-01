# Architecture

`Pipeline<T,R>` owns ordered `Stage` strategies. Each stage has one responsibility and can be unit-tested in isolation. Observer subscribers receive START/COMPLETE events without coupling the pipeline to monitoring. Command objects mutate configuration and retain reversible history through `CommandHistory`.

For production integration, Redis Streams should use `XGROUP`, `XREADGROUP`, and `XACK`; PostgreSQL persists durable event state. A bounded executor/queue is preferred over unlimited asynchronous work so overload becomes controlled backpressure rather than memory growth.
