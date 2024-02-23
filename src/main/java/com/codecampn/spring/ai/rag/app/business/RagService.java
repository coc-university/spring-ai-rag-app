package com.codecampn.spring.ai.rag.app.business;

import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.messages.Message;
import org.springframework.ai.chat.prompt.SystemPromptTemplate;
import org.springframework.ai.document.Document;
import org.springframework.ai.reader.JsonReader;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Slf4j
public class RagService {

    @Value("classpath:/data/footballer.json")
    private Resource footballerJson;

    @Value("classpath:/prompts/footballer-prompt.st")
    private Resource footballerPrompt;

    private final VectorStore vectorStore;

    public RagService(VectorStore vectorStore) {
        this.vectorStore = vectorStore;
    }

    public void saveDocuments() {
        JsonReader jsonReader = new JsonReader(footballerJson, "name", "age ", "team", "country");
        List<Document> documents = jsonReader.get();
        log.info("ask llm for embeddings and store them in db ... so let's wait some seconds");
        vectorStore.accept(documents);
        log.info("saved all {} documents", documents.size());
    }

    public Message loadSimilarDocumentsAndCreateSystemMessage(String query) {
        List<Document> similarDocuments = vectorStore.similaritySearch(query);
        String documents = similarDocuments
                .stream()
                .map(Document::getContent)
                .collect(Collectors.joining("\n"));

        SystemPromptTemplate systemPromptTemplate = new SystemPromptTemplate(footballerPrompt);
        //log.info("System Message documents" + documents);
        return systemPromptTemplate.createMessage(Map.of("documents", documents));
    }
}
