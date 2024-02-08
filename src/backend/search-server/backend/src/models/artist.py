from pydantic import BaseModel

class Artist(BaseModel):
    name: str
    gender: str
    type: str
    agency: str

class ArtistIndexRequest(BaseModel):
    name: str
    gender: str
    type: str
    agency: str