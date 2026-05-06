package org.folio.dew.utils;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.time.Instant;
import org.apache.commons.lang3.exception.ExceptionUtils;

public final class Log2File {
  private Log2File() {
  }


  public static void log(String msg, Throwable t) {
    var e = new RuntimeException(msg, t);
    var stackTrace = Instant.now() + "\n" + ExceptionUtils.getStackTrace(e);
    try {
      Files.writeString(Path.of("/tmp/edifact-error.txt"), stackTrace, StandardOpenOption.CREATE, StandardOpenOption.APPEND);
    } catch (IOException ioe) {
      throw new UncheckedIOException(stackTrace, ioe);
    }
  }

}
