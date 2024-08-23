![1000001499.jpg](https://prod-files-secure.s3.us-west-2.amazonaws.com/a4504163-b45e-4d2e-be24-1b01f4fea7e2/11097c4c-0e88-4ea4-8646-86f450a29392/1000001499.jpg)

- Each elevator object runs on its own thread, independent of each other.
- When a thread enters the wait() state, it means it releases the lock.
- notify() releases the lock and wakes up the other thread which is waiting.

**Interaction between ElevatorController and Elevator class**

In your elevator system, the interaction with a particular `Elevator` object primarily involves two threads:

1. **Main Thread (or the Controller Thread):**
    - This thread handles the initialization of the `Elevator` objects and manages the interaction with them by calling methods like `requestElevator`.
    - When a new request comes in, this thread will find the optimal elevator and invoke the `addRequest` method on the appropriate `Elevator` object.
2. **Elevator's Own Thread:**
    - Each `Elevator` object has its own dedicated thread that continuously runs the `run` method, which in turn calls `processRequests`.
    - If there are no requests in the queue, this thread will enter a wait state by calling `wait()`. This happens within the `getNextRequest` method.

**Elevator Entering the Wait State**

- **Waiting:**
    - When the `Elevator` thread is running its `processRequests` method and finds that the request queue is empty, it will call `wait()`. This releases the lock on the `Elevator` object and puts the thread into a waiting state.
    - The thread stays in this state until it is notified by another thread, indicating that a new request has been added to the queue.
- **Notification:**
    - The `addRequest` method, which is likely called by the main thread or controller thread when a new request is made, adds the request to the `Elevator`’s queue. After adding the request, it calls `notifyAll()`, which wakes up the waiting `Elevator` thread.
    - Once notified, the `Elevator` thread will reacquire the lock, continue its execution, and process the new request.

**Interaction Between Threads**

- The main thread and the elevator thread interact via the request queue. The main thread adds requests, and the elevator thread processes them.
- The `wait()` and `notifyAll()` mechanism ensures that the `Elevator` thread efficiently waits when there are no requests and resumes processing as soon as a new request is available.

This design allows the elevator to be responsive without constantly polling the queue, which would waste CPU resources. Instead, it effectively sleeps and wakes up as needed.
