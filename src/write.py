from fastapi import FastAPI, Query
from fastapi.middleware.cors import CORSMiddleware
import requests
import re
import os
import logging
from yt_dlp import YoutubeDL

logging.basicConfig(level=logging.INFO)
logger = logging.getLogger(__name__)

app = FastAPI()

# --------------------------
# CORS
# --------------------------
app.add_middleware(
    CORSMiddleware,
    allow_origins=["*"],
    allow_credentials=True,
    allow_methods=["*"],
    allow_headers=["*"],
)

YOUTUBE_API_KEY = "AIzaSyDZxfGnQBrriUVLojR4ZJiNyIK0zgLGsZ4"


# --------------------------
# SEARCH VIDEOS
# --------------------------
@app.get("/search_videos")
def search_videos(q: str):
    url = "https://www.googleapis.com/youtube/v3/search"

    params = {
        "part": "snippet",
        "q": q,
        "key": YOUTUBE_API_KEY,
        "maxResults": 10,
        "type": "video"
    }

    response = requests.get(url, params=params).json()

    results = []
    for item in response.get("items", []):
        vid = item["id"]["videoId"]
        snip = item["snippet"]

        results.append({
            "videoId": vid,
            "title": snip["title"],
            "url": f"https://www.youtube.com/watch?v={vid}"
        })

    return results


# --------------------------
# EXTRACT VIDEO ID
def extract_video_id(url: str) -> str:
    # Remove query parameters and fragments
    url = url.split("?")[1].split("#")[0]

    patterns = [
        r"(?:v=|\/)([0-9A-Za-z_-]{11})",  # Standard YouTube video ID
        r"youtu\.be\/([0-9A-Za-z_-]{11})",
        r"shorts\/([0-9A-Za-z_-]{11})",
        r"embed\/([0-9A-Za-z_-]{11})",
        r"watch\/([0-9A-Za-z_-]{11})"
    ]

    for p in patterns:
        m = re.search(p, url)
        if m:
            return m.group(1)

    return None



# --------------------------
# DOWNLOAD VIDEO (using yt-dlp)
# --------------------------
@app.get("/download_video")
def download_video(url: str = Query(...)):
    try:
        video_id = extract_video_id(url)
        if not video_id:
            return {"success": False, "message": "Invalid YouTube URL"}

        clean_url = f"https://www.youtube.com/watch?v={video_id}"
        os.makedirs("downloads", exist_ok=True)

        ydl_opts = {
            'outtmpl': 'downloads/%(title)s.%(ext)s',
            'format': 'bestvideo+bestaudio/best',
            'merge_output_format': 'mp4',
            'quiet': True,
        }

        with YoutubeDL(ydl_opts) as ydl:
            info = ydl.extract_info(clean_url, download=True)
            filename = ydl.prepare_filename(info)

        return {"success": True, "file_path": filename}

    except Exception as e:
        import traceback
        logger.error(traceback.format_exc())
        return {"success": False, "message": str(e)}



# --------------------------
if __name__ == "__main__":
    import uvicorn
    uvicorn.run(app, host="0.0.0.0", port=8001, reload=True)





from fastapi import FastAPI, Query
from fastapi.middleware.cors import CORSMiddleware
import requests
import re
import os
import logging
from yt_dlp import YoutubeDL

logging.basicConfig(level=logging.INFO)
logger = logging.getLogger(__name__)

app = FastAPI()

# --------------------------
# CORS
# --------------------------
app.add_middleware(
    CORSMiddleware,
    allow_origins=["*"],
    allow_credentials=True,
    allow_methods=["*"],
    allow_headers=["*"],
)

YOUTUBE_API_KEY = "AIzaSyDZxfGnQBrriUVLojR4ZJiNyIK0zgLGsZ4"


# --------------------------
# SEARCH VIDEOS
# --------------------------
@app.get("/search_videos")
def search_videos(q: str):
    url = "https://www.googleapis.com/youtube/v3/search"

    params = {
        "part": "snippet",
        "q": q,
        "key": YOUTUBE_API_KEY,
        "maxResults": 10,
        "type": "video"
    }

    response = requests.get(url, params=params).json()

    results = []
    for item in response.get("items", []):
        vid = item["id"]["videoId"]
        snip = item["snippet"]

        results.append({
            "videoId": vid,
            "title": snip["title"],
            "url": f"https://www.youtube.com/watch?v={vid}"
        })

    return results


# --------------------------
# EXTRACT VIDEO ID
def extract_video_id(url: str) -> str:
    # Remove query parameters and fragments
    url = url.split("?")[1].split("#")[0]

    patterns = [
        r"(?:v=|\/)([0-9A-Za-z_-]{11})",  # Standard YouTube video ID
        r"youtu\.be\/([0-9A-Za-z_-]{11})",
        r"shorts\/([0-9A-Za-z_-]{11})",
        r"embed\/([0-9A-Za-z_-]{11})",
        r"watch\/([0-9A-Za-z_-]{11})"
    ]

    for p in patterns:
        m = re.search(p, url)
        if m:
            return m.group(1)

    return None



# --------------------------
# DOWNLOAD VIDEO (using yt-dlp)
# --------------------------
@app.get("/download_video")
def download_video(url: str = Query(...)):
    try:
        video_id = extract_video_id(url)
        if not video_id:
            return {"success": False, "message": "Invalid YouTube URL"}

        clean_url = f"https://www.youtube.com/watch?v={video_id}"
        os.makedirs("downloads", exist_ok=True)

        ydl_opts = {
            'outtmpl': 'downloads/%(title)s.%(ext)s',
            'format': 'bestvideo+bestaudio/best',
            'merge_output_format': 'mp4',
            'quiet': True,
        }

        with YoutubeDL(ydl_opts) as ydl:
            info = ydl.extract_info(clean_url, download=True)
            filename = ydl.prepare_filename(info)

        return {"success": True, "file_path": filename}

    except Exception as e:
        import traceback
        logger.error(traceback.format_exc())
        return {"success": False, "message": str(e)}



# --------------------------
if __name__ == "__main__":
    import uvicorn
    uvicorn.run(app, host="0.0.0.0", port=8001, reload=True)

