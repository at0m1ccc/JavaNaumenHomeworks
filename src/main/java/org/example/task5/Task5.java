package org.example.task5;

import org.example.UserInterface;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Task5 implements Task {
    private final Path sourceDir;
    private final Path targetDir;
    private final long syncIntervalSeconds;
    private ScheduledExecutorService executorService;
    private final UserInterface userInterface = new UserInterface();

    public Task5() {
        this.sourceDir = Paths.get("D:\\test1");
        this.targetDir = Paths.get("D:\\test2");
        this.syncIntervalSeconds = 30;
    }

    public Task5(String sourceDir, String targetDir, long syncIntervalSeconds) {
        this.sourceDir = Paths.get(sourceDir);
        this.targetDir = Paths.get(targetDir);
        this.syncIntervalSeconds = syncIntervalSeconds;
    }

    @Override
    public void start() {
        if (executorService != null && !executorService.isShutdown()) {
            userInterface.printMessage("Синхронизация уже запущена!");
            return;
        }
        executorService = Executors.newSingleThreadScheduledExecutor();
        executorService.scheduleAtFixedRate(this::synchronizeFiles, 0, syncIntervalSeconds, TimeUnit.SECONDS);
        userInterface.printMessage("Начинается синхронизация между " + sourceDir + " и " + targetDir);
    }

    @Override
    public void stop() {
        if (executorService != null) {
            executorService.shutdown();
            try {
                if (!executorService.awaitTermination(5, TimeUnit.SECONDS)) {
                    executorService.shutdownNow();
                }
            } catch (InterruptedException e) {
                executorService.shutdownNow();
                Thread.currentThread().interrupt();
            }
            userInterface.printMessage("Синхронизация завершена.");
        } else {
            userInterface.printMessage("Синхронизация не была запущена.");
        }
    }

    private void synchronizeFiles() {
        try {
            Files.walkFileTree(sourceDir, new SimpleFileVisitor<>() {
                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                    Path relativePath = sourceDir.relativize(file);
                    Path targetFile = targetDir.resolve(relativePath);

                    if (!Files.exists(targetFile) || Files.getLastModifiedTime(file).compareTo(Files.getLastModifiedTime(targetFile)) > 0) {
                        Files.createDirectories(targetFile.getParent());
                        Files.copy(file, targetFile, StandardCopyOption.REPLACE_EXISTING);
                        userInterface.printMessage("Синхронизация файла: " + file + " -> " + targetFile);
                    }
                    return FileVisitResult.CONTINUE;
                }

                @Override
                public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                    Path relativePath = sourceDir.relativize(dir);
                    Path targetDirToCreate = targetDir.resolve(relativePath);
                    if (!Files.exists(targetDirToCreate)) {
                        Files.createDirectories(targetDirToCreate);
                        userInterface.printMessage("Создана директория: " + targetDirToCreate);
                    }
                    return FileVisitResult.CONTINUE;
                }

                @Override
                public FileVisitResult visitFileFailed(Path file, IOException exc) {
                    userInterface.printMessage("Ошибка посещения файла: " + file + " - " + exc.getMessage());
                    return FileVisitResult.CONTINUE;
                }
            });
        } catch (IOException e) {
            userInterface.printMessage("Synchronization failed: " + e.getMessage());
        }
    }

    public void solveTask5() {
        userInterface.printMessage("Выполнение задания №5");
        String sourceDir = "D:\\test1";
        String targetDir = "D:\\test2";
        long syncInterval = 5;

        try {
            Files.createDirectories(Paths.get(sourceDir));
            Files.createDirectories(Paths.get(targetDir));
        } catch (IOException e) {
            userInterface.printMessage("Ошибка создания директорий: " + e.getMessage());
            return;
        }

        Task5 task = new Task5(sourceDir, targetDir, syncInterval);
        task.start();

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        task.stop();

        userInterface.printMessage("-".repeat(50));
    }
}
