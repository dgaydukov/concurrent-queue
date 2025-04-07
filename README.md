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
We have 3 implementation of `Queue` interface:
* [BoundedArrayQueue](/src/main/java/com/java/queue/singlethread/BoundedArrayQueue.java) - standard queue implementation with 3 variables `head/tail/size`, where for `offer` we increase `tail`, and for `poll` we increase `head`. `size` is changed in both methods.
* [BoundedArrayQueue2](/src/main/java/com/java/queue/singlethread/BoundedArrayQueue2.java) - same as previous one, but here we used only 2 variables `head/tail`. We can accomplish this by making them `long`, and increase them forever. This is a nice way, because you need fewer variables, and by printing `head` - you can always understand how many elements we totally added. 
* [UnBoundedArrayQueue](/src/main/java/com/java/queue/singlethread/UnBoundedArrayQueue.java) - same as first one, but we have added `grow` function to infinitely increase queue size


### Blocking queue implementation
Blocking programming is quite simple. We use locks to get exclusive access for block of code, and only one thread at a time can access it. This simplifies code, because it can be used by all possible scenario from single producer, single consumer all the way to multiple producers multiple consumers (for non-blocking you may have 4 different classes). The code is quite straight-forward. You can take look into [BoundedArrayBlockingQueue](/src/main/java/com/java/queue/blocking/BoundedArrayBlockingQueue.java) to see how it works. Basically we use `ReentrantLock` with 2 conditions for `put/take` methods. Lock ensures that only 1 thread at a time can access any of 3 methods of `put/take/size`. And conditions with `await/signal` keeps threads waiting for element.

### Non blocking queue implementation
Generally using blocking is bad for performance, so you should prefer non-blocking concurrency.
With blocking queue everything is clear. But how non-blocking algo is working. The trick is quite simple, we can use CAS (compare-and-swap) algo to achieve non-blocking concurrency. If 2 threads try to update variable with CAS, only one will succeed. How this helps us. We can write spinning code with `while (true)` where on each iteration we read up-to-date value from main memory and try to update it. To write such code in java, you would need to be able to read from main memory directly and call CAS operations. In java you have 2 options to do it:
* `Unsafe` - old way, prone to errors, but very efficient. It's widely used by Aeron library and many others.
* `VarHandle` - new safely way to manipulate raw memory in java9.
We would use second class to write our code.
There are a few nice features:
* CAS is generally much faster than locking
* for SPSC (single producer, single consumer) - producer and consumer not contend with each other, providing fast performance, way faster than any blocking queue implementation. .
Downsides:
* contention exist when multiple producers try to add elements into queue
* since CAS force retry if value changed, thread may stuck in busy-wait constantly trying to call CAS and failing
* coding is much more difficult than locking
There are several implementation here:
* [OneToOneBoundedArrayQueueSimple](/src/main/java/com/java/queue/nonblocking/OneToOneBoundedArrayQueueSimple.java) - this is naive/simple implementation, but it still works, yet may be slow at times. The problem here, is that `head/tail` are both volatile, and since they changed only by one thread (one thread offering and changes `tail`, another thread polling and changes `head`), they would behave correctly and would be visible to each other. Yet changes made to array may not be immediately visible. That's why for polling, we check that value is not null, and only in this case we increase `head`. So it's correct, but slow. Ideal way if we can change array directly. In java, this can be achieved with either one `Unsafe` or `VarHandle`.
*  [OneToOneBoundedArrayQueue](/src/main/java/com/java/queue/nonblocking/OneToOneBoundedArrayQueue.java) - this is more advanced version of previous queue. Here we heavily use `VarHandle` to directly manipulate main memory. This ensures that changes in underlying variables and inside array, would be visible immediately. Pay attention, that we write/read array elements directly from main memory using `VarHandle`. You can achieve same results with `Unsafe`. Many older libraries using this approach with `Unsafe`, but since java9, it's suggested to use `VarHandle` for such tasks.
* [ManyToOneBoundedArrayQueue](/src/main/java/com/java/queue/nonblocking/ManyToOneBoundedArrayQueue.java) - this is similar to one-to-one, but this time we have multiple producers compete to add elements into queue. Since we have single consumer, the code for `poll` is not changed at all, but the code for `offer` has some changes. To achieve non-blocking concurrency when we have multiple threads competing to change value, we use CAS operations. `VarHandl` (just like `Unsafe`) provides a few versions of compare-and-swap implementations. Here only one thread, that can successfully update tail, can proceed further and modify array.