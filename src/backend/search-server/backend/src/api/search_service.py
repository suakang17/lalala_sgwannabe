from fastapi import FastAPI, APIRouter, HTTPException, Depends, Query
from pydantic import BaseModel
from elasticsearch import Elasticsearch
from kafka import KafkaConsumer
import json
import threading

app = FastAPI()

# Elasticsearch 설정
es = Elasticsearch([{'host': 'elasticsearch', 'port': 9200, 'scheme': 'http'}])

# Kafka 설정
kafka_consumer = KafkaConsumer(
    'chatroom_created',
    'playlist_created',
    bootstrap_servers=['localhost:9092'],
    auto_offset_reset='earliest',
    enable_auto_commit=True,
    group_id='search_indexer',
    value_deserializer=lambda x: json.loads(x.decode('utf-8'))
)

# 라우터 search API
search_router = APIRouter(prefix="/v1/api/search", tags=["search API"])

# 라우터 indexing API
index_router = APIRouter(tags=["index API"])

# 인덱싱 request 모델
class IndexRequest(BaseModel):
    id: str
    name: str
    description: str

# Search
def search_index(index: str, query: str, size: int, sort: str, start: int):
    body = {
        "query": {"match": {"name": query}},
        "size": size,
        "from": start,
        "sort": [{"created_at" if sort == "latest" else "popularity": {"order": "desc"}}]
    }
    return es.search(index=index, body=body)

# Search API
@search_router.get("/{index}")
async def search(
    index: str,
    query: str = Query(..., description="Search query"),
    size: int = Query(10, description="Number of results"),
    sort: str = Query("latest", description="Sort order (latest or popularity)"),
    start: int = Query(0, description="Start position")
):
    try:
        results = search_index(index, query, size, sort, start)
        return {"results": results["hits"]["hits"]}
    except Exception as e:
        raise HTTPException(status_code=500, detail=f"Search failed: {str(e)}")

# Index
def index_document(index: str, doc: IndexRequest):
    try:
        es.index(index=index, id=doc.id, body=doc.dict())
        return {"message": f"Indexed {index} successfully"}
    except Exception as e:
        raise HTTPException(status_code=500, detail=f"Indexing failed: {str(e)}")

# Index API
@index_router.post("/index/{index}")
async def index(index: str, doc: IndexRequest):
    return index_document(index, doc)

# Kafka consumer
def consume_kafka_messages():
    for message in kafka_consumer:
        topic = message.topic
        value = message.value
        
        if topic == 'chatroom_created':
            index_document('chatroom', IndexRequest(**value))
        elif topic == 'playlist_created':
            index_document('playlist', IndexRequest(**value))

threading.Thread(target=consume_kafka_messages, daemon=True).start()

app.include_router(search_router)
app.include_router(index_router)

if __name__ == "__main__":
    import uvicorn
    uvicorn.run(app, host="0.0.0.0", port=8000)