package sorters;

import analyzer.Configuration;
import fillers.Filler;

public interface Recorder {

    int getRecord(Integer arraySize, Filler filler);

    void addRecord(Integer arraySize, Filler filler, int value);
}
