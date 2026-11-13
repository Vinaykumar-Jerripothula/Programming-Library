
public class FuturesAndAsynchronousProgramming {

    /*
     * ============================================================
     * 1. WHY DO WE NEED ASYNCHRONOUS PROGRAMMING?
     * ============================================================
     *
     * Imagine a backend application performing three operations:
     *
     * 1. Fetch user details from a database.
     * 2. Fetch order details from another service.
     * 3. Fetch payment details from another service.
     *
     * If we execute them sequentially:
     *
     * First operation  -> 2 seconds
     * Second operation -> 3 seconds
     * Third operation  -> 2 seconds
     *
     * Total time = 7 seconds.
     *
     * If the operations are independent, we may execute
     * them concurrently.
     *
     * The total time could be close to the duration
     * of the slowest operation, rather than their sum.
     *
     * This is one reason asynchronous programming is useful.
     *
     *
     * ============================================================
     * 2. SYNCHRONOUS VS ASYNCHRONOUS EXECUTION
     * ============================================================
     *
     * SYNCHRONOUS:
     *
     * The caller performs an operation and waits for it
     * to finish before proceeding.
     *
     * Example:
     *
     * String result = fetchData();
     *
     * System.out.println(result);
     *
     * The next statement executes after fetchData() returns.
     *
     *
     * ASYNCHRONOUS:
     *
     * The caller starts an operation and continues without
     * necessarily waiting for its completion.
     *
     * Example:
     *
     * executor.submit(() -> {
     *
     *     fetchData();
     * });
     *
     * System.out.println("Main flow continues");
     *
     * The submitted task may execute independently
     * of the caller's current flow.
     *
     * IMPORTANT:
     *
     * Asynchronous execution does not automatically mean
     * parallel execution.
     *
     * It means the caller does not have to wait immediately.
     *
     *
     * ============================================================
     * 3. WHAT IS A RESULT OF AN ASYNCHRONOUS TASK?
     * ============================================================
     *
     * Consider:
     *
     * Callable<Integer> task = () -> {
     *
     *     return 100 + 200;
     * };
     *
     * The result is not necessarily available immediately
     * after the task is submitted.
     *
     * We need an object that represents the future result.
     *
     * Java provides Future for this purpose.
     *
     *
     * ============================================================
     * 4. FUTURE INTERFACE
     * ============================================================
     *
     * Future<T> represents the result of an asynchronous
     * computation.
     *
     * It belongs to:
     *
     * java.util.concurrent
     *
     * It allows us to:
     *
     * - Check whether a task is completed.
     * - Wait for a task's result.
     * - Retrieve the result.
     * - Cancel a task.
     * - Check whether a task was cancelled.
     *
     * Important methods:
     *
     * get()
     * get(timeout, unit)
     * isDone()
     * isCancelled()
     * cancel()
     *
     *
     * ============================================================
     * 5. CREATING A FUTURE USING EXECUTORSERVICE
     * ============================================================
     *
     * Example:
     *
     * ExecutorService executor =
     *     Executors.newSingleThreadExecutor();
     *
     * Future<Integer> future = executor.submit(() -> {
     *
     *     return 100 + 200;
     * });
     *
     * System.out.println(future.get());
     *
     * executor.shutdown();
     *
     * Explanation:
     *
     * 1. Submit the task.
     * 2. The executor schedules it for execution.
     * 3. submit() returns a Future.
     * 4. get() waits if the result is not ready.
     * 5. The result is returned after completion.
     *
     *
     * ============================================================
     * 6. FUTURE.GET()
     * ============================================================
     *
     * get() retrieves the task's result.
     *
     * Example:
     *
     * Future<String> future = executor.submit(() -> {
     *
     *     return "Task completed";
     * });
     *
     * String result = future.get();
     *
     * System.out.println(result);
     *
     * IMPORTANT:
     *
     * get() is a blocking operation.
     *
     * If the task has not completed, the calling thread
     * waits until:
     *
     * - The task completes.
     * - The task is cancelled.
     * - The waiting thread is interrupted.
     * - The task fails.
     *
     *
     * ============================================================
     * 7. FUTURE.GET() WITH TIMEOUT
     * ============================================================
     *
     * Sometimes we do not want to wait indefinitely.
     *
     * Java provides:
     *
     * get(long timeout, TimeUnit unit)
     *
     * Example:
     *
     * Future<String> future = executor.submit(() -> {
     *
     *     Thread.sleep(3000);
     *
     *     return "Completed";
     * });
     *
     * try {
     *
     *     String result =
     *         future.get(1, TimeUnit.SECONDS);
     *
     * } catch (TimeoutException e) {
     *
     *     System.out.println("Task took too long");
     * }
     *
     * IMPORTANT:
     *
     * A timeout from get() does not automatically cancel
     * the underlying task.
     *
     * The task may continue executing.
     *
     *
     * ============================================================
     * 8. ISDONE()
     * ============================================================
     *
     * isDone() checks whether a task has completed.
     *
     * Example:
     *
     * if (future.isDone()) {
     *
     *     System.out.println("Task has completed");
     * }
     *
     * It returns true when the task:
     *
     * - Completed normally.
     * - Completed exceptionally.
     * - Was cancelled.
     *
     * It does not mean the task completed successfully.
     *
     *
     * ============================================================
     * 9. ISCANCELLED()
     * ============================================================
     *
     * isCancelled() checks whether the task was cancelled
     * before it completed normally.
     *
     * Example:
     *
     * if (future.isCancelled()) {
     *
     *     System.out.println("Task was cancelled");
     * }
     *
     *
     * ============================================================
     * 10. CANCEL()
     * ============================================================
     *
     * cancel() attempts to cancel a task.
     *
     * Method:
     *
     * cancel(boolean mayInterruptIfRunning)
     *
     * Example:
     *
     * future.cancel(true);
     *
     * true:
     *
     * If the task is running, an attempt is made to interrupt
     * its executing thread.
     *
     * false:
     *
     * Do not interrupt a task that is already running.
     *
     * A task that has not started can still be cancelled.
     *
     * IMPORTANT:
     *
     * Cancellation is cooperative.
     *
     * Interrupting a thread does not guarantee that
     * the task immediately stops.
     *
     *
     * ============================================================
     * 11. EXCEPTION HANDLING IN FUTURE
     * ============================================================
     *
     * If a submitted Callable throws an exception,
     * Future.get() reports it through ExecutionException.
     *
     * Example:
     *
     * Future<Integer> future = executor.submit(() -> {
     *
     *     throw new IllegalArgumentException("Invalid input");
     * });
     *
     * try {
     *
     *     future.get();
     *
     * } catch (ExecutionException e) {
     *
     *     Throwable originalCause = e.getCause();
     *
     *     System.out.println(originalCause);
     * }
     *
     * Other relevant exceptions:
     *
     * InterruptedException:
     * The waiting thread was interrupted.
     *
     * ExecutionException:
     * The task completed exceptionally.
     *
     * CancellationException:
     * The task was cancelled.
     *
     * TimeoutException:
     * The timed get() did not obtain a result in time.
     *
     *
     * ============================================================
     * 12. LIMITATIONS OF FUTURE
     * ============================================================
     *
     * Future is useful, but its original API has limitations.
     *
     * 1. get() is blocking.
     *
     * 2. It does not directly provide convenient callbacks.
     *
     * 3. Combining multiple futures is difficult.
     *
     * 4. Chaining dependent operations is not convenient.
     *
     * 5. Exception recovery and transformation require
     *    additional handling.
     *
     * Consider:
     *
     * Future<Integer> future = executor.submit(task);
     *
     * int result = future.get();
     *
     * int finalResult = result * 2;
     *
     * The caller must wait before processing the result.
     *
     * Java introduced CompletableFuture to provide
     * a more powerful asynchronous programming model.
     *
     *
     * ============================================================
     * 13. COMPLETABLEFUTURE
     * ============================================================
     *
     * CompletableFuture<T> represents a future result
     * that can be explicitly completed.
     *
     * It implements:
     *
     * Future<T>
     * CompletionStage<T>
     *
     * It was introduced in Java 8.
     *
     * It supports:
     *
     * - Asynchronous execution.
     * - Callback-based processing.
     * - Chaining operations.
     * - Combining multiple tasks.
     * - Exception handling.
     * - Manual completion.
     *
     * It is one of the most important Java APIs
     * for asynchronous programming.
     *
     *
     * ============================================================
     * 14. CREATING A COMPLETABLEFUTURE
     * ============================================================
     *
     * supplyAsync() executes a task asynchronously
     * and produces a result.
     *
     * Example:
     *
     * CompletableFuture<String> future =
     *     CompletableFuture.supplyAsync(() -> {
     *
     *         return "Hello from CompletableFuture";
     *     });
     *
     * The task is submitted for asynchronous execution.
     *
     * We can retrieve the result using:
     *
     * future.get();
     *
     * or:
     *
     * future.join();
     *
     * By default, asynchronous methods without an explicit
     * executor generally use ForkJoinPool.commonPool().
     *
     *
     * ============================================================
     * 15. RUNASYNC() VS SUPPLYASYNC()
     * ============================================================
     *
     * Both methods start asynchronous work.
     *
     * runAsync():
     *
     * - Executes a task.
     * - Does not produce a result.
     * - Uses a Runnable.
     *
     * Example:
     *
     * CompletableFuture<Void> future =
     *     CompletableFuture.runAsync(() -> {
     *
     *         System.out.println("Task running");
     *     });
     *
     *
     * supplyAsync():
     *
     * - Executes a task.
     * - Produces a result.
     * - Uses a Supplier<T>.
     *
     * Example:
     *
     * CompletableFuture<Integer> future =
     *     CompletableFuture.supplyAsync(() -> {
     *
     *         return 100;
     *     });
     *
     * Remember:
     *
     * runAsync()    -> No result.
     * supplyAsync() -> Produces a result.
     *
     *
     * ============================================================
     * 16. GET() VS JOIN()
     * ============================================================
     *
     * Both methods wait for completion and retrieve
     * the completed result.
     *
     * get():
     *
     * - Inherited from Future.
     * - Throws checked InterruptedException.
     * - Throws checked ExecutionException.
     *
     * join():
     *
     * - Defined by CompletableFuture.
     * - Does not declare those checked exceptions.
     * - Reports exceptional completion using
     *   CompletionException.
     *
     * Example:
     *
     * String result = future.join();
     *
     * IMPORTANT:
     *
     * Both can block when the result is not yet available.
     *
     * join() is not automatically non-blocking.
     *
     *
     * ============================================================
     * 17. THENAPPLY()
     * ============================================================
     *
     * thenApply() transforms the result of a completed stage.
     *
     * It receives the previous result and returns
     * a new result.
     *
     * Example:
     *
     * CompletableFuture<Integer> future =
     *     CompletableFuture.supplyAsync(() -> 10)
     *         .thenApply(value -> value * 2);
     *
     * Result = 20.
     *
     * Flow:
     *
     * supplyAsync() -> Produces 10.
     *
     * thenApply()   -> Receives 10.
     *
     * thenApply()   -> Produces 20.
     *
     * It is useful for transforming results.
     *
     *
     * ============================================================
     * 18. THENACCEPT()
     * ============================================================
     *
     * thenAccept() consumes the previous result
     * without producing another result.
     *
     * Example:
     *
     * CompletableFuture.supplyAsync(() -> "Vinay")
     *
     *     .thenAccept(name -> {
     *
     *         System.out.println(name);
     *     });
     *
     * The resulting CompletableFuture has type Void.
     *
     *
     * ============================================================
     * 19. THENDRUN()
     * ============================================================
     *
     * thenRun() executes an action after the previous
     * stage completes.
     *
     * It does not receive the previous result.
     *
     * It does not produce a result.
     *
     * Example:
     *
     * CompletableFuture.supplyAsync(() -> 100)
     *
     *     .thenRun(() -> {
     *
     *         System.out.println("Previous task completed");
     *     });
     *
     *
     * ============================================================
     * 20. THENAPPLY VS THENACCEPT VS THENDRUN
     * ============================================================
     *
     * thenApply():
     *
     * Receives a result and transforms it.
     *
     * Input:  T
     * Output: U
     *
     * thenAccept():
     *
     * Receives a result and consumes it.
     *
     * Input:  T
     * Output: Void
     *
     * thenRun():
     *
     * Does not receive the previous result.
     *
     * Input:  None
     * Output: Void
     *
     *
     * ============================================================
     * 21. THENAPPLYASYNC()
     * ============================================================
     *
     * thenApply() and thenApplyAsync() both transform
     * the previous stage's result.
     *
     * thenApply():
     *
     * May execute in the thread that completes the previous
     * stage or another thread performing completion.
     *
     * thenApplyAsync():
     *
     * Schedules the action asynchronously, generally using
     * the default asynchronous execution facility.
     *
     * An explicit executor can also be supplied.
     *
     * Example:
     *
     * future.thenApplyAsync(
     *     value -> value * 2,
     *     customExecutor
     * );
     *
     * Similar async variants exist for many continuation methods:
     *
     * thenAcceptAsync()
     * thenRunAsync()
     * thenComposeAsync()
     * thenCombineAsync()
     *
     * IMPORTANT:
     *
     * Async does not always mean a new dedicated thread.
     *
     * The chosen executor controls execution.
     *
     *
     * ============================================================
     * 22. THENCOMPOSE()
     * ============================================================
     *
     * thenCompose() is used when the next operation
     * itself returns a CompletableFuture.
     *
     * It chains dependent asynchronous operations
     * without nesting futures.
     *
     * Example:
     *
     * CompletableFuture<User> userFuture =
     *     findUserAsync(userId);
     *
     * CompletableFuture<Order> orderFuture =
     *     userFuture.thenCompose(user ->
     *         findOrderAsync(user.id())
     *     );
     *
     * Flow:
     *
     * 1. Find the user asynchronously.
     * 2. Use the user result to start another asynchronous task.
     * 3. Return one flattened CompletableFuture<Order>.
     *
     *
     * ============================================================
     * 23. THENCOMPOSE VS THENAPPLY
     * ============================================================
     *
     * thenApply():
     *
     * Transforms a result.
     *
     * If the function returns CompletableFuture<U>,
     * the result becomes CompletableFuture<CompletableFuture<U>>.
     *
     * thenCompose():
     *
     * Chains a function that returns CompletableFuture<U>.
     *
     * The nested future is flattened.
     *
     * Example:
     *
     * thenApply(user -> findOrderAsync(user.id()))
     *
     * Result type:
     *
     * CompletableFuture<CompletableFuture<Order>>
     *
     *
     * thenCompose(user -> findOrderAsync(user.id()))
     *
     * Result type:
     *
     * CompletableFuture<Order>
     *
     * Remember:
     *
     * thenApply  -> Transform.
     * thenCompose -> Chain dependent async work.
     *
     *
     * ============================================================
     * 24. THENCOMBINE()
     * ============================================================
     *
     * thenCombine() combines the results of two independent
     * completion stages.
     *
     * Example:
     *
     * CompletableFuture<Integer> first =
     *     CompletableFuture.supplyAsync(() -> 10);
     *
     * CompletableFuture<Integer> second =
     *     CompletableFuture.supplyAsync(() -> 20);
     *
     * CompletableFuture<Integer> combined =
     *     first.thenCombine(
     *         second,
     *         (a, b) -> a + b
     *     );
     *
     * Result = 30.
     *
     * Both tasks can execute independently.
     *
     * The combining function runs when both complete normally.
     *
     *
     * ============================================================
     * 25. ALL OF()
     * ============================================================
     *
     * allOf() creates a CompletableFuture<Void> that
     * completes when all supplied futures complete.
     *
     * Example:
     *
     * CompletableFuture<String> first =
     *     CompletableFuture.supplyAsync(() -> "A");
     *
     * CompletableFuture<String> second =
     *     CompletableFuture.supplyAsync(() -> "B");
     *
     * CompletableFuture<Void> all =
     *     CompletableFuture.allOf(first, second);
     *
     * all.join();
     *
     * System.out.println(first.join());
     * System.out.println(second.join());
     *
     * IMPORTANT:
     *
     * allOf() does not directly return a collection
     * of the individual results.
     *
     * It returns CompletableFuture<Void>.
     *
     * Retrieve individual results from their respective futures.
     *
     *
     * ============================================================
     * 26. ANY OF()
     * ============================================================
     *
     * anyOf() creates a CompletableFuture<Object>
     * that completes when any one of the supplied
     * completion stages completes.
     *
     * Example:
     *
     * CompletableFuture<String> first =
     *     CompletableFuture.supplyAsync(() -> "First");
     *
     * CompletableFuture<String> second =
     *     CompletableFuture.supplyAsync(() -> "Second");
     *
     * CompletableFuture<Object> result =
     *     CompletableFuture.anyOf(first, second);
     *
     * System.out.println(result.join());
     *
     * IMPORTANT:
     *
     * anyOf() may complete with a normal result or
     * an exceptional completion, depending on which
     * stage completes first.
     *
     * Its result type is Object because the supplied
     * stages may have different result types.
     *
     *
     * ============================================================
     * 27. EXCEPTIONALLY()
     * ============================================================
     *
     * exceptionally() handles an exceptional completion
     * and can provide a fallback result.
     *
     * Example:
     *
     * CompletableFuture<Integer> future =
     *     CompletableFuture.supplyAsync(() -> {
     *
     *         if (true) {
     *             throw new RuntimeException("Failed");
     *         }
     *
     *         return 100;
     *
     *     }).exceptionally(exception -> {
     *
     *         return 0;
     *     });
     *
     * The fallback result is 0.
     *
     * It is useful for recovering from failures.
     *
     *
     * ============================================================
     * 28. HANDLE()
     * ============================================================
     *
     * handle() processes both:
     *
     * - Normal completion.
     * - Exceptional completion.
     *
     * It receives:
     *
     * 1. The result, if successful.
     * 2. The exception, if failed.
     *
     * Example:
     *
     * CompletableFuture<Integer> future =
     *     CompletableFuture.supplyAsync(() -> 100)
     *
     *         .handle((result, exception) -> {
     *
     *             if (exception != null) {
     *                 return 0;
     *             }
     *
     *             return result;
     *         });
     *
     * handle() can transform either successful
     * or exceptional completion into a new result.
     *
     *
     * ============================================================
     * 29. WHENCOMPLETE()
     * ============================================================
     *
     * whenComplete() observes completion.
     *
     * It receives the result and exception.
     *
     * It is commonly used for:
     *
     * - Logging.
     * - Metrics.
     * - Auditing.
     * - Completion notifications.
     *
     * Example:
     *
     * future.whenComplete((result, exception) -> {
     *
     *     if (exception != null) {
     *         System.out.println("Failed: " + exception);
     *     } else {
     *         System.out.println("Result: " + result);
     *     }
     * });
     *
     * Unlike handle(), whenComplete() is primarily
     * an observation step and normally preserves
     * the original result or failure.
     *
     *
     * ============================================================
     * 30. EXCEPTIONALLY VS HANDLE VS WHENCOMPLETE
     * ============================================================
     *
     * exceptionally():
     *
     * Handles failure and can provide a fallback result.
     *
     * handle():
     *
     * Processes success or failure and can transform
     * either into a new result.
     *
     * whenComplete():
     *
     * Observes success or failure, usually for side effects,
     * while preserving the original outcome.
     *
     *
     * ============================================================
     * 31. MANUAL COMPLETION
     * ============================================================
     *
     * CompletableFuture can be completed explicitly.
     *
     * complete(value):
     *
     * Attempts to complete the future normally.
     *
     * completeExceptionally(exception):
     *
     * Attempts to complete the future exceptionally.
     *
     * Example:
     *
     * CompletableFuture<String> future =
     *     new CompletableFuture<>();
     *
     * future.complete("Manually completed");
     *
     * System.out.println(future.join());
     *
     * IMPORTANT:
     *
     * Completion methods only change the result
     * if the future has not already completed.
     *
     *
     * ============================================================
     * 32. COMPLETEONETIMEOUT()
     * ============================================================
     *
     * completeOnTimeout() completes a CompletableFuture
     * with a fallback value if it has not completed
     * within the specified time.
     *
     * Example:
     *
     * future.completeOnTimeout(
     *     "Fallback",
     *     2,
     *     TimeUnit.SECONDS
     * );
     *
     * Available since Java 9.
     *
     * This operation can complete the same future
     * with the fallback value.
     *
     *
     * ============================================================
     * 33. ORTIMEOUT()
     * ============================================================
     *
     * orTimeout() completes a CompletableFuture
     * exceptionally with TimeoutException if it
     * has not completed within the specified time.
     *
     * Example:
     *
     * future.orTimeout(
     *     2,
     *     TimeUnit.SECONDS
     * );
     *
     * Available since Java 9.
     *
     * IMPORTANT:
     *
     * Timing out a CompletableFuture does not necessarily
     * stop the underlying operation.
     *
     * Cancellation and resource cleanup may require
     * separate application logic.
     *
     *
     * ============================================================
     * 34. ASYNC METHODS WITH CUSTOM EXECUTOR
     * ============================================================
     *
     * By default, many async CompletableFuture methods
     * use ForkJoinPool.commonPool().
     *
     * We can supply our own executor.
     *
     * Example:
     *
     * ExecutorService executor =
     *     Executors.newFixedThreadPool(5);
     *
     * CompletableFuture<String> future =
     *     CompletableFuture.supplyAsync(
     *
     *         () -> "Database result",
     *
     *         executor
     *     );
     *
     * executor.shutdown();
     *
     * Benefits:
     *
     * - Control over worker resources.
     * - Isolation between workloads.
     * - Custom thread naming.
     * - Custom queue and rejection policies.
     *
     * Choose an executor based on the workload.
     *
     *
     * ============================================================
     * 35. COMMON FORKJOINPOOL
     * ============================================================
     *
     * ForkJoinPool is a specialized executor designed
     * for parallel task processing.
     *
     * It uses work-stealing.
     *
     * Workers can steal tasks from other workers'
     * queues when useful.
     *
     * CompletableFuture asynchronous methods without
     * an explicit executor commonly use the common pool.
     *
     * The common pool is shared by unrelated work
     * in the JVM.
     *
     * Avoid placing long-running blocking operations
     * in it without considering the consequences.
     *
     *
     * ============================================================
     * 36. DEPENDENT VS INDEPENDENT TASKS
     * ============================================================
     *
     * Independent tasks:
     *
     * Can execute without waiting for one another.
     *
     * Example:
     *
     * Fetch user profile.
     * Fetch recommended products.
     *
     * These may run concurrently.
     *
     *
     * Dependent tasks:
     *
     * Need the result of an earlier operation.
     *
     * Example:
     *
     * Fetch user.
     * Use user ID to fetch orders.
     *
     * The second task depends on the first.
     *
     * Use:
     *
     * thenCombine() for combining independent results.
     *
     * thenCompose() for chaining dependent async operations.
     *
     *
     * ============================================================
     * 37. ASYNCHRONOUS PIPELINE
     * ============================================================
     *
     * A pipeline is a sequence of dependent operations.
     *
     * Example:
     *
     * CompletableFuture.supplyAsync(
     *     () -> fetchUser(userId)
     * )
     *
     * .thenCompose(user ->
     *     fetchOrders(user.id())
     * )
     *
     * .thenApply(orders ->
     *     calculateTotal(orders)
     * )
     *
     * .thenAccept(total ->
     *     System.out.println(total)
     * );
     *
     * Conceptual flow:
     *
     * Fetch user
     *     |
     * Fetch orders using user
     *     |
     * Calculate total
     *     |
     * Display total
     *
     * Each stage runs when its dependency is satisfied.
     *
     *
     * ============================================================
     * 38. EXCEPTION PROPAGATION IN PIPELINES
     * ============================================================
     *
     * If a stage completes exceptionally:
     *
     * - Normal dependent stages generally do not execute.
     * - Exceptional completion propagates through the chain.
     * - An exception-handling stage can recover or transform it.
     *
     * Example:
     *
     * CompletableFuture<Integer> future =
     *     CompletableFuture.supplyAsync(() -> {
     *
     *         throw new RuntimeException("Failure");
     *
     *     })
     *
     *     .thenApply(value -> value * 2)
     *
     *     .exceptionally(exception -> 0);
     *
     * Result = 0.
     *
     *
     * ============================================================
     * 39. ASYNC PROGRAMMING IN BACKEND DEVELOPMENT
     * ============================================================
     *
     * Common examples:
     *
     * - Calling multiple independent services.
     * - Fetching information from multiple APIs.
     * - Sending notifications in the background.
     * - Processing independent data sources.
     * - Running long-running computations.
     * - Combining results from multiple operations.
     *
     * Example backend flow:
     *
     * Request arrives.
     *
     * Start:
     *     Fetch user details.
     *     Fetch order history.
     *     Fetch payment information.
     *
     * Combine results.
     *
     * Return response.
     *
     * This can reduce waiting time when operations
     * are independent and the system has sufficient capacity.
     *
     *
     * ============================================================
     * 40. COMPLETABLEFUTURE AND SPRING BOOT
     * ============================================================
     *
     * CompletableFuture can be used in Spring Boot
     * service-layer methods.
     *
     * Example:
     *
     * public CompletableFuture<User> getUserAsync() {
     *
     *     return CompletableFuture.supplyAsync(
     *         () -> userService.fetchUser()
     *     );
     * }
     *
     * Spring also supports asynchronous execution
     * through @Async and configured TaskExecutor instances.
     *
     * IMPORTANT:
     *
     * Do not assume that every CompletableFuture created
     * inside a Spring application automatically uses
     * Spring's configured task executor.
     *
     * supplyAsync() without an executor normally uses
     * the common ForkJoinPool.
     *
     * Configure the execution strategy deliberately.
     *
     *
     * ============================================================
     * 41. COMMON MISTAKES
     * ============================================================
     *
     * 1. Calling get() immediately after submitting a task
     *    and expecting the caller to remain non-blocking.
     *
     * 2. Assuming asynchronous means parallel.
     *
     * 3. Confusing thenApply() with thenCompose().
     *
     * 4. Using thenApply() when the next operation returns
     *    another CompletableFuture and a flattened chain is needed.
     *
     * 5. Ignoring exceptional completion.
     *
     * 6. Assuming a timeout automatically stops the task.
     *
     * 7. Using the common pool for long-running blocking work
     *    without considering pool contention.
     *
     * 8. Assuming all async methods create a new thread.
     *
     * 9. Forgetting that join() can block.
     *
     * 10. Starting many tasks without considering downstream
     *     capacity and resource limits.
     *
     *
     * ============================================================
     * 42. FUTURE VS COMPLETABLEFUTURE
     * ============================================================
     *
     * Future:
     *
     * - Represents a future result.
     * - Supports get(), cancellation and status checks.
     * - Does not provide convenient built-in chaining.
     * - get() blocks when the result is unavailable.
     *
     * CompletableFuture:
     *
     * - Implements Future and CompletionStage.
     * - Supports chaining and composition.
     * - Supports callbacks and result transformation.
     * - Supports combining multiple stages.
     * - Supports exceptional completion and recovery.
     * - Supports manual completion.
     *
     * Remember:
     *
     * Future:
     * A handle to an asynchronous result.
     *
     * CompletableFuture:
     * A future result plus a rich API for building
     * asynchronous workflows.
     *
     *
     * ============================================================
     * 43. INTERVIEW REVISION
     * ============================================================
     *
     * Q1. What is asynchronous programming?
     *
     * Q2. What is the difference between synchronous
     *     and asynchronous execution?
     *
     * Q3. What is Future?
     *
     * Q4. What is the difference between get() and join()?
     *
     * Q5. Does get(timeout) cancel a task automatically?
     *
     * Q6. How does Future handle task exceptions?
     *
     * Q7. What are the limitations of Future?
     *
     * Q8. What is CompletableFuture?
     *
     * Q9. What is the difference between runAsync()
     *     and supplyAsync()?
     *
     * Q10. What is the difference between thenApply(),
     *      thenAccept() and thenRun()?
     *
     * Q11. What is the difference between thenApply()
     *      and thenApplyAsync()?
     *
     * Q12. What is thenCompose()?
     *
     * Q13. What is the difference between thenApply()
     *      and thenCompose()?
     *
     * Q14. What is thenCombine()?
     *
     * Q15. What is the difference between allOf()
     *      and anyOf()?
     *
     * Q16. Explain exceptionally(), handle()
     *      and whenComplete().
     *
     * Q17. What is the common ForkJoinPool?
     *
     * Q18. Can we provide a custom executor to
     *      CompletableFuture?
     *
     * Q19. What is the difference between independent
     *      and dependent asynchronous tasks?
     *
     * Q20. Does CompletableFuture timeout necessarily
     *      stop the underlying operation?
     *
     *
     * ============================================================
     * 44. FINAL TAKEAWAY
     * ============================================================
     *
     * Future:
     * Represents the result of an asynchronous computation.
     *
     * get():
     * Waits for and retrieves the result.
     *
     * CompletableFuture:
     * Provides a richer API for asynchronous workflows.
     *
     * supplyAsync():
     * Starts asynchronous work that produces a result.
     *
     * runAsync():
     * Starts asynchronous work without a result.
     *
     * thenApply():
     * Transforms a result.
     *
     * thenAccept():
     * Consumes a result.
     *
     * thenRun():
     * Runs an action without receiving the previous result.
     *
     * thenCompose():
     * Chains dependent asynchronous operations.
     *
     * thenCombine():
     * Combines independent results.
     *
     * allOf():
     * Waits for all supplied stages to complete.
     *
     * anyOf():
     * Completes when any supplied stage completes.
     *
     * exceptionally():
     * Handles exceptional completion with a fallback.
     *
     * handle():
     * Processes success or failure.
     *
     * whenComplete():
     * Observes completion.
     *
     * Remember:
     *
     * Asynchronous programming is not just about executing
     * tasks in the background.
     *
     * It is about coordinating tasks, managing their results,
     * handling failures and building reliable workflows.
     */

    public static void main(String[] args) {
        System.out.println("The above is all about Futures and Asynchronous Programming , Happy Learning ..!");
    }
}
