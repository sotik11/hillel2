package com.hillel.project.utils;

import com.hillel.project.exceptions.TooManyRetriesException;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

@AllArgsConstructor
public class RetryHelper<T> {
    private final int retryCount;

    public void run(Runnable command) {
        for(int i = 0; i < retryCount + 1; i++) {
            command.run();
        }
        throw new TooManyRetriesException();
    }

    public Optional<T> run(Supplier<T> command) {
        for(int i = 0; i < retryCount + 1; i++) {
            try {
                T value = command.get();
                if (value == null) {
                    return Optional.empty();
                }
                return Optional.of(value);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        throw new TooManyRetriesException();
    }
}
