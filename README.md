# Concurrent queue

### Content
* [Description](#description)
* [2 concurrent models](#2-concurrent-models)
* [Queue interfaces](#queue-interfaces)
* [Single thread queue implementation](#single-thread-queue-implementation)
* [Blocking queue implementation](#blocking-queue-implementation)
* [Non blocking queue implementation](#non-blocking-queue-implementation)

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

### Queue interfaces
There are 2 types of queue interface:
* [Queue](/src/main/java/com/java/queue/interfaces/Queue.java) - default queue interface with only 3 methods:
  * `offer`- add new element to the end of queue (add to the tail)
  * `poll` - remove and get element from the beginning of the queue (get from head)
  * `size` - get current queue size. Don't confuse with `capapcity` - how many elements can be in the queue overall, but size - how many elements currently resides in the queue. So we can have queue with capacity=100, that means we can put max 100 elements into queue. But size can be 10 - which means, currently only 10 elements are in the queue.
* [BlockingQueue](/src/main/java/com/java/queue/interfaces/BlockingQueue.java) - blocking queue interface, it's similar to standard queue, but has different names for adding/reading elements, to signify that those are blocking operations:
  * `put` - add new element, similar to `offer`, but offer returns immediately, where put would wait until messages is inserted into queue
  * `take` - read element from the beginning of the queue, similar to `poll`, but it would wait if queue is empty.
Usually for compatibility your `BlockingQueue` should extend `Queue` to make sure that all operations are supported. For the sake of simplicity we would extend it, but won't implement `Queue` methods in BlockingQueue implementations.

### Single thread queue implementation
Before going into multithreaded environment, it's useful to understand how we can write queue code in single-threaded environment. Generally it's considered a best practice approach, before starting to write any concurrent code, understand how it's supposed to work in single thread environment. So we would add simple queue implementation here.

### Blocking queue implementation

### Non blocking queue implementation
