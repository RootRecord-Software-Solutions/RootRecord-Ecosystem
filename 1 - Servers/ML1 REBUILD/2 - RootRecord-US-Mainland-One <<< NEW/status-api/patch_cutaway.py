#!/usr/bin/env python3
"""Patch stream.js for YouTube cutaway that keeps live PCM audio."""
from __future__ import annotations

import subprocess
import time
from pathlib import Path

path = Path("/home/ubuntu/rootrecord-radio/active/stream.js")
text = path.read_text()
bak = path.with_suffix(".js.bak.hvo-" + time.strftime("%Y%m%d%H%M%S"))
bak.write_text(text)
print("backup", bak)

old = r"""function startYoutube() {
  if (stopped) return;
  if (youtube && youtube.exitCode == null && !youtube.killed) return;
  const dest = youtubeDest();
  const thumb = path.join(YOUTUBE_DIR, 'thumb.png');
  if (!dest || !fs.existsSync(thumb)) {
    setTimeout(startYoutube, 5000);
    return;
  }
  youtube = spawn('ffmpeg', [
    '-hide_banner', '-loglevel', 'warning',
    '-loop', '1', '-framerate', '15', '-i', thumb,
    '-f', 's16le', '-ar', String(RATE), '-ac', '2', '-i', 'pipe:0',
    '-filter:v', 'scale=1920:1080:force_original_aspect_ratio=increase,crop=1920:1080,drawtext=fontfile=/usr/share/fonts/truetype/dejavu/DejaVuSans-Bold.ttf:textfile=/home/ubuntu/youtube-stills/clock.txt:reload=1:fontsize=72:fontcolor=0xECF6FF:x=(328-text_w/2):y=112,format=yuv420p',
    '-c:v', 'libx264', '-preset', 'ultrafast', '-tune', 'stillimage',
    '-b:v', '2500k', '-maxrate', '4000k', '-bufsize', '5000k',
    '-r', '15', '-g', '30', '-pix_fmt', 'yuv420p',
    '-c:a', 'aac', '-b:a', '128k', '-ar', '44100', '-ac', '2',
    '-map', '0:v:0', '-map', '1:a:0',
    '-f', 'flv', '-flvflags', 'no_duration_filesize', dest
  ], { stdio: ['pipe', 'ignore', 'pipe'] });
  missingTool(youtube, 'ffmpeg');
  if (youtube.stdin) youtube.stdin.on('error', function () {});
  log('youtube_up', { pid: youtube.pid });
  youtube.stderr.on('data', (chunk) => {
    const detail = String(chunk || '').trim().replace(/rtmps?:\/\/\S+/g, 'rtmps://[redacted]');
    if (detail) log('youtube_error', { detail: detail.slice(0, 180) });
  });
  youtube.on('exit', (code) => {
    if (stopped) return;
    log('youtube_down', { code: code });
    youtube = null;
    setTimeout(startYoutube, 2000);
  });
}"""

