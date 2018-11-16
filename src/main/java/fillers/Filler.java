package fillers;

import analyzer.Configuration;

import java.util.Random;
import java.util.stream.IntStream;

public interface Filler {

    Random random = new Random();

    int [] generateArray(int arraySize);

    default IntStream generateRandomValues(int streamSize) {
        return random.ints(streamSize, Configuration.getDefaultOrigin(), Configuration.getDefaultBoundForRandom());
    }

}
