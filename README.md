# Spring AI RAG App

## Based on:

- https://github.com/rd-1-2022/ai-azure-retrieval-augmented-generation/tree/main
- https://github.com/markpollack/spring-ai-carina-faq/tree/main
- https://github.com/coffee-software-show/llm-rag-with-spring-ai/tree/main
- https://github.com/klindziukp/spring-ai-ollama-llama
- so mainly oriented on repo's of mark pollack, spring-ai project lead

## In general:

- this project uses snapshot dependencies (work in progress)
- so they will definitely change in the future
- check pom.xml

## Setup:

- the whole project can run locally without an api-key
- choose your vectorStore in DbConfig (Postgres or in-mem Db)
- run ```docker-compose up``` (ollama and postgres)
- and then ```docker exec -it ollama ollama run llama2``` [check out docker-hub](https://hub.docker.com/r/ollama/ollama)
- use StartupRunner to fill the database

## Run project:

- trigger ChatController to query db for similar documents 
- service layer will stuff the prompt
- and ask llm for answer of your question


## Dependencies and Flowchart: 
- [spring-ai-rag-app.drawio.pdf](spring-ai-rag-app.drawio.pdf)