new = r"""function youtubeCutawayOn() {
  try {
    return fs.existsSync(path.join(YOUTUBE_DIR, 'cutaway.on'));
  } catch (err) {
    return false;
  }
}

function youtubeCutawayFifo() {
  return path.join(YOUTUBE_DIR, 'cutaway.fifo');
}

function startYoutube() {
  if (stopped) return;
  if (youtube && youtube.exitCode == null && !youtube.killed) return;
  const dest = youtubeDest();
  const thumb = path.join(YOUTUBE_DIR, 'thumb.png');
  const cutaway = youtubeCutawayOn();
  const fifo = youtubeCutawayFifo();
  if (!dest) {
    setTimeout(startYoutube, 5000);
    return;
  }
  if (cutaway) {
    if (!fs.existsSync(fifo)) {
      log('youtube_cutaway_wait', { reason: 'no_fifo' });
      setTimeout(startYoutube, 1000);
      return;
    }
    // Live program audio stays on pipe:0 — only the picture switches.
    youtube = spawn('ffmpeg', [
      '-hide_banner', '-loglevel', 'warning',
      '-fflags', 'nobuffer', '-flags', 'low_delay',
      '-thread_queue_size', '512', '-f', 'mpegts', '-i', fifo,
      '-f', 's16le', '-ar', String(RATE), '-ac', '2', '-i', 'pipe:0',
      '-filter:v', 'scale=1280:720:force_original_aspect_ratio=decrease,pad=1280:720:(ow-iw)/2:(oh-ih)/2,fps=15,format=yuv420p',
      '-c:v', 'libx264', '-preset', 'ultrafast',
      '-b:v', '1800k', '-maxrate', '2200k', '-bufsize', '4000k',
      '-r', '15', '-g', '30', '-pix_fmt', 'yuv420p',
      '-c:a', 'aac', '-b:a', '128k', '-ar', '44100', '-ac', '2',
      '-map', '0:v:0', '-map', '1:a:0',
      '-f', 'flv', '-flvflags', 'no_duration_filesize', dest
    ], { stdio: ['pipe', 'ignore', 'pipe'] });
  } else {
    if (!fs.existsSync(thumb)) {
      setTimeout(startYoutube, 5000);
      return;
    }
    youtube = spawn('ffmpeg', [
      '-hide_banner', '-loglevel', 'warning',
      '-loop', '1', '-framerate', '15', '-i', thumb,
      '-f', 's16le', '-ar', String(RATE), '-ac', '2', '-i', 'pipe:0',
      '-filter:v', 'scale=1920:1080:force_original_aspect_ratio=increase,crop=1920:1080,drawtext=fontfile=/usr/share/fonts/truetype/dejavu/DejaVuSans-Bold.ttf:textfile=/home/ubuntu/youtube-stills/clock.txt:reload=1:fontsize=72:fontcolor=0xECF6FF:x=(328-text_w/2):y=112,format=yuv420p',
      '-c:v', 'libx264', '-preset', 'ultrafast', '-tune', 'stillimage',
      '-b:v', '2500k', '-maxrate', '4000k', '-bufsize', '5000k',
      '-r', '15', '-g', '30', '-pix_fmt', 'yuv420p',
      '-c:a', 'aac', '-b:a', '128k', '-ar', '44100', '-ac', '2',
      '-map', '0:v:0', '-map', '1:a:0',
      '-f', 'flv', '-flvflags', 'no_duration_filesize', dest
    ], { stdio: ['pipe', 'ignore', 'pipe'] });
  }
  missingTool(youtube, 'ffmpeg');
  if (youtube.stdin) youtube.stdin.on('error', function () {});
  log('youtube_up', { pid: youtube.pid, mode: cutaway ? 'cutaway' : 'still' });
  youtube.stderr.on('data', (chunk) => {
    const detail = String(chunk || '').trim().replace(/rtmps?:\/\/\S+/g, 'rtmps://[redacted]');
    if (detail) log('youtube_error', { detail: detail.slice(0, 180) });
  });
  youtube.on('exit', (code) => {
    if (stopped) return;
    log('youtube_down', { code: code });
    youtube = null;
    setTimeout(startYoutube, 2000);
  });
}"""

if old not in text:
    raise SystemExit("startYoutube block not found exactly")
path.write_text(text.replace(old, new, 1))

old_watch = r"""    if (!next || next === youtubeThumbIdent) return;
    youtubeThumbIdent = next;
    if (!youtube || youtube.killed) return;
    log('youtube_thumb_reload', { ident: next });
    try { youtube.kill('SIGTERM'); } catch (err) {}"""
new_watch = r"""    if (!next || next === youtubeThumbIdent) return;
    youtubeThumbIdent = next;
    if (!youtube || youtube.killed) return;
    if (youtubeCutawayOn()) return;
    log('youtube_thumb_reload', { ident: next });
    try { youtube.kill('SIGTERM'); } catch (err) {}"""
text2 = path.read_text()
if old_watch not in text2:
    raise SystemExit("watch block not found")
path.write_text(text2.replace(old_watch, new_watch, 1))
subprocess.check_call(["node", "--check", str(path)])
print("patched ok")
