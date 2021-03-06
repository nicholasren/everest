### Why everest?

<img src="./docs/images/mt-everest-summer.jpg" width="400"/>

This project aims to create a clone of [akka](http://akka.io). This project is for learning purpose,

As developer, we don't only want to know how to use it, we also want to understand the implementation, the design behind it.

Akka is a toolkit and runtime for building highly concurrent, distributed, and resilient message-driven applications on the JVM.

We'd like to understand what make it _highly concurrent_, _distributed_, _resilient_.

so let's create our own version of akka - **everest**


###Tasks

#### everest-actor
Actor is a lightweight concept for concurrency.

##### key concepts:

- Actor System

    a system which has thread pool behind, responsible to execute actors

- Actor

    Actor is just a bunch of code which only respond to messages in its mailbox.
    Actor can not be initialized other than ActorSystem
    representing a actor(locally or remotely), can be used to send message to the underlying actor

- ActorCell

    a Actor's stub within ActorSystem(can it be replaced with Actor?)

    - a actor should be created via ActorSystem
    - a actor should be able to receive message
    - a actor should be able to process message

- Mailbox
- Actor Selection & ActorRef
- Actor
- SupervisorStrategy
- Actor Deployment


### Tasks:
local actor
    - creation
        - actor path
           - top level :
                `akka://<actor-system-name>/<actor-name>`
           - created by parent actor:
                `akka://<actor-system-name>/<parent-actor-name>/<actor-name>`

    - lookup
    - run synchronously
    - run asynchronously(driven by a thread pool)
    - be aware of sender
        - an inbox can function as a implicit sender
    - respond to `ask` 
    - supervisation
