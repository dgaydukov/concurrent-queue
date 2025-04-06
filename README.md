# Concurrent queue

### Content
* [Description](#description)


### Description
The goal of the project is to show different approaches to write concurrent code. We choose queue, because it's the most common task in multi-threaded programming, knows as producer-consumer problem. When you have 2 threads, they can communicate with each other using such a queue, where one thread would be writing and another reading. There are several scenarios:
* single writer, single reader
* multiple writers, single reader
* single writer, multiple readers
* multiple writers, multiple readers