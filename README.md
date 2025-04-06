# Concurrent queue

### Content
* [Description](#description)
* [2 concurrent models](#2-concurrent-models)
* [Single-threaded queue implementation](#single-threaded-queue-implementation)

### Description
The goal of the project is to show different approaches to write concurrent code. We choose queue, because it's the most common task in multithreaded programming, knows as producer-consumer problem. When you have 2 threads, they can communicate with each other using such a queue, where one thread would be writing and another reading. There are several scenarios:
* single writer, single reader
* multiple writers, single reader
* single writer, multiple readers
* multiple writers, multiple readers

### 2 concurrent models
There are 2 main concurrent models in java: blocking and non-blocking. They differ in their implementation, and use different approaches. But both working well. Let's take a close look.
Blocking concurrency:
* we use locks explicitly (keyword `synchonized` use lock implicitly under-the-hood)
* threads waiting in queue to get exclusive access
* only one thread at a time can access the code
* implementation is the same regardless of how many producers/consumers are there
* use following in java: `synchronized`, `Lock`
Non-blocking concurrency:
* use atomic operations and CAS (compare-and-swap)
* no locking is used (either explicit or implicit)
* threads are spinning to get access
* implementation may differ depending on access model (single producer, single consumer would be very different from mulitple producers, multiple consumers)
* use following in java: `VarHandle`, CAS, volatile, loops
* it's a bit faster than blocking concurrency

### Single-threaded queue implementation
Before going into multithreaded environment, it's useful to understand how we can write queue code in single-threaded environment. Generally it's considered a best practice approach, before starting to write any concurrent code, understand how it's supposed to work in single thread environment. So we would add simple queue implementation here.