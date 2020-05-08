package com.ex;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;

public class JSONFruitDeserializer extends StdDeserializer<Fruit> {

  public JSONFruitDeserializer() {
    this(null);
  }

  public JSONFruitDeserializer(Class<?> c) {
    super(c);
  }

  @Override
  public Fruit deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
    JsonNode node = jsonParser.getCodec().readTree(jsonParser);
    String type = node.get("type").asText();

    switch(type) {
      case "apple":
        return new Apple();
      case "orange":
        return new Orange();
      default:
        return null;
    }
  }
}
