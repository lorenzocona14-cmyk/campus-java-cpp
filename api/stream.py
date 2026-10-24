# -*- coding: utf-8 -*-
from http.server import BaseHTTPRequestHandler
import urllib.request
import urllib.parse
import json
import yt_dlp

stream_cache = {}

def resolve_direct_stream(video_id):
    if video_id in stream_cache:
        return stream_cache[video_id]

    client_candidates = [
        ['tv_embedded'],
        ['android'],
        ['ios'],
        ['mweb']
    ]

    last_error = None
    for client_list in client_candidates:
        ydl_opts = {
            'quiet': True,
            'no_warnings': True,
            'nocheckcertificate': True,
            'extractor_args': {
                'youtube': {
                    'player_client': client_list
                }
            }
        }
        try:
            with yt_dlp.YoutubeDL(ydl_opts) as ydl:
                info = ydl.extract_info(f"https://www.youtube.com/watch?v={video_id}", download=False)
                stream_url = None
                for f in info.get('formats', []):
                    if f.get('format_id') == '18' and f.get('url'):
                        stream_url = f.get('url')
                        break
                if not stream_url:
                    for f in info.get('formats', []):
                        if f.get('vcodec') != 'none' and f.get('acodec') != 'none' and f.get('url'):
                            stream_url = f.get('url')
                            break
                if stream_url:
                    stream_cache[video_id] = stream_url
                    return stream_url
        except Exception as e:
            last_error = e
            continue

    if last_error:
        raise last_error
    return None

class handler(BaseHTTPRequestHandler):
    def do_OPTIONS(self):
        self.send_response(200)
        self.send_header('Access-Control-Allow-Origin', '*')
        self.send_header('Access-Control-Allow-Methods', 'GET, OPTIONS')
        self.send_header('Access-Control-Allow-Headers', 'Range, Content-Type')
        self.end_headers()

    def do_GET(self):
        parsed = urllib.parse.urlparse(self.path)
        qs = urllib.parse.parse_qs(parsed.query)
        vid = qs.get('v', [None])[0]

        if not vid:
            self.send_response(400)
            self.send_header('Content-Type', 'text/plain; charset=utf-8')
            self.send_header('Access-Control-Allow-Origin', '*')
            self.end_headers()
            self.wfile.write(b"Parametro 'v' requerido")
            return

        try:
            real_url = resolve_direct_stream(vid)
            if not real_url:
                self.send_response(404)
                self.send_header('Content-Type', 'text/plain; charset=utf-8')
                self.send_header('Access-Control-Allow-Origin', '*')
                self.end_headers()
                self.wfile.write(b"No se encontro flujo de streaming.")
                return

            req_headers = {
                'User-Agent': 'com.google.android.youtube/19.29.37 (Linux; U; Android 11) gzip'
            }
            range_header = self.headers.get('Range')
            if range_header:
                req_headers['Range'] = range_header

            req = urllib.request.Request(real_url, headers=req_headers)
            with urllib.request.urlopen(req, timeout=15) as resp:
                self.send_response(resp.status)
                for h, v in resp.headers.items():
                    hl = h.lower()
                    if hl in ['content-type', 'content-length', 'content-range', 'accept-ranges']:
                        self.send_header(h, v)
                self.send_header('Access-Control-Allow-Origin', '*')
                self.send_header('Access-Control-Allow-Headers', 'Range')
                self.send_header('Access-Control-Expose-Headers', 'Content-Range, Content-Length, Accept-Ranges')
                self.send_header('Cache-Control', 'public, max-age=3600')
                self.end_headers()

                while True:
                    chunk = resp.read(65536)
                    if not chunk:
                        break
                    self.wfile.write(chunk)
        except Exception as e:
            try:
                self.send_response(500)
                self.send_header('Content-Type', 'text/plain; charset=utf-8')
                self.send_header('Access-Control-Allow-Origin', '*')
                self.end_headers()
                self.wfile.write(f"Error en streaming nativo: {str(e)}".encode('utf-8'))
            except Exception:
                pass
