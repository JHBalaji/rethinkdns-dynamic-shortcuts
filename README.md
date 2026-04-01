# RethinkDNS VPN Controller

A lightweight, standalone Android application designed to provide quick automation and dynamic shortcuts for the [RethinkDNS](https://github.com/celzero/rethink-app) VPN client.

## Features

- **Dynamic Shortcuts**: Add "Start VPN" and "Stop VPN" shortcuts directly to your launcher or home screen.
- **Automation Ready**: Provides a trampoline activity to trigger VPN states via intents.
- **Android 14+ Compatible**: Uses explicit intents and proper package visibility declarations for modern Android security standards.
- **Minimal Footprint**: No background services or unnecessary battery drain.

## Prerequisites

1.  **RethinkDNS App**: Must be installed on the device.
2.  **Whitelisting**: For security, RethinkDNS only accepts commands from authorized apps. In RethinkDNS -> Configure -> Settings -> Automation add `com.jh.rethinkvpncontroller`

## Setup Instructions

### 1. Whitelist the Controller
Before the shortcuts will work, you must authorize this app within RethinkDNS:
1.  Open **RethinkDNS**.
2.  Navigate to **Configure** → **Settings**.
3.  Look for **Automation** (under Customize).
4.  Add `com.jh.rethinkvpncontroller` to the list (comma-separated if there are others).

### 2. Enable Shortcuts
1.  Long-press the **Rethink Shortcut** app icon on your launcher.
2.  Drag the **Start VPN** or **Stop VPN** shortcuts to your home screen, or tap them directly. 
3. These are dynamic shortcuts, so these shortcuts get exposed to Samsung routines, Tasker, MacroDroid. 

## Technical Details

### Intent Actions
The app communicates with RethinkDNS using the following explicit broadcasts:
- **Start**: `com.celzero.bravedns.intent.action.VPN_START`
- **Stop**: `com.celzero.bravedns.intent.action.VPN_STOP`

### Security
- **Explicit Intents**: All broadcasts target the RethinkDNS package name specifically.
- **Sender Verification**: The app includes its own package name in the `sender` extra, allowing RethinkDNS to verify the caller against its whitelist.

## Development

Built with Kotlin and follows the modern Android 14 (API 34) and Android 15 (API 35) standards.

### Build Requirements
- Android Studio Iguana or newer
- Gradle 8.5+
- Android SDK 35

## License
This project is open-source and licensed under the GNU General Public License v3 (GPLv3).
