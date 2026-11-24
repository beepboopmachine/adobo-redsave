# Adobo Redsave

[![GitHub Workflow Status (with event)](https://img.shields.io/github/actions/workflow/status/beepboopmachine/adobo-redsave/release.yml)][gh-actions]
[![GPLv3 License](https://img.shields.io/badge/License-GPL%20v3-yellow.svg)][gpl-3.0]
![Android](https://img.shields.io/badge/Android-3DDC84?logo=android&logoColor=white)

This repository combines patches from **Adobo** and **Redsave** for [Morphe](https://morphe.software/), an open-source Android patching tool. It brings together powerful adblocking, anti-tracking, layout tweaks, and a native Reddit media downloader into a single patch bundle.

<a href="https://morphe.software/add-source?github=beepboopmachine/adobo-redsave">
  <img src=".github/assets/add-to-morphe.svg" alt="Add to Morphe" height="48" />
</a>

## Features

### Reddit Media Downloader (Redsave)
- **Native Download Button**: Clean download action pill in the Reddit feed between Repost and Share (supports Classic, Plain, and Refresh feed layouts).
- **High-Quality Media Without Watermarks**:
  - **Images & Galleries**: Saves full-resolution JPEG/PNG files, including multi-image posts.
  - **GIFs**: Saves animated GIFs/MP4s properly.
  - **Reddit Videos (`v.redd.it`)**: Downloads high-resolution video streams with audio.
  - **RedGifs**: Automatically downloads direct MP4 files.
- **Single Tap & Long Press**:
  - **Single tap**: Downloads single-item posts immediately. For multi-item posts, opens options or remembers your preference.
  - **Long press**: Displays dialog with post item counts, allowing you to choose single vs. all downloads.
- **Fix Built-in Download**: Intercepts and fixes Reddit's 3-dot menu download action.
- **Media Organization**:
  - Images saved to `/sdcard/Pictures/Reddit/`
  - Videos saved to `/sdcard/Movies/Reddit/`

### Adblocking & Privacy (Adobo)
- **Reddit**: Block ads everywhere in feeds and comments, sanitize share URLs, open links directly, and customize feed layout (hide awards, scores, flairs, and more).
- **9GAG**: Remove ads and sanitize share links.
- **Universal Adblocking**: Block ad networks (AdMob, AppLovin, Bigo, Meta Audience Network, Mintegral, MyTarget, Pangle, TopOn, Unity, Vungle, Yandex).
- **Privacy & Protection**: Disable Firebase analytics, bypass signature checks, deactivate screenshot capture detection, and force Gboard incognito mode.

## Patches

<!-- PATCHES_START -->
> **[v1.0.2](https://github.com/beepboopmachine/adobo-redsave/releases/tag/v1.0.2)**&nbsp;&nbsp;&middot;&nbsp;&nbsp;`main`&nbsp;&nbsp;&middot;&nbsp;&nbsp;**56 patches** across **4 apps**&nbsp;&nbsp;&middot;&nbsp;&nbsp;[Full details](PATCHES.md)

| # | App | Patches | Version | Package |
|---|---|---|---|---|
| 1 | [**9GAG**](PATCHES.md#9gag-comninegagandroidapp) | 3 | `8.30.04`, `8.29.08`, `8.26.1`, `8.25.1`, `8.24.8` | [`com.ninegag.android.app`](https://play.google.com/store/apps/details?id=com.ninegag.android.app) |
| 2 | [**Gboard**](PATCHES.md#gboard-comgoogleandroidinputmethodlatin) | 10 | — | [`com.google.android.inputmethod.latin`](https://play.google.com/store/apps/details?id=com.google.android.inputmethod.latin) |
| 3 | [**IMDb**](PATCHES.md#imdb-comimdbmobile) | 1 | — | [`com.imdb.mobile`](https://play.google.com/store/apps/details?id=com.imdb.mobile) |
| 4 | [**Reddit**](PATCHES.md#reddit-comredditfrontpage) | 29 | `2026.47.0`, `2026.46.0`, `2026.45.0`, `2026.44.0`, `2026.24.0` | [`com.reddit.frontpage`](https://play.google.com/store/apps/details?id=com.reddit.frontpage) |
| 5 | [**Universal**](PATCHES.md#universal) | 13 | — | — |
<!-- PATCHES_END -->

## Getting Started

You'll need [Morphe Manager](https://github.com/MorpheApp/morphe-manager) or [Morphe Desktop](https://github.com/MorpheApp/morphe-desktop).

### Morphe Manager

#### Option A: One-click import

Tap [this link](https://morphe.software/add-source?github=beepboopmachine/adobo-redsave) to import Adobo-Redsave patches directly into Morphe Manager in one click!

#### Option B: Manual import

1. Open the **Morphe Manager** app.
2. Switch to the **Sources** tab, then tap the **+** icon in the top-right corner.
3. On the **Add patch source** bottom sheet, enter:
   ```
   https://github.com/beepboopmachine/adobo-redsave
   ```
4. Optional: Toggle on _Pre-release patches_ for early access.
5. Tap **Add** to import this patch source.

### Morphe CLI (PC)

1. Clone the repository and build:
   ```bash
   git clone https://github.com/beepboopmachine/adobo-redsave.git
   cd adobo-redsave
   ./gradlew :patches:buildAndroid
   ```
2. Patch your Reddit APKM:
   ```bash
   java -jar morphe-cli.jar patch \
     --patches patches/build/libs/patches-2.0.1.mpp \
     --out reddit-patched.apk \
     reddit.apkm
   ```

## License

Adobo Redsave is licensed under the [GNU General Public License v3.0 (GPL-3.0)][gpl-3.0].
See [LICENSE](LICENSE) and [NOTICE](NOTICE) for details.

[gh-actions]: https://github.com/beepboopmachine/adobo-redsave/actions
[releases]: https://github.com/beepboopmachine/adobo-redsave/releases
[gpl-3.0]: https://www.gnu.org/licenses/gpl-3.0.html
