package software.ulpgc.control.io;

import software.ulpgc.model.Title;

public interface TitleDeserializer {
    Title deserialize(String value);
}